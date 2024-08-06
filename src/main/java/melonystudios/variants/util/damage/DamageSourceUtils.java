package melonystudios.variants.util.damage;

import com.google.common.collect.ImmutableList;
import com.google.gson.*;
import melonystudios.variants.util.damage.custom.DamageBehaviorSource;
import melonystudios.variants.util.damage.custom.DamageManagerSource;
import melonystudios.variants.util.damage.misc.DeathMessageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static melonystudios.variants.Variants.resourceLoc;
import static melonystudios.variants.util.VSUtils.minecraft;

public class DamageSourceUtils {
    public static Map<ResourceLocation, DamageSource> DATA_DRIVEN_SOURCES = new HashMap<>();
    public static List<ResourceLocation> VALID_DAMAGE_SOURCES = new ImmutableList.Builder<ResourceLocation>().add(minecraft("generic"), minecraft("in_fire"), minecraft("on_fire"), minecraft("lava"), minecraft("lightning_bolt"),
            minecraft("hot_floor"), minecraft("in_wall"), minecraft("fly_into_wall"), minecraft("cramming"), minecraft("drown"), minecraft("starve"), minecraft("fall"), minecraft("magic"), minecraft("wither"),
            minecraft("falling_anvil"), minecraft("out_of_world"), minecraft("falling_block"), minecraft("dragon_breath"), minecraft("dry_out"), minecraft("sweet_berry_bush"), minecraft("bad_respawn_point"),
            minecraft("wither_skull"), minecraft("fireball"), minecraft("firework_rocket"), minecraft("arrow"), minecraft("player_attack"), minecraft("thorns"), minecraft("even_more_magic"), minecraft("message_too_long"),
            minecraft("indirect_magic"), resourceLoc("redstone_poisoning"), resourceLoc("bluestone_poisoning"), resourceLoc("glowstone_poisoning"), resourceLoc("gunpowder_poisoning"), resourceLoc("explosive_blend_poisoning"))
            .build();

    public static DamageSource fromLocation(LivingEntity target, ResourceLocation sourceLocation) {
        if (DATA_DRIVEN_SOURCES.containsKey(sourceLocation)) return DATA_DRIVEN_SOURCES.get(sourceLocation);
        switch (sourceLocation.toString()) {
            case "minecraft:in_fire": return DamageSource.IN_FIRE;
            case "minecraft:on_fire": return DamageSource.ON_FIRE;
            case "minecraft:lava": return DamageSource.LAVA;
            case "minecraft:lightning_bolt": return DamageSource.LIGHTNING_BOLT;
            case "minecraft:hot_floor": return DamageSource.HOT_FLOOR;
            case "minecraft:in_wall": return DamageSource.IN_WALL;
            case "minecraft:fly_into_wall": return DamageSource.FLY_INTO_WALL;
            case "minecraft:cramming": return DamageSource.CRAMMING;
            case "minecraft:drown": return DamageSource.DROWN;
            case "minecraft:starve": return DamageSource.STARVE;
            case "minecraft:cactus": return DamageSource.CACTUS;
            case "minecraft:fall": return DamageSource.FALL;
            case "minecraft:magic": return DamageSource.MAGIC;
            case "minecraft:even_more_magic": return VSDamageSources.EVEN_MORE_MAGIC;
            case "minecraft:message_too_long": return VSDamageSources.MESSAGE_TOO_LONG;
            case "minecraft:indirect_magic": return DamageSource.indirectMagic(target, target);
            case "minecraft:wither": return DamageSource.WITHER;
            case "minecraft:anvil": return DamageSource.ANVIL;
            case "minecraft:out_of_world": return DamageSource.OUT_OF_WORLD;
            case "minecraft:falling_block": return DamageSource.FALLING_BLOCK;
            case "minecraft:dragon_breath": return DamageSource.DRAGON_BREATH;
            case "minecraft:dry_out": return DamageSource.DRY_OUT;
            case "minecraft:sweet_berry_bush": return DamageSource.SWEET_BERRY_BUSH;
            case "minecraft:bad_respawn_point": return DamageSource.badRespawnPointExplosion();
            case "minecraft:sting": return DamageSource.sting(target);
            case "minecraft:mob_attack": return DamageSource.mobAttack(target);
            case "minecraft:indirect_mob_attack": return DamageSource.indirectMobAttack(target, target);
            case "minecraft:trident": return DamageSource.trident(target, target);
            case "minecraft:thrown": return DamageSource.thrown(target, target);
            case "minecraft:thorns": return DamageSource.thorns(target);
            case "minecraft:explosion": return DamageSource.explosion(target);
            case "minecraft:generic": return DamageSource.GENERIC;
            case "minecraft:wither_skull": return new IndirectEntityDamageSource("witherSkull", target, target).setProjectile();
            case "minecraft:fireball": return new IndirectEntityDamageSource("fireball", target, target).setIsFire().setProjectile();
            case "minecraft:firework_rocket": return new IndirectEntityDamageSource("fireworks", target, target).setExplosion();
            case "minecraft:arrow": return new IndirectEntityDamageSource("arrow", target, target).setProjectile();
            case "minecraft:player_attack": return new EntityDamageSource("player", target);
            case "variants:redstone_poisoning": return VSDamageSources.REDSTONE_POISONING;
            case "variants:bluestone_poisoning": return VSDamageSources.BLUESTONE_POISONING;
            case "variants:glowstone_poisoning": return VSDamageSources.GLOWSTONE_POISONING;
            case "variants:gunpowder_poisoning": return VSDamageSources.GUNPOWDER_POISONING;
            case "variants:explosive_blend_poisoning": return VSDamageSources.EXPLOSIVE_BLEND_POISONING;
            default: return null;
        }
    }

    public static DamageSource fromLocationWithKiller(Entity killer, ResourceLocation sourceLocation) {
        if (DATA_DRIVEN_SOURCES.containsKey(sourceLocation)) {
            return DATA_DRIVEN_SOURCES.get(sourceLocation);
        } else if (sourceLocation.toString().equals("minecraft:in_fire")) {
            return DamageSource.IN_FIRE;
        } else if (sourceLocation.toString().equals("minecraft:on_fire")) {
            return DamageSource.ON_FIRE;
        } else if (sourceLocation.toString().equals("minecraft:lava")) {
            return DamageSource.LAVA;
        } else if (sourceLocation.toString().equals("minecraft:lightning_bolt")) {
            return DamageSource.LIGHTNING_BOLT;
        } else if (sourceLocation.toString().equals("minecraft:hot_floor")) {
            return DamageSource.HOT_FLOOR;
        } else if (sourceLocation.toString().equals("minecraft:in_wall")) {
            return DamageSource.IN_WALL;
        } else if (sourceLocation.toString().equals("minecraft:fly_into_wall")) {
            return DamageSource.FLY_INTO_WALL;
        } else if (sourceLocation.toString().equals("minecraft:cramming")) {
            return DamageSource.CRAMMING;
        } else if (sourceLocation.toString().equals("minecraft:drown")) {
            return DamageSource.DROWN;
        } else if (sourceLocation.toString().equals("minecraft:starve")) {
            return DamageSource.STARVE;
        } else if (sourceLocation.toString().equals("minecraft:cactus")) {
            return DamageSource.CACTUS;
        } else if (sourceLocation.toString().equals("minecraft:fall")) {
            return DamageSource.FALL;
        } else if (sourceLocation.toString().equals("minecraft:magic")) {
            return DamageSource.MAGIC;
        } else if (sourceLocation.toString().equals("minecraft:even_more_magic")) {
            return VSDamageSources.EVEN_MORE_MAGIC;
        } else if (sourceLocation.toString().equals("minecraft:indirect_magic")) {
            return DamageSource.indirectMagic(killer, killer);
        } else if (sourceLocation.toString().equals("minecraft:message_too_long")) {
            return VSDamageSources.MESSAGE_TOO_LONG;
        } else if (sourceLocation.toString().equals("minecraft:wither")) {
            return DamageSource.WITHER;
        } else if (sourceLocation.toString().equals("minecraft:anvil")) {
            return DamageSource.ANVIL;
        } else if (sourceLocation.toString().equals("minecraft:out_of_world")) {
            return DamageSource.OUT_OF_WORLD;
        } else if (sourceLocation.toString().equals("minecraft:falling_block")) {
            return DamageSource.FALLING_BLOCK;
        } else if (sourceLocation.toString().equals("minecraft:dragon_breath")) {
            return DamageSource.DRAGON_BREATH;
        } else if (sourceLocation.toString().equals("minecraft:dry_out")) {
            return DamageSource.DRY_OUT;
        } else if (sourceLocation.toString().equals("minecraft:sweet_berry_bush")) {
            return DamageSource.SWEET_BERRY_BUSH;
        } else if (sourceLocation.toString().equals("minecraft:bad_respawn_point")) {
            return DamageSource.badRespawnPointExplosion();
        } else if (sourceLocation.toString().equals("minecraft:sting")) {
            return new EntityDamageSource("sting", killer);
        } else if (sourceLocation.toString().equals("minecraft:mob_attack")) {
            return new EntityDamageSource("mob", killer);
        } else if (sourceLocation.toString().equals("minecraft:indirect_mob_attack")) {
            return new IndirectEntityDamageSource("mob", killer, killer);
        } else if (sourceLocation.toString().equals("minecraft:trident")) {
            return DamageSource.trident(killer, killer);
        } else if (sourceLocation.toString().equals("minecraft:thrown")) {
            return DamageSource.thrown(killer, killer);
        } else if (sourceLocation.toString().equals("minecraft:thorns")) {
            return DamageSource.thorns(killer);
        } else if (sourceLocation.toString().equals("minecraft:explosion")) {
            return DamageSource.explosion((LivingEntity) killer);
        } else if (sourceLocation.toString().equals("minecraft:generic")) {
            return DamageSource.GENERIC;
        } else if (sourceLocation.toString().equals("minecraft:wither_skull")) {
            return new IndirectEntityDamageSource("witherSkull", killer, killer).setProjectile();
        } else if (sourceLocation.toString().equals("minecraft:fireball")) {
            return new IndirectEntityDamageSource("fireball", killer, killer).setIsFire().setProjectile();
        } else if (sourceLocation.toString().equals("minecraft:firework_rocket")) {
            return new IndirectEntityDamageSource("fireworks", killer, killer).setExplosion();
        } else if (sourceLocation.toString().equals("minecraft:arrow")) {
            return new IndirectEntityDamageSource("arrow", killer, killer).setProjectile();
        } else if (sourceLocation.toString().equals("minecraft:player_attack")) {
            return new EntityDamageSource("player", killer);
        } else if (sourceLocation.toString().equals("variants:redstone_poisoning")) {
            return VSDamageSources.REDSTONE_POISONING;
        } else if (sourceLocation.toString().equals("variants:bluestone_poisoning")) {
            return VSDamageSources.BLUESTONE_POISONING;
        } else if (sourceLocation.toString().equals("variants:glowstone_poisoning")) {
            return VSDamageSources.GLOWSTONE_POISONING;
        } else if (sourceLocation.toString().equals("variants:gunpowder_poisoning")) {
            return VSDamageSources.GUNPOWDER_POISONING;
        } else if (sourceLocation.toString().equals("variants:explosive_blend_poisoning")) {
            return VSDamageSources.EXPLOSIVE_BLEND_POISONING;
        }
        return null;
    }

    public static class Serializer implements JsonDeserializer<DamageSource>, JsonSerializer<DamageSource> {
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
}
