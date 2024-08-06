package melonystudios.variants.util.tab;

import melonystudios.variants.item.VSWeaponry;
import melonystudios.variants.item.custom.armor.WoolArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class VSWeaponryTab extends ItemGroup {
    public static final VSWeaponryTab TAB = new VSWeaponryTab(ItemGroup.TABS.length, "variants.weapons");

    public VSWeaponryTab(int index, String label) {
        super(index, label);
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        return WoolArmorItem.pickRandomColor(new ItemStack(VSWeaponry.WOOL_SWEATER.get()));
    }
}
