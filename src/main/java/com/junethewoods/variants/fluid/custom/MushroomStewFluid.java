package com.junethewoods.variants.fluid.custom;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.fluid.VSFluids;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class MushroomStewFluid extends ForgeFlowingFluid {
    public static final ForgeFlowingFluid.Properties MUSHROOM_STEW_PROPERTIES = new ForgeFlowingFluid.Properties(VSFluids.MUSHROOM_STEW, VSFluids.FLOWING_MUSHROOM_STEW, FluidAttributes.builder(Variants.resourceLoc("block/mushroom_stew_still"),
            Variants.resourceLoc("block/mushroom_stew_flowing")).rarity(Rarity.UNCOMMON).sound(SoundEvents.BUCKET_EMPTY).overlay(Variants.resourceLoc("block/mushroom_stew_overlay"))).block(VSBlocks.MUSHROOM_STEW)
            .bucket(VSItems.MUSHROOM_STEW_BUCKET);

    public MushroomStewFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Fluid getFlowing() {
        return VSFluids.FLOWING_MUSHROOM_STEW.get();
    }

    @Override
    public Fluid getSource() {
        return VSFluids.MUSHROOM_STEW.get();
    }

    @Override
    public Item getBucket() {
        return VSItems.MUSHROOM_STEW_BUCKET.get();
    }

    protected boolean canConvertToSource() {
        return false;
    }

    protected void beforeDestroyingBlock(IWorld world, BlockPos pos, BlockState state) {
        TileEntity blockEntity = state.hasTileEntity() ? world.getBlockEntity(pos) : null;
        Block.dropResources(state, world, pos, blockEntity);
    }

    public int getSlopeFindDistance(IWorldReader world) {
        return 4;
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return VSBlocks.MUSHROOM_STEW.get().defaultBlockState().setValue(FlowingFluidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public boolean isSame(Fluid fluid) {
        return fluid.is(VSTags.Fluids.MUSHROOM_STEW);
    }

    public int getDropOff(IWorldReader world) {
        return 1;
    }

    public int getTickDelay(IWorldReader world) {
        return 5;
    }

    @Override
    protected float getExplosionResistance() {
        return 100;
    }

    public static class Flowing extends MushroomStewFluid {
        public Flowing() {
            super(MUSHROOM_STEW_PROPERTIES);
        }

        protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends MushroomStewFluid {
        public Source() {
            super(MUSHROOM_STEW_PROPERTIES);
        }

        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
