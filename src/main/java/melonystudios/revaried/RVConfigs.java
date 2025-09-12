package melonystudios.revaried;

import net.neoforged.neoforge.common.ModConfigSpec;

public class RVConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue POPULATE_SPAWNER_MINECARTS = BUILDER.comment("Whether to populate all spawner minecart variants on Revaried's creative tab.").translation("config.revaried.populate_spawner_minecarts").define("item.populateSpawnerMinecarts", false);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
