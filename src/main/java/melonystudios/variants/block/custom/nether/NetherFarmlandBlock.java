package melonystudios.variants.block.custom.nether;

import melonystudios.variants.block.custom.AbstractFarmlandBlock;
import melonystudios.variants.util.tag.VSFluidTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.ITag;

import javax.annotation.Nonnull;

public class NetherFarmlandBlock extends AbstractFarmlandBlock {
    public NetherFarmlandBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public BlockState getDirtLikeBlock() {
        return Blocks.NETHERRACK.defaultBlockState();
    }

    @Override
    @Nonnull
    public ITag<Fluid> getHydrationFluid() {
        return VSFluidTags.HYDRATES_LAVA_BASED_FARMLAND;
    }
}
