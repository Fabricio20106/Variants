package com.junethewoods.variants.common.block.nether;

import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;

public class WarpedWartBlock extends BushBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0, 0, 0, 16, 5, 16),
            Block.box(0, 0, 0, 16, 8, 16),
            Block.box(0, 0, 0, 16, 11, 16),
            Block.box(0, 0, 0, 16, 14, 16)};

    public WarpedWartBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext selectionContext) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(Blocks.SOUL_SAND) || state.is(Blocks.SOUL_SOIL);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
        int age = state.getValue(AGE);
        if (age < 3 && ForgeHooks.onCropsGrowPre(level, pos, state, rand.nextInt(10) == 0)) {
            state = state.setValue(AGE, age + 1);
            level.setBlock(pos, state, 2);
            ForgeHooks.onCropsGrowPost(level, pos, state);
        }
    }

    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return new ItemStack(VSItems.WARPED_WART.get());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
