package melonystudios.variants.block.custom;

import melonystudios.variants.block.VSBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class RVLogBlock extends RotatedPillarBlock {
    public RVLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (stack.getItem() instanceof AxeItem) {
            if (state.is(VSBlocks.PAINTING_LOG.get())) {
                return VSBlocks.STRIPPED_PAINTING_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(VSBlocks.PAINTING_WOOD.get())) {
                return VSBlocks.STRIPPED_PAINTING_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(VSBlocks.ENDERWOOD_STEM.get())) {
                return VSBlocks.STRIPPED_ENDERWOOD_STEM.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(VSBlocks.ENDERWOOD_HYPHAE.get())) {
                return VSBlocks.STRIPPED_ENDERWOOD_HYPHAE.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }
}
