package melonystudios.revaried.data.tag;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.fluid.RVFluids;
import melonystudios.revaried.tag.RVFluidTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RVFluidTagsProvider extends FluidTagsProvider {
    public RVFluidTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper fileHelper) {
        super(output, registries, Revaried.MOD_ID, fileHelper);
    }

    @Override
    @NotNull
    public String getName() {
        return Revaried.generatorName("Fluid Tags");
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        // Common tags
        this.tag(Tags.Fluids.MUSHROOM_STEW).add(RVFluids.MUSHROOM_STEW.get(), RVFluids.FLOWING_MUSHROOM_STEW.get());
        this.tag(RVFluidTags.SOUL_LAVA).add(RVFluids.SOUL_LAVA.get(), RVFluids.FLOWING_SOUL_LAVA.get());

        // Minecraft tags
        this.tag(FluidTags.LAVA).addTag(RVFluidTags.SOUL_LAVA);
    }
}
