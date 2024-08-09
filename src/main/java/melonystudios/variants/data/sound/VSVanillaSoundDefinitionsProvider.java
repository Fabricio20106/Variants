package melonystudios.variants.data.sound;

import melonystudios.variants.Variants;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import javax.annotation.Nonnull;

public class VSVanillaSoundDefinitionsProvider extends SoundDefinitionsProvider {
    public VSVanillaSoundDefinitionsProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, "minecraft", fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Vanilla Sound Definitions";
    }

    @Override
    public void registerSounds() {
        this.add(SoundEvents.MUSIC_MENU, SoundDefinition.definition().with(SoundDefinition.Sound.sound(Variants.variants("music/menu/moog_city"), SoundDefinition.SoundType.SOUND).stream()));
    }
}
