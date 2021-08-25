package com.junethewoods.variants.common.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum VariantTools implements IItemTier {
    andesite(1, 131, 4.0F, 1.0F, 5, () -> {
        return Ingredient.fromItems(Blocks.ANDESITE);
    }),
    end_stone(1, 151, 4.0F, 1.0F, 5, () -> {
        return Ingredient.fromItems(Blocks.END_STONE);
    }),
    diorite(1, 131, 4.0F, 1.0F, 5, () -> {
        return Ingredient.fromItems(Blocks.DIORITE);
    }),
    granite(2, 425, 4.0F, 1.0F, 5, () -> {
        return Ingredient.fromItems(Blocks.GRANITE);
    }),
    magma(1, 131, 4.0F, 1.0F, 5, () -> {
        return Ingredient.fromItems(Blocks.MAGMA_BLOCK);
    }),
    emerald(3, 2031, 5.0F, 3.0F, 15, () -> {
        return Ingredient.fromItems(Items.EMERALD);
    }),
    amethyst(2, 250, 6.0F, 2.0F, 14, () ->
            Ingredient.EMPTY),
    copper(2, 250, 6.0F, 2.0F, 14, () ->
        Ingredient.EMPTY),
    quartz(2, 350, 6.0F, 2.0F, 14, () -> {
        return Ingredient.fromItems(Items.QUARTZ);
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    VariantTools(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyValue<>(repairMaterialIn);
    }

    public int getMaxUses() {
        return this.maxUses;
    }

    public float getEfficiency() {
        return this.efficiency;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
}