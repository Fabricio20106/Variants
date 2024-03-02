package com.junethewoods.variants.compat.jei;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.junethewoods.variants.item.VSWeaponry;
import com.junethewoods.variants.item.tool.VSArmors;
import com.junethewoods.variants.item.tool.VSTools;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VSAnvilRecipeMaker {
    public static final Logger LOGGER = LogManager.getLogger();

    public static List<Object> getAnvilRecipes(IVanillaRecipeFactory recipeFactory, IIngredientManager manager) {
        List<Object> recipes = new ArrayList<>();
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            getVSRepairRecipes(recipes, recipeFactory);
        } catch (RuntimeException exception) {
            LOGGER.error("Variants: Failed to create anvil repair recipes.", exception);
        }
        stopwatch.stop();
        LOGGER.debug("Variants: Registered Variants' anvil repair recipes in {}", stopwatch);
        return recipes;
    }

    private static void getVSRepairRecipes(List<Object> recipes, IVanillaRecipeFactory recipeFactory) {
        Map<Ingredient, List<ItemStack>> items = Maps.newHashMap();

        items.put(VSTools.ANDESITE.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.ANDESITE_SWORD.get()), new ItemStack(VSWeaponry.ANDESITE_PICKAXE.get()), new ItemStack(VSWeaponry.ANDESITE_SHOVEL.get()),
                new ItemStack(VSWeaponry.ANDESITE_AXE.get()), new ItemStack(VSWeaponry.ANDESITE_HOE.get())));

        items.put(VSTools.DIORITE.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.DIORITE_SWORD.get()), new ItemStack(VSWeaponry.DIORITE_PICKAXE.get()), new ItemStack(VSWeaponry.DIORITE_SHOVEL.get()),
                new ItemStack(VSWeaponry.DIORITE_AXE.get()), new ItemStack(VSWeaponry.DIORITE_HOE.get())));

        items.put(VSTools.GRANITE.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.GRANITE_SWORD.get()), new ItemStack(VSWeaponry.GRANITE_PICKAXE.get()), new ItemStack(VSWeaponry.GRANITE_SHOVEL.get()),
                new ItemStack(VSWeaponry.GRANITE_AXE.get()), new ItemStack(VSWeaponry.GRANITE_HOE.get())));

        items.put(VSTools.END_STONE.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.END_STONE_SWORD.get()), new ItemStack(VSWeaponry.END_STONE_PICKAXE.get()), new ItemStack(VSWeaponry.END_STONE_SHOVEL.get()),
                new ItemStack(VSWeaponry.END_STONE_AXE.get()), new ItemStack(VSWeaponry.END_STONE_HOE.get())));

        items.put(VSTools.QUARTZ.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.QUARTZ_SWORD.get()), new ItemStack(VSWeaponry.QUARTZ_PICKAXE.get()), new ItemStack(VSWeaponry.QUARTZ_SHOVEL.get()),
                new ItemStack(VSWeaponry.QUARTZ_AXE.get()), new ItemStack(VSWeaponry.QUARTZ_HOE.get()), new ItemStack(VSWeaponry.QUARTZ_HELMET.get()), new ItemStack(VSWeaponry.QUARTZ_CHESTPLATE.get()), new ItemStack(VSWeaponry.QUARTZ_LEGGINGS.get()),
                new ItemStack(VSWeaponry.QUARTZ_BOOTS.get())));

        items.put(VSArmors.EMPTY_SLOT.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_HELMET.get()), new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_CHESTPLATE.get()),
                new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_LEGGINGS.get()), new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_BOOTS.get()), new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_SHIELD.get())));

        items.put(VSArmors.PHANTOM_MEMBRANE.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.PHANTOM_MEMBRANE_TUNIC.get())));
        items.put(VSArmors.RABBIT_HIDE.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.RABBIT_HIDE_TUNIC.get())));
        items.put(VSArmors.WOOL.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.WOOL_SWEATER.get())));

        items.put(VSTools.MAGMA.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.MAGMA_SWORD.get())));
        items.put(VSTools.AMETHYST.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.AMETHYST_SWORD.get())));
        items.put(VSTools.COPPER.getRepairIngredient(), Lists.newArrayList(new ItemStack(VSWeaponry.COPPER_SWORD.get()), new ItemStack(VSWeaponry.COPPER_CHESTPLATE.get())));

        for (Map.Entry<Ingredient, List<ItemStack>> entry : items.entrySet()) {

            List<ItemStack> repairMaterials = Lists.newArrayList(
                    entry.getKey().getItems()
            );

            for (ItemStack repairIngredients : entry.getValue()) {
                ItemStack damagedStack1 = repairIngredients.copy();
                damagedStack1.setDamageValue(damagedStack1.getMaxDamage());
                ItemStack damagedStack2 = repairIngredients.copy();
                damagedStack2.setDamageValue(damagedStack2.getMaxDamage() * 3 / 4);
                ItemStack damagedStack3 = repairIngredients.copy();
                damagedStack3.setDamageValue(damagedStack3.getMaxDamage() * 2 / 4);

                if (!repairMaterials.isEmpty()) {
                    Object repairWithMaterial = recipeFactory.createAnvilRecipe(damagedStack1, repairMaterials, Collections.singletonList(damagedStack2));
                    recipes.add(repairWithMaterial);
                }
                Object repairWithSame = recipeFactory.createAnvilRecipe(damagedStack2, Collections.singletonList(damagedStack2), Collections.singletonList(damagedStack3));
                recipes.add(repairWithSame);
            }
        }
    }
}
