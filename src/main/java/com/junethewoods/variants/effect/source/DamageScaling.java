package com.junethewoods.variants.effect.source;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public enum DamageScaling implements IStringSerializable {
    NEVER("never"),
    WHEN_CAUSED_BY_LIVING_NON_PLAYER("when_caused_by_living_non_player"),
    ALWAYS("always");

    private final String name;

    DamageScaling(String name) {
        this.name = name;
    }

    @Override
    @Nonnull
    public String getSerializedName() {
        return this.name;
    }
}
