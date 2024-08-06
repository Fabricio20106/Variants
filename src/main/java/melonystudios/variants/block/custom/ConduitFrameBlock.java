package melonystudios.variants.block.custom;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class ConduitFrameBlock extends Block {
    public ConduitFrameBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isConduitFrame(BlockState state, IWorldReader world, BlockPos pos, BlockPos conduit) {
        return state.is(VSBlockTags.CONDUIT_FRAME_BLOCKS);
    }
}
