package melonystudios.variants.consumable;

import com.google.common.collect.Lists;
import melonystudios.variants.item.custom.food.Consumable;
import melonystudios.variants.item.custom.food.ConsumableItem;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
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
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class ConsumeBehavior extends ForgeRegistryEntry<ConsumeBehavior> {
    private final ReverseTagWrapper<ConsumeBehavior> reverseTags = new ReverseTagWrapper<>(this, () -> TagCollectionManager.getInstance().getCustomTypeCollection(VSRegistries.CONSUME_BEHAVIOR));
    @Nullable
    private String descriptionID;

    public abstract void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag);

    public abstract void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag);

    public abstract CompoundNBT writeProperties();

    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.DEFAULT.get();
    }

    public Set<ResourceLocation> getAllTags() {
        return this.reverseTags.getTagNames();
    }

    public boolean is(ITag<ConsumeBehavior> behaviorTag) {
        return behaviorTag.contains(this);
    }

    protected String getOrCreateDescriptionID() {
        if (this.descriptionID == null) this.descriptionID = Util.makeDescriptionId("consume_behavior", VSRegistries.CONSUME_BEHAVIOR.getKey(this));
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
                .append(new StringTextComponent(this.registryEntry().getRegistryName().toString()).withStyle(TextFormatting.DARK_GRAY)))));
        return component;
    }

    public CompoundNBT writeBehavior(ItemStack stack) {
        CompoundNBT behaviorTag = writeProperties();
        behaviorTag.putString("id", getBehaviorFromNBT(stack).getRegistryName().toString());
        return behaviorTag;
    }

    public List<ITextComponent> addToTooltip(ItemStack stack, @Nullable World world, ITooltipFlag flag) {
        return Lists.newArrayList();
    }

    public static boolean checkNBTExists(ItemStack stack, NBTChecks checks, Consumer<ItemStack> consumer, @Nullable Consumer<ItemStack> withNoNBT) {
        if (checks.check()) {
            CompoundNBT consumableTag = stack.getTagElement("consumable");
            if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
                CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
                if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                    ConsumeBehavior behavior = VSRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                    if (behavior != null) consumer.accept(stack);
                }
            } else {
                if (withNoNBT != null) withNoNBT.accept(stack);
            }
        }
        return false;
    }

    public static boolean hasBehaviorIDInNBT(ItemStack stack) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
            return consumableTag.getCompound("behavior").contains("id", Constants.TagTypes.STRING);
        }
        return false;
    }

    public ConsumeBehavior getBehaviorFromNBT(ItemStack stack) {
        if (hasBehaviorIDInNBT(stack)) {
            try {
                ResourceLocation behavior = ResourceLocation.tryParse(stack.getOrCreateTagElement("consumable").getCompound("behavior").getString("id"));
                if (VSRegistries.CONSUME_BEHAVIOR.containsKey(behavior)) return VSRegistries.CONSUME_BEHAVIOR.getValue(behavior);
            } catch (NullPointerException exception) {
                LogManager.getLogger().error("Could not get the consume behavior from {} NBT", stack.getHoverName().getString(), exception);
            }
        } else {
            return registryEntry();
        }
        return this;
    }

    @Nullable
    public CompoundNBT getBehaviorProperties(ItemStack stack) {
        if (Consumable.validConsumableClass(stack.getItem())) {
            CompoundNBT consumableTag = stack.getTagElement("consumable");
            if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) return consumableTag.getCompound("behavior");
        }
        return null;
    }

    @Nullable
    public static CompoundNBT getBehaviorPropertiesStatic(ItemStack stack) {
        if (Consumable.validConsumableClass(stack.getItem())) {
            CompoundNBT consumableTag = stack.getTagElement("consumable");
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
                ConsumeBehavior tagBehavior = VSRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                if (tagBehavior != null && Consumable.canRunBehavior(tagBehavior)) tagBehavior.loadFromNBT(stack, world, livEntity, getBehaviorPropertiesStatic(stack));
            }
        } else {
            if (Consumable.validConsumableClass(stack.getItem())) {
                ConsumeBehavior behavior = ((ConsumableItem) stack.getItem()).getBehavior();
                if (Consumable.canRunBehavior(behavior)) behavior.loadFromNBT(stack, world, livEntity, getBehaviorPropertiesStatic(stack));
            }
        }
    }

    public interface RunningBehavior {
        void runBehavior(ItemStack stack, World world, LivingEntity livEntity);
    }

    public interface NBTChecks {
        boolean check();
    }
}
