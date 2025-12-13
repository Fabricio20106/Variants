package melonystudios.variants.item.custom.bottle;

import melonystudios.variants.item.VSItems;
import melonystudios.variants.util.VSUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class GlassType {
    public static final GlassType WHITE = new GlassType(VSItems.WHITE_STAINED_GLASS_BOTTLE.get(), "white", 0, true);
    public static final GlassType LIGHT_GRAY = new GlassType(VSItems.LIGHT_GRAY_STAINED_GLASS_BOTTLE.get(), "light_gray", 1, true);
    public static final GlassType GRAY = new GlassType(VSItems.GRAY_STAINED_GLASS_BOTTLE.get(), "gray", 2, true);
    public static final GlassType BLACK = new GlassType(VSItems.BLACK_STAINED_GLASS_BOTTLE.get(), "black", 3, true);
    public static final GlassType BROWN = new GlassType(VSItems.BROWN_STAINED_GLASS_BOTTLE.get(), "brown", 4, true);
    public static final GlassType RED = new GlassType(VSItems.RED_STAINED_GLASS_BOTTLE.get(), "red", 5, true);
    public static final GlassType ORANGE = new GlassType(VSItems.ORANGE_STAINED_GLASS_BOTTLE.get(), "orange", 6, true);
    public static final GlassType YELLOW = new GlassType(VSItems.YELLOW_STAINED_GLASS_BOTTLE.get(), "yellow", 7, true);
    public static final GlassType LIME = new GlassType(VSItems.LIME_STAINED_GLASS_BOTTLE.get(), "lime", 8, true);
    public static final GlassType GREEN = new GlassType(VSItems.GREEN_STAINED_GLASS_BOTTLE.get(), "green", 9, true);
    public static final GlassType CYAN = new GlassType(VSItems.CYAN_STAINED_GLASS_BOTTLE.get(), "cyan", 10, true);
    public static final GlassType LIGHT_BLUE = new GlassType(VSItems.LIGHT_BLUE_STAINED_GLASS_BOTTLE.get(), "light_blue", 11, true);
    public static final GlassType BLUE = new GlassType(VSItems.BLUE_STAINED_GLASS_BOTTLE.get(), "blue", 12, true);
    public static final GlassType PURPLE = new GlassType(VSItems.PURPLE_STAINED_GLASS_BOTTLE.get(), "purple", 13, true);
    public static final GlassType MAGENTA = new GlassType(VSItems.MAGENTA_STAINED_GLASS_BOTTLE.get(), "magenta", 14, true);
    public static final GlassType PINK = new GlassType(VSItems.PINK_STAINED_GLASS_BOTTLE.get(), "pink", 15, true);
    public static final GlassType GLOW_BLACK = new GlassType(VSItems.GLOW_BLACK_STAINED_GLASS_BOTTLE.get(), "glow_black", 16, true);
    public static final GlassType QUARTZ = new GlassType(VSItems.QUARTZ_GLASS_BOTTLE.get(), "quartz", 17, false);
    private final ItemStack bottle;
    private final String name;
    private final int textureID;
    private final boolean hasOverlay;

    public GlassType(ItemStack bottleStack, String name, int textureID, boolean hasOverlay) {
        this.bottle = bottleStack;
        this.name = name;
        this.textureID = textureID;
        this.hasOverlay = hasOverlay;
    }

    public GlassType(Item bottleItem, String name, int textureID, boolean hasOverlay) {
        this(new ItemStack(bottleItem), name, textureID, hasOverlay);
    }

    public ItemStack bottle() {
        return this.bottle;
    }

    public String name() {
        return this.name;
    }

    public int textureID() {
        return this.textureID;
    }

    public boolean hasOverlay() {
        return this.hasOverlay;
    }

    public static int textureIDFromBottle(ItemStack bottleStack) {
        if (bottleStack.getItem() instanceof StainedEmptyGlassBottleItem) {
            StainedEmptyGlassBottleItem bottleItem = (StainedEmptyGlassBottleItem) bottleStack.getItem();
            for (GlassType type : StainedFullGlassBottleItem.BOTTLES) {
                if (bottleItem.getColorName(bottleStack).getPath().equals(type.name())) return type.textureID();
            }
        }
        return 0;
    }

    public static void setBottle(ItemStack bottleStack, ItemStack filledStack) {
        filledStack.getOrCreateTag().putInt("texture_id", textureIDFromBottle(bottleStack));
        ItemStack copyStack = bottleStack.copy();
        copyStack.setCount(1);
        filledStack.getOrCreateTagElement("consumable").put("use_remainder", VSUtils.saveStack(copyStack, new CompoundNBT()));
    }
}
