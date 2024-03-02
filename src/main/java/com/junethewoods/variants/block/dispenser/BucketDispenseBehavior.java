package com.junethewoods.variants.block.dispenser;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BucketDispenseBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior dispenseItemBehavior = new DefaultDispenseItemBehavior();

    public ItemStack execute(IBlockSource source, ItemStack stack) {
        BucketItem bucket = (BucketItem) stack.getItem();
        BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
        World world = source.getLevel();
        if (bucket.emptyBucket(null, world, pos, null)) {
            bucket.checkExtraContent(world, stack, pos);
            return stack.getContainerItem();
        } else {
            return this.dispenseItemBehavior.dispense(source, stack);
        }
    }
}
