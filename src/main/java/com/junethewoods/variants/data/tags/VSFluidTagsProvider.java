package com.junethewoods.variants.data.tags;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.fluid.VSFluids;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class VSFluidTagsProvider extends FluidTagsProvider {
    public VSFluidTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Override
    public String getName() {
        return "Variants - Fluid Tags";
    }

    @Override
    protected void addTags() {
        this.tag(VSTags.Fluids.SOUL_LAVA).add(VSFluids.SOUL_LAVA.get()).add(VSFluids.FLOWING_SOUL_LAVA.get());
        this.tag(FluidTags.LAVA).addTag(VSTags.Fluids.SOUL_LAVA);
    }
}
