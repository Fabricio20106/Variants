package com.junethewoods.variants.common.util;

import com.junethewoods.variants.core.init.VSItems;
import com.junethewoods.variants.core.init.VSTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

public enum VSTools implements Tier {
    ANDESITE(1, 131, 4.0F, 1.0F, 5, () -> Ingredient.of(Blocks.ANDESITE)),
    END_STONE(1, 151, 4.0F, 1.0F, 5, () -> Ingredient.of(Blocks.END_STONE)),
    DIORITE(1, 131, 4.0F, 1.0F, 5, () -> Ingredient.of(Blocks.DIORITE)),
    GRANITE(2, 425, 4.0F, 1.0F, 5, () -> Ingredient.of(Blocks.GRANITE)),
    MAGMA(1, 131, 4.0F, 1.0F, 5, () -> Ingredient.of(Blocks.MAGMA_BLOCK)),
    EMERALD(3, 2031, 5.0F, 3.0F, 15, () -> Ingredient.of(Tags.Items.GEMS_EMERALD)),
    AMETHYST(2, 250, 6.0F, 2.0F, 17, () -> Ingredient.of(Tags.Items.GEMS_AMETHYST)),
    COPPER(2, 250, 6.0F, 2.0F, 14, () -> Ingredient.of(Tags.Items.INGOTS_COPPER)),
    QUARTZ(2, 350, 6.0F, 2.0F, 14, () -> Ingredient.of(Tags.Items.GEMS_QUARTZ)),

    // Wood tools
    OAK(0, 59, 2, 0, 15, () -> Ingredient.of(Items.OAK_PLANKS)),
    SPRUCE(0, 62, 2.2f, 0.5f, 15, () -> Ingredient.of(Items.SPRUCE_PLANKS)),
    BIRCH(0, 60, 2, 0.3f, 16, () -> Ingredient.of(Items.BIRCH_PLANKS)),
    JUNGLE(0, 60, 2.1f, 0.2f, 15, () -> Ingredient.of(Items.JUNGLE_PLANKS)),
    ACACIA(0, 61, 2.2f, 0.3f, 14, () -> Ingredient.of(Items.ACACIA_PLANKS)),
    DARK_OAK(0, 60, 2.3f, 0.2f, 15, () -> Ingredient.of(Items.DARK_OAK_PLANKS)),
    MANGROVE(0, 62, 2.2f, 0.1f, 14, () -> Ingredient.EMPTY),
    BAMBOO(0, 57, 1.9f, -0.1f, 16, () -> Ingredient.of(Items.BAMBOO)),
    CHERRY(0, 63, 2.4f, 0.5f, 14, () -> Ingredient.EMPTY),
    PAINTING(0, 60, 2.2f, 0.1f, 15, () -> Ingredient.of(VSItems.PAINTING_PLANKS.get())),
    CRIMSON(0, 64, 2.4f, 0.4f, 14, () -> Ingredient.of(Items.CRIMSON_PLANKS)),
    WARPED(0, 65, 2.5f, 0.5f, 13, () -> Ingredient.of(Items.WARPED_PLANKS)),
    ENDER(0, 68, 2.6f, 0.6f, 13, () -> Ingredient.of(VSItems.ENDER_PLANKS.get())),

    // Stone tools
    DEEPSLATE(1, 151, 3.75f, 1.5f, 5, () -> Ingredient.of(Items.COBBLED_DEEPSLATE)),
    SANDSTONE(1, 101, 4, 0.8f, 3, () -> Ingredient.of(Items.SANDSTONE)),
    RED_SANDSTONE(1, 101, 4, 0.8f, 3, () -> Ingredient.of(Items.RED_SANDSTONE)),
    CALCITE(1, 121, 4, 0.75f, 3, () -> Ingredient.of(Items.CALCITE)),
    TUFF(1, 121, 4, 1.5f, 6, () -> Ingredient.of(Items.TUFF)),
    BEDROCK(32, 16384, 4, 200000, 16384, () -> Ingredient.of(Items.BEDROCK)),
    OBSIDIAN(3, 2031, 3, 4, 3, () -> Ingredient.of(Items.OBSIDIAN)),
    CRYING_OBSIDIAN(3, 2031, 3, 4, 6, () -> Ingredient.of(Items.CRYING_OBSIDIAN)),
    NETHERRACK(0, 91, 2, 0, 2, () -> Ingredient.of(Items.NETHERRACK)),
    BASALT(1, 141, 4.25f, 1.25f, 3, () -> Ingredient.of(Items.BASALT)),
    BLACKSTONE(1, 151, 4f, 1.25f, 4, () -> Ingredient.of(Items.BLACKSTONE)),

    // Mineral tools
    REDSTONE(2, 350, 10, 0.5f, 8, () -> Ingredient.of(Tags.Items.DUSTS_REDSTONE)),
    PRISMARINE_CRYSTALS(3, 850, 3.5f, 2, 12, () -> Ingredient.of(Tags.Items.GEMS_PRISMARINE)),
    ELDER_PRISMARINE_CRYSTALS(3, 850, 3.5f, 2, 12, () -> Ingredient.of(VSItems.ELDER_PRISMARINE_CRYSTALS.get())),
    COAL(1, 200, 3, 1.5f, 6, () -> Ingredient.of(ItemTags.COALS)),
    EMPTY_SLOT(2, 450, 7, 3, 9, () -> Ingredient.of(VSTags.Items.INGOTS_EMPTY_SLOT));

    private final int harvestLevel;
    private final int durability;
    private final float miningSpeed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    VSTools(int harvestLevel, int durability, float miningSpeed, float attackDamageBonus, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.harvestLevel = harvestLevel;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getUses() {
        return this.durability;
    }

    public float getSpeed() {
        return this.miningSpeed;
    }

    public float getAttackDamageBonus() {
        return this.attackDamageBonus;
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