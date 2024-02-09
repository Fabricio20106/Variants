package com.junethewoods.variants.crafting;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.crafting.custom.SuspiciousStewBucketRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Variants.MOD_ID);

    public static final RegistryObject<SpecialRecipeSerializer<SuspiciousStewBucketRecipe>> SUSPICIOUS_STEW_BUCKET = RECIPE_TYPES.register("suspicious_stew_bucket_special_recipe", () -> new SpecialRecipeSerializer<>(SuspiciousStewBucketRecipe::new));
}
