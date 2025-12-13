package melonystudios.variants.data.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.item.fix.TagFix;
import melonystudios.variants.item.fix.RVTagFixes;
import melonystudios.variants.util.RVRegistries;
import melonystudios.variants.util.tag.TagFixTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VSTagFixTagsProvider extends ForgeRegistryTagsProvider<TagFix> {
    public VSTagFixTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, RVRegistries.TAG_FIX, Variants.MOD_ID, fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return Variants.generatorName("Tag Fix Tags");
    }

    @Override
    protected void addTags() {
        this.tag(TagFixTags.APPLIES_ON_TAG_RELOAD).add(RVTagFixes.CORRECT_ENDER_BOWL.get());
    }
}
