package com.junethewoods.variants.common.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class VariantFoods {
    public static final Food glowstone_pot = new Food.Builder().hunger(1).saturation(1).effect(() -> new EffectInstance(Effects.GLOWING, 200, 0), 1.0F).build();
    public static final Food lava_bowl = new Food.Builder().hunger(1).saturation(1).effect(() ->
            new EffectInstance(Effects.INSTANT_DAMAGE, 100, 1), 1.0F).build();
    public static final Food standard_bowl = new Food.Builder().hunger(4).saturation(5).build();
    public static final Food water_bowl = new Food.Builder().hunger(1).saturation(1).effect(() ->
            new EffectInstance(Effects.WATER_BREATHING, 100, 0), 1.0F).build();

}
