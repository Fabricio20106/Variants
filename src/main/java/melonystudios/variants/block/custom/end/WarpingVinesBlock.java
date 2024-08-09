package melonystudios.variants.block.custom.end;

import melonystudios.variants.block.VSBlocks;
import net.minecraft.block.AbstractBodyPlantBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class WarpingVinesBlock extends AbstractBodyPlantBlock {
    public static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 16, 15);

    public WarpingVinesBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    @Override
    @Nonnull
    protected AbstractTopPlantBlock getHeadBlock() {
        return (AbstractTopPlantBlock) VSBlocks.WARPING_VINES.get();
    }
}
