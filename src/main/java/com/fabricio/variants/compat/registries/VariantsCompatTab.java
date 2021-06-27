package com.fabricio.variants.compat.registries;

import com.fabricio.variants.compat.CompatItem;
import com.fabricio.variants.compat.Edits;
import com.fabricio.variants.compat.registries.edits.EditsStuffInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.ModList;

public class VariantsCompatTab extends ItemGroup {
    public static final VariantsCompatTab f10Compat = new VariantsCompatTab(ItemGroup.GROUPS.length, "f10ModsCompat");
    //public static final VariantsCompatTab variant = new VariantsCompatTab(ItemGroup.GROUPS.length, "variantsCompat");

    public VariantsCompatTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(EditsStuffInit.splash_soph_potion.get());
    }

    @Override
    public void fill(NonNullList<ItemStack> items) {
        EditsStuffInit.items.getEntries().stream().filter(object -> {
            Item item = object.get();
            return !(item instanceof CompatItem) || (ModList.get().isLoaded("edits") && item instanceof Edits);
        }).forEach(entry -> entry.get().fillItemGroup(this, items));
    }
}
