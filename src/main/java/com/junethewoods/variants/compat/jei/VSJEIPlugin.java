package com.junethewoods.variants.compat.jei;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.VSItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class VSJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return Variants.resourceLoc("jei_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(VSItems.ENCHANTED_KNOWLEDGE_BOOK.get(), (stack, context) -> {
            List<String> enchantmentNames = new ArrayList<>();
            ListNBT enchantments = EnchantedBookItem.getEnchantments(stack);
            for (int i = 0; i < enchantments.size(); ++i) {
                CompoundNBT tag = enchantments.getCompound(i);
                String id = tag.getString("id");
                Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(ResourceLocation.tryParse(id));
                if (enchantment != null) {
                    String enchantmentUid = enchantment.getDescriptionId() + ".lvl" + tag.getShort("lvl");
                    enchantmentNames.add(enchantmentUid);
                }
            }
            enchantmentNames.sort(null);
            return enchantmentNames.toString();
        });
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IIngredientManager manager = registration.getIngredientManager();
        IVanillaRecipeFactory recipeFactory = registration.getVanillaRecipeFactory();

        registration.addRecipes(VSAnvilRecipeMaker.getAnvilRecipes(recipeFactory, manager), VanillaRecipeCategoryUid.ANVIL);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(VSItems.SOUL_BREWING_STAND.get()), VanillaRecipeCategoryUid.BREWING);
    }
}
