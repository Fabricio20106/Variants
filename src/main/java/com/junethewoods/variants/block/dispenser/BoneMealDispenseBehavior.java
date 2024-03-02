package com.junethewoods.variants.block.dispenser;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayerFactory;

public class BoneMealDispenseBehavior extends OptionalDispenseBehavior {
    protected ItemStack execute(IBlockSource source, ItemStack stack) {
        this.setSuccess(true);
        World world = source.getLevel();
        BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
        if (!BoneMealItem.applyBonemeal(stack, world, pos, FakePlayerFactory.getMinecraft((ServerWorld) world)) && !BoneMealItem.growWaterPlant(stack, world, pos, null)) {
            this.setSuccess(false);
        } else if (!world.isClientSide) {
            world.levelEvent(2005, pos, 0);
        }

        return stack;
    }
}
