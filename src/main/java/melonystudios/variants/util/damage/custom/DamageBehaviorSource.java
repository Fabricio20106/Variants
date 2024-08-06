package melonystudios.variants.util.damage.custom;

import melonystudios.variants.util.damage.misc.DamageScaling;
import melonystudios.variants.util.damage.misc.DeathMessageTypes;
import melonystudios.variants.util.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DamageBehaviorSource extends DamageSource {
    public DamageScaling scaling = DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER;
    public DeathMessageTypes deathMessageType = DeathMessageTypes.DEFAULT;
    private final CompoundNBT sourceTag;
    private final LivingEntity livEntity;

    public DamageBehaviorSource(CompoundNBT sourceTag, LivingEntity livEntity) {
        super(!sourceTag.contains("message_id", Constants.TagTypes.STRING) || sourceTag.getString("message_id").isEmpty() ? "generic" : sourceTag.getString("message_id"));
        this.sourceTag = sourceTag;
        this.livEntity = livEntity;
        if (this.sourceTag.getString("death_message_type").equals("direct_entity")) {
            this.deathMessageType = DeathMessageTypes.DIRECT_ENTITY;
        } else if (this.sourceTag.getString("death_message_type").equals("indirect_entity")) {
            this.deathMessageType = DeathMessageTypes.INDIRECT_ENTITY;
        } else if (this.sourceTag.getString("death_message_type").equals("intentional_game_design")) {
            this.deathMessageType = DeathMessageTypes.INTENTIONAL_GAME_DESIGN;
        }
        if (this.sourceTag.getBoolean("is_explosion")) this.setExplosion();
        if (this.sourceTag.getBoolean("is_projectile")) this.setProjectile();
        if (this.sourceTag.getBoolean("is_magic")) this.setMagic();
        if (this.sourceTag.getBoolean("is_fire")) this.setIsFire();
        if (this.sourceTag.contains("scaling", Constants.TagTypes.STRING)) {
            if (this.sourceTag.getString("scaling").equals("always")) {
                this.setScalesWithDifficulty();
                this.scaling = DamageScaling.ALWAYS;
            } else if (this.sourceTag.getString("scaling").equals("when_caused_by_living_non_player")) {
                if (!(livEntity instanceof PlayerEntity)) this.setScalesWithDifficulty();
            } else if (this.sourceTag.getString("scaling").equals("never")) {
                this.scaling = DamageScaling.NEVER;
            }
        }
        if (this.sourceTag.getBoolean("bypasses_armor")) this.bypassArmor();
        if (this.sourceTag.getBoolean("bypasses_invulnerability")) this.bypassInvul();
        if (this.sourceTag.getBoolean("bypasses_magic")) this.bypassMagic();
    }

    @Override
    public float getFoodExhaustion() {
        return this.sourceTag.contains("food_exhaustion", Constants.TagTypes.FLOAT) ? this.sourceTag.getFloat("food_exhaustion") : super.getFoodExhaustion();
    }

    @Override
    @Nullable
    public Entity getDirectEntity() {
        return this.livEntity;
    }

    @Override
    @Nonnull
    public ITextComponent getLocalizedDeathMessage(LivingEntity livEntity) {
        switch (this.deathMessageType) {
            case DIRECT_ENTITY: {
                ItemStack handStack = this.livEntity.getMainHandItem();
                String messageIDString = "death.attack." + this.msgId;
                return !handStack.isEmpty() && handStack.hasCustomHoverName() ? new TranslationTextComponent(messageIDString + ".item", livEntity.getDisplayName(), this.livEntity.getDisplayName(), handStack.getDisplayName()) :
                        new TranslationTextComponent(messageIDString, livEntity.getDisplayName(), this.livEntity.getDisplayName());
            }
            case INDIRECT_ENTITY: {
                ITextComponent displayName = this.livEntity.getDisplayName();
                ItemStack handStack = this.livEntity.getMainHandItem();
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
            default:
                return super.getLocalizedDeathMessage(livEntity);
        }
    }
}
