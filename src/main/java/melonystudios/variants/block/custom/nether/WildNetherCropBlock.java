package melonystudios.variants.block.custom.nether;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class WildNetherCropBlock extends BushBlock {
    private final Supplier<Item> seedItem;

    public WildNetherCropBlock(Supplier<Item> seedItem, Properties properties) {
        super(properties);
        this.seedItem = seedItem;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSBlockTags.WILD_NETHER_CROPS_PLANTABLE_ON);
    }

    @Override
    @Nonnull
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(this.seedItem.get());
    }
}
