package melonystudios.variants.block.custom.end;

import melonystudios.variants.block.VSBlocks;
import melonystudios.variants.world.feature.VSConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NyliumBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NetherVegetationFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;

public class EnderNyliumBlock extends NyliumBlock {
    public EnderNyliumBlock(Properties properties) {
        super(properties);
    }

    private static boolean canBeNylium(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = world.getBlockState(abovePos);
        int lightAtBlock = LightEngine.getLightBlockInto(world, state, pos, aboveState, abovePos, Direction.UP, aboveState.getLightBlock(world, abovePos));
        return lightAtBlock < world.getMaxLightLevel();
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (!canBeNylium(state, world, pos)) {
            world.setBlockAndUpdate(pos, Blocks.END_STONE.defaultBlockState());
        }
    }

    @Override
    public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        BlockState posState = world.getBlockState(pos);
        BlockPos abovePos = pos.above();
         if (posState.is(VSBlocks.ENDER_NYLIUM.get())) {
            NetherVegetationFeature.place(world, rand, abovePos, VSConfiguredFeatures.ENDERWOOD_FOREST_CONFIG, 3, 1);
            NetherVegetationFeature.place(world, rand, abovePos, VSConfiguredFeatures.END_SPROUTS_CONFIG, 3, 1);
        }
    }

    @Override
    @Nullable
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (stack.getItem() instanceof HoeItem && state.is(VSBlocks.ENDER_NYLIUM.get())) return VSBlocks.ENDER_FARMLAND.get().defaultBlockState();
        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }
}
