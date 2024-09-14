package melonystudios.variants.data.tags;

import melonystudios.variants.Variants;
import melonystudios.variants.item.fix.TagFix;
import melonystudios.variants.util.VSRegistries;
import melonystudios.variants.util.tag.TagFixTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VSTagFixTagsProvider extends ForgeRegistryTagsProvider<TagFix> {
    public VSTagFixTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, VSRegistries.TAG_FIX, Variants.MOD_ID, fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Tag Fix Tags";
    }

    @Override
    protected void addTags() {
        this.tag(TagFixTags.APPLIES_ON_TAG_RELOAD);
    }
}
