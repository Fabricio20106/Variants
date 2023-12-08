package com.junethewoods.variants.item.custom;

import net.minecraft.world.effect.MobEffect;

import java.util.function.Supplier;

public interface IPoisoningType {
    Supplier<MobEffect> getPoisoningEffect();
}
