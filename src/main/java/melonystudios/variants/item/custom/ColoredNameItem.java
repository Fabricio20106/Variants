package melonystudios.variants.item.custom;

import melonystudios.variants.util.VSStyles;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

public class ColoredNameItem extends Item {
    private final int nameColor;

    public ColoredNameItem(int nameColor, Properties properties) {
        super(properties);
        this.nameColor = nameColor;
    }

    @Override
    @Nonnull
    public ITextComponent getName(ItemStack stack) {
        return new TranslationTextComponent(this.getDescriptionId()).withStyle(VSStyles.getFromRGB(this.nameColor));
    }
}
