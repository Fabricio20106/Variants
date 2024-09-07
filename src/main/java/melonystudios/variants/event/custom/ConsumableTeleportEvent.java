package melonystudios.variants.event.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class ConsumableTeleportEvent extends EntityTeleportEvent {
    private final LivingEntity livEntity;
    private final float teleportDiameter;

    public ConsumableTeleportEvent(LivingEntity livEntity, float teleportDiameter, double targetX, double targetY, double targetZ) {
        super(livEntity, targetX, targetY, targetZ);
        this.livEntity = livEntity;
        this.teleportDiameter = teleportDiameter;
    }

    public LivingEntity getTeleportedEntity() {
        return this.livEntity;
    }

    public float getTeleportDiameter() {
        return this.teleportDiameter;
    }
}
