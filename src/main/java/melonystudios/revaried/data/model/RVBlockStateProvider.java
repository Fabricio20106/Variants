package melonystudios.revaried.data.model;

import melonystudios.reutilities.data.model.ReBlockStateProvider;
import melonystudios.revaried.Revaried;
import melonystudios.revaried.util.RVResourceLocations;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import static melonystudios.revaried.block.RVBlocks.*;

public class RVBlockStateProvider extends ReBlockStateProvider {
    public RVBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, Revaried.MOD_ID, fileHelper);
    }

    @Override
    @NotNull
    public String getName() {
        return Revaried.generatorName("Block States and Models");
    }

    @Override
    protected void registerStatesAndModels() {
        this.fluid(MUSHROOM_STEW.get(), RVResourceLocations.STILL_MUSHROOM_STEW);
        this.fluid(SOUL_LAVA.get(), RVResourceLocations.STILL_SOUL_LAVA);
        this.singleLayerCauldron(SOUL_LAVA_CAULDRON.get(), RVResourceLocations.STILL_SOUL_LAVA);
    }
}
