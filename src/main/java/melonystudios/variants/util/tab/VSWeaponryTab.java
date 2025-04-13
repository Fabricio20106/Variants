package melonystudios.variants.util.tab;

import melonystudios.variants.item.VSWeaponry;
import melonystudios.variants.item.custom.armor.DyeableArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Random;

public class VSWeaponryTab extends ItemGroup {
    public static final VSWeaponryTab TAB = new VSWeaponryTab(ItemGroup.TABS.length, "variants.weapons");
    private final Random random = new Random();

    public VSWeaponryTab(int index, String label) {
        super(index, label);
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        return DyeableArmorItem.pickRandomColor(new ItemStack(VSWeaponry.WOOL_SWEATER.get()), this.random);
    }
}
