package melonystudios.revaried.item;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.component.RVDataComponents;
import melonystudios.revaried.fluid.RVFluids;
import melonystudios.revaried.item.custom.CompatItem;
import melonystudios.revaried.item.custom.SpawnerMinecartItem;
import melonystudios.revaried.item.custom.food.BucketFoodItem;
import melonystudios.revaried.item.custom.food.PlaceableBucketFoodItem;
import melonystudios.revaried.item.custom.food.RVFoods;
import melonystudios.revaried.misc.RVJukeboxSongs;
import melonystudios.revaried.util.ModTarget;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RVItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Revaried.MOD_ID);

    // Blocks

    // Items
    public static final DeferredItem<Item> WHITE_SHULKER_SHELL = ITEMS.register("white_shulker_shell", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INNO_SHULKER_SHELL = ITEMS.register("inno_shulker_shell", () -> new CompatItem(ModTarget.F10_ELEMENTS, new Item.Properties()));
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
    public static final DeferredItem<Item> MUSHROOM_STEW_BUCKET = ITEMS.register("mushroom_stew_bucket", () -> new PlaceableBucketFoodItem(RVFluids.MUSHROOM_STEW.get(), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).food(RVFoods.MUSHROOM_STEW_BUCKET).component(RVDataComponents.USE_ANIMATION, UseAnim.DRINK)));
    public static final DeferredItem<Item> BEETROOT_SOUP_BUCKET = ITEMS.register("beetroot_soup_bucket", () -> new BucketFoodItem(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).food(RVFoods.BEETROOT_SOUP_BUCKET).component(RVDataComponents.USE_ANIMATION, UseAnim.DRINK)));
    public static final DeferredItem<Item> RABBIT_STEW_BUCKET = ITEMS.register("rabbit_stew_bucket", () -> new BucketFoodItem(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).food(RVFoods.RABBIT_STEW_BUCKET).component(RVDataComponents.USE_ANIMATION, UseAnim.DRINK)));
    public static final DeferredItem<Item> SUSPICIOUS_STEW_BUCKET = ITEMS.register("suspicious_stew_bucket", () -> new BucketFoodItem(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).food(RVFoods.SUSPICIOUS_STEW_BUCKET).component(RVDataComponents.USE_ANIMATION, UseAnim.DRINK)));
    public static final DeferredItem<Item> MELTING_BEET_SOUP_BUCKET = ITEMS.register("melting_beet_soup_bucket", () -> new BucketFoodItem(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).food(RVFoods.BEETROOT_SOUP_BUCKET).component(RVDataComponents.USE_ANIMATION, UseAnim.DRINK)));
    public static final DeferredItem<Item> FUNGI_STEW_BUCKET = ITEMS.register("fungi_stew_bucket", () -> new BucketFoodItem(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).food(RVFoods.MUSHROOM_STEW_BUCKET).component(RVDataComponents.USE_ANIMATION, UseAnim.DRINK)));
    public static final DeferredItem<Item> END_FUNGI_STEW_BUCKET = ITEMS.register("end_fungi_stew_bucket", () -> new BucketFoodItem(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).food(RVFoods.MUSHROOM_STEW_BUCKET).component(RVDataComponents.USE_ANIMATION, UseAnim.DRINK)));
    public static final DeferredItem<Item> SOUL_LAVA_BUCKET = ITEMS.register("soul_lava_bucket", () -> new BucketItem(RVFluids.SOUL_LAVA.get(), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

}
