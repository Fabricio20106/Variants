package melonystudios.variants.util.damage.custom;

import com.google.gson.JsonObject;
import melonystudios.variants.util.JSONDeserializer;
import melonystudios.variants.util.damage.misc.DamageScaling;
import melonystudios.variants.util.damage.misc.DeathMessageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nonnull;

public class DamageManagerSource extends DamageSource {
    public DamageScaling scaling = DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER;
    public DeathMessageTypes deathMessageType = DeathMessageTypes.DEFAULT;
    private final JsonObject object;

    public DamageManagerSource(JsonObject object) {
        super(object.has("message_id") && object.get("message_id").isJsonPrimitive() ? object.get("message_id").getAsString() : "generic");
        this.object = object;
        JSONDeserializer.loadManagerDamageSource(object, this);
    }

    @Override
    public float getFoodExhaustion() {
        return JSONDeserializer.getFoodExhaustion(this.object, super.getFoodExhaustion());
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
}
