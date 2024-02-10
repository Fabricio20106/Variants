package com.junethewoods.variants.util.tab;

import com.junethewoods.variants.item.VSWeaponry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VSSweaterTab extends ItemGroup {
    public static final VSSweaterTab TAB = (VSSweaterTab) new VSSweaterTab("variants.infinity_sweaters").setBackgroundSuffix("item_search.png");

    public VSSweaterTab(String label) {
        super(label);
    }

    @Override
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
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("itemGroup.variants.infinity_sweaters").withStyle(Style.EMPTY.withColor(Color.fromRgb(0x31CCDD)));
    }
}
