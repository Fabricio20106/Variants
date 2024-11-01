package melonystudios.variants.block.custom.nether;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class SoulCarrotsBlock extends CropsBlock {
    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, 0, 16, 5, 16),
            Block.box(0, 0, 0, 16, 6, 16),
            Block.box(0, 0, 0, 16, 7, 16),
            Block.box(0, 0, 0, 16, 8, 16),
            Block.box(0, 0, 0, 16, 9, 16)};

    public SoulCarrotsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSBlockTags.NETHER_CROPS_PLANTABLE_ON);
    }

    @Override
    @Nonnull
    protected IItemProvider getBaseSeedId() {
        // return VSItems.SOUL_CARROT.get(); todo: replace this
        return Items.AIR;
    }

    @Override
    @Nonnull
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.getValue(this.getAgeProperty())];
    }

    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        super.animateTick(state, world, pos, rand);
        Vector3d deltaMovement = new Vector3d(0, 0, 0);
        AxisAlignedBB boundingBox = new AxisAlignedBB(pos);
        world.addParticle(ParticleTypes.SOUL, pos.getX() + (world.random.nextDouble() - 0.5) * boundingBox.getSize(), pos.getY() + 0.1, pos.getZ() +
                (world.random.nextDouble() - 0.5) * boundingBox.getSize(), deltaMovement.x * -0.2, 0.1, deltaMovement.z * -0.2);
        float volume = world.random.nextFloat() * 0.4F + world.random.nextFloat() > 0.9F ? 0.6F : 0;
        world.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.SOUL_ESCAPE, SoundCategory.BLOCKS, volume, 0.6F + world.random.nextFloat() * 0.4F, false);
    }
}
