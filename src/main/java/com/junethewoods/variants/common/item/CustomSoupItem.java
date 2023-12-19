package com.junethewoods.variants.common.item;

import com.junethewoods.variants.core.init.VSItems;
import com.junethewoods.variants.core.init.compat.F10CItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CustomSoupItem {
    public static class OakBowl extends Item {
        public OakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.OAK_BOWL.get());
        }
    }
    public static class BirchBowl extends Item {
        public BirchBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.BIRCH_BOWL.get());
        }
    }
    public static class SpruceBowl extends Item {
        public SpruceBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.SPRUCE_BOWL.get());
        }
    }
    public static class JungleBowl extends Item {
        public JungleBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.JUNGLE_BOWL.get());
        }
    }
    public static class AcaciaBowl extends Item {
        public AcaciaBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.ACACIA_BOWL.get());
        }
    }
    public static class DarkOakBowl extends Item {
        public DarkOakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.DARK_OAK_BOWL.get());
        }
    }
    public static class CrimsonBowl extends Item {
        public CrimsonBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.CRIMSON_BOWL.get());
        }
    }
    public static class WarpedBowl extends Item {
        public WarpedBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.WARPED_BOWL.get());
        }
    }
    public static class PaintingBowl extends Item {
        public PaintingBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.PAINTING_BOWL.get());
        }
    }
    public static class GlassyOakBowl extends Item {
        public GlassyOakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(F10CItems.GLASSY_OAK_BOWL.get());
        }
    }
    public static class PlainBirchBowl extends Item {
        public PlainBirchBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(F10CItems.PLAIN_BIRCH_BOWL.get());
        }
    }
    public static class WoodenBowl extends Item {
        public WoodenBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(F10CItems.WOODEN_BOWL.get());
        }
    }
    public static class EnderBowl extends Item {
        public EnderBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livEntity);
            return livEntity instanceof Player && ((Player)livEntity).getAbilities().instabuild ? itemstack : new ItemStack(VSItems.ENDER_BOWL.get());
        }
    }
}
