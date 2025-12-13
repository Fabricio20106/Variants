package melonystudios.variants.item.custom.food;

import melonystudios.variants.Variants;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.item.bowl.BowlType;
import melonystudios.variants.item.bowl.BowlTypes;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.RVRegistries;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.IRandomRange;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ExponentialStewItem extends ConsumableItem {
    public ExponentialStewItem(ConsumeBehavior behavior, Properties properties) {
        super(false, behavior, properties);
        this.useDefaultBehaviorTooltips = false;
    }

    @Override
    public boolean hasUseRemainder() {
        return true;
    }

    public TranslationTextComponent getBehaviorTranslation(ItemStack stewStack) {
        TranslationTextComponent fromConstructor = new TranslationTextComponent("consume_behavior." + this.behavior().registryEntry().getRegistryName().getNamespace() + "." + this.behavior().registryEntry().getRegistryName().getPath());
        if (hasBehaviorInNBT(stewStack)) {
            CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
            CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
            ResourceLocation behaviorID = ResourceLocation.tryParse(behaviorTag.getString("id"));
            assert behaviorID != null;
            if (!behaviorTag.contains("id", Constants.TagTypes.STRING)) return fromConstructor;
            return new TranslationTextComponent("consume_behavior." + behaviorID.getNamespace() + "." + behaviorID.getPath());
        } else {
            return fromConstructor;
        }
    }

    public static void writeBowl(ItemStack stewStack, ItemStack bowlStack) {
        CompoundNBT tag = stewStack.getOrCreateTag();
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        CompoundNBT remainderTag = new CompoundNBT();
        remainderTag.putString("id", bowlStack.getItem().getRegistryName().toString());
        if (bowlStack.getCount() != 1) remainderTag.putInt("count", bowlStack.getCount());
        if (bowlStack.getTag() != null) remainderTag.put("tags", bowlStack.getTag());
        consumableTag.put("use_remainder", remainderTag);
        tag.put("tags", consumableTag);

        for (ResourceLocation bowlLocation : BowlType.DATA_DRIVEN_TYPES.keySet()) {
            BowlType bowlType = BowlType.DATA_DRIVEN_TYPES.get(bowlLocation);
            if (bowlType.bowl().equals(bowlStack, false)) tag.putInt("texture_id", bowlType.textureID());
        }
    }

    public static void writeBowlWithTextureID(ItemStack stewStack, ItemStack bowlStack, IRandomRange textureID) {
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        CompoundNBT remainderTag = new CompoundNBT();
        remainderTag.putString("id", bowlStack.getItem().getRegistryName().toString());
        if (bowlStack.getCount() != 1) remainderTag.putInt("count", bowlStack.getCount());
        if (bowlStack.getTag() != null) remainderTag.put("tags", bowlStack.getTag());
        consumableTag.put("use_remainder", remainderTag);
        stewStack.getOrCreateTag().put("consumable", consumableTag);

        stewStack.getOrCreateTag().putInt("texture_id", textureID.getInt(random));
    }

    public static void writeBehaviorToStew(ItemStack stewStack, ConsumeBehavior behavior, CompoundNBT properties) {
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        properties.putString("id", behavior.registryEntry().getRegistryName().toString());
        consumableTag.put("behavior", properties);
    }

    @Override
    @Nonnull
    public ItemStack finishUsingItem(ItemStack stewStack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stewStack, world, livEntity);
        boolean isPlayerInCreative = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;

        // Custom Consume Behavior
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
        if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
            ConsumeBehavior behavior = RVRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
            if (behavior != null && canRunBehavior(consumableTag, behavior)) behavior.loadFromNBT(stewStack, world, livEntity, this.getBehaviorProperties(stewStack));
        } else {
            if (canRunBehavior(consumableTag, this.behavior())) this.behavior().loadFromNBT(stewStack, world, livEntity, this.getBehaviorProperties(stewStack));
        }

        return isPlayerInCreative ? superStack : getBowlFromNBT(stewStack, livEntity);
    }

    @Override
    public ItemStack getDefaultUseRemainder() {
        return new ItemStack(Items.BOWL);
    }

    public ItemStack getBowlFromNBT(ItemStack stewStack, @Nullable LivingEntity livEntity) {
        if (livEntity != null) livEntity.eat(livEntity.level, stewStack);
        CompoundNBT consumableTag = stewStack.getTagElement("consumable");

        if (consumableTag != null && consumableTag.contains("use_remainder", Constants.TagTypes.COMPOUND)) {
            CompoundNBT remainderTag = consumableTag.getCompound("use_remainder");
            if (remainderTag.contains("id", Constants.TagTypes.STRING) && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(remainderTag.getString("id")))) return VSUtils.loadStack(remainderTag);
        }

        return this.getDefaultUseRemainder();
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab) && Variants.revaried().settings().populateExponentialStewsInTabs) {
            // New data-driven way of adding the bowls.
            for (ResourceLocation location : BowlType.DATA_DRIVEN_TYPES.keySet()) {
                BowlType type = BowlType.DATA_DRIVEN_TYPES.get(location);
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");

                consumableTag.put("use_remainder", VSUtils.saveStack(type.bowl(), new CompoundNBT()));
                tag.putInt("texture_id", type.textureID());

                consumableTag.put("behavior", this.behavior().writeBehavior(stack));
                list.add(stack);
            }
        } else {
            if (this.allowdedIn(tab)) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");

                consumableTag.put("use_remainder", VSUtils.saveStack(BowlTypes.OAK.bowl(), new CompoundNBT()));
                tag.putInt("texture_id", BowlTypes.OAK.textureID());

                consumableTag.put("behavior", this.behavior().writeBehavior(stack));
                list.add(stack);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        if (NBTUtils.shouldNotHideTooltip("hide_bowl_name", stack)) {
            ItemStack bowlStack = getBowlFromNBT(stack, null);
            ITextComponent bowlName = bowlStack.getItem().getName(bowlStack.getItem().getDefaultInstance());
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.bowl", bowlName).withStyle(TextFormatting.GRAY));
        }

        if (NBTUtils.shouldNotHideTooltip("hide_consume_behavior", stack)) {
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.behavior",
                    getBehaviorTranslation(stack)).withStyle(TextFormatting.GRAY));
        }

        if (NBTUtils.shouldNotHideTooltip("hide_behavior_tooltips", stack)) {
            CompoundNBT consumableTag = stack.getTagElement("consumable");
            if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
                CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
                if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                    ConsumeBehavior behavior = RVRegistries.CONSUME_BEHAVIOR.getValue(new ResourceLocation(behaviorTag.getString("id")));
                    if (behavior != null) tooltip.addAll(behavior.addToTooltip(stack, world, flag));
                }
            } else {
                tooltip.addAll(this.behavior().addToTooltip(stack, world, flag));
            }
        }
    }
}
