package melonystudios.variants.util.damage.misc;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public enum DeathMessageTypes implements IStringSerializable {
    DEFAULT("default"),
    DIRECT_ENTITY("direct_entity"),
    INDIRECT_ENTITY("indirect_entity"),
    INTENTIONAL_GAME_DESIGN("intentional_game_design");

    private final String name;

    DeathMessageTypes(String name) {
        this.name = name;
    }

    @Override
    @Nonnull
    public String getSerializedName() {
        return this.name;
    }
}
