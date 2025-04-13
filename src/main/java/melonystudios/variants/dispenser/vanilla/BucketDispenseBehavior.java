package melonystudios.variants.dispenser.vanilla;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BucketDispenseBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior dispenseBehavior = new DefaultDispenseItemBehavior();

    @Nonnull
    public ItemStack execute(IBlockSource source, ItemStack stack) {
        BucketItem bucket = (BucketItem) stack.getItem();
        BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
        World world = source.getLevel();
        BlockState state = world.getBlockState(pos);
        if (bucket.emptyBucket(null, world, pos, null)) {
            bucket.checkExtraContent(world, stack, pos);
            return stack.getContainerItem();
        } if (state.is(VSBlockTags.CAULDRONS) && state.hasProperty(BlockStateProperties.LEVEL_CAULDRON)) {
            int level = state.getValue(BlockStateProperties.LEVEL_CAULDRON);
            if (stack.getItem() == Items.WATER_BUCKET && level == 0) {
                world.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LEVEL_CAULDRON, 3));
                return this.dispenseBehavior.dispense(source, new ItemStack(Items.BUCKET));
            }
        }
        return this.dispenseBehavior.dispense(source, stack);
    }
}
