package melonystudios.variants.data.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.fluid.VSFluids;
import melonystudios.variants.util.tag.VSFluidTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VSFluidTagsProvider extends FluidTagsProvider {
    public VSFluidTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return Variants.generatorName("Fluid Tags");
    }

    @Override
    protected void addTags() {
        this.tag(VSFluidTags.SOUL_LAVA).add(VSFluids.SOUL_LAVA.get()).add(VSFluids.FLOWING_SOUL_LAVA.get());
        this.tag(VSFluidTags.MUSHROOM_STEW).add(VSFluids.MUSHROOM_STEW.get()).add(VSFluids.FLOWING_MUSHROOM_STEW.get());
        this.tag(VSFluidTags.HYDRATES_WATER_BASED_FARMLAND).addTag(FluidTags.WATER);
        this.tag(VSFluidTags.HYDRATES_LAVA_BASED_FARMLAND).addTag(FluidTags.LAVA);
        this.tag(FluidTags.LAVA).addTag(VSFluidTags.SOUL_LAVA);
        this.tag(FluidTags.WATER).addTag(VSFluidTags.MUSHROOM_STEW);
    }
}
