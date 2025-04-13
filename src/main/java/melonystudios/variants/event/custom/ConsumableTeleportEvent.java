package melonystudios.variants.event.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * This event is fired whenever the {@link melonystudios.variants.consumable.custom.TeleportEntityBehavior teleport entity} behavior with
 * {@code random_teleport} set to {@code true} is called.
 */
@Cancelable
public class ConsumableTeleportEvent extends EntityTeleportEvent {
    private final LivingEntity livEntity;
    private final float teleportDiameter;

    /**
     * This event is fired whenever the {@link melonystudios.variants.consumable.custom.TeleportEntityBehavior teleport entity} behavior with
     * {@code random_teleport} set to {@code true} is called.
     *
     * @param livEntity The entity being teleported;
     * @param teleportDiameter The diameter of this teleport. Defaults to {@code 8};
     * @param targetX The X location of this teleport;
     * @param targetY The Y location of this teleport;
     * @param targetZ The Z location of this teleport.
     */
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
