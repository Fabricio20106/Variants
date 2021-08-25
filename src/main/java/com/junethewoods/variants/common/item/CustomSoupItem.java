package com.junethewoods.variants.common.item;

import com.junethewoods.variants.core.init.F10CompatItems;
import com.junethewoods.variants.core.init.StuffInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CustomSoupItem {
    public static class OakBowl extends Item {
        public OakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.oak_bowl.get());
        }
    }
    public static class BirchBowl extends Item {
        public BirchBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.birch_bowl.get());
        }
    }
    public static class SpruceBowl extends Item {
        public SpruceBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.spruce_bowl.get());
        }
    }
    public static class JungleBowl extends Item {
        public JungleBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.jungle_bowl.get());
        }
    }
    public static class AcaciaBowl extends Item {
        public AcaciaBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.acacia_bowl.get());
        }
    }
    public static class DarkOakBowl extends Item {
        public DarkOakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.dark_oak_bowl.get());
        }
    }
    public static class CrimsonBowl extends Item {
        public CrimsonBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.crimson_bowl.get());
        }
    }
    public static class WarpedBowl extends Item {
        public WarpedBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.warped_bowl.get());
        }
    }
    public static class PaintingBowl extends Item {
        public PaintingBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.painting_bowl.get());
        }
    }
    public static class GlassyOakBowl extends Item {
        public GlassyOakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(F10CompatItems.glassy_oak_bowl.get());
        }
    }
    public static class PlainBirchBowl extends Item {
        public PlainBirchBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(F10CompatItems.plain_birch_bowl.get());
        }
    }
    public static class WoodenBowl extends Item {
        public WoodenBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(F10CompatItems.wooden_bowl.get());
        }
    }
    public static class EnderBowl extends Item {
        public EnderBowl(Properties properties) {
            super(properties);
        }

        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(StuffInit.ender_bowl.get());
        }
    }
}
