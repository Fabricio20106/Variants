package melonystudios.revaried.fluid.custom;

import melonystudios.revaried.block.RVBlocks;
import melonystudios.revaried.fluid.RVFluidTypes;
import melonystudios.revaried.fluid.RVFluids;
import melonystudios.revaried.item.RVItems;
import melonystudios.revaried.misc.particle.RVParticleTypes;
import melonystudios.revaried.util.RVUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import org.jetbrains.annotations.Nullable;

public abstract class SoulLavaFluid extends BaseFlowingFluid {
    public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(
            RVFluidTypes.SOUL_LAVA, RVFluids.SOUL_LAVA, RVFluids.FLOWING_SOUL_LAVA)
            .block(RVBlocks.SOUL_LAVA).bucket(RVItems.SOUL_LAVA_BUCKET);

    public SoulLavaFluid(Properties properties) {
        super(properties);
    }

    @Override
    protected void animateTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
        BlockPos abovePos = pos.above();
        if (level.getBlockState(abovePos).isAir() && !level.getBlockState(abovePos).isSolidRender(level, abovePos)) {
            if (random.nextInt(100) == 0) {
                double x = (double) pos.getX() + random.nextDouble();
                double y = (double) pos.getY() + 1;
                double z = (double) pos.getZ() + random.nextDouble();
                level.addParticle(RVParticleTypes.SOUL_LAVA.get(), x, y, z, 0, 0, 0);
                level.playLocalSound(x, y, z, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }

            if (random.nextInt(200) == 0) {
                level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }
    }

    @Override
    @Nullable
    protected ParticleOptions getDripParticle() {
        return RVParticleTypes.DRIPPING_SOUL_LAVA.get();
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        this.fizz(level, pos);
    }

    @Override
    protected int getSlopeFindDistance(LevelReader level) {
        return level.dimensionType().ultraWarm() ? 4 : 2;
    }

    @Override
    protected int getDropOff(LevelReader level) {
        return level.dimensionType().ultraWarm() ? 1 : 2;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
        return state.getHeight(level, pos) >= LavaFluid.MIN_LEVEL_CUTOFF && fluid.getFluidType() == NeoForgeMod.WATER_TYPE.value();
    }

    @Override
    public int getTickDelay(LevelReader level) {
        return level.dimensionType().ultraWarm() ? 8 : 24;
    }

    @Override
    public int getSpreadDelay(Level level, BlockPos pos, FluidState currentState, FluidState newState) {
        int tickDelay = this.getTickDelay(level);
        if (!currentState.isEmpty()
                && !newState.isEmpty()
                && !currentState.getValue(FALLING)
                && !newState.getValue(FALLING)
                && newState.getHeight(level, pos) > currentState.getHeight(level, pos)
                && level.getRandom().nextInt(4) != 0) {
            tickDelay *= 4;
        }

        return tickDelay;
    }

    private void fizz(LevelAccessor level, BlockPos pos) {
        level.levelEvent(1501, pos, 0);
    }

    @Override
    public boolean canConvertToSource(FluidState state, Level level, BlockPos pos) {
        return level.getGameRules().getBoolean(RVUtils.RULE_SOUL_LAVA_SOURCE_CONVERSION);
    }

    @Override
    protected void spreadTo(LevelAccessor level, BlockPos pos, BlockState blockState, Direction direction, FluidState fluidState) {
        if (direction == Direction.DOWN) {
            FluidState state = level.getFluidState(pos);
            if (state.getType().getFluidType() == NeoForgeMod.WATER_TYPE.value()) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, EventHooks.fireFluidPlaceBlockEvent(level, pos, pos, Blocks.BLACKSTONE.defaultBlockState()), 3);
                }

                this.fizz(level, pos);
                return;
            }
        }

        super.spreadTo(level, pos, blockState, direction, fluidState);
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
    }

    public static class Flowing extends BaseFlowingFluid {
        public Flowing(Properties properties) {
            super(properties);
            this.registerDefaultState(this.getStateDefinition().any().setValue(LEVEL, 7));
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends BaseFlowingFluid {
        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
