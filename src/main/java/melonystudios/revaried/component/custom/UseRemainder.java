package melonystudios.revaried.component.custom;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public record UseRemainder(ItemStack stack) {
    public static final Codec<UseRemainder> CODEC = ItemStack.CODEC.xmap(UseRemainder::new, UseRemainder::stack);
    public static final StreamCodec<RegistryFriendlyByteBuf, UseRemainder> STREAM_CODEC = ItemStack.STREAM_CODEC.map(UseRemainder::new, UseRemainder::stack);

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else {
            return other instanceof UseRemainder(ItemStack stack) && ItemStack.matches(this.stack(), stack);
        }
    }

    @Override
    public int hashCode() {
        return ItemStack.hashItemAndComponents(this.stack());
    }

    @Override
    @NotNull
    public String toString() {
        return "UseRemainder[item=" + this.stack() + "]";
    }
}
