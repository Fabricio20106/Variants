package melonystudios.variants.data.tags;

import melonystudios.variants.Variants;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.util.VSRegistries;
import melonystudios.variants.util.tag.StewBehaviorTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VSStewBehaviorTagsProvider extends ForgeRegistryTagsProvider<StewBehavior> {
    public VSStewBehaviorTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, VSRegistries.CONSUME_BEHAVIOR, Variants.MOD_ID, fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Stew Behavior Tags";
    }

    @Override
    protected void addTags() {
        this.tag(StewBehaviorTags.CANNOT_RUN_WITHOUT_NBT);
    }
}
