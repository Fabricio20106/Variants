package com.junethewoods.variants.fluid.custom;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.fluid.VSFluids;
import com.junethewoods.variants.item.VSItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class SoulLavaFluid extends ForgeFlowingFluid {
    public static final ForgeFlowingFluid.Properties SOUL_LAVA_PROPERTIES = new ForgeFlowingFluid.Properties(VSFluids.SOUL_LAVA, VSFluids.FLOWING_SOUL_LAVA,
            FluidAttributes.builder(Variants.resourceLoc("block/soul_lava_still"), Variants.resourceLoc("block/soul_lava_flowing")).rarity(Rarity.UNCOMMON).sound(SoundEvents.BUCKET_EMPTY_LAVA)
                    .overlay(Variants.resourceLoc("block/soul_lava_overlay"))).levelDecreasePerBlock(2).slopeFindDistance(2).block(VSBlocks.SOUL_LAVA).tickRate(40).bucket(VSItems.SOUL_LAVA_BUCKET);

    public SoulLavaFluid(Properties properties) {
        super(properties);
    }

    public Fluid getFlowing() {
        return VSFluids.FLOWING_SOUL_LAVA.get();
    }

    public Fluid getSource() {
        return VSFluids.SOUL_LAVA.get();
    }

    public Item getBucket() {
        return VSItems.SOUL_LAVA_BUCKET.get();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(World world, BlockPos pos, FluidState state, Random rand) {
        BlockPos abovePos = pos.above();
        if (world.getBlockState(abovePos).isAir() && !world.getBlockState(abovePos).isSolidRender(world, abovePos)) {
            if (rand.nextInt(100) == 0) {
                double d0 = (double) pos.getX() + rand.nextDouble();
                double d1 = (double) pos.getY() + 1;
                double d2 = (double) pos.getZ() + rand.nextDouble();
                world.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0, 0, 0);
                world.playLocalSound(d0, d1, d2, SoundEvents.LAVA_POP, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }

            if (rand.nextInt(200) == 0) {
                world.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
        }
    }

    public void randomTick(World world, BlockPos pos, FluidState state, Random rand) {
        if (world.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            int randInt = rand.nextInt(3);
            if (randInt > 0) {
                BlockPos pos1 = pos;

                for(int j = 0; j < randInt; ++j) {
                    pos1 = pos1.offset(rand.nextInt(3) - 1, 1, rand.nextInt(3) - 1);
                    if (!world.isLoaded(pos1)) {
                        return;
                    }

                    BlockState state1 = world.getBlockState(pos1);
                    if (state1.isAir()) {
                        if (this.hasFlammableNeighbours(world, pos1)) {
                            world.setBlockAndUpdate(pos1, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos1, pos, Blocks.FIRE.defaultBlockState()));
                            return;
                        }
                    } else if (state1.getMaterial().blocksMotion()) {
                        return;
                    }
                }
            } else {
                for(int k = 0; k < 3; ++k) {
                    BlockPos state1 = pos.offset(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);
                    if (!world.isLoaded(state1)) {
                        return;
                    }

                    if (world.isEmptyBlock(state1.above()) && this.isFlammable(world, state1, Direction.UP)) {
                        world.setBlockAndUpdate(state1.above(), ForgeEventFactory.fireFluidPlaceBlockEvent(world, state1.above(), pos, Blocks.FIRE.defaultBlockState()));
                    }
                }
            }

        }
    }

    private boolean hasFlammableNeighbours(IWorldReader world, BlockPos pos) {
        for(Direction direction : Direction.values()) {
            if (this.isFlammable(world, pos.relative(direction), direction.getOpposite())) {
                return true;
            }
        }

        return false;
    }

    private boolean isFlammable(IWorldReader world, BlockPos pos, Direction face) {
        return (pos.getY() < 0 || pos.getY() >= 256 || world.hasChunkAt(pos)) && world.getBlockState(pos).isFlammable(world, pos, face);
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public IParticleData getDripParticle() {
        return ParticleTypes.DRIPPING_LAVA;
    }

    protected void beforeDestroyingBlock(IWorld world, BlockPos pos, BlockState state) {
        this.fizz(world, pos);
    }

    public int getSlopeFindDistance(IWorldReader world) {
        return world.dimensionType().ultraWarm() ? 4 : 2;
    }

    public BlockState createLegacyBlock(FluidState state) {
        return VSBlocks.SOUL_LAVA.get().defaultBlockState().setValue(FlowingFluidBlock.LEVEL, getLegacyLevel(state));
    }

    public boolean isSame(Fluid fluid) {
        return fluid == VSFluids.SOUL_LAVA.get() || fluid == VSFluids.FLOWING_SOUL_LAVA.get();
    }

    public int getDropOff(IWorldReader world) {
        return world.dimensionType().ultraWarm() ? 1 : 2;
    }

    public boolean canBeReplacedWith(FluidState state, IBlockReader world, BlockPos pos, Fluid fluid, Direction direction) {
        return state.getHeight(world, pos) >= 0.44444445F && fluid.is(FluidTags.WATER);
    }

    public int getTickDelay(IWorldReader world) {
        return world.dimensionType().ultraWarm() ? 10 : 30;
    }

    public int getSpreadDelay(World world, BlockPos pos, FluidState state, FluidState state1) {
        int tickDelay = this.getTickDelay(world);
        if (!state.isEmpty() && !state1.isEmpty() && !state.getValue(FALLING) && !state1.getValue(FALLING) && state1.getHeight(world, pos) > state.getHeight(world, pos) && world.getRandom().nextInt(4) != 0) {
            tickDelay *= 4;
        }

        return tickDelay;
    }

    private void fizz(IWorld world, BlockPos pos) {
        world.levelEvent(1501, pos, 0);
    }

    protected boolean canConvertToSource() {
        return false;
    }

    protected void spreadTo(IWorld world, BlockPos pos, BlockState state, Direction direction, FluidState state1) {
        if (direction == Direction.DOWN) {
            FluidState fluidState = world.getFluidState(pos);
            if (this.is(FluidTags.LAVA) && fluidState.is(FluidTags.WATER)) {
                if (state.getBlock() instanceof FlowingFluidBlock) {
                    world.setBlock(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, Blocks.STONE.defaultBlockState()), 3);
                }

                this.fizz(world, pos);
                return;
            }
        }

        super.spreadTo(world, pos, state, direction, state1);
    }

    protected boolean isRandomlyTicking() {
        return true;
    }

    protected float getExplosionResistance() {
        return 100;
    }

    public static class Flowing extends SoulLavaFluid {
        public Flowing() {
            super(SOUL_LAVA_PROPERTIES);
        }

        protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }
    }

    public static class Source extends SoulLavaFluid {
        public Source() {
            super(SOUL_LAVA_PROPERTIES);
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }
    }
}
