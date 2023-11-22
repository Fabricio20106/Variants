package com.junethewoods.variants.block.custom.theend;

import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;

public class EnderWartBlock extends BushBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.box(0d, 0d, 0d, 16d, 5d, 16d),
            Block.box(0d, 0d, 0d, 16d, 8d, 16d),
            Block.box(0d, 0d, 0d, 16d, 11d, 16d),
            Block.box(0d, 0d, 0d, 16d, 14d, 16d)};

    public EnderWartBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.getValue(AGE)];
    }

    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSTags.Blocks.ENDER_WART_PLANTABLE_ON);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        int age = state.getValue(AGE);
        if (age < 3 && ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt(10) == 0)) {
            state = state.setValue(AGE, age + 1);
            world.setBlock(pos, state, 2);
            ForgeHooks.onCropsGrowPost(world, pos, state);
        }
    }

    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(VSItems.ENDER_WART.get());
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
