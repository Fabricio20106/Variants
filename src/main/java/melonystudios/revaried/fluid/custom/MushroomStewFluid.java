package melonystudios.revaried.fluid.custom;

import melonystudios.revaried.block.RVBlocks;
import melonystudios.revaried.fluid.RVFluidTypes;
import melonystudios.revaried.fluid.RVFluids;
import melonystudios.revaried.item.RVItems;
import melonystudios.revaried.util.RVUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public abstract class MushroomStewFluid extends BaseFlowingFluid {
    public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(
            RVFluidTypes.MUSHROOM_STEW, RVFluids.MUSHROOM_STEW, RVFluids.FLOWING_MUSHROOM_STEW)
            .block(RVBlocks.MUSHROOM_STEW).bucket(RVItems.MUSHROOM_STEW_BUCKET);

    public MushroomStewFluid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canConvertToSource(FluidState state, Level level, BlockPos pos) {
        return level.getGameRules().getBoolean(RVUtils.RULE_MUSHROOM_STEW_SOURCE_CONVERSION);
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
