package melonystudios.variants.util;

import com.google.gson.*;
import melonystudios.variants.Variants;
import melonystudios.variants.effect.VSEffectInstance;
import melonystudios.variants.consumable.ConsumeBehavior;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.StringUtils;

import static melonystudios.variants.util.Constants.TagTypes.*;
import static melonystudios.variants.util.NBTUtils.*;

public class JSONUtils {
    public static JsonObject writeDamageSourceToJSON(CompoundNBT propertiesTag, JsonObject propertiesObj) {
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
        propertiesObj.addProperty("amount", floatOrDefault("amount", propertiesTag, 0));
        return propertiesObj;
    }

    public static JsonObject writeExplosionToJSON(CompoundNBT propertiesTag, JsonObject propertiesObj) {
        propertiesObj.addProperty("radius", floatOrDefault("radius", propertiesTag, 0));
        propertiesObj.addProperty("create_fire", booleanOrDefault("create_fire", propertiesTag, false));
        writeDamageSourceToJSON(propertiesTag, propertiesObj);
        propertiesObj.addProperty("mode", stringOrDefault("mode", propertiesTag, "none"));
        JsonArray position = vec3OrDefault("position", propertiesTag);
        if (position != null) propertiesObj.add("position", position);
        return propertiesObj;
    }

    public static void writeEffectToJSON(VSEffectInstance instance, JsonArray effects) {
        JsonObject effectObj = new JsonObject();
        effectObj.addProperty("id", instance.getEffect().getRegistryName().toString());
        effectObj.addProperty("duration", instance.getDuration());
        if (instance.getAmplifier() > 0) effectObj.addProperty("amplifier", instance.getAmplifier());
        if (instance.isAmbient()) effectObj.addProperty("ambient", true);
        if (!instance.isVisible()) effectObj.addProperty("show_particle", false);
        if (!instance.showIcon()) effectObj.addProperty("show_icon", false);
        if (instance.isNoCounter()) effectObj.addProperty("no_counter", true);
        if (instance.getChance() < 1) effectObj.addProperty("chance", instance.getChance());
        effects.add(effectObj);
    }

    public static JsonArray vec3OrDefault(String name, CompoundNBT tag) {
        JsonArray array = new JsonArray();
        if (tag.contains(name, LIST)) {
            ListNBT doubleList = tag.getList(name, DOUBLE);
            if (doubleList.size() == 3) {
                array.add(doubleList.getDouble(0));
                array.add(doubleList.getDouble(1));
                array.add(doubleList.getDouble(2));
                return array;
            }
        }
        return null;
    }

    public static ConsumeBehavior convertToBehavior(JsonElement element, String objectName) {
        if (element.isJsonPrimitive()) {
            String behaviorID = element.getAsString();
            if (RVRegistries.CONSUME_BEHAVIOR.containsKey(ResourceLocation.tryParse(behaviorID))) {
                return RVRegistries.CONSUME_BEHAVIOR.getValue(new ResourceLocation(behaviorID));
            } else {
                throw new JsonSyntaxException(new TranslationTextComponent("exception." + Variants.MOD_ID + ".unknown_string_not_behavior", objectName, behaviorID).getString());
            }
        } else {
            throw new JsonSyntaxException(new TranslationTextComponent("exception." + Variants.MOD_ID + ".json_primitive_not_behavior", objectName, getTranslatedType(element)).getString());
        }
    }

    public static ConsumeBehavior getAsBehavior(JsonObject object, String objectName) {
        if (object.has(objectName)) {
            return convertToBehavior(object.get(objectName), objectName);
        } else {
            throw new JsonSyntaxException(new TranslationTextComponent("exception." + Variants.MOD_ID + ".behavior_object_not_found", objectName).getString());
        }
    }

    public static TranslationTextComponent getTranslatedType(JsonElement element) {
        String abbreviation = StringUtils.abbreviateMiddle(String.valueOf(element), new TranslationTextComponent("exception.variants.ellipsis").getString(), 10);
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
}
