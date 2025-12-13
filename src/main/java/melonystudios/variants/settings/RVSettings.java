package melonystudios.variants.settings;

import com.google.gson.*;
import melonystudios.variants.util.VSUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.lang.reflect.Type;

public class RVSettings {
    // Latest Revaried version. Update when I add, change or remove a setting.
    public int version = RVSettingsManager.SETTINGS_FILE_VERSION;

    // Items
    public boolean placeSpawnerWhenBreakingMinecart = true;
    public boolean showTagsWithAlt = false;
    public boolean lineBreaksOnTags = true;
    public int spyglassZoomLevel = 5;
    public int anvilCharacterLimit = 50;
    public boolean enchantableShears = true;
    public boolean enchantableShields = true;
    public boolean enchantableFlintAndSteel = true;

    // Creative tabs
    public boolean populateTagConfigurableFoodTags = false;
    public boolean populateExponentialStewsInTabs = true;
    public boolean populateStainedGlassBottlesInTabs = true;
    public boolean populateSpawnerMinecartsInTabs = false;
    public boolean populateWoolArmorColorsInTabs = true;
    public boolean populateWoolArmorDesignsInTabs = true;

    // Tooltips
    public boolean updatedEnchantmentTooltips = true;
    public boolean enchantmentTypesTooltip = false;
    public boolean updatedPotionTooltips = true;
    public boolean durationFactorTooltip = false;
    public boolean foodEffectsTooltip = true;
    public boolean updatedFireworkTooltips = true;
    public boolean horseArmorPointsTooltip = true;

    // Infinity sweaters
    public boolean infinitySweatersEnabled = false;
    public int infinitySweatersTabLength = 4096;
    public int infinitySweatersTabSpacing = 16;

    // World generation
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

    // Consume behaviors
    public double explosionRadiusUpperLimit = 128;
    public double soundPitchUpperLimit = 2;

    public static class Serializer implements JsonDeserializer<RVSettings>, JsonSerializer<RVSettings> {
        @Override
        public JsonElement serialize(RVSettings settings, Type sourceType, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("version", settings.version);

            // Items
            JsonObject items = new JsonObject();
            items.addProperty("place_spawner_when_breaking_minecart", settings.placeSpawnerWhenBreakingMinecart);
            items.addProperty("show_tags_with_alt", settings.showTagsWithAlt);
            items.addProperty("line_breaks_on_tags", settings.lineBreaksOnTags);
            items.addProperty("spyglass_zoom_level", settings.spyglassZoomLevel);
            items.addProperty("anvil_character_limit", settings.anvilCharacterLimit);
            items.addProperty("enchantable_shears", settings.enchantableShears);
            items.addProperty("enchantable_shields", settings.enchantableShields);
            items.addProperty("enchantable_flint_and_steel", settings.enchantableFlintAndSteel);

            // Creative tabs
            JsonObject creativeTabs = new JsonObject();
            creativeTabs.addProperty("tag_configurable_food_tags", settings.populateTagConfigurableFoodTags);
            creativeTabs.addProperty("exponential_stews", settings.populateExponentialStewsInTabs);
            creativeTabs.addProperty("stained_glass_bottles", settings.populateStainedGlassBottlesInTabs);
            creativeTabs.addProperty("spawner_minecarts", settings.populateSpawnerMinecartsInTabs);
            creativeTabs.addProperty("wool_armor_colors", settings.populateWoolArmorColorsInTabs);
            creativeTabs.addProperty("wool_armor_designs", settings.populateWoolArmorDesignsInTabs);
            items.add("creative_tabs", creativeTabs);

            // Tooltips
            JsonObject tooltips = new JsonObject();
            tooltips.addProperty("enchantments", settings.updatedEnchantmentTooltips);
            tooltips.addProperty("enchantment_types", settings.enchantmentTypesTooltip);
            tooltips.addProperty("potions", settings.updatedPotionTooltips);
            tooltips.addProperty("effect_duration_factor", settings.durationFactorTooltip);
            tooltips.addProperty("food_effects", settings.foodEffectsTooltip);
            tooltips.addProperty("fireworks", settings.updatedFireworkTooltips);
            tooltips.addProperty("horse_armor_points", settings.horseArmorPointsTooltip);
            items.add("tooltips", tooltips);

            // Infinity sweaters
            JsonObject infinitySweaters = new JsonObject();
            infinitySweaters.addProperty("enabled", settings.infinitySweatersEnabled);
            infinitySweaters.addProperty("tab_length", settings.infinitySweatersTabLength);
            infinitySweaters.addProperty("tab_spacing", settings.infinitySweatersTabSpacing);
            items.add("infinity_sweaters", infinitySweaters);
            object.add("items", items);

            // World generation
            JsonObject worldGeneration = new JsonObject();
            worldGeneration.addProperty("paintingwood_forest", settings.paintingwoodForest);
            worldGeneration.addProperty("azure_fields", settings.azureFields);
            worldGeneration.addProperty("flower_patches", settings.flowerPatches);
            worldGeneration.addProperty("crimson_wheat_patches", settings.crimsonWheatPatches);
            worldGeneration.addProperty("soul_carrot_patches", settings.soulCarrotPatches);
            worldGeneration.addProperty("warped_potato_patches", settings.warpedPotatoPatches);
            worldGeneration.addProperty("melting_beet_patches", settings.meltingBeetPatches);
            worldGeneration.addProperty("quartz_ore", settings.quartzOre);
            worldGeneration.addProperty("end_quartz_ore", settings.endQuartzOre);
            worldGeneration.addProperty("nether_coal_ore", settings.netherCoalOre);
            worldGeneration.addProperty("crystallized_magma_cream_ore", settings.crystallizedMagmaCreamOre);
            worldGeneration.addProperty("soul_lava_springs", settings.soulLavaSprings);
            worldGeneration.addProperty("end_caves_and_ravines", settings.endCavesAndRavines);
            worldGeneration.addProperty("substitute_the_end_biome_with", settings.substituteTheEndBiomeWith.toString());
            object.add("world_generation", worldGeneration);

            // Entities
            JsonObject entities = new JsonObject();
            entities.addProperty("fish_spawning", settings.fishSpawning);
            object.add("entities", entities);

            // Enchantments
            JsonObject enchantments = new JsonObject();
            enchantments.addProperty("quick_charge_max_level", MathHelper.clamp(settings.quickChargeMaxLevel, 1, 25));
            object.add("enchantments", enchantments);

            // Consume behaviors
            JsonObject behaviors = new JsonObject();
            behaviors.addProperty("explosion_radius_upper_limit", settings.explosionRadiusUpperLimit);
            behaviors.addProperty("sound_pitch_upper_limit", settings.soundPitchUpperLimit);
            object.add("consume_behaviors", behaviors);

            return object;
        }

        @Override
        public RVSettings deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (element.isJsonObject()) {
                JsonObject object = element.getAsJsonObject();
                RVSettings settings = new RVSettings();
                settings.version = object.get("version").getAsInt();

                // Items
                JsonObject items = object.get("items").getAsJsonObject();
                settings.placeSpawnerWhenBreakingMinecart = this.booleanValue(items, "place_spawner_when_breaking_minecart", true);
                settings.showTagsWithAlt = this.booleanValue(items, "show_tags_with_alt", false);
                settings.lineBreaksOnTags = this.booleanValue(items, "line_breaks_on_tags", true);
                settings.spyglassZoomLevel = MathHelper.clamp(this.integerValue(items, "spyglass_zoom_level", 5), 0, Integer.MAX_VALUE);
                settings.anvilCharacterLimit = MathHelper.clamp(this.integerValue(items, "anvil_character_limit", 50), 1, Integer.MAX_VALUE);
                settings.enchantableShears = this.booleanValue(items, "enchantable_shears", true);
                settings.enchantableShields = this.booleanValue(items, "enchantable_shields", true);
                settings.enchantableFlintAndSteel = this.booleanValue(items, "enchantable_flint_and_steel", true);

                // Creative tabs
                JsonObject creativeTabs = items.get("creative_tabs").getAsJsonObject();
                settings.populateTagConfigurableFoodTags = this.booleanValue(creativeTabs, "tag_configurable_food_tags", false);
                settings.populateExponentialStewsInTabs = this.booleanValue(creativeTabs, "exponential_stews", true);
                settings.populateStainedGlassBottlesInTabs = this.booleanValue(creativeTabs, "stained_glass_bottles", true);
                settings.populateSpawnerMinecartsInTabs = this.booleanValue(creativeTabs, "spawner_minecarts", false);
                settings.populateWoolArmorColorsInTabs = this.booleanValue(creativeTabs, "wool_armor_colors", true);
                settings.populateWoolArmorDesignsInTabs = this.booleanValue(creativeTabs, "wool_armor_designs", true);

                // Tooltips
                JsonObject tooltips = items.get("tooltips").getAsJsonObject();
                settings.updatedEnchantmentTooltips = this.booleanValue(tooltips, "enchantments", true);
                settings.enchantmentTypesTooltip = this.booleanValue(tooltips, "enchantment_types", false);
                settings.updatedPotionTooltips = this.booleanValue(tooltips, "potions", true);
                settings.durationFactorTooltip = this.booleanValue(tooltips, "effect_duration_factor", false);
                settings.foodEffectsTooltip = this.booleanValue(tooltips, "food_effects", true);
                settings.updatedFireworkTooltips = this.booleanValue(tooltips, "fireworks", true);
                settings.horseArmorPointsTooltip = this.booleanValue(tooltips, "horse_armor_points", true);

                // Infinity sweaters
                JsonObject infinitySweaters = items.get("infinity_sweaters").getAsJsonObject();
                settings.infinitySweatersEnabled = this.booleanValue(infinitySweaters, "enabled", false);
                settings.infinitySweatersTabLength = MathHelper.clamp(this.integerValue(infinitySweaters, "tab_length", 4096), 1, 16777215);
                settings.infinitySweatersTabSpacing = MathHelper.clamp(this.integerValue(infinitySweaters, "tab_spacing", 16), 1, 16777215);

                // World generation
                JsonObject worldGeneration = object.get("world_generation").getAsJsonObject();
                settings.paintingwoodForest = this.booleanValue(worldGeneration, "paintingwood_forest", true);
                settings.azureFields = this.booleanValue(worldGeneration, "azure_fields", true);
                settings.flowerPatches = this.booleanValue(worldGeneration, "flower_patches", true);
                settings.crimsonWheatPatches = this.booleanValue(worldGeneration, "crimson_wheat_patches", true);
                settings.soulCarrotPatches = this.booleanValue(worldGeneration, "soul_carrot_patches", true);
                settings.warpedPotatoPatches = this.booleanValue(worldGeneration, "warped_potato_patches", true);
                settings.meltingBeetPatches = this.booleanValue(worldGeneration, "melting_beet_patches", true);
                settings.quartzOre = this.booleanValue(worldGeneration, "quartz_ore", true);
                settings.endQuartzOre = this.booleanValue(worldGeneration, "end_quartz_ore", true);
                settings.netherCoalOre = this.booleanValue(worldGeneration, "nether_coal_ore", true);
                settings.crystallizedMagmaCreamOre = this.booleanValue(worldGeneration, "crystallized_magma_cream_ore", true);
                settings.soulLavaSprings = this.booleanValue(worldGeneration, "soul_lava_springs", true);
                settings.endCavesAndRavines = this.booleanValue(worldGeneration, "end_caves_and_ravines", true);
                settings.substituteTheEndBiomeWith = new ResourceLocation(this.stringValue(worldGeneration, "substitute_the_end_biome_with", "minecraft:the_end"));

                // Entities
                JsonObject entities = object.get("entities").getAsJsonObject();
                settings.fishSpawning = this.booleanValue(entities, "fish_spawning", true);

                // Enchantments
                JsonObject enchantments = object.get("enchantments").getAsJsonObject();
                settings.quickChargeMaxLevel = MathHelper.clamp(this.integerValue(enchantments, "quick_charge_max_level", 5), 1, 25);

                // Consume behaviors
                JsonObject behaviors = object.get("consume_behaviors").getAsJsonObject();
                settings.explosionRadiusUpperLimit = MathHelper.clamp(this.doubleValue(behaviors, "explosion_radius_upper_limit", 128), 0, 128);
                settings.soundPitchUpperLimit = MathHelper.clamp(this.doubleValue(behaviors, "sound_pitch_upper_limit", 2), 0, 2);

                RVSettingsManager.LOGGER.debug(VSUtils.translate("console.variants.settings.deserializing", "Deserialized settings file from JSON (version %s).", settings.version));
                return settings;
            } else {
                throw new JsonParseException(VSUtils.translate("exception.variants.settings_error.deserializing", "Unable to deserialize the settings file."));
            }
        }

        private boolean booleanValue(JsonObject object, String name, boolean fallback) {
            if (object.has(name) && object.get(name).isJsonPrimitive()) return object.get(name).getAsBoolean();
            else return fallback;
        }

        private int integerValue(JsonObject object, String name, int fallback) {
            if (object.has(name) && object.get(name).isJsonPrimitive()) return object.get(name).getAsInt();
            else return fallback;
        }

        private double doubleValue(JsonObject object, String name, double fallback) {
            if (object.has(name) && object.get(name).isJsonPrimitive()) return object.get(name).getAsDouble();
            else return fallback;
        }

        private String stringValue(JsonObject object, String name, String fallback) {
            if (object.has(name) && object.get(name).isJsonPrimitive()) return object.get(name).getAsString();
            else return fallback;
        }
    }
}
