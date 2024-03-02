package com.junethewoods.variants.block.dispenser;

import com.junethewoods.variants.entity.custom.SmallSoulFireballEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.world.World;

import java.util.Random;

public class SoulChargeDispenseBehavior extends DefaultDispenseItemBehavior {
    @Override
    protected ItemStack execute(IBlockSource source, ItemStack stack) {
        Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
        IPosition dispensePos = DispenserBlock.getDispensePosition(source);
        double x = dispensePos.x() + (double) ((float) direction.getStepX() * 0.3F);
        double y = dispensePos.y() + (double) ((float) direction.getStepY() * 0.3F);
        double z = dispensePos.z() + (double) ((float) direction.getStepZ() * 0.3F);
        World world = source.getLevel();
        Random rand = world.random;
        double stepX = rand.nextGaussian() * 0.05D + (double) direction.getStepX();
        double stepY = rand.nextGaussian() * 0.05D + (double) direction.getStepY();
        double stepZ = rand.nextGaussian() * 0.05D + (double) direction.getStepZ();
        world.addFreshEntity(Util.make(new SmallSoulFireballEntity(world, x, y, z, stepX, stepY, stepZ), (fireball) -> fireball.setItem(stack)));
        stack.shrink(1);
        return stack;
    }

    protected void playSound(IBlockSource source) {
        source.getLevel().levelEvent(1018, source.getPos(), 0);
    }
}
