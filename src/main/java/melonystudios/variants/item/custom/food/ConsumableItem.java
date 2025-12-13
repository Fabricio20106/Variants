package melonystudios.variants.item.custom.food;

import melonystudios.variants.Variants;
import melonystudios.variants.component.Consumable;
import melonystudios.variants.item.custom.VSItem;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.RVRegistries;
import melonystudios.variants.util.VSUtils;
import melonystudios.variants.util.tag.ConsumeBehaviorTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.stats.Stats;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

public class ConsumableItem extends VSItem implements Consumable {
    public boolean useDefaultBehaviorTooltips = true;
    public boolean populateTagsByDefault = false;
    public boolean populateBehavior = false;
    private final boolean useDefaultBehavior;
    private final ConsumeBehavior behavior;

    public ConsumableItem(boolean useDefaultBehavior, ConsumeBehavior behavior, Properties properties) {
        super(properties);
        this.useDefaultBehavior = useDefaultBehavior;
        this.behavior = behavior;
    }

    public ConsumeBehavior behavior() {
        return this.behavior;
    }

    public boolean useDefaultBehavior() {
        return this.useDefaultBehavior;
    }

    public static boolean hasBehaviorInNBT(ItemStack stewStack) {
        CompoundNBT consumableTag = stewStack.getTagElement("consumable");
        return consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND);
    }

    public static boolean canRunBehavior(CompoundNBT consumableTag, ConsumeBehavior behavior) {
        return consumableTag.contains("behavior", Constants.TagTypes.COMPOUND) || !behavior.is(ConsumeBehaviorTags.CANNOT_RUN_WITHOUT_NBT);
    }

    public static void writeEffectToNBT(ItemStack stewStack, Effect effect, int duration) {
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        CompoundNBT behaviorTag = new CompoundNBT();
        ListNBT effectList = new ListNBT();
        CompoundNBT effectTag = new CompoundNBT();

        effectTag.putString("id", effect.getRegistryName().toString());
        effectTag.putInt("duration", duration);
        effectList.add(effectTag);

        behaviorTag.put("effects", effectList);
        behaviorTag.putString("id", VSConsumeBehaviors.APPLY_MOB_EFFECTS.get().getRegistryName().toString());

        consumableTag.put("behavior", behaviorTag);
    }

    @Override
    @Nonnull
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        super.finishUsingItem(stack, world, livEntity);
        if (livEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) livEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (this.useDefaultBehavior() && !world.isClientSide()) this.executeConsumeBehavior(stack, world, livEntity, this.behavior());
        if (this.getCooldown(stack, 0) != 0) this.applyCooldown(stack, livEntity, 0);

        ItemStack remainderStack = this.getUseRemainder(stack);

        if (stack.isEmpty()) {
            return remainderStack;
        } else {
            if (livEntity instanceof PlayerEntity && !((PlayerEntity) livEntity).abilities.instabuild) {
                PlayerEntity player = (PlayerEntity) livEntity;
                stack.shrink(1);
                if (stack.isEmpty()) return remainderStack;
                if (!player.inventory.add(remainderStack)) player.drop(remainderStack, false);
            }
            return stack;
        }
    }

    @Override
    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return this.getConsumeAnimation(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        Food foodProperties = stack.getItem().getFoodProperties();
        if (foodProperties != null && foodProperties.isFastFood()) {
            return this.getConsumeTicks(stack, 16);
        }
        return this.getConsumeTicks(stack);
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("use_remainder", Constants.TagTypes.COMPOUND)) {
            return VSUtils.loadStack(consumableTag.getCompound("use_remainder"));
        }
        return this.hasUseRemainder() ? this.getDefaultUseRemainder() : super.getContainerItem(stack);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return this.hasUseRemainder();
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) list.add(this.populateDefaultConsumeTags(new ItemStack(this)));
    }

    public ItemStack populateDefaultConsumeTags(ItemStack stack) {
        boolean populateConsumeTags = Variants.revaried().settings().populateTagConfigurableFoodTags || this.populateTagsByDefault;
        CompoundNBT tag = new CompoundNBT();
        CompoundNBT consumableTag = new CompoundNBT();
        if (populateConsumeTags || this.populateBehavior) tag = stack.getOrCreateTag();

        if (populateConsumeTags) {
            consumableTag.putString("animation", stack.getItem().getUseAnimation(stack).toString().toLowerCase(Locale.ROOT));
            consumableTag.putInt("consume_ticks", stack.getItem().getUseDuration(stack));
            consumableTag.putString("sound", getDefaultConsumeSound().getRegistryName().toString());
            consumableTag.put("use_remainder", VSUtils.saveStack(getUseRemainder(stack), new CompoundNBT()));
        }

        if (this.populateBehavior) {
            CompoundNBT behaviorTag = this.behavior().writeProperties();
            behaviorTag.putString("id", this.behavior().registryEntry().getRegistryName().toString());
            consumableTag.put("behavior", behaviorTag);
        }

        if (populateConsumeTags || this.populateBehavior) tag.put("consumable", consumableTag);
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (NBTUtils.shouldNotHideTooltip("hide_behavior_tooltips", stack) && this.useDefaultBehaviorTooltips) {
            CompoundNBT consumableTag = stack.getTagElement("consumable");
            if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
                CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
                if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                    ConsumeBehavior behavior = RVRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                    if (behavior != null) tooltip.addAll(behavior.addToTooltip(stack, world, flag));
                }
            } else {
                tooltip.addAll(this.behavior().addToTooltip(stack, world, flag));
            }
        }
    }
}
