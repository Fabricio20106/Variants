package com.junethewoods.variants.block.custom;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

public class PottedRedstoneTorchBlock extends PottedTorchBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final Map<IBlockReader, List<Toggle>> RECENT_TOGGLES = new WeakHashMap<>();

    public PottedRedstoneTorchBlock(Block torch, Properties properties) {
        super(torch, RedstoneParticleData.REDSTONE, properties);
    }

    public void onPlace(BlockState state, World world, BlockPos pos, BlockState state1, boolean bool) {
        for(Direction direction : Direction.values()) {
            world.updateNeighborsAt(pos.relative(direction), this);
        }
    }

    public void onRemove(BlockState state, World world, BlockPos pos, BlockState state1, boolean bool) {
        if (!bool) {
            for(Direction direction : Direction.values()) {
                world.updateNeighborsAt(pos.relative(direction), this);
            }
        }
    }

    public int getSignal(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        return state.getValue(LIT) && Direction.UP != direction ? 15 : 0;
    }

    protected boolean hasNeighborSignal(World world, BlockPos pos, BlockState state) {
        return world.hasSignal(pos.below(), Direction.DOWN);
    }

    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        boolean hasNeighborSignal = this.hasNeighborSignal(world, pos, state);
        List<Toggle> recentToggles = RECENT_TOGGLES.get(world);

        while(recentToggles != null && !recentToggles.isEmpty() && world.getGameTime() - (recentToggles.get(0)).when > 60L) {
            recentToggles.remove(0);
        }

        if (state.getValue(LIT)) {
            if (hasNeighborSignal) {
                world.setBlock(pos, state.setValue(LIT, false), 3);
                if (isToggledTooFrequently(world, pos, true)) {
                    world.levelEvent(1502, pos, 0);
                    world.getBlockTicks().scheduleTick(pos, world.getBlockState(pos).getBlock(), 160);
                }
            }
        } else if (!hasNeighborSignal && !isToggledTooFrequently(world, pos, false)) {
            world.setBlock(pos, state.setValue(LIT, true), 3);
        }
    }

    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos pos1, boolean bool) {
        if (state.getValue(LIT) == this.hasNeighborSignal(world, pos, state) && !world.getBlockTicks().willTickThisTick(pos, this)) {
            world.getBlockTicks().scheduleTick(pos, this, 2);
        }
    }

    public int getDirectSignal(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        return direction == Direction.DOWN ? state.getSignal(world, pos, direction) : 0;
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (state.getValue(LIT)) {
            double posX = (double) pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
            double posY = (double) pos.getY() + 0.9D + (rand.nextDouble() - 0.5D) * 0.2D;
            double posZ = (double) pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
            world.addParticle(this.flameParticle, posX, posY, posZ, 0, 0, 0);
        }
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    private static boolean isToggledTooFrequently(World world, BlockPos pos, boolean isLit) {
        List<Toggle> list = RECENT_TOGGLES.computeIfAbsent(world, (reader) -> Lists.newArrayList());
        if (isLit) list.add(new Toggle(pos.immutable(), world.getGameTime()));

        int toggles = 0;

        for(int j = 0; j < list.size(); ++j) {
            Toggle toggleList = list.get(j);
            if (toggleList.pos.equals(pos)) {
                ++toggles;
                if (toggles >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    public static class Toggle {
        private final BlockPos pos;
        private final long when;

        public Toggle(BlockPos pos, long when) {
            this.pos = pos;
            this.when = when;
        }
    }
}
