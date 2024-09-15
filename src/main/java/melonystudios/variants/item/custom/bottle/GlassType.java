package melonystudios.variants.item.custom.bottle;

import melonystudios.variants.item.VSItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GlassType {
    public static final GlassType WHITE = new GlassType(VSItems.WHITE_STAINED_GLASS_BOTTLE.get(), "white", 1, true);
    public static final GlassType LIGHT_GRAY = new GlassType(VSItems.LIGHT_GRAY_STAINED_GLASS_BOTTLE.get(), "light_gray", 2, true);
    public static final GlassType GRAY = new GlassType(VSItems.GRAY_STAINED_GLASS_BOTTLE.get(), "gray", 3, true);
    public static final GlassType BLACK = new GlassType(VSItems.BLACK_STAINED_GLASS_BOTTLE.get(), "black", 4, true);
    public static final GlassType BROWN = new GlassType(VSItems.BROWN_STAINED_GLASS_BOTTLE.get(), "brown", 5, true);
    public static final GlassType RED = new GlassType(VSItems.RED_STAINED_GLASS_BOTTLE.get(), "red", 6, true);
    public static final GlassType ORANGE = new GlassType(VSItems.ORANGE_STAINED_GLASS_BOTTLE.get(), "orange", 7, true);
    public static final GlassType YELLOW = new GlassType(VSItems.YELLOW_STAINED_GLASS_BOTTLE.get(), "yellow", 8, true);
    public static final GlassType LIME = new GlassType(VSItems.LIME_STAINED_GLASS_BOTTLE.get(), "lime", 9, true);
    public static final GlassType GREEN = new GlassType(VSItems.GREEN_STAINED_GLASS_BOTTLE.get(), "green", 10, true);
    public static final GlassType CYAN = new GlassType(VSItems.CYAN_STAINED_GLASS_BOTTLE.get(), "cyan", 11, true);
    public static final GlassType LIGHT_BLUE = new GlassType(VSItems.LIGHT_BLUE_STAINED_GLASS_BOTTLE.get(), "light_blue", 12, true);
    public static final GlassType BLUE = new GlassType(VSItems.BLUE_STAINED_GLASS_BOTTLE.get(), "blue", 13, true);
    public static final GlassType PURPLE = new GlassType(VSItems.PURPLE_STAINED_GLASS_BOTTLE.get(), "purple", 14, true);
    public static final GlassType MAGENTA = new GlassType(VSItems.MAGENTA_STAINED_GLASS_BOTTLE.get(), "magenta", 15, true);
    public static final GlassType PINK = new GlassType(VSItems.PINK_STAINED_GLASS_BOTTLE.get(), "pink", 16, true);
    public static final GlassType GLOW_BLACK = new GlassType(VSItems.GLOW_BLACK_STAINED_GLASS_BOTTLE.get(), "glow_black", 17, true);
    public static final GlassType QUARTZ = new GlassType(VSItems.QUARTZ_GLASS_BOTTLE.get(), "quartz", 18, false);
    private final ItemStack bottleStack;
    private final String name;
    private final int textureIdentifier;
    private final boolean hasOverlay;

    public GlassType(ItemStack bottleStack, String name, int textureIdentifier, boolean hasOverlay) {
        this.bottleStack = bottleStack;
        this.name = name;
        this.textureIdentifier = textureIdentifier;
        this.hasOverlay = hasOverlay;
    }

    public GlassType(Item bottleItem, String name, int textureID, boolean hasOverlay) {
        this(new ItemStack(bottleItem), name, textureID, hasOverlay);
    }

    public ItemStack getBottle() {
        return this.bottleStack;
    }

    public String getName() {
        return this.name;
    }

    public int getTextureIdentifier() {
        return this.textureIdentifier;
    }

    public boolean hasOverlay() {
        return this.hasOverlay;
    }
}
