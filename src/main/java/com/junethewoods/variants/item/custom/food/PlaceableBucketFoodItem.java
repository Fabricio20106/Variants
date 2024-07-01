package com.junethewoods.variants.item.custom.food;

import com.junethewoods.variants.dispenser.vanilla.BucketDispenseBehavior;
import com.junethewoods.variants.util.NBTUtils;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class PlaceableBucketFoodItem extends BucketItem {
    public PlaceableBucketFoodItem(Supplier<? extends Fluid> fluid, Properties properties) {
        super(fluid, properties);
        DispenserBlock.registerBehavior(this, new BucketDispenseBehavior());
    }

    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Nonnull
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
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

        // For Suspicious Stew
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("effects", NBTUtils.LIST)) {
            ListNBT effectList = tag.getList("effects", NBTUtils.COMPOUND);

            for (int i = 0; i < effectList.size(); ++i) {
                int duration = 160; // Default of 8 seconds from Suspicious Stew.
                int amplifier = 0;
                boolean ambient = false;
                boolean showParticles = true;
                boolean showIcon = true;
                boolean noCounter = true;
                CompoundNBT effectTag = effectList.getCompound(i);
                if (effectTag.contains("duration", NBTUtils.INTEGER)) duration = effectTag.getInt("duration");
                if (effectTag.contains("amplifier", NBTUtils.INTEGER)) amplifier = effectTag.getInt("amplifier");
                if (effectTag.contains("ambient", NBTUtils.BYTE)) ambient = effectTag.getBoolean("ambient");
                if (effectTag.contains("show_particles", NBTUtils.BYTE)) showParticles = effectTag.getBoolean("show_particles");
                if (effectTag.contains("show_icon", NBTUtils.BYTE)) showIcon = effectTag.getBoolean("show_icon");
                if (effectTag.contains("no_counter", NBTUtils.BYTE)) noCounter = effectTag.getBoolean("no_counter");

                Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
                if (effect != null) {
                    EffectInstance instance = new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon);
                    if (world.isClientSide) instance.setNoCounter(noCounter);
                    livEntity.addEffect(instance);
                }
            }
        }

        return isPlayerInCreative ? superStack : new ItemStack(Items.BUCKET);
    }
}