package melonystudios.variants.util;

import net.minecraft.item.DyeColor;
import net.minecraft.util.text.*;

import static net.minecraft.util.ColorHelper.PackedColor.*;

public class VSStyles {
    public static final int REVARIED_ACCENT_COLOR = 0xFFC55F;
    public static final Style REVARIED_ACCENT_COLOR_STYLE = Style.EMPTY.withColor(Color.fromRgb(REVARIED_ACCENT_COLOR));
    public static final Style FIREWORK_TITLES = Style.EMPTY.withColor(TextFormatting.DARK_AQUA).applyFormat(TextFormatting.BOLD);
    public static final Style DIAMOND = Style.EMPTY.withColor(Color.fromRgb(0x4AEDD9));
    public static final Style GLOWSTONE_DUST = Style.EMPTY.withColor(Color.fromRgb(0xFFBC5E));
    public static final Style EXPERIENCE = Style.EMPTY.withColor(Color.fromRgb(8453920));

    public static Style getFromBeaconBeamColor(DyeColor dyeColor) {
        return Style.EMPTY.withColor(Color.fromRgb(dyeColor.getColorValue()));
    }

    public static Style getFromRGB(int color) {
        return Style.EMPTY.withColor(Color.fromRgb(color));
    }

    // =-------------------= METHODS COPIED AND ADAPTED FROM MELLOW UI =-------------------=

    public static IFormattableTextComponent buildScreenTitle(String modName) {
        return new TranslationTextComponent("menu.variants.options.title",
                new TranslationTextComponent("menu.variants.options.mod", modName).withStyle(VSStyles.getFromRGB(REVARIED_ACCENT_COLOR).withBold(true)));
    }

    public static IFormattableTextComponent buildScreenSubtitle(String modName, ITextComponent subtitle) {
        return new TranslationTextComponent("menu.variants.options.subtitle",
                new TranslationTextComponent("menu.variants.options.mod", modName).withStyle(VSStyles.getFromRGB(REVARIED_ACCENT_COLOR).withBold(true)),
                new TranslationTextComponent("menu.variants.options.arrow").withStyle(VSStyles.getFromRGB(REVARIED_ACCENT_COLOR).withBold(true)),
                subtitle);
    }

    /// Darkens a color based on a darkening factor.
    /// @param color The color to be darkened.
    /// @param alpha The transparency to be applied on the color. Defaults to `1`.
    /// @param darkeningFactor How much to darken the color. Defaults to `0.25`.
    public static int darkenColor(int color, float alpha, float darkeningFactor) {
        float red = red(color) * darkeningFactor;
        float green = green(color) * darkeningFactor;
        float blue = blue(color) * darkeningFactor;
        return color((int) (alpha * 255), (int) red, (int) green, (int) blue);
    }
}
