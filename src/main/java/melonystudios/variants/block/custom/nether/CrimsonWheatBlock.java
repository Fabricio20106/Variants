package melonystudios.variants.block.custom.nether;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;

public class CrimsonWheatBlock extends CropsBlock {
    public CrimsonWheatBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSBlockTags.NETHER_CROPS_PLANTABLE_ON);
    }

    @Override
    @Nonnull
    protected IItemProvider getBaseSeedId() {
        // return VSItems.CRIMSON_WHEAT_SEEDS.get(); todo: replace this
        return Items.AIR;
    }
}
