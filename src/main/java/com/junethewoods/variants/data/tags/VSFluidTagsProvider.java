package com.junethewoods.variants.data.tags;

import com.junethewoods.variants.Variants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class VSFluidTagsProvider extends FluidTagsProvider {
    public VSFluidTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper fileHelper) {
        super(output, lookupProvider, Variants.MOD_ID, fileHelper);
    }

    @Override
    public String getName() {
        return "Variants - Fluid Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //this.tag(FluidTags.LAVA).add(VSFluids.SOUL_LAVA.get()).add(VSFluids.FLOWING_SOUL_LAVA.get());
    }
}
