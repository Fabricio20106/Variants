package com.fabricio.variants.compat;

import com.fabricio.variants.Variants;
import com.fabricio.variants.init.VariantsTab;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EditsStuffInit {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);

    public static final RegistryObject<Item> splash_soph_potion = items.register("splash_soph_potion", () ->
            new Item(new Item.Properties().food(new Food.Builder().hunger(6).saturation(3).build()).group(F10CompatTab.compat)));
    public static final RegistryObject<Item> purple_nugget = items.register("purple_nugget", () ->
            new Item(new Item.Properties().group(F10CompatTab.compat)));

}
