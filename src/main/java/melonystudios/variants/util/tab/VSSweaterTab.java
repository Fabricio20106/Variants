package melonystudios.variants.util.tab;

import melonystudios.variants.item.VSWeaponry;
import melonystudios.variants.util.VSStyles;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class VSSweaterTab extends ItemGroup {
    public static final VSSweaterTab TAB = (VSSweaterTab) new VSSweaterTab("variants.infinity_sweaters").setBackgroundSuffix("item_search.png");

    public VSSweaterTab(String label) {
        super(label);
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        return new ItemStack(VSWeaponry.INFINITY_SWEATERS_TAB_ICON.get());
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public boolean showTitle() {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @Nonnull
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("itemGroup.variants.infinity_sweaters").withStyle(VSStyles.getFromRGB(0x31CCDD));
    }
}
