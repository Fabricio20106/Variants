package melonystudios.variants.config;

import com.google.gson.*;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Type;

public class VSJSONConfig {
    // Latest Variants version. Update when I add, change or remove a config.
    public int version = 1805;

    // World Generation
    public boolean flowerPatches = true;
    public boolean quartzOre = true;
    public boolean endQuartzOre = true;
    public boolean soulLavaSprings = true;
    public boolean endCavesAndRavines = true;
    public ResourceLocation substituteTheEndBiomeWith = new ResourceLocation("the_end");

    public static class Serializer implements JsonDeserializer<VSJSONConfig>, JsonSerializer<VSJSONConfig> {
        @Override
        public JsonElement serialize(VSJSONConfig config, Type sourceType, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("version", config.version);

            // World Generation
            JsonObject worldGeneration = new JsonObject();
            worldGeneration.addProperty("flower_patches", config.flowerPatches);
            worldGeneration.addProperty("quartz_ore", config.quartzOre);
            worldGeneration.addProperty("end_quartz_ore", config.endQuartzOre);
            worldGeneration.addProperty("soul_lava_springs", config.soulLavaSprings);
            worldGeneration.addProperty("end_caves_and_ravines", config.endCavesAndRavines);
            worldGeneration.addProperty("substitute_the_end_biome_with", config.substituteTheEndBiomeWith.toString());
            object.add("world_generation", worldGeneration);

            return object;
        }

        @Override
        public VSJSONConfig deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (element.isJsonObject()) {
                JsonObject object = element.getAsJsonObject();
                VSJSONConfig config = new VSJSONConfig();
                config.version = object.get("version").getAsInt();

                // World Generation
                JsonObject worldGeneration = object.get("world_generation").getAsJsonObject();
                config.flowerPatches = worldGeneration.get("flower_patches").getAsBoolean();
                config.quartzOre = worldGeneration.get("quartz_ore").getAsBoolean();
                config.endQuartzOre = worldGeneration.get("end_quartz_ore").getAsBoolean();
                config.soulLavaSprings = worldGeneration.get("soul_lava_springs").getAsBoolean();
                config.endCavesAndRavines = worldGeneration.get("end_caves_and_ravines").getAsBoolean();
                config.substituteTheEndBiomeWith = new ResourceLocation(worldGeneration.get("substitute_the_end_biome_with").getAsString());
                return config;
            }
            return new VSJSONConfig();
        }
    }
}
