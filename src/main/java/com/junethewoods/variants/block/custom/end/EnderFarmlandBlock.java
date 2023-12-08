package com.junethewoods.variants.block.custom.end;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.FarmlandWaterManager;
import net.neoforged.neoforge.common.IPlantable;

public class EnderFarmlandBlock extends Block {
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    protected static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 15, 16);

    public EnderFarmlandBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 0));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor world, BlockPos pos, BlockPos pos1) {
        if (direction == Direction.UP && !state.canSurvive(world, pos)) {
            world.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, state1, world, pos, pos1);
    }

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState aboveState = world.getBlockState(pos.above());
        return !aboveState.isSolid() || aboveState.getBlock() instanceof FenceGateBlock || aboveState.getBlock() instanceof MovingPistonBlock;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Blocks.END_STONE.defaultBlockState() : super.getStateForPlacement(context);
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (!state.canSurvive(level, pos)) {
            turnToEndStone(state, level, pos);
        }
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        int moisture = state.getValue(MOISTURE);
        if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
            if (moisture > 0) {
                level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            } else if (!isUnderCrops(level, pos)) {
                turnToEndStone(state, level, pos);
            }
        } else if (moisture < 7) {
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        }
    }

    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float f) {
        // Forge: Move logic to Entity#canTrample
        if (!level.isClientSide && CommonHooks.onFarmlandTrample(level, pos, Blocks.END_STONE.defaultBlockState(), f, entity)) {
            turnToEndStone(level.getBlockState(pos), level, pos);
        }

        super.fallOn(level, state, pos, entity, f);
    }

    public static void turnToEndStone(BlockState state, Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, pushEntitiesUp(state, Blocks.END_STONE.defaultBlockState(), level, pos));
    }

    private boolean isUnderCrops(BlockGetter level, BlockPos pos) {
        BlockState plantAbove = level.getBlockState(pos.above());
        BlockState state = level.getBlockState(pos);
        return plantAbove.getBlock() instanceof IPlantable && state.canSustainPlant(level, pos, Direction.UP, (IPlantable) plantAbove.getBlock());
    }

    private static boolean isNearWater(LevelReader level, BlockPos pos) {
        for(BlockPos positions : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (level.getFluidState(positions).is(FluidTags.WATER)) {
                return true;
            }
        }

        return FarmlandWaterManager.hasBlockWaterTicket(level, pos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE);
    }

    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}
