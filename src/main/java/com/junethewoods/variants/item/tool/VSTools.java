package com.junethewoods.variants.item.tool;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

import java.util.function.Supplier;

public class VSTools implements Tier {
    public static final VSTools ANDESITE = new VSTools(1, 131, 4f, 1f, 5, () -> Ingredient.of(VSTags.Items.CM_ANDESITE));
    public static final VSTools DIORITE = new VSTools(1, 131, 4f, 1f, 5, () -> Ingredient.of(VSTags.Items.CM_DIORITE));
    public static final VSTools GRANITE = new VSTools(1, 131, 4f, 1f, 5, () -> Ingredient.of(VSTags.Items.CM_GRANITE));
    public static final VSTools MAGMA = new VSTools(1, 250, 4f, 1f, 5, () -> Ingredient.of(VSTags.Items.CM_MAGMA_BLOCK));
    public static final VSTools END_STONE = new VSTools(1, 250, 4f, 1f, 5, () -> Ingredient.of(VSTags.Items.CM_END_STONE));
    public static final VSTools EMERALD = new VSTools(3, 1561, 5f, 3f, 15, () -> Ingredient.of(Tags.Items.GEMS_EMERALD));
    public static final VSTools QUARTZ = new VSTools(2, 350, 6f, 2f, 14, () -> Ingredient.of(Tags.Items.GEMS_QUARTZ));
    public static final VSTools AMETHYST = new VSTools(2, 250, 6f, 2f, 14, () -> Ingredient.EMPTY);
    public static final VSTools COPPER = new VSTools(2, 250, 6f, 2f, 14, () -> Ingredient.EMPTY);

    private final int harvestLevel;
    private final int durability;
    private final float harvestSpeed;
    private final float attackDamage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    public VSTools(int level, int durability, float efficiency, float damage, int enchValue, Supplier<Ingredient> repairIngredient) {
        this.harvestLevel = level;
        this.durability = durability;
        this.harvestSpeed = efficiency;
        this.attackDamage = damage;
        this.enchantmentValue = enchValue;
        this.repairIngredient = repairIngredient;
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