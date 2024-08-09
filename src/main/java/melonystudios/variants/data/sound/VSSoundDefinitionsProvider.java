package melonystudios.variants.data.sound;

import melonystudios.variants.Variants;
import melonystudios.variants.sound.VSSounds;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import javax.annotation.Nonnull;

import static melonystudios.variants.Variants.variants;
import static melonystudios.variants.util.VSUtils.minecraft;

public class VSSoundDefinitionsProvider extends SoundDefinitionsProvider {
    /**
     * Creates a new instance of this data provider.
     *
     * @param generator The data generator instance provided by the event you are initializing this provider in.
     * @param fileHelper    The existing file helper provided by the event you are initializing this provider in.
     */
    public VSSoundDefinitionsProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Sound Definitions";
    }

    @Override
    public void registerSounds() {
        // Music
        this.add(VSSounds.MUSIC_DISC_DOG.get(), SoundDefinition.definition().with(SoundDefinition.Sound.sound(variants("music/disc/dog"), SoundDefinition.SoundType.SOUND).stream()));

        // Entities
        // this.add(VSSounds.FISH_AMBIENT.get(), SoundDefinition.definition().with());
        this.add(VSSounds.FISH_FLOP.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/flop1"), SoundDefinition.SoundType.SOUND).volume(0.3))
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/flop2"), SoundDefinition.SoundType.SOUND).volume(0.3))
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/flop3"), SoundDefinition.SoundType.SOUND).volume(0.3))
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/flop4"), SoundDefinition.SoundType.SOUND).volume(0.3)).subtitle("subtitles.entity.fish.flop"));
        this.add(VSSounds.FISH_HURT.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/hurt1"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/hurt2"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/hurt3"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/hurt4"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.entity.fish.hurt"));
        this.add(VSSounds.FISH_DEATH.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/hurt1"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/hurt2"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/hurt3"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("entity/fish/hurt4"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.entity.fish.death"));
        this.add(VSSounds.DRAGON_BREATH_BOTTLE_THROW.get(), SoundDefinition.definition().with(SoundDefinition.Sound.sound(minecraft("random/bow"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.entity.potion.throw"));

        // Items
        this.add(VSSounds.SPYGLASS_USE.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(variants("item/spyglass/use"), SoundDefinition.SoundType.SOUND).volume(0.5).pitch(1.15))
                .with(SoundDefinition.Sound.sound(variants("item/spyglass/use"), SoundDefinition.SoundType.SOUND).volume(0.5).pitch(1.33))
                .with(SoundDefinition.Sound.sound(variants("item/spyglass/use"), SoundDefinition.SoundType.SOUND).volume(0.5).pitch(1.4))
                .with(SoundDefinition.Sound.sound(variants("item/spyglass/use"), SoundDefinition.SoundType.SOUND).volume(0.5).pitch(1.55)).subtitle("subtitles.item.spyglass.use"));
        this.add(VSSounds.SPYGLASS_STOP_USING.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(variants("item/spyglass/stop"), SoundDefinition.SoundType.SOUND).volume(0.5))
                .with(SoundDefinition.Sound.sound(variants("item/spyglass/stop"), SoundDefinition.SoundType.SOUND).volume(0.5).pitch(0.8))
                .with(SoundDefinition.Sound.sound(variants("item/spyglass/stop"), SoundDefinition.SoundType.SOUND).volume(0.5).pitch(0.9)).subtitle("subtitles.item.spyglass.stop_using"));

        // Stew Behaviors
        // this.add(VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get(), SoundDefinition.definition().with());

        // Unused as of now because the game crashes when using these (don't know why, Minecraft is weird).
        this.add(VSSounds.ARMOR_EQUIP_EMPTY_SLOT.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron1"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron2"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron3"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron4"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron5"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron6"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.item.armor.equip_empty_slot"));
        this.add(VSSounds.ARMOR_EQUIP_EMERALD.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_diamond1"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_diamond2"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_diamond3"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_diamond4"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_diamond5"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_diamond6"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.item.armor.equip_emerald"));
        this.add(VSSounds.ARMOR_EQUIP_QUARTZ.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron1"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron2"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron3"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron4"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron5"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron6"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.item.armor.equip_quartz"));
        this.add(VSSounds.ARMOR_EQUIP_COPPER.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron1"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron2"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron3"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron4"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron5"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_iron6"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.item.armor.equip_copper"));
        this.add(VSSounds.ARMOR_EQUIP_PHANTOM_MEMBRANE.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather1"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather2"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather3"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather4"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather5"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather6"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.item.armor.equip_phantom_membrane"));
        this.add(VSSounds.ARMOR_EQUIP_RABBIT_HIDE.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather1"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather2"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather3"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather4"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather5"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather6"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.item.armor.equip_rabbit_hide"));
        this.add(VSSounds.ARMOR_EQUIP_WOOL.get(), SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather1"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather2"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather3"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather4"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather5"), SoundDefinition.SoundType.SOUND))
                .with(SoundDefinition.Sound.sound(minecraft("item/armor/equip_leather6"), SoundDefinition.SoundType.SOUND)).subtitle("subtitles.item.armor.equip_wool"));
    }
}
