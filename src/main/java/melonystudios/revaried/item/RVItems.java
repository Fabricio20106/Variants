package melonystudios.revaried.item;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.item.custom.CompatItem;
import melonystudios.revaried.item.custom.SpawnerMinecartItem;
import melonystudios.revaried.misc.RVJukeboxSongs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RVItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Revaried.MOD_ID);

    // Blocks

    // Items
    public static final DeferredItem<Item> WHITE_SHULKER_SHELL = ITEMS.register("white_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INNO_SHULKER_SHELL = ITEMS.register("inno_shulker_shell", () -> new CompatItem("F10 Elements", new Item.Properties()));
    public static final DeferredItem<Item> ORANGE_SHULKER_SHELL = ITEMS.register("orange_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MAGENTA_SHULKER_SHELL = ITEMS.register("magenta_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_BLUE_SHULKER_SHELL = ITEMS.register("light_blue_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GLOW_BLACK_SHULKER_SHELL = ITEMS.register("glow_black_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> YELLOW_SHULKER_SHELL = ITEMS.register("yellow_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIME_SHULKER_SHELL = ITEMS.register("lime_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PINK_SHULKER_SHELL = ITEMS.register("pink_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHULKER_SHELL = ITEMS.register("gray_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_GRAY_SHULKER_SHELL = ITEMS.register("light_gray_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CYAN_SHULKER_SHELL = ITEMS.register("cyan_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PURPLE_SHULKER_SHELL = ITEMS.register("purple_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLUE_SHULKER_SHELL = ITEMS.register("blue_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BROWN_SHULKER_SHELL = ITEMS.register("brown_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GREEN_SHULKER_SHELL = ITEMS.register("green_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RED_SHULKER_SHELL = ITEMS.register("red_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLACK_SHULKER_SHELL = ITEMS.register("black_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SHULKER_SPECTRUM_ICON = ITEMS.register("shulker_spectrum_icon", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final DeferredItem<Item> MUSIC_DISC_DOG = ITEMS.register("music_disc_dog", () -> new Item(new Item.Properties().jukeboxPlayable(RVJukeboxSongs.DOG).rarity(Rarity.UNCOMMON).stacksTo(1)));
    public static final DeferredItem<Item> SPAWNER_MINECART = ITEMS.register("spawner_minecart", () -> new SpawnerMinecartItem(new Item.Properties().stacksTo(1)));

}
