package melonystudios.variants.util.tab;

import melonystudios.variants.item.VSItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class VSTab extends ItemGroup {
    public static final VSTab TAB = new VSTab(ItemGroup.TABS.length, "variants.main");

    public VSTab(int index, String label) {
        super(index, label);
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        return new ItemStack(VSItems.CYAN_SHULKER_SHELL.get());
    }
}

