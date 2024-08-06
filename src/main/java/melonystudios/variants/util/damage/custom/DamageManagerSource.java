package melonystudios.variants.util.damage.custom;

import com.google.gson.JsonObject;
import melonystudios.variants.util.damage.misc.DamageScaling;
import melonystudios.variants.util.damage.misc.DeathMessageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DamageManagerSource extends DamageSource {
    public DamageScaling scaling = DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER;
    public DeathMessageTypes deathMessageType = DeathMessageTypes.DEFAULT;
    private final JsonObject object;

    public DamageManagerSource(JsonObject object) {
        super(object.has("message_id") ? object.get("message_id").getAsString() : "generic");
        this.object = object;
        this.applySourceParameters(object);
    }

    public void applySourceParameters(JsonObject object) {
        if (object.get("death_message_type").getAsString().equals("direct_entity")) {
            this.deathMessageType = DeathMessageTypes.DIRECT_ENTITY;
        } else if (object.get("death_message_type").getAsString().equals("indirect_entity")) {
            this.deathMessageType = DeathMessageTypes.INDIRECT_ENTITY;
        } else if (object.get("death_message_type").getAsString().equals("intentional_game_design")) {
            this.deathMessageType = DeathMessageTypes.INTENTIONAL_GAME_DESIGN;
        }
        if (object.has("scaling")) {
            if (JSONUtils.getAsString(object, "scaling").equals("always")) {
                this.setScalesWithDifficulty();
                this.scaling = DamageScaling.ALWAYS;
            } else if (JSONUtils.getAsString(object, "scaling").equals("when_caused_by_living_non_player")) {
                if (this.getDirectEntity() != null) {
                    if (this.getDirectEntity() instanceof LivingEntity && !(this.getDirectEntity() instanceof PlayerEntity)) this.setScalesWithDifficulty();
                }
            } else if (JSONUtils.getAsString(object, "scaling").equals("never")) {
                this.scaling = DamageScaling.NEVER;
            }
        }
        if (object.has("is_explosion")) this.setExplosion();
        if (object.has("is_projectile")) this.setProjectile();
        if (object.has("is_magic")) this.setMagic();
        if (object.has("is_fire")) this.setIsFire();
        if (object.has("bypasses_armor")) this.bypassArmor();
        if (object.has("bypasses_invulnerability")) this.bypassInvul();
        if (object.has("bypasses_magic")) this.bypassMagic();
    }

    @Override
    public float getFoodExhaustion() {
        return this.object.has("food_exhaustion") ? this.object.get("food_exhaustion").getAsFloat() : super.getFoodExhaustion();
    }

    @Override
    @Nonnull
    public ITextComponent getLocalizedDeathMessage(LivingEntity livEntity) {
        switch (this.deathMessageType) {
            case DIRECT_ENTITY: {
                ItemStack handStack = livEntity.getMainHandItem();
                String messageIDString = "death.attack." + this.msgId;
                return !handStack.isEmpty() && handStack.hasCustomHoverName() ? new TranslationTextComponent(messageIDString + ".item", livEntity.getDisplayName(), livEntity.getDisplayName(), handStack.getDisplayName()) :
                        new TranslationTextComponent(messageIDString, livEntity.getDisplayName(), livEntity.getDisplayName());
            }
            case INDIRECT_ENTITY: {
                ITextComponent displayName = livEntity.getDisplayName();
                ItemStack handStack = livEntity.getMainHandItem();
                String messageIDString = "death.attack." + this.msgId;
                String itemAddition = messageIDString + ".item";
                return !handStack.isEmpty() && handStack.hasCustomHoverName() ? new TranslationTextComponent(itemAddition, livEntity.getDisplayName(), displayName, handStack.getDisplayName()) : new TranslationTextComponent(messageIDString,
                        livEntity.getDisplayName(), displayName);
            }
            case INTENTIONAL_GAME_DESIGN: {
                ITextComponent linkComponent = TextComponentUtils.wrapInSquareBrackets(new TranslationTextComponent("death.attack.badRespawnPoint.link")).withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
                        "https://bugs.mojang.com/browse/MCPE-28723")).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("death.attack.badRespawnPoint.bug"))));
                return new TranslationTextComponent("death.attack.badRespawnPoint.message", livEntity.getDisplayName(), linkComponent);
            }
            case DEFAULT:
            default: {
                LivingEntity killCreditEntity = livEntity.getKillCredit();
                String messageIDString = "death.attack." + this.msgId;
                String playerAddition = messageIDString + ".player";
                return killCreditEntity != null ? new TranslationTextComponent(playerAddition, livEntity.getDisplayName(), killCreditEntity.getDisplayName()) : new TranslationTextComponent(messageIDString, livEntity.getDisplayName());
            }
        }
    }

    public static class EntityDMSource extends EntityDamageSource {
        public DamageScaling scaling = DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER;
        public DeathMessageTypes deathMessageType = DeathMessageTypes.DEFAULT;

        public EntityDMSource(JsonObject object, @Nullable Entity entity) {
            super(object.has("message_id") ? object.get("message_id").getAsString() : "generic", entity);
            this.applySourceParameters(object);
        }

        public void applySourceParameters(JsonObject object) {
            if (object.get("death_message_type").getAsString().equals("direct_entity")) {
                this.deathMessageType = DeathMessageTypes.DIRECT_ENTITY;
            } else if (object.get("death_message_type").getAsString().equals("indirect_entity")) {
                this.deathMessageType = DeathMessageTypes.INDIRECT_ENTITY;
            } else if (object.get("death_message_type").getAsString().equals("intentional_game_design")) {
                this.deathMessageType = DeathMessageTypes.INTENTIONAL_GAME_DESIGN;
            }
            if (object.has("scaling")) {
                if (JSONUtils.getAsString(object, "scaling").equals("always")) {
                    this.setScalesWithDifficulty();
                    this.scaling = DamageScaling.ALWAYS;
                } else if (JSONUtils.getAsString(object, "scaling").equals("when_caused_by_living_non_player")) {
                    if (this.getDirectEntity() != null) {
                        if (this.getDirectEntity() instanceof LivingEntity && !(this.getDirectEntity() instanceof PlayerEntity)) this.setScalesWithDifficulty();
                    }
                } else if (JSONUtils.getAsString(object, "scaling").equals("never")) {
                    this.scaling = DamageScaling.NEVER;
                }
            }
            if (object.has("is_explosion")) this.setExplosion();
            if (object.has("is_projectile")) this.setProjectile();
            if (object.has("is_magic")) this.setMagic();
            if (object.has("is_fire")) this.setIsFire();
            if (object.has("is_thorns")) this.setThorns();
            if (object.has("bypasses_armor")) this.bypassArmor();
            if (object.has("bypasses_invulnerability")) this.bypassInvul();
            if (object.has("bypasses_magic")) this.bypassMagic();
        }
    }
}
