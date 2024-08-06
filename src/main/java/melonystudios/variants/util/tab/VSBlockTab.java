package melonystudios.variants.util.tab;

import melonystudios.variants.item.VSItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class VSBlockTab extends ItemGroup {
    public static final VSBlockTab TAB = new VSBlockTab("variants.blocks");

    public VSBlockTab(String label) {
        super(label);
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        return new ItemStack(VSItems.PAINTING_DOOR_WANDERER.get());
    }
}
