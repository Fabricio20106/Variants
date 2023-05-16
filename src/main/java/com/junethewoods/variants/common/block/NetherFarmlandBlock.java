package com.junethewoods.variants.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class NetherFarmlandBlock extends Block {
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public NetherFarmlandBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 0));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor accessor, BlockPos pos, BlockPos pos1) {
        if (direction == Direction.UP && !state.canSurvive(accessor, pos)) {
            accessor.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, state1, accessor, pos, pos1);
    }

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        BlockState abovePos = reader.getBlockState(pos.above());
        return !abovePos.getMaterial().isSolid() || abovePos.getBlock() instanceof FenceGateBlock || abovePos.getBlock() instanceof MovingPistonBlock;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Blocks.NETHERRACK.defaultBlockState() : super.getStateForPlacement(context);
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
        if (!state.canSurvive(world, pos)) {
            turnToNetherrack(state, world, pos);
        }
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
        int moisture = state.getValue(MOISTURE);
        if (!isNearLava(world, pos) && !world.isRainingAt(pos.above())) {
            if (moisture > 0) {
                world.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            } else if (!isUnderCrops(world, pos)) {
                turnToNetherrack(state, world, pos);
            }
        } else if (moisture < 7) {
            world.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        }
    }

    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float f) {
        if (!world.isClientSide && ForgeHooks.onFarmlandTrample(world, pos, Blocks.NETHERRACK.defaultBlockState(), f, entity)) { // Forge: Move logic to Entity#canTrample
            turnToNetherrack(world.getBlockState(pos), world, pos);
        }

        super.fallOn(world, state, pos, entity, f);
    }

    public static void turnToNetherrack(BlockState state, Level world, BlockPos pos) {
        world.setBlockAndUpdate(pos, pushEntitiesUp(state, Blocks.NETHERRACK.defaultBlockState(), world, pos));
    }

    private boolean isUnderCrops(BlockGetter getter, BlockPos pos) {
        BlockState plantAbove = getter.getBlockState(pos.above());
        BlockState state = getter.getBlockState(pos);
        return plantAbove.getBlock() instanceof IPlantable && state.canSustainPlant(getter, pos, Direction.UP, (IPlantable) plantAbove.getBlock());
    }

    private static boolean isNearLava(LevelReader reader, BlockPos pos) {
        for(BlockPos pos1 : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (reader.getFluidState(pos1).is(FluidTags.LAVA)) {
                return true;
            }
        }

        return FarmlandWaterManager.hasBlockWaterTicket(reader, pos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE);
    }

    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType pathType) {
        return false;
    }
}
