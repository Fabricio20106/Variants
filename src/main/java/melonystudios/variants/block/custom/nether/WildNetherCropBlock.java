package melonystudios.variants.block.custom.nether;

import net.minecraft.block.Block;
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
    private final Block baseBlock;

    public WildNetherCropBlock(Supplier<Item> seedItem, Block baseBlock, Properties properties) {
        super(properties);
        this.seedItem = seedItem;
        this.baseBlock = baseBlock;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(this.baseBlock);
    }

    @Override
    @Nonnull
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(this.seedItem.get());
    }
}
