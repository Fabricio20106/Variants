package melonystudios.variants.block.custom.end;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherRootsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class EnderRootsBlock extends NetherRootsBlock {
    public EnderRootsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSBlockTags.END_PLANTS_MAY_PLACE_ON) || super.mayPlaceOn(state, world, pos);
    }
}
