package melonystudios.variants.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import melonystudios.variants.Variants;
import melonystudios.variants.util.damage.DamageSourceUtils;
import melonystudios.variants.util.damage.custom.DamageManagerSource;
import melonystudios.variants.util.damage.custom.EntityDamageManagerSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

public class JSONDeserializer {
    public static int nonNegativeInteger(JsonObject object, String objectName) {
        if (object.has(objectName)) {
            JsonElement element = object.get(objectName);
            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
                int count = element.getAsInt();
                if (count >= 0) {
                    return count;
                } else {
                    Variants.LOGGER.warn(new TranslationTextComponent("parser.variants.negative_count", objectName, count).getString());
                    return 0;
                }
            } else {
                throw new JsonSyntaxException(new TranslationTextComponent("parser.variants.invalid_integer", objectName, JSONUtils.getTranslatedType(element)).getString());
            }
        }
        throw new JsonSyntaxException(new TranslationTextComponent("parser.variants.missing_integer", objectName).getString());
    }

    public static ItemStack loadStack(String objectName, JsonObject object) throws JsonSyntaxException {
        ResourceLocation identifier;
        int count = 1;
        CompoundNBT tags = new CompoundNBT();

        JsonObject stackObject = object.get(objectName).getAsJsonObject();
        if (stackObject == null) throw new JsonSyntaxException(new TranslationTextComponent("item_parser.variants.missing_parent_object", objectName).getString());

        // "id" tag
        if (stackObject.has("id")) {
            JsonElement idElement = stackObject.get("id");
            if (idElement.isJsonPrimitive()) {
                ResourceLocation id = ResourceLocation.tryParse(idElement.getAsString());
                if (id != null && ForgeRegistries.ITEMS.containsKey(id)) {
                    identifier = id;
                } else {
                    throw new JsonSyntaxException(new TranslationTextComponent("item_parser.variants.invalid_item", id).getString());
                }
            } else {
                throw new JsonSyntaxException(new TranslationTextComponent("item_parser.variants.invalid_id", JSONUtils.getTranslatedType(idElement)).getString());
            }
        } else {
            throw new JsonSyntaxException(new TranslationTextComponent("item_parser.variants.missing_id", objectName).getString());
        }

        // "count" tag
        if (stackObject.has("count")) {
            JsonElement countElement = stackObject.get("count");
            if (countElement.isJsonPrimitive() && countElement.getAsJsonPrimitive().isNumber()) {
                int subCount = countElement.getAsInt();
                if (subCount >= 0) {
                    count = subCount;
                } else {
                    Variants.LOGGER.warn(new TranslationTextComponent("item_parser.variants.negative_count", subCount));
                }
            } else {
                throw new JsonSyntaxException(new TranslationTextComponent("item_parser.variants.invalid_count", JSONUtils.getTranslatedType(countElement)).getString());
            }
        }

        // "tags" tag
        if (stackObject.has("tags")) {
            JsonElement tagsElement = stackObject.get("tags");
            if (tagsElement.isJsonObject() || tagsElement.isJsonPrimitive()) {
                try {
                    tags = JsonToNBT.parseTag(tagsElement.toString());
                } catch (CommandSyntaxException exception) {
                    throw new JsonSyntaxException(new TranslationTextComponent("item_parser.variants.tag_parse_failed", tagsElement).getString());
                }
            } else {
                throw new JsonSyntaxException(new TranslationTextComponent("item_parser.variants.invalid_tags", JSONUtils.getTranslatedType(tagsElement)).getString());
            }
        }

        CompoundNBT tag = new CompoundNBT();
        tag.putString("id", identifier.toString());
        tag.putInt("count", count);
        if (!tags.isEmpty()) tag.put("tags", tags);

        return VSUtils.loadStack(tag);
    }

    public static float getFoodExhaustion(JsonObject object, float baseExhaustion) {
        if (object.has("food_exhaustion") && object.get("food_exhaustion").isJsonPrimitive()) {
            return object.get("food_exhaustion").getAsFloat();
        }
        return baseExhaustion;
    }

    public static void loadManagerEntityDamageSource(JsonObject object, EntityDamageManagerSource source) {
        loadDefaultDamageSource(object, source);
        setSourceParameter(object, "is_thorns", source::setThorns);
        source.deathMessageType = DamageSourceUtils.deathMessageType(object);
        source.scaling = DamageSourceUtils.damageScaling(object, source);
    }

    public static void loadManagerDamageSource(JsonObject object, DamageManagerSource source) {
        loadDefaultDamageSource(object, source);
        source.deathMessageType = DamageSourceUtils.deathMessageType(object);
        source.scaling = DamageSourceUtils.damageScaling(object, source);
    }

    public static void loadDefaultDamageSource(JsonObject object, DamageSource source) {
        setSourceParameter(object, "is_explosion", source::setExplosion);
        setSourceParameter(object, "is_projectile", source::setProjectile);
        setSourceParameter(object, "is_magic", source::setMagic);
        setSourceParameter(object, "is_fire", source::setIsFire);
        setSourceParameter(object, "bypasses_armor", source::bypassArmor);
        setSourceParameter(object, "bypasses_invulnerability", source::bypassInvul);
        setSourceParameter(object, "bypasses_magic", source::bypassMagic);
    }

    private static void setSourceParameter(JsonObject object, String objectName, Runnable runnable) {
        if (object.has(objectName) && object.get(objectName).isJsonPrimitive() && object.get(objectName).getAsJsonPrimitive().isBoolean()) {
            if (object.get(objectName).getAsBoolean()) runnable.run();
        }
    }
}
