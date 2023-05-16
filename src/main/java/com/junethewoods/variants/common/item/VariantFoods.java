package com.junethewoods.variants.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class VariantFoods {
    public static final FoodProperties GLOWSTONE_POT = new FoodProperties.Builder().nutrition(1).saturationMod(1).effect(() ->
            new MobEffectInstance(MobEffects.GLOWING, 200, 0), 1.0F).build();
    public static final FoodProperties LAVA_BOWL = new FoodProperties.Builder().nutrition(1).saturationMod(1).effect(() ->
            new MobEffectInstance(MobEffects.HARM, 100, 1), 1.0F).build();
    public static final FoodProperties STANDARD_BOWL = new FoodProperties.Builder().nutrition(4).saturationMod(5).build();
    public static final FoodProperties WATER_BOWL = new FoodProperties.Builder().nutrition(1).saturationMod(1).effect(() ->
            new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0), 1.0F).build();
}
