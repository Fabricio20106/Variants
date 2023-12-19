package com.junethewoods.variants.common.block.end;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;
import java.util.function.Supplier;

public class EnderFungusBlock extends BushBlock implements BonemealableBlock {
    protected static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 9, 12);
    private final Supplier<Holder<ConfiguredFeature<HugeFungusConfiguration, ?>>> feature;

    public EnderFungusBlock(Properties properties, Supplier<Holder<ConfiguredFeature<HugeFungusConfiguration, ?>>> holderSupplier) {
        super(properties);
        this.feature = holderSupplier;
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(BlockTags.NYLIUM) || state.is(Blocks.MYCELIUM) || state.is(Blocks.SOUL_SOIL) || super.mayPlaceOn(state, getter, pos);
    }

    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean p_176473_4_) {
        Block baseBlock = (this.feature.get().value().config()).validBaseState.getBlock();
        Block blockBelow = getter.getBlockState(pos.below()).getBlock();
        return blockBelow == baseBlock;
    }

    public boolean isBonemealSuccess(Level level, Random rand, BlockPos pos, BlockState state) {
        return (double) rand.nextFloat() < 0.4D;
    }

    public void performBonemeal(ServerLevel level, Random rand, BlockPos pos, BlockState state) {
        this.feature.get().value().place(level, level.getChunkSource().getGenerator(), rand, pos);
    }
}
