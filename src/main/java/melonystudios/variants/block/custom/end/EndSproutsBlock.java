package melonystudios.variants.block.custom.end;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherSproutsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class EndSproutsBlock extends NetherSproutsBlock {
    public EndSproutsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSBlockTags.END_PLANTS_PLANTABLE_ON) || super.mayPlaceOn(state, world, pos);
    }
}
