package melonystudios.variants.util.damage.custom;

import com.google.gson.JsonObject;
import melonystudios.variants.util.JSONDeserializer;
import melonystudios.variants.util.damage.misc.DamageScaling;
import melonystudios.variants.util.damage.misc.DeathMessageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

import javax.annotation.Nullable;

public class EntityDamageManagerSource extends EntityDamageSource {
    public DamageScaling scaling = DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER;
    public DeathMessageTypes deathMessageType = DeathMessageTypes.DEFAULT;
    private final JsonObject object;

    public EntityDamageManagerSource(JsonObject object, @Nullable Entity entity) {
        super(object.has("message_id") && object.get("message_id").isJsonPrimitive() ? object.get("message_id").getAsString() : "generic", entity);
        this.object = object;
        JSONDeserializer.loadManagerEntityDamageSource(object, this);
    }

    @Override
    public float getFoodExhaustion() {
        return JSONDeserializer.getFoodExhaustion(this.object, super.getFoodExhaustion());
    }
}
