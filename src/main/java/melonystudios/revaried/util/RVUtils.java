package melonystudios.revaried.util;

import melonystudios.revaried.Revaried;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ItemLike;

import static net.minecraft.client.renderer.item.ItemProperties.register;

public class RVUtils {
    /// Add properties for mob ids for spawner minecarts.
    public static void makeSpawnerMinecart(ItemLike item) {
        register(item.asItem(), mobID(), (stack, world, livEntity, seed) -> {
            CustomData entityData = stack.get(DataComponents.ENTITY_DATA);
            if (entityData != null) {
                String entityID = entityData.copyTag().getCompound("SpawnData").getCompound("entity").getString("id");
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
