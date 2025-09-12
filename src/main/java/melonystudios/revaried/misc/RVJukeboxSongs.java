package melonystudios.revaried.misc;

import melonystudios.revaried.Revaried;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

public class RVJukeboxSongs {
    public static final ResourceKey<JukeboxSong> DOG = register("dog");

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        var soundEvents = context.lookup(Registries.SOUND_EVENT);

        context.register(DOG, new JukeboxSong(soundEvents.getOrThrow(RVSounds.MUSIC_DISC_DOG.getKey()), Component.translatable("jukebox_song.revaried.dog"), 145, 2));
    }

    private static ResourceKey<JukeboxSong> register(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Revaried.revaried(name));
    }
}
