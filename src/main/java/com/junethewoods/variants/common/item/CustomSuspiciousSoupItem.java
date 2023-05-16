package com.junethewoods.variants.common.item;

import com.junethewoods.variants.core.init.VSItems;
import com.junethewoods.variants.core.init.compat.F10CompatItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.level.Level;

public class CustomSuspiciousSoupItem {
    public static class OakBowl extends SuspiciousStewItem {
        public OakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.OAK_BOWL.get());
        }
    }
    public static class BirchBowl extends SuspiciousStewItem {
        public BirchBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.BIRCH_BOWL.get());
        }
    }
    public static class SpruceBowl extends SuspiciousStewItem {
        public SpruceBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.SPRUCE_BOWL.get());
        }
    }
    public static class JungleBowl extends SuspiciousStewItem {
        public JungleBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.JUNGLE_BOWL.get());
        }
    }
    public static class AcaciaBowl extends SuspiciousStewItem {
        public AcaciaBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.ACACIA_BOWL.get());
        }
    }
    public static class DarkOakBowl extends SuspiciousStewItem {
        public DarkOakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.DARK_OAK_BOWL.get());
        }
    }
    public static class CrimsonBowl extends SuspiciousStewItem {
        public CrimsonBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.CRIMSON_BOWL.get());
        }
    }
    public static class WarpedBowl extends SuspiciousStewItem {
        public WarpedBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.WARPED_BOWL.get());
        }
    }
    public static class PaintingBowl extends SuspiciousStewItem {
        public PaintingBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.PAINTING_BOWL.get());
        }
    }
    public static class GlassyOakBowl extends SuspiciousStewItem {
        public GlassyOakBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(F10CompatItems.GLASSY_OAK_BOWL.get());
        }
    }
    public static class PlainBirchBowl extends SuspiciousStewItem {
        public PlainBirchBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(F10CompatItems.PLAIN_BIRCH_BOWL.get());
        }
    }
    public static class WoodenBowl extends SuspiciousStewItem {
        public WoodenBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(F10CompatItems.WOODEN_BOWL.get());
        }
    }
    public static class EnderBowl extends SuspiciousStewItem {
        public EnderBowl(Properties properties) {
            super(properties);
        }

        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
            ItemStack stack1 = super.finishUsingItem(stack, world, livingEntity);
            return livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild ? stack1 : new ItemStack(VSItems.ENDER_BOWL.get());
        }
    }
}
