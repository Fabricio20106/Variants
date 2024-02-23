package com.junethewoods.variants.item.custom.food;

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

import java.util.function.Supplier;

public class PlaceableBucketFoodItem extends BucketItem {
    public PlaceableBucketFoodItem(Supplier<? extends Fluid> fluid, Properties properties) {
        super(fluid, properties);
    }

    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        // TODO: Fix Mushroom Stew fluid duplication glitch.
        super.use(world, player, hand);
        return DrinkHelper.useDrink(world, player, hand);
    }

    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stack, world, livEntity);

        // For Suspicious Stew
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("effects", 9)) {
            ListNBT effectList = tag.getList("effects", 10);

            for (int i = 0; i < effectList.size(); ++i) {
                int duration = 160; // Default of 8 seconds.
                CompoundNBT tag1 = effectList.getCompound(i);
                if (tag1.contains("duration", 3)) {
                    duration = tag1.getInt("duration");
                }

                Effect effect = Effect.byId(tag1.getByte("id"));
                if (effect != null) {
                    livEntity.addEffect(new EffectInstance(effect, duration));
                }
            }
        }

        return livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild ? superStack : new ItemStack(Items.BUCKET);
    }
}