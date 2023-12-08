package com.junethewoods.variants.block.custom.end;

import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

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

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(AGE)];
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(VSTags.Blocks.ENDER_WART_PLANTABLE_ON);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        int age = state.getValue(AGE);
        if (age < 3 && CommonHooks.onCropsGrowPre(level, pos, state, rand.nextInt(10) == 0)) {
            state = state.setValue(AGE, age + 1);
            level.setBlock(pos, state, 2);
            CommonHooks.onCropsGrowPost(level, pos, state);
        }
    }

    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(VSItems.ENDER_WART.get());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
