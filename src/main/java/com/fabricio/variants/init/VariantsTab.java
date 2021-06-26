package com.fabricio.variants.init;

import com.fabricio.variants.compat.CompatItem;
import com.fabricio.variants.compat.Edits;
import com.fabricio.variants.compat.PhoenixIdeas;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.ModList;

public class VariantsTab extends ItemGroup {
    public static final VariantsTab variant = new VariantsTab(ItemGroup.GROUPS.length, "variants");

    public VariantsTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(StuffInit.cyan_shulker_shell.get());
    }

    @Override
    public void fill(NonNullList<ItemStack> items) {
        StuffInit.items.getEntries().stream().filter(object -> {
            Item item = object.get();
            return !(item instanceof CompatItem) || (ModList.get().isLoaded("edits") && item instanceof Edits);
        }).forEach(entry -> entry.get().fillItemGroup(this, items));
    }
}

