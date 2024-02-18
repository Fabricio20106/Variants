package com.junethewoods.variants.data.tags;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.fluid.VSFluids;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.fluid.Fluids;
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
        return "Variants - Fluid Tags";
    }

    @Override
    protected void addTags() {
        this.tag(VSTags.Fluids.SOUL_LAVA).add(VSFluids.SOUL_LAVA.get()).add(VSFluids.FLOWING_SOUL_LAVA.get());
        this.tag(VSTags.Fluids.MUSHROOM_STEW).add(VSFluids.MUSHROOM_STEW.get()).add(VSFluids.FLOWING_MUSHROOM_STEW.get());
        this.tag(FluidTags.LAVA).addTag(VSTags.Fluids.SOUL_LAVA);
        this.tag(FluidTags.WATER).addTag(VSTags.Fluids.MUSHROOM_STEW);
    }
}
