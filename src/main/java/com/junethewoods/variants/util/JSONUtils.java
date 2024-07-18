package com.junethewoods.variants.util;

import com.google.gson.*;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;

import static com.junethewoods.variants.util.NBTUtils.*;

public class JSONUtils {
    public static void writeItemFromNBT(String compoundName, CompoundNBT propertiesTag, JsonObject propertiesObj) {
        JsonObject object = new JsonObject();
        CompoundNBT curativeTag = propertiesTag.getCompound(compoundName);
        object.addProperty("id", curativeTag.getString("id"));
        object.addProperty("Count", curativeTag.getByte("Count"));
        if (ForgeRegistries.ITEMS.containsKey(ResourceLocation.tryParse(curativeTag.getString("id")))) {
            CompoundNBT curativeItemTag = new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(curativeTag.getString("id")))).getTag();
            if (curativeItemTag != null) object.addProperty("tag", curativeItemTag.toString());
        }
        propertiesObj.add(compoundName, object);
    }

    public static void writeDamageSourceFromNBT(CompoundNBT propertiesTag, JsonObject propertiesObj) {
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

    public static void writeExplosionFromNBT(CompoundNBT propertiesTag, JsonObject propertiesObj) {
        propertiesObj.addProperty("radius", floatOrDefault("radius", propertiesTag, 0));
        propertiesObj.addProperty("create_fire", booleanOrDefault("create_fire", propertiesTag, false));
        writeDamageSourceFromNBT(propertiesTag, propertiesObj);
        propertiesObj.addProperty("mode", stringOrDefault("mode", propertiesTag, "none"));
        propertiesObj.add("pos", blockPosOrDefault("pos", propertiesTag, new int[] {0, 0, 0}));
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
            if (VSRegistries.STEW_BEHAVIOR.containsKey(ResourceLocation.tryParse(behaviorID))) {
                return VSRegistries.STEW_BEHAVIOR.getValue(new ResourceLocation(behaviorID));
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
}
