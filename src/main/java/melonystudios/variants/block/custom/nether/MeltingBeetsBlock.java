package melonystudios.variants.block.custom.nether;

import melonystudios.variants.item.VSItems;
import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.Random;

public class MeltingBeetsBlock extends CropsBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, 0, 16, 6, 16),
            Block.box(0, 0, 0, 16, 8, 16)};

    public MeltingBeetsBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSBlockTags.NETHER_CROPS_MAY_PLACE_ON);
    }

    @Override
    @Nonnull
    protected IItemProvider getBaseSeedId() {
        return VSItems.MELTING_BEET_SEEDS.get();
    }

    @Override
    @Nonnull
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.getValue(this.getAgeProperty())];
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (rand.nextInt(3) != 0) super.randomTick(state, world, pos, rand);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected int getBonemealAgeIncrease(World world) {
        return super.getBonemealAgeIncrease(world) / 3;
    }
}
