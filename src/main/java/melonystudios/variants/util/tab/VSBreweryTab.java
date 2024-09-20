package melonystudios.variants.util.tab;

import melonystudios.variants.item.VSItems;
import melonystudios.variants.util.VSStyles;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class VSBreweryTab extends ItemGroup {
    @SuppressWarnings("deprecation")
    public static final VSBreweryTab TAB = (VSBreweryTab) new VSBreweryTab("variants.brewery").setBackgroundSuffix("item_search.png");

    public VSBreweryTab(String label) {
        super(label);
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        ItemStack stack = new ItemStack(VSItems.STAINED_EXPERIENCE_BOTTLE.get());
        stack.getOrCreateTag().putInt("texture_id", 16);
        return stack;
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
        return new TranslationTextComponent("itemGroup.variants.brewery").withStyle(VSStyles.getFromRGB(0x4BDEBA));
    }
}
