package melonystudios.revaried.util;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.item.UseAnim;

import java.util.Locale;

public class RVCodecs {
    public static final Codec<UseAnim> USE_ANIMATION_CODEC = Codec.stringResolver(animation -> animation.name().toLowerCase(Locale.ROOT), name -> {
        try {
            return UseAnim.valueOf(name.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException exception) {
            return null;
        }
    });
    public static final StreamCodec<ByteBuf, UseAnim> USE_ANIMATION_STREAM_CODEC = ByteBufCodecs.idMapper(ByIdMap.continuous(Enum::ordinal, UseAnim.values(), ByIdMap.OutOfBoundsStrategy.ZERO), Enum::ordinal);
}
