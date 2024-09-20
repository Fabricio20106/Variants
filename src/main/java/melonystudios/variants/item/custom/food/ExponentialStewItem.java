package melonystudios.variants.item.custom.food;

import melonystudios.variants.Variants;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.stew.bowl.BowlType;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSRegistries;
import melonystudios.variants.util.VSUtils;
import melonystudios.variants.util.tag.ConsumeBehaviorTags;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.IRandomRange;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
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

public class ExponentialStewItem extends TagConfigurableFoodItem {
    private final StewBehavior stewBehavior;

    public ExponentialStewItem(StewBehavior behavior, Properties properties) {
        super(false, behavior, properties);
        this.stewBehavior = behavior;
        this.useDefaultBehaviorTooltips = false;
    }

    @Override
    public boolean hasUseRemainder() {
        return true;
    }

    public StewBehavior getBehavior() {
        return this.stewBehavior;
    }

    public boolean hasBehaviorInNBT(ItemStack stewStack) {
        CompoundNBT consumableTag = stewStack.getTagElement("consumable");
        return consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND);
    }

    public TranslationTextComponent getBehaviorTranslation(ItemStack stewStack) {
        TranslationTextComponent fromConstructor = new TranslationTextComponent("stew_behavior." + this.stewBehavior.getBehaviorRegistry().getRegistryName().getNamespace() + "." + this.stewBehavior.getBehaviorRegistry().getRegistryName().getPath());
        if (hasBehaviorInNBT(stewStack)) {
            CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
            CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
            ResourceLocation behaviorID = ResourceLocation.tryParse(behaviorTag.getString("id"));
            assert behaviorID != null;
            if (!behaviorTag.contains("id", Constants.TagTypes.STRING)) return fromConstructor;
            return new TranslationTextComponent("stew_behavior." + behaviorID.getNamespace() + "." + behaviorID.getPath());
        } else {
            return fromConstructor;
        }
    }

    public static void writeEffectToStew(ItemStack stewStack, Effect effect, int duration) {
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        CompoundNBT behaviorTag = new CompoundNBT();
        ListNBT effectList = new ListNBT();
        CompoundNBT effectTag = new CompoundNBT();

        effectTag.putString("id", effect.getRegistryName().toString());
        effectTag.putInt("duration", duration);
        effectList.add(effectTag);

        behaviorTag.put("effects", effectList);
        behaviorTag.putString("id", VSStewBehaviors.APPLY_MOB_EFFECTS.get().getRegistryName().toString());

        consumableTag.put("behavior", behaviorTag);
    }

    public static void writeBowl(ItemStack stewStack, ItemStack bowlStack) {
        CompoundNBT tag = stewStack.getOrCreateTag();
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        CompoundNBT remainderTag = new CompoundNBT();
        remainderTag.putString("id", bowlStack.getItem().getRegistryName().toString());
        if (bowlStack.getCount() != 1) remainderTag.putInt("count", bowlStack.getCount());
        if (bowlStack.getTag() != null) remainderTag.put("components", bowlStack.getTag());
        consumableTag.put("use_remainder", remainderTag);
        tag.put("consumable", consumableTag);

        for (ResourceLocation bowlLocation : BowlType.DATA_DRIVEN_TYPES.keySet()) {
            BowlType bowlType = BowlType.DATA_DRIVEN_TYPES.get(bowlLocation);
            if (bowlType.getBowlStack().equals(bowlStack, false)) tag.putInt("texture_id", bowlType.getTextureID());
        }
    }

    public static void writeBowlWithTextureID(ItemStack stewStack, ItemStack bowlStack, IRandomRange textureID) {
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        CompoundNBT remainderTag = new CompoundNBT();
        remainderTag.putString("id", bowlStack.getItem().getRegistryName().toString());
        if (bowlStack.getCount() != 1) remainderTag.putInt("count", bowlStack.getCount());
        if (bowlStack.getTag() != null) remainderTag.put("components", bowlStack.getTag());
        consumableTag.put("use_remainder", remainderTag);
        stewStack.getOrCreateTag().put("consumable", consumableTag);

        stewStack.getOrCreateTag().putInt("texture_id", textureID.getInt(random));
    }

    public static void writeBehaviorToStew(ItemStack stewStack, StewBehavior behavior, CompoundNBT properties) {
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        properties.putString("id", behavior.getBehaviorRegistry().getRegistryName().toString());
        consumableTag.put("behavior", properties);
    }

    public static boolean canRunBehavior(CompoundNBT consumableTag, StewBehavior behavior) {
        return consumableTag.contains("behavior", Constants.TagTypes.COMPOUND) || !behavior.is(ConsumeBehaviorTags.CANNOT_RUN_WITHOUT_NBT);
    }

    @Override
    @Nonnull
    public ItemStack finishUsingItem(ItemStack stewStack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stewStack, world, livEntity);
        boolean isPlayerInCreative = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;

        // Custom Stew Behavior
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
        if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
            StewBehavior behavior = VSRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
            if (behavior != null && canRunBehavior(consumableTag, behavior)) behavior.executeFromStewNBT(stewStack, world, livEntity, behavior.getBehaviorProperties(stewStack));
        } else {
            if (canRunBehavior(consumableTag, this.stewBehavior)) this.stewBehavior.executeFromStewNBT(stewStack, world, livEntity, this.stewBehavior.getBehaviorProperties(stewStack));
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
        if (this.allowdedIn(tab) && VSConfigs.COMMON_CONFIGS.populateExponentialBowlsInTabs.get()) {
            // New data-driven way of adding the bowls.
            for (ResourceLocation location : BowlType.DATA_DRIVEN_TYPES.keySet()) {
                BowlType type = BowlType.DATA_DRIVEN_TYPES.get(location);
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");

                consumableTag.put("use_remainder", VSUtils.saveStack(type.getBowlStack(), new CompoundNBT()));
                tag.putInt("texture_id", type.getTextureID());

                consumableTag.put("behavior", this.stewBehavior.writeBehaviorToNBT(stack));
                list.add(stack);
            }
        } else {
            super.fillItemCategory(tab, list);
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

        if (NBTUtils.shouldNotHideTooltip("hide_stew_behavior", stack)) {
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.behavior",
                    getBehaviorTranslation(stack)).withStyle(TextFormatting.GRAY));
        }

        if (NBTUtils.shouldNotHideTooltip("hide_behavior_tooltips", stack)) {
            CompoundNBT consumableTag = stack.getTagElement("consumable");
            if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
                CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
                if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                    StewBehavior behavior = VSRegistries.CONSUME_BEHAVIOR.getValue(new ResourceLocation(behaviorTag.getString("id")));
                    if (behavior != null) tooltip.addAll(behavior.addToStewTooltip(stack, world, flag));
                }
            } else {
                tooltip.addAll(this.stewBehavior.addToStewTooltip(stack, world, flag));
            }
        }
    }
}
