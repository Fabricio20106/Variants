package melonystudios.variants.dispenser.vanilla;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.DispenserTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;

public class EmptyBucketDispenseBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior dispenseBehavior = new DefaultDispenseItemBehavior();

    @Nonnull
    public ItemStack execute(IBlockSource source, ItemStack stack) {
        ServerWorld world = source.getLevel();
        BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (block instanceof IBucketPickupHandler) {
            Fluid fluid = ((IBucketPickupHandler) block).takeLiquid(world, pos, state);
            if (!(fluid instanceof FlowingFluid)) {
                return super.execute(source, stack);
            } else {
                Item bucketItem = fluid.getBucket();
                stack.shrink(1);
                if (stack.isEmpty()) {
                    return new ItemStack(bucketItem);
                } else {
                    if (source.<DispenserTileEntity>getEntity().addItem(new ItemStack(bucketItem)) < 0) {
                        this.dispenseBehavior.dispense(source, new ItemStack(bucketItem));
                    }

                    return stack;
                }
            }
        } else if (block.is(VSBlockTags.CAULDRONS) && state.hasProperty(BlockStateProperties.LEVEL_CAULDRON)) {
            int level = state.getValue(BlockStateProperties.LEVEL_CAULDRON);
            if (stack.getItem() == Items.BUCKET && level == 3) {
                world.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LEVEL_CAULDRON, 0));
                return this.dispenseBehavior.dispense(source, new ItemStack(Items.WATER_BUCKET));
            }
        }
        return this.dispenseBehavior.dispense(source, stack);
    }
}
