package com.junethewoods.variants.effect.source.manager;

import com.google.gson.*;
import com.junethewoods.variants.effect.source.DamageBehaviorSource;
import com.junethewoods.variants.effect.source.DeathMessageTypes;
import net.minecraft.util.*;

import java.lang.reflect.Type;

public class DamageSourceSerializer implements JsonDeserializer<DamageSource>, JsonSerializer<DamageSource> {
    @Override
    public DamageSource deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        if (object.has("death_message_type") && (object.get("death_message_type").getAsString().equals("direct_entity") || object.get("death_message_type").getAsString().equals("indirect_entity"))) {
            return new DamageManagerSource.EntityDMSource(element.getAsJsonObject(), null);
        }
        return new DamageManagerSource(element.getAsJsonObject());
    }

    @Override
    public JsonElement serialize(DamageSource source, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("message_id", source.msgId);
        object.addProperty("food_exhaustion", source.getFoodExhaustion());
        if (source instanceof DamageBehaviorSource) {
            object.addProperty("death_message_type", DeathMessageTypes.DEFAULT.getSerializedName());
            object.addProperty("scaling", ((DamageBehaviorSource) source).scaling.getSerializedName());
        } else if (source instanceof DamageManagerSource) {
            object.addProperty("death_message_type", DeathMessageTypes.DEFAULT.getSerializedName());
            object.addProperty("scaling", ((DamageManagerSource) source).scaling.getSerializedName());
        } else if (source instanceof EntityDamageSource) {
            if (source instanceof IndirectEntityDamageSource) {
                object.addProperty("death_message_type", DeathMessageTypes.INDIRECT_ENTITY.getSerializedName());
            } else {
                object.addProperty("death_message_type", DeathMessageTypes.DIRECT_ENTITY.getSerializedName());
            }
            object.addProperty("scaling", "when_caused_by_living_non_player");
        } else if (source instanceof BedExplosionDamageSource) {
            object.addProperty("death_message_type", DeathMessageTypes.INTENTIONAL_GAME_DESIGN.getSerializedName());
            object.addProperty("scaling", "always");
        }
        if (source.isExplosion()) object.addProperty("is_explosion", source.isExplosion());
        if (source.isProjectile()) object.addProperty("is_projectile", source.isProjectile());
        if (source.isMagic()) object.addProperty("is_magic", source.isMagic());
        if (source.isFire()) object.addProperty("is_fire", source.isFire());
        if (source instanceof EntityDamageSource) if (((EntityDamageSource) source).isThorns()) object.addProperty("is_thorns", ((EntityDamageSource) source).isThorns());
        if (source.isBypassArmor()) object.addProperty("bypasses_armor", source.isBypassArmor());
        if (source.isBypassInvul()) object.addProperty("bypasses_invulnerability", source.isBypassInvul());
        if (source.isBypassMagic()) object.addProperty("bypasses_magic", source.isBypassMagic());
        return object;
    }
}
