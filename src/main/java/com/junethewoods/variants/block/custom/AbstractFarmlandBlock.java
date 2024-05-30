package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public abstract class AbstractFarmlandBlock extends Block {
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    protected static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 15, 16);

    public BlockState getDirtLikeBlock() {
        return Blocks.DIRT.defaultBlockState();
    }

    public ITag<Fluid> getHydrationFluid() {
        return VSTags.Fluids.HYDRATES_WATER_BASED_FARMLAND;
    }

    public AbstractFarmlandBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 0));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, IWorld world, BlockPos pos, BlockPos pos1) {
        if (direction == Direction.UP && !state.canSurvive(world, pos)) {
            world.getBlockTicks().scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, state1, world, pos, pos1);
    }

    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        BlockState aboveState = world.getBlockState(pos.above());
        return !aboveState.getMaterial().isSolid() || aboveState.is(VSTags.Blocks.FARMLAND_TRANSPARENT);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? getDirtLikeBlock() : super.getStateForPlacement(context);
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (!state.canSurvive(world, pos)) {
            turnToDirtLike(state, world, pos);
        }
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        int moisture = state.getValue(MOISTURE);
        if (!isNearWater(world, pos) && !world.isRainingAt(pos.above())) {
            if (moisture > 0) {
                world.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            } else if (!isUnderCrops(world, pos)) {
                turnToDirtLike(state, world, pos);
            }
        } else if (moisture < 7) {
            world.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        }
    }

    public void fallOn(World world, BlockPos pos, Entity entity, float distance) {
        // Forge: Move logic to IForgeEntity#canTrample
        if (!world.isClientSide && ForgeHooks.onFarmlandTrample(world, pos, getDirtLikeBlock(), distance, entity)) {
            turnToDirtLike(world.getBlockState(pos), world, pos);
        }

        super.fallOn(world, pos, entity, distance);
    }

    public void turnToDirtLike(BlockState state, World world, BlockPos pos) {
        world.setBlockAndUpdate(pos, pushEntitiesUp(state, getDirtLikeBlock(), world, pos));
    }

    private boolean isUnderCrops(IBlockReader world, BlockPos pos) {
        BlockState plantAbove = world.getBlockState(pos.above());
        BlockState state = world.getBlockState(pos);
        return plantAbove.getBlock() instanceof IPlantable && state.canSustainPlant(world, pos, Direction.UP, (IPlantable) plantAbove.getBlock());
    }

    private boolean isNearWater(IWorldReader world, BlockPos pos) {
        for(BlockPos positions : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (world.getFluidState(positions).is(getHydrationFluid())) {
                return true;
            }
        }
        return FarmlandWaterManager.hasBlockWaterTicket(world, pos);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE);
    }

    public boolean isPathfindable(BlockState state, IBlockReader world, BlockPos pos, PathType type) {
        return false;
    }
}
