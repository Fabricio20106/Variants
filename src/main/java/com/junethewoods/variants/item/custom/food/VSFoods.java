package com.junethewoods.variants.item.custom.food;

import net.minecraft.item.Food;

public class VSFoods {
    public static final Food SOPHIE_POTION = new Food.Builder().nutrition(6).saturationMod(0.5F).build(); // Now has correct saturation value of 3.
    public static final Food BERRY_POTS = new Food.Builder().nutrition(6).saturationMod(0.3F).build();
    public static final Food FLUID_BOWL = new Food.Builder().nutrition(4).saturationMod(1.25f).build();
}
