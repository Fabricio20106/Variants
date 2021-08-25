package com.junethewoods.variants.common.block;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.Tags;

public enum VariantDyeColor implements IStringSerializable {
    glow_black(16, "glow_black", 0x80ffc0, MaterialColor.SNOW, 0x80ffc0, 0x80ffc0);

    private static final VariantDyeColor[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(VariantDyeColor::getId)).toArray(VariantDyeColor[]::new);
    private static final Int2ObjectOpenHashMap<VariantDyeColor> BY_FIREWORK_COLOR = new Int2ObjectOpenHashMap<>(Arrays.stream(values()).collect(Collectors.toMap((color) -> color.fireworkColor, (color) -> color)));
    private final int id;
    private final String translationKey;
    private final MaterialColor mapColor;
    private final int colorValue;
    private final int swappedColorValue;
    private final float[] colorComponentValues;
    private final int fireworkColor;
    private final Tags.IOptionalNamedTag<Item> tag;
    private final int textColor;

    VariantDyeColor(int idIn, String translationKeyIn, int colorValueIn, MaterialColor mapColorIn, int fireworkColorIn, int textColorIn) {
        this.id = idIn;
        this.translationKey = translationKeyIn;
        this.colorValue = colorValueIn;
        this.mapColor = mapColorIn;
        this.textColor = textColorIn;
        int i = (colorValueIn & 16711680) >> 16;
        int j = (colorValueIn & '\uff00') >> 8;
        int k = (colorValueIn & 255) >> 0;
        this.swappedColorValue = k << 16 | j << 8 | i << 0;
        this.tag = ItemTags.createOptional(new net.minecraft.util.ResourceLocation("forge", "dyes/" + translationKeyIn));
        this.colorComponentValues = new float[]{(float)i / 255.0F, (float)j / 255.0F, (float)k / 255.0F};
        this.fireworkColor = fireworkColorIn;
    }

    public int getId() {
        return this.id;
    }

    public String getTranslationKey() {
        return this.translationKey;
    }

    /**
     * Gets an array containing 3 floats ranging from 0.0 to 1.0: the red, green, and blue components of the
     * corresponding color.
     */
    public float[] getColorComponentValues() {
        return this.colorComponentValues;
    }

    public MaterialColor getMapColor() {
        return this.mapColor;
    }

    public int getFireworkColor() {
        return this.fireworkColor;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public static VariantDyeColor byId(int colorId) {
        if (colorId < 0 || colorId >= VALUES.length) {
            colorId = 0;
        }

        return VALUES[colorId];
    }

    public static VariantDyeColor byTranslationKey(String translationKeyIn, VariantDyeColor fallback) {
        for(VariantDyeColor dyecolor : values()) {
            if (dyecolor.translationKey.equals(translationKeyIn)) {
                return dyecolor;
            }
        }

        return fallback;
    }

    @Nullable
    public static VariantDyeColor byFireworkColor(int fireworkColorIn) {
        return BY_FIREWORK_COLOR.get(fireworkColorIn);
    }

    public String toString() {
        return this.translationKey;
    }

    public String getString() {
        return this.translationKey;
    }

    public int getColorValue() {
        return colorValue;
    }

    public net.minecraftforge.common.Tags.IOptionalNamedTag<Item> getTag() {
        return tag;
    }

    @Nullable
    public static Enum<? extends Enum<?>> getColor(ItemStack stack) {
        if (stack.getItem() instanceof DyeItem)
            return ((DyeItem)stack.getItem()).getDyeColor();

        for (VariantDyeColor color : VALUES) {
            if (stack.getItem().isIn(color.getTag()))
                return color;
        }

        return null;
    }
}
