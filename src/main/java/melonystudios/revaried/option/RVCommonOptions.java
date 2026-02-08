package melonystudios.revaried.option;

import net.neoforged.neoforge.common.ModConfigSpec;

public class RVCommonOptions {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Items
    public static final ModConfigSpec.BooleanValue PLACE_SPAWNER_WHEN_BREAKING_MINECART = BUILDER.comment("Whether the spawner in a spawner minecart should place itself when the minecart is broken.").translation("config.revaried.place_spawner_when_breaking_minecart").define("item.placeSpawnerWhenBreakingMinecart", true);

    // Creative tabs
    public static final ModConfigSpec.BooleanValue POPULATE_SPAWNER_MINECARTS = BUILDER.comment("Whether to populate all spawner minecart variants on Revaried's creative tab.").worldRestart().translation("config.revaried.populate_spawner_minecarts").define("creativeTab.populateSpawnerMinecarts", false);

    // Tooltips

    // Infinity sweaters

    // World generation

    // Entities

    // Enchantments

    public static final ModConfigSpec SPEC = BUILDER.build();
}
