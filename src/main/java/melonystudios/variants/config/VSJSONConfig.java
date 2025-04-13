package melonystudios.variants.config;

import com.google.gson.*;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Type;

public class VSJSONConfig {
    // Latest Revaried version. Update when I add, change or remove a config.
    public int version = 1809;

    // World Generation
    public boolean paintingwoodForest = true;
    public boolean azureFields = true;
    public boolean flowerPatches = true;
    public boolean crimsonWheatPatches = true;
    public boolean soulCarrotPatches = true;
    public boolean warpedPotatoPatches = true;
    public boolean meltingBeetPatches = true;
    public boolean quartzOre = true;
    public boolean endQuartzOre = true;
    public boolean netherCoalOre = true;
    public boolean crystallizedMagmaCreamOre = true;
    public boolean soulLavaSprings = true;
    public boolean endCavesAndRavines = true;
    public ResourceLocation substituteTheEndBiomeWith = new ResourceLocation("the_end");

    // Entities
    public boolean fishSpawning = true;

    // Enchantments
    public int quickChargeMaxLevel = 5;

    // Consume Behaviors
    public double explosionRadiusUpperLimit = 128;
    public double soundPitchUpperLimit = 2;

    public static class Serializer implements JsonDeserializer<VSJSONConfig>, JsonSerializer<VSJSONConfig> {
        @Override
        public JsonElement serialize(VSJSONConfig config, Type sourceType, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("version", config.version);

            // World Generation
            JsonObject worldGeneration = new JsonObject();
            worldGeneration.addProperty("paintingwood_forest", config.paintingwoodForest);
            worldGeneration.addProperty("azure_fields", config.azureFields);
            worldGeneration.addProperty("flower_patches", config.flowerPatches);
            worldGeneration.addProperty("crimson_wheat_patches", config.crimsonWheatPatches);
            worldGeneration.addProperty("soul_carrot_patches", config.soulCarrotPatches);
            worldGeneration.addProperty("warped_potato_patches", config.warpedPotatoPatches);
            worldGeneration.addProperty("melting_beet_patches", config.meltingBeetPatches);
            worldGeneration.addProperty("quartz_ore", config.quartzOre);
            worldGeneration.addProperty("end_quartz_ore", config.endQuartzOre);
            worldGeneration.addProperty("nether_coal_ore", config.netherCoalOre);
            worldGeneration.addProperty("crystallized_magma_cream_ore", config.crystallizedMagmaCreamOre);
            worldGeneration.addProperty("soul_lava_springs", config.soulLavaSprings);
            worldGeneration.addProperty("end_caves_and_ravines", config.endCavesAndRavines);
            worldGeneration.addProperty("substitute_the_end_biome_with", config.substituteTheEndBiomeWith.toString());
            object.add("world_generation", worldGeneration);

            // Entities
            JsonObject entities = new JsonObject();
            entities.addProperty("fish_spawning", config.fishSpawning);
            object.add("entities", entities);

            // Enchantments
            JsonObject enchantments = new JsonObject();
            enchantments.addProperty("quick_charge_max_level", config.quickChargeMaxLevel);
            object.add("enchantments", enchantments);

            // Consume Behaviors
            JsonObject behaviors = new JsonObject();
            behaviors.addProperty("explosion_radius_upper_limit", config.explosionRadiusUpperLimit);
            behaviors.addProperty("sound_pitch_upper_limit", config.soundPitchUpperLimit);
            object.add("consume_behaviors", behaviors);

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
                config.paintingwoodForest = worldGeneration.get("paintingwood_forest").getAsBoolean();
                config.azureFields = worldGeneration.get("azure_fields").getAsBoolean();
                config.flowerPatches = worldGeneration.get("flower_patches").getAsBoolean();
                config.crimsonWheatPatches = worldGeneration.get("crimson_wheat_patches").getAsBoolean();
                config.soulCarrotPatches = worldGeneration.get("soul_carrot_patches").getAsBoolean();
                config.warpedPotatoPatches = worldGeneration.get("warped_potato_patches").getAsBoolean();
                config.meltingBeetPatches = worldGeneration.get("melting_beet_patches").getAsBoolean();
                config.quartzOre = worldGeneration.get("quartz_ore").getAsBoolean();
                config.endQuartzOre = worldGeneration.get("end_quartz_ore").getAsBoolean();
                config.netherCoalOre = worldGeneration.get("nether_coal_ore").getAsBoolean();
                config.crystallizedMagmaCreamOre = worldGeneration.get("crystallized_magma_cream_ore").getAsBoolean();
                config.soulLavaSprings = worldGeneration.get("soul_lava_springs").getAsBoolean();
                config.endCavesAndRavines = worldGeneration.get("end_caves_and_ravines").getAsBoolean();
                config.substituteTheEndBiomeWith = new ResourceLocation(worldGeneration.get("substitute_the_end_biome_with").getAsString());

                // Entities
                JsonObject entities = object.get("entities").getAsJsonObject();
                config.fishSpawning = entities.get("fish_spawning").getAsBoolean();

                // Enchantments
                JsonObject enchantments = object.get("enchantments").getAsJsonObject();
                config.quickChargeMaxLevel = enchantments.get("quick_charge_max_level").getAsInt();

                // Consume Behaviors
                JsonObject behaviors = object.get("consume_behaviors").getAsJsonObject();
                config.explosionRadiusUpperLimit = behaviors.get("explosion_radius_upper_limit").getAsDouble();
                config.soundPitchUpperLimit = behaviors.get("sound_pitch_upper_limit").getAsDouble();

                return config;
            }
            return new VSJSONConfig();
        }
    }
}
