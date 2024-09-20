package melonystudios.variants.util;

import com.google.gson.*;
import melonystudios.variants.Variants;
import melonystudios.variants.item.custom.food.TagConfigurableFoodItem;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.custom.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.StringUtils;

import static melonystudios.variants.util.Constants.TagTypes.*;
import static melonystudios.variants.util.NBTUtils.*;

public class JSONUtils {
    public static void writeDamageSourceToJSON(CompoundNBT propertiesTag, JsonObject propertiesObj) {
        CompoundNBT sourceTag = propertiesTag.getCompound("source");
        if (propertiesTag.contains("source", COMPOUND)) {
            JsonObject sourceObject = new JsonObject();
            sourceObject.addProperty("message_id", stringOrDefault("message_id", sourceTag, "generic"));
            sourceObject.addProperty("food_exhaustion", floatOrDefault("food_exhaustion", sourceTag, 0));
            sourceObject.addProperty("scaling", stringOrDefault("scaling", sourceTag, "when_caused_by_living_non_player"));
            sourceObject.addProperty("death_message_type", stringOrDefault("death_message_type", sourceTag, "default"));
            sourceObject.addProperty("is_explosion", booleanOrDefault("is_explosion", sourceTag, false));
            sourceObject.addProperty("is_projectile", booleanOrDefault("is_projectile", sourceTag, false));
            sourceObject.addProperty("is_magic", booleanOrDefault("is_magic", sourceTag, false));
            sourceObject.addProperty("is_fire", booleanOrDefault("is_fire", sourceTag, false));
            sourceObject.addProperty("bypasses_armor", booleanOrDefault("bypasses_armor", sourceTag, false));
            sourceObject.addProperty("bypasses_invulnerability", booleanOrDefault("bypasses_invulnerability", sourceTag, false));
            sourceObject.addProperty("bypasses_magic", booleanOrDefault("bypasses_magic", sourceTag, false));
            propertiesObj.add("source", sourceObject);
        } else if (propertiesTag.contains("source", STRING)) {
            propertiesObj.addProperty("source", stringOrDefault("source", propertiesTag, "minecraft:generic"));
        }
        propertiesObj.addProperty("amount", floatOrDefault("amount", propertiesTag, 1));
    }

    public static void writeExplosionToJSON(CompoundNBT propertiesTag, JsonObject propertiesObj) {
        propertiesObj.addProperty("radius", floatOrDefault("radius", propertiesTag, 0));
        propertiesObj.addProperty("create_fire", booleanOrDefault("create_fire", propertiesTag, false));
        writeDamageSourceToJSON(propertiesTag, propertiesObj);
        propertiesObj.addProperty("mode", stringOrDefault("mode", propertiesTag, "none"));
        propertiesObj.add("pos", blockPosOrDefault("pos", propertiesTag, new int[] {0, 0, 0}));
    }

    private static void writeEffectToJSON(EffectInstance instance, JsonArray effectsList) {
        JsonObject effectObj = new JsonObject();
        effectObj.addProperty("id", instance.getEffect().getRegistryName().toString());
        effectObj.addProperty("duration", instance.getDuration());
        if (instance.getAmplifier() > 0) effectObj.addProperty("amplifier", instance.getAmplifier());
        if (instance.isAmbient()) effectObj.addProperty("ambient", true);
        if (!instance.isVisible()) effectObj.addProperty("show_particle", false);
        if (!instance.showIcon()) effectObj.addProperty("show_icon", false);
        if (instance.isNoCounter()) effectObj.addProperty("no_counter", true);
        effectsList.add(effectObj);
    }

    public static JsonArray blockPosOrDefault(String name, CompoundNBT tag, int[] fallback) {
        JsonArray array = new JsonArray();
        if (tag.contains(name, INTEGER_ARRAY)) {
            int[] intArray = tag.getIntArray(name);
            if (intArray.length == 3) {
                array.add(intArray[0]);
                array.add(intArray[1]);
                array.add(intArray[2]);
                return array;
            } else {
                array.add(fallback[0]);
                array.add(fallback[1]);
                array.add(fallback[2]);
            }
        }
        return array;
    }

    public static StewBehavior convertToBehavior(JsonElement element, String objectName) {
        if (element.isJsonPrimitive()) {
            String behaviorID = element.getAsString();
            if (VSRegistries.CONSUME_BEHAVIOR.containsKey(ResourceLocation.tryParse(behaviorID))) {
                return VSRegistries.CONSUME_BEHAVIOR.getValue(new ResourceLocation(behaviorID));
            } else {
                throw new JsonSyntaxException(new TranslationTextComponent("exception." + Variants.MOD_ID + ".unknown_string_not_behavior", objectName, behaviorID).getString());
            }
        } else {
            throw new JsonSyntaxException(new TranslationTextComponent("exception." + Variants.MOD_ID + ".json_primitive_not_behavior", objectName, getTranslatedType(element)).getString());
        }
    }

    public static StewBehavior getAsBehavior(JsonObject object, String objectName) {
        if (object.has(objectName)) {
            return convertToBehavior(object.get(objectName), objectName);
        } else {
            throw new JsonSyntaxException(new TranslationTextComponent("exception." + Variants.MOD_ID + ".behavior_object_not_found", objectName).getString());
        }
    }

    public static TranslationTextComponent getTranslatedType(JsonElement element) {
        String abbreviation = StringUtils.abbreviateMiddle(String.valueOf(element), "...", 10);
        String template = "exception.variants.json_primitive.";

        if (element == null) {
            return new TranslationTextComponent(template + "null");
        } else if (element.isJsonNull()) {
            return new TranslationTextComponent(template + "json_null");
        } else if (element.isJsonArray()) {
            return new TranslationTextComponent(template + "array", abbreviation);
        } else if (element.isJsonObject()) {
            return new TranslationTextComponent(template + "object", abbreviation);
        } else {
            if (element.isJsonPrimitive()) {
                JsonPrimitive primitive = element.getAsJsonPrimitive();
                if (primitive.isNumber()) return new TranslationTextComponent(template + "number", abbreviation);
                if (primitive.isBoolean()) return new TranslationTextComponent(template + "boolean", abbreviation);
            }

            return new TranslationTextComponent(template + "entire_object", element);
        }
    }

    public static void saveBehaviorToJSON(TagConfigurableFoodItem tcfItem, StewBehavior behavior, JsonObject behaviorObj, CompoundNBT behaviorTag) {
        // Behaviors
        if (behavior instanceof ApplyMobEffectsBehavior) {
            ApplyMobEffectsBehavior applyEffectsBehavior = (ApplyMobEffectsBehavior) tcfItem.getBehavior();
            JsonArray effectsList = new JsonArray();
            for (EffectInstance instance : applyEffectsBehavior.getEffects()) {
                writeEffectToJSON(instance, effectsList);
            }
            behaviorObj.add("effects", effectsList);
        } else if (behavior instanceof ClearMobEffectsBehavior) {
            ClearMobEffectsBehavior clearEffectsBehavior = (ClearMobEffectsBehavior) tcfItem.getBehavior();
            JsonObject curativeObject = new JsonObject();
            curativeObject.addProperty("id", clearEffectsBehavior.getCurativeItem().getItem().getRegistryName().toString());
            if (clearEffectsBehavior.getCurativeItem().getCount() != 1) curativeObject.addProperty("count", clearEffectsBehavior.getCurativeItem().getCount());
            if (clearEffectsBehavior.getCurativeItem().getTag() != null) curativeObject.addProperty("components", clearEffectsBehavior.getCurativeItem().getTag().toString());
            behaviorObj.add("curative_item", curativeObject);
        } else if (behavior instanceof DamageEntityBehavior) {
            JSONUtils.writeDamageSourceToJSON(behaviorTag, behaviorObj);
        } else if (behavior instanceof ExplodeBehavior) {
            JSONUtils.writeExplosionToJSON(behaviorTag, behaviorObj);
        } else if (behavior instanceof IgniteBehavior) {
            IgniteBehavior igniteBehavior = (IgniteBehavior) tcfItem.getBehavior();
            behaviorObj.addProperty("ticks_on_fire", igniteBehavior.getTicksOnFire());
        } else if (behavior instanceof AddExperienceBehavior) {
            AddExperienceBehavior addExperienceBehavior = (AddExperienceBehavior) tcfItem.getBehavior();
            behaviorObj.addProperty("amount", addExperienceBehavior.getExperienceAmount());
            behaviorObj.addProperty("levels", addExperienceBehavior.addsLevels());
        } else if (behavior instanceof TeleportEntityBehavior) {
            TeleportEntityBehavior teleportBehavior = (TeleportEntityBehavior) tcfItem.getBehavior();
            if (teleportBehavior.randomlyTeleports()) {
                behaviorObj.addProperty("random_teleport", true);
                behaviorObj.addProperty("teleport_diameter", teleportBehavior.getTeleportDiameter());
            } else {
                behaviorObj.addProperty("random_teleport", false);
                JsonArray posArray = new JsonArray();
                posArray.add(teleportBehavior.getTeleportPosition().getX());
                posArray.add(teleportBehavior.getTeleportPosition().getY());
                posArray.add(teleportBehavior.getTeleportPosition().getZ());
                behaviorObj.add("teleport_position", posArray);
            }
        } else if (behavior instanceof RemoveEffectsBehavior) {
            RemoveEffectsBehavior removeEffectsBehavior = (RemoveEffectsBehavior) tcfItem.getBehavior();
            JsonArray effectArray = new JsonArray();
            for (Effect effect : removeEffectsBehavior.getEffectsToRemove()) effectArray.add(effect.getRegistryName().toString());
            behaviorObj.add("effects", effectArray);
        }
    }
}
