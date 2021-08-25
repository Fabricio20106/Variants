package com.junethewoods.variants.common.item;

import com.junethewoods.variants.core.init.F10CompatItems;
import com.junethewoods.variants.core.init.StuffInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.world.World;

public class CustomSusSoupItem {
    public static class OakBowl extends SuspiciousStewItem {
        public OakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.oak_bowl.get());
        }
    }
    public static class BirchBowl extends SuspiciousStewItem {
        public BirchBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.birch_bowl.get());
        }
    }
    public static class SpruceBowl extends SuspiciousStewItem {
        public SpruceBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.spruce_bowl.get());
        }
    }
    public static class JungleBowl extends SuspiciousStewItem {
        public JungleBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.jungle_bowl.get());
        }
    }
    public static class AcaciaBowl extends SuspiciousStewItem {
        public AcaciaBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.acacia_bowl.get());
        }
    }
    public static class DarkOakBowl extends SuspiciousStewItem {
        public DarkOakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.dark_oak_bowl.get());
        }
    }
    public static class CrimsonBowl extends SuspiciousStewItem {
        public CrimsonBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.crimson_bowl.get());
        }
    }
    public static class WarpedBowl extends SuspiciousStewItem {
        public WarpedBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.warped_bowl.get());
        }
    }
    public static class PaintingBowl extends SuspiciousStewItem {
        public PaintingBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.painting_bowl.get());
        }
    }
    public static class GlassyOakBowl extends SuspiciousStewItem {
        public GlassyOakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(F10CompatItems.glassy_oak_bowl.get());
        }
    }
    public static class PlainBirchBowl extends SuspiciousStewItem {
        public PlainBirchBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(F10CompatItems.plain_birch_bowl.get());
        }
    }
    public static class WoodenBowl extends SuspiciousStewItem {
        public WoodenBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(F10CompatItems.wooden_bowl.get());
        }
    }
    public static class EnderBowl extends SuspiciousStewItem {
        public EnderBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.ender_bowl.get());
        }
    }
}
