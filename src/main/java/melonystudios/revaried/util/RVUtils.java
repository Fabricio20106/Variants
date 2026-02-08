package melonystudios.revaried.util;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.component.RVDataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.ItemLike;

import static net.minecraft.client.renderer.item.ItemProperties.register;

public class RVUtils {
    public static final GameRules.Key<GameRules.BooleanValue> RULE_MUSHROOM_STEW_SOURCE_CONVERSION = GameRules.register(
            Revaried.MOD_PREFIX + "MushroomStewSourceConversion",
            GameRules.Category.UPDATES, GameRules.BooleanValue.create(false)
    );
    public static final GameRules.Key<GameRules.BooleanValue> RULE_SOUL_LAVA_SOURCE_CONVERSION = GameRules.register(
            Revaried.MOD_PREFIX + "SoulLavaSourceConversion",
            GameRules.Category.UPDATES, GameRules.BooleanValue.create(false)
    );

    /// Add properties for mob ids for spawner minecarts.
    public static void makeSpawnerMinecart(ItemLike item) {
        register(item.asItem(), mobID(), (stack, level, livEntity, seed) -> {
            CustomData spawnerData = stack.get(RVDataComponents.SPAWNER_DATA.get());
            if (spawnerData != null) {
                String entityID = spawnerData.copyTag().getCompound("SpawnData").getCompound("entity").getString("id");
                return switch (entityID) {
                    case "minecraft:zombie" -> 1;
                    case "minecraft:skeleton" -> 2;
                    case "minecraft:spider" -> 3;
                    case "minecraft:cave_spider" -> 4;
                    case "minecraft:silverfish" -> 5;
                    case "minecraft:blaze" -> 6;
                    case "minecraft:magma_cube" -> 7;
                    case "minecraft:pig" -> 8;
                    default -> 0;
                };
            }
            return 0;
        });
    }

    public static ResourceLocation mobID() {
        return Revaried.revaried("mob_id");
    }
}
