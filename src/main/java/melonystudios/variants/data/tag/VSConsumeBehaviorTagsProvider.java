package melonystudios.variants.data.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.util.VSRegistries;
import melonystudios.variants.util.tag.ConsumeBehaviorTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VSConsumeBehaviorTagsProvider extends ForgeRegistryTagsProvider<ConsumeBehavior> {
    public VSConsumeBehaviorTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, VSRegistries.CONSUME_BEHAVIOR, Variants.MOD_ID, fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Consume Behavior Tags";
    }

    @Override
    protected void addTags() {
        this.tag(ConsumeBehaviorTags.CANNOT_RUN_WITHOUT_NBT);
    }
}
