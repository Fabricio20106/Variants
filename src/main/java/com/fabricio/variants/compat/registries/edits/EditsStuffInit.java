package com.fabricio.variants.compat.registries.edits;

import com.fabricio.variants.Variants;
import com.fabricio.variants.compat.registries.F10ModsFoods;
import com.fabricio.variants.compat.registries.VariantsCompatTab;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EditsStuffInit {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);

    public static final RegistryObject<Item> splath_soph_potion = items.register("splath_soph_potion", () -> new Item(new Item.Properties().group(VariantsCompatTab.f10Compat).food(F10ModsFoods.soph_potion)));
}
