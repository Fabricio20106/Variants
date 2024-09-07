package melonystudios.variants.item.custom.food;

import melonystudios.variants.dispenser.vanilla.BucketDispenseBehavior;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.custom.DefaultStewBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class PlaceableBucketFoodItem extends BucketItem implements TagConfigurableFood {
    private final StewBehavior behavior;

    public PlaceableBucketFoodItem(Supplier<? extends Fluid> fluid, StewBehavior behavior, Properties properties) {
        super(fluid, properties);
        this.behavior = behavior;
        DispenserBlock.registerBehavior(this, new BucketDispenseBehavior());
    }

    public PlaceableBucketFoodItem(Supplier<? extends Fluid> fluid, Properties properties) {
        this(fluid, new DefaultStewBehavior(), properties);
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

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (this.getFoodProperties() != null && player.canEat(this.getFoodProperties().canAlwaysEat())) DrinkHelper.useDrink(world, player, hand);
        return super.use(world, player, hand);
    }

    @Nonnull
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stack, world, livEntity);
        boolean isPlayerInCreative = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;

        executeConsumeBehavior(stack, world, livEntity, this.behavior);

        // For Suspicious Stew
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("effects", Constants.TagTypes.LIST)) {
            ListNBT effectList = tag.getList("effects", Constants.TagTypes.COMPOUND);

            for (int i = 0; i < effectList.size(); ++i) NBTUtils.addEffectsFromNBT(effectList.getCompound(i), world, livEntity);
        }

        return isPlayerInCreative ? superStack : getUseRemainder(stack);
    }
}