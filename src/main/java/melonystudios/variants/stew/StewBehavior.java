package melonystudios.variants.stew;

import com.google.common.collect.Lists;
import melonystudios.variants.item.custom.food.TagConfigurableFood;
import melonystudios.variants.item.custom.food.TagConfigurableFoodItem;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VSRegistries;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ReverseTagWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public abstract class StewBehavior extends ForgeRegistryEntry<StewBehavior> {
    private final ReverseTagWrapper<StewBehavior> reverseTags = new ReverseTagWrapper<>(this, () -> TagCollectionManager.getInstance().getCustomTypeCollection(VSRegistries.CONSUME_BEHAVIOR));
    @Nullable
    private String descriptionID;

    public abstract void executeBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag);

    public abstract void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag);

    public abstract CompoundNBT writePropertiesToNBT();

    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.DEFAULT.get();
    }

    public Set<ResourceLocation> getAllTags() {
        return this.reverseTags.getTagNames();
    }

    public boolean is(ITag<StewBehavior> behaviorTag) {
        return behaviorTag.contains(this);
    }

    protected String getOrCreateDescriptionID() {
        if (this.descriptionID == null) this.descriptionID = Util.makeDescriptionId("stew_behavior", VSRegistries.CONSUME_BEHAVIOR.getKey(this));
        return this.descriptionID;
    }

    public String getDescriptionID() {
        return this.getOrCreateDescriptionID();
    }

    public ITextComponent getCommandDisplayName() {
        IFormattableTextComponent component = TextComponentUtils.wrapInSquareBrackets(new TranslationTextComponent(this.getDescriptionID())).withStyle(TextFormatting.LIGHT_PURPLE);
        component.withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("")
                .append(new TranslationTextComponent(this.getDescriptionID()).withStyle(TextFormatting.LIGHT_PURPLE).withStyle(TextFormatting.BOLD)).append("\n")
                .append(new TranslationTextComponent(this.getDescriptionID() + ".desc").withStyle(TextFormatting.GRAY)).append("\n")
                .append(new StringTextComponent(this.getBehaviorRegistry().getRegistryName().toString()).withStyle(TextFormatting.DARK_GRAY)))));
        return component;
    }

    public CompoundNBT writeBehaviorToNBT(ItemStack stewStack) {
        CompoundNBT behaviorTag = writePropertiesToNBT();
        behaviorTag.putString("id", getBehaviorFromNBT(stewStack).getRegistryName().toString());
        return behaviorTag;
    }

    public List<ITextComponent> addToStewTooltip(ItemStack stack, @Nullable World world, ITooltipFlag flag) {
        return Lists.newArrayList();
    }

    public boolean hasBehaviorIDInNBT(ItemStack stewStack) {
        CompoundNBT consumableTag = stewStack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
            return consumableTag.getCompound("behavior").contains("id", Constants.TagTypes.STRING);
        }
        return false;
    }

    public StewBehavior getBehaviorFromNBT(ItemStack stewStack) {
        if (hasBehaviorIDInNBT(stewStack)) {
            try {
                ResourceLocation behavior = ResourceLocation.tryParse(stewStack.getOrCreateTagElement("consumable").getCompound("behavior").getString("id"));
                if (VSRegistries.CONSUME_BEHAVIOR.containsKey(behavior)) return VSRegistries.CONSUME_BEHAVIOR.getValue(behavior);
            } catch (NullPointerException exception) {
                LogManager.getLogger().error("Could not get the consume behavior from {} NBT", stewStack.getHoverName().getString(), exception);
            }
        } else {
            return getBehaviorRegistry();
        }
        return this;
    }

    @Nullable
    public CompoundNBT getBehaviorProperties(ItemStack stewStack) {
        if (stewStack.getItem() instanceof TagConfigurableFood) {
            CompoundNBT consumableTag = stewStack.getTagElement("consumable");
            if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) return consumableTag.getCompound("behavior");
        }
        return null;
    }

    @Nullable
    public static CompoundNBT getBehaviorPropertiesStatic(ItemStack stewStack) {
        if (stewStack.getItem() instanceof TagConfigurableFood) {
            CompoundNBT consumableTag = stewStack.getTagElement("consumable");
            if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) return consumableTag.getCompound("behavior");
        }
        return null;
    }

    public static void runAreaEffectBehavior(ItemStack stack, Entity sourceEntity, RunningBehavior behavior) {
        List<LivingEntity> livEntities = sourceEntity.level.getEntitiesOfClass(LivingEntity.class, sourceEntity.getBoundingBox().inflate(4, 2, 4));
        if (!livEntities.isEmpty()) {
            for (LivingEntity livEntity : livEntities) behavior.runBehavior(stack, sourceEntity.level, livEntity);
        }
    }

    public static void runBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
            CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
            if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                StewBehavior tagBehavior = VSRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                if (tagBehavior != null && TagConfigurableFood.canRunBehavior(tagBehavior)) tagBehavior.executeFromStewNBT(stack, world, livEntity, getBehaviorPropertiesStatic(stack));
            }
        } else {
            if (stack.getItem() instanceof TagConfigurableFoodItem) {
                StewBehavior behavior = ((TagConfigurableFoodItem) stack.getItem()).getBehavior();
                if (TagConfigurableFood.canRunBehavior(behavior)) behavior.executeFromStewNBT(stack, world, livEntity, getBehaviorPropertiesStatic(stack));
            }
        }
    }

    public interface RunningBehavior {
        void runBehavior(ItemStack stack, World world, LivingEntity livEntity);
    }
}
