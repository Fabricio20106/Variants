package melonystudios.variants.block.custom.nether;

import melonystudios.variants.block.custom.AbstractFarmlandBlock;
import melonystudios.variants.util.tag.VSFluidTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.ITag;

public class NetherFarmlandBlock extends AbstractFarmlandBlock {
    public NetherFarmlandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getDirtLikeBlock() {
        return Blocks.NETHERRACK.defaultBlockState();
    }

    @Override
    public ITag<Fluid> getHydrationFluid() {
        return VSFluidTags.HYDRATES_LAVA_BASED_FARMLAND;
    }
}
