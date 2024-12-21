package melonystudios.variants.block.custom.nether;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class WildMeltingBeetBlock extends WildNetherCropBlock {
    public WildMeltingBeetBlock(Supplier<Item> seedItem, Properties properties) {
        super(seedItem, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSBlockTags.WILD_MELTING_BEET_PLANTABLE_ON);
    }
}
