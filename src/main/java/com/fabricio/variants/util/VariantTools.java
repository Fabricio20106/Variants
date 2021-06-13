package com.fabricio.variants.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum VariantTools implements IItemTier {
    ANDESITE(1, 131, 4.0F, 1.0F, 5, () -> {
        return Ingredient.of(Blocks.ANDESITE);
    }),
    DIORITE(1, 131, 4.0F, 1.0F, 5, () -> {
        return Ingredient.of(Blocks.DIORITE);
    }),
    GRANITE(1, 131, 4.0F, 1.0F, 5, () -> {
        return Ingredient.of(Blocks.GRANITE);
    }),
    EMERALD(3, 2031, 5.0F, 3.0F, 15, () -> {
        return Ingredient.of(Items.EMERALD);
    }),
    AMETHYST(2, 250, 6.0F, 2.0F, 14, () ->
            Ingredient.EMPTY),
    COPPER(2, 250, 6.0F, 2.0F, 14, () ->
            Ingredient.EMPTY);

    public final int level;
    public final int uses;
    public final float speed;
    public final float damage;
    public final int enchantmentValue;
    public final LazyValue<Ingredient> repairIngredient;

    VariantTools(int p_i48458_3_, int p_i48458_4_, float p_i48458_5_, float p_i48458_6_, int p_i48458_7_, Supplier<Ingredient> p_i48458_8_) {
        this.level = p_i48458_3_;
        this.uses = p_i48458_4_;
        this.speed = p_i48458_5_;
        this.damage = p_i48458_6_;
        this.enchantmentValue = p_i48458_7_;
        this.repairIngredient = new LazyValue<>(p_i48458_8_);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}