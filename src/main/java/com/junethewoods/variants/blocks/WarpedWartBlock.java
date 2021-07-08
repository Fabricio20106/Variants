package com.junethewoods.variants.blocks;

import com.junethewoods.variants.common.register.StuffInit;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class WarpedWartBlock extends BushBlock {
    public static final IntegerProperty age = BlockStateProperties.AGE_0_3;
    private static final VoxelShape[] shapes = new VoxelShape[]{Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D)};

    public WarpedWartBlock(AbstractBlock.Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(age, 0));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shapes[state.get(age)];
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(Blocks.SOUL_SAND) || state.isIn(Blocks.SOUL_SOIL);
    }

    public boolean ticksRandomly(BlockState state) {
        return state.get(age) < 3;
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        int i = state.get(age);
        if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(10) == 0)) {
            state = state.with(age, i + 1);
            worldIn.setBlockState(pos, state, 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }

    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(StuffInit.warped_wart.get());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(age);
    }
}
