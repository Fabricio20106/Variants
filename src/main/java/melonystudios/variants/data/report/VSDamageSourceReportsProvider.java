package melonystudios.variants.data.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import melonystudios.variants.Variants;
import melonystudios.variants.util.damage.VSDamageSources;
import melonystudios.variants.util.damage.custom.DamageBehaviorSource;
import melonystudios.variants.util.damage.custom.DamageManagerSource;
import melonystudios.variants.util.damage.DamageSourceUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.*;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;

public class VSDamageSourceReportsProvider implements IDataProvider {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final DataGenerator generator;

    public VSDamageSourceReportsProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    @Nonnull
    public String getName() {
        return Variants.generatorName("Damage Source Reports");
    }

    private static Path createPath(Path path) {
        return path.resolve("reports/miscellaneous/damage_sources.json");
    }

    @Override
    public void run(DirectoryCache cache) {
        Path outputFolder = this.generator.getOutputFolder();
        Path filePath = createPath(outputFolder);

        JsonObject sources = new JsonObject();
        for (ResourceLocation location : DamageSourceUtils.VALID_DAMAGE_SOURCES) {
            DamageSource source = fromLocation(location);

            if (source != null) {
                JsonObject object = new JsonObject();
                 if (source instanceof IndirectEntityDamageSource) {
                    object.addProperty("death_message_type", "indirect_entity");
                } else if (source instanceof EntityDamageSource) {
                    object.addProperty("death_message_type", "direct_entity");
                } else if (source instanceof BedExplosionDamageSource) {
                    object.addProperty("death_message_type", "intentional_game_design");
                } else if (source instanceof DamageBehaviorSource) {
                    object.addProperty("death_message_type", "damage_behavior");
                } else if (source instanceof DamageManagerSource) {
                    object.addProperty("death_message_type", "damage_manager");
                } else {
                    object.addProperty("death_message_type", "default");
                }
                if (source.scalesWithDifficulty() || source instanceof EntityDamageSource) {
                    object.addProperty("scaling", "when_caused_by_living_non_player");
                } else {
                    object.addProperty("scaling", "never");
                }
                object.addProperty("message_id", source.msgId);
                object.addProperty("food_exhaustion", source.getFoodExhaustion());
                if (source.isExplosion()) object.addProperty("is_explosion", source.isExplosion());
                if (source.isProjectile()) object.addProperty("is_projectile", source.isProjectile());
                if (source.isMagic()) object.addProperty("is_magic", source.isMagic());
                if (source.isFire()) object.addProperty("is_fire", source.isFire());
                if (source instanceof EntityDamageSource && ((EntityDamageSource) source).isThorns()) object.addProperty("is_thorns", ((EntityDamageSource) source).isThorns());
                if (source.isBypassArmor()) object.addProperty("bypasses_armor", source.isBypassArmor());
                if (source.isBypassInvul()) object.addProperty("bypasses_invulnerability", source.isBypassInvul());
                if (source.isBypassMagic()) object.addProperty("bypasses_magic", source.isBypassMagic());
                sources.add(location.toString(), object);
            }
        }

        try {
            IDataProvider.save(GSON, cache, sources, filePath);
        } catch (IOException exception) {
            Variants.LOGGER.error(new TranslationTextComponent("error." + Variants.MOD_ID + ".damage_source_reports.saving", filePath).getString(), filePath, exception);
        }
    }

    public static DamageSource fromLocation(ResourceLocation sourceLocation) {
        if (DamageSourceUtils.DATA_DRIVEN_SOURCES.containsKey(sourceLocation)) return DamageSourceUtils.DATA_DRIVEN_SOURCES.get(sourceLocation);
        switch (sourceLocation.toString()) {
            case "minecraft:generic": return DamageSource.GENERIC;
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
            case "minecraft:indirect_magic": return new IndirectEntityDamageSource("indirectMagic", null, null).bypassArmor().setMagic();
            case "minecraft:message_too_long": return VSDamageSources.MESSAGE_TOO_LONG;
            case "minecraft:wither": return DamageSource.WITHER;
            case "minecraft:anvil": return DamageSource.ANVIL;
            case "minecraft:out_of_world": return DamageSource.OUT_OF_WORLD;
            case "minecraft:falling_block": return DamageSource.FALLING_BLOCK;
            case "minecraft:dragon_breath": return DamageSource.DRAGON_BREATH;
            case "minecraft:dry_out": return DamageSource.DRY_OUT;
            case "minecraft:sweet_berry_bush": return DamageSource.SWEET_BERRY_BUSH;
            case "minecraft:bad_respawn_point": return DamageSource.badRespawnPointExplosion();
            case "minecraft:sting": return new EntityDamageSource("sting", null);
            case "minecraft:mob_attack": return new EntityDamageSource("mob", null);
            case "minecraft:indirect_mob_attack": return new IndirectEntityDamageSource("mob", null, null);
            case "minecraft:trident": return new IndirectEntityDamageSource("trident", null, null).setProjectile();
            case "minecraft:thrown": return new IndirectEntityDamageSource("thrown", null, null).setProjectile();
            case "minecraft:thorns": return DamageSource.thorns(null);
            case "minecraft:explosion": return new DamageSource("explosion").setScalesWithDifficulty().setExplosion();
            case "minecraft:wither_skull": return new IndirectEntityDamageSource("witherSkull", null, null).setProjectile();
            case "minecraft:fireball": return new IndirectEntityDamageSource("fireball", null, null).setIsFire().setProjectile();
            case "minecraft:firework_rocket": return new IndirectEntityDamageSource("fireworks", null, null).setExplosion();
            case "minecraft:arrow": return new IndirectEntityDamageSource("arrow", null, null).setProjectile();
            case "minecraft:player_attack": return new EntityDamageSource("player", null);
            case "variants:redstone_poisoning": return VSDamageSources.REDSTONE_POISONING;
            case "variants:bluestone_poisoning": return VSDamageSources.BLUESTONE_POISONING;
            case "variants:glowstone_poisoning": return VSDamageSources.GLOWSTONE_POISONING;
            case "variants:gunpowder_poisoning": return VSDamageSources.GUNPOWDER_POISONING;
            case "variants:explosive_blend_poisoning": return VSDamageSources.EXPLOSIVE_BLEND_POISONING;
            case "3d_shareware:nightmare": return VSDamageSources.TOO_SOFT;
            case "vote_update:on_moon": return VSDamageSources.ON_MOON;
            case "vote_update:midas_touch": return VSDamageSources.MIDAS_TOUCH;
            case "poisonous_potato_update:potato_heat": return VSDamageSources.HOT_POTATO;
            case "poisonous_potato_update:potato_magic": return VSDamageSources.POTATO_BATTERY;
            default: return null;
        }
    }
}
