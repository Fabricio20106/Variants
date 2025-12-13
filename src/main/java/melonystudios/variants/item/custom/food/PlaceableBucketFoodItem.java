package melonystudios.variants.item.custom.food;

import melonystudios.variants.component.Consumable;
import melonystudios.variants.dispenser.vanilla.BucketDispenseBehavior;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.custom.DefaultConsumeBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.RVRegistries;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class PlaceableBucketFoodItem extends BucketItem implements Consumable {
    public boolean useDefaultBehavior = true;
    private final ConsumeBehavior behavior;

    public PlaceableBucketFoodItem(Supplier<? extends Fluid> fluid, ConsumeBehavior behavior, Properties properties) {
        super(fluid, properties);
        this.behavior = behavior;
        DispenserBlock.registerBehavior(this, new BucketDispenseBehavior());
    }

    public PlaceableBucketFoodItem(Supplier<? extends Fluid> fluid, Properties properties) {
        this(fluid, new DefaultConsumeBehavior(), properties);
    }

    public int getUseDuration(ItemStack stack) {
        Food foodProperties = stack.getItem().getFoodProperties();
        if (foodProperties != null && foodProperties.isFastFood()) {
            return getConsumeTicks(stack, 16);
        }
        return getConsumeTicks(stack);
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return getConsumeAnimation(stack, UseAction.DRINK);
    }

    @Override
    public SoundEvent getDefaultConsumeSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public ItemStack getDefaultUseRemainder() {
        return new ItemStack(Items.BUCKET);
    }

    @Override
    public boolean hasUseRemainder() {
        return true;
    }

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (this.getFoodProperties() != null && player.canEat(this.getFoodProperties().canAlwaysEat())) DrinkHelper.useDrink(world, player, hand);
        return super.use(world, player, hand);
    }

    @Nonnull
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        if (this.useDefaultBehavior) executeConsumeBehavior(stack, world, livEntity, this.behavior);
        if (getCooldown(stack, 0) != 0) applyCooldown(stack, livEntity, 0);
        return super.finishUsingItem(stack, world, livEntity);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (NBTUtils.shouldNotHideTooltip("hide_behavior_tooltips", stack)) {
            CompoundNBT consumableTag = stack.getTagElement("consumable");
            if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
                CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
                if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                    ConsumeBehavior behavior = RVRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                    if (behavior != null) tooltip.addAll(behavior.addToTooltip(stack, world, flag));
                }
            } else {
                tooltip.addAll(this.behavior.addToTooltip(stack, world, flag));
            }
        }
    }
}