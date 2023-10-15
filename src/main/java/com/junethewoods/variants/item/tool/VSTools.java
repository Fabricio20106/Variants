package com.junethewoods.variants.item.tool;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

public enum VSTools implements IItemTier {
    ANDESITE(1, 131, 4f, 1f, 5, () -> Ingredient.of(VSTags.Items.CM_ANDESITE)),
    DIORITE(1, 131, 4f, 1f, 5, () -> Ingredient.of(VSTags.Items.CM_DIORITE)),
    GRANITE(1, 131, 4f, 1f, 5, () -> Ingredient.of(VSTags.Items.CM_GRANITE)),
    EMERALD(3, 1561, 5f, 3f, 15, () -> Ingredient.of(Tags.Items.GEMS_EMERALD)),
    MAGMA(1, 250, 4f, 1f, 5, () -> Ingredient.of(VSTags.Items.CM_MAGMA_BLOCK)),
    AMETHYST(2, 250, 6f, 2f, 14, () -> Ingredient.EMPTY),
    COPPER(2, 250, 6f, 2f, 14, () -> Ingredient.EMPTY);

    private final int harvestLevel;
    private final int durability;
    private final float harvestSpeed;
    private final float attackDamage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    VSTools(int level, int durability, float efficiency, float damage, int enchValue, Supplier<Ingredient> repairIngredient) {
        this.harvestLevel = level;
        this.durability = durability;
        this.harvestSpeed = efficiency;
        this.attackDamage = damage;
        this.enchantmentValue = enchValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public int getUses() {
        return this.durability;
    }

    public float getSpeed() {
        return this.harvestSpeed;
    }

    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    public int getLevel() {
        return this.harvestLevel;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}