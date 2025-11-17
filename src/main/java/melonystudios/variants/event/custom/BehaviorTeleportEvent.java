package melonystudios.variants.event.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/// `BehaviorTeleportEvent` is fired before a LivingEntity is teleported from a {@linkplain melonystudios.variants.consumable.custom.TeleportEntityBehavior Teleport Entity consume behavior}.
///
/// This event is {@linkplain Cancelable cancellable}. When cancelled, the entity will not be teleported.
///
/// This event is only fired on the **logical server side**.
@Cancelable
public class BehaviorTeleportEvent extends EntityTeleportEvent {
    private final ItemStack stack;
    private final World world;
    private final LivingEntity livEntity;

    /// `BehaviorTeleportEvent` is fired before a LivingEntity is teleported from a {@linkplain melonystudios.variants.consumable.custom.TeleportEntityBehavior Teleport Entity consume behavior}.
    ///
    /// This event is {@linkplain Cancelable cancellable}. When cancelled, the entity will not be teleported.
    ///
    /// This event is only fired on the **logical server side**.
    /// @param stack The item stack with the Teleport Entity consume behavior.
    /// @param world The world.
    /// @param livEntity The entity running the effect.
    /// @param targetX The x-position of the target.
    /// @param targetY The y-position of the target.
    /// @param targetZ The z-position of the target.
    public BehaviorTeleportEvent(ItemStack stack, World world, LivingEntity livEntity, double targetX, double targetY, double targetZ) {
        super(livEntity, targetX, targetY, targetZ);
        this.stack = stack;
        this.world = world;
        this.livEntity = livEntity;
    }

    /// The item stack with the Teleport Entity consume behavior.
    public ItemStack getStack() {
        return this.stack;
    }

    public World getWorld() {
        return this.world;
    }

    public LivingEntity getTargetEntity() {
        return this.livEntity;
    }

    /// `BehaviorTeleportEvent.RandomTeleport` is fired before a LivingEntity randomly teleported from a
    /// {@linkplain melonystudios.variants.consumable.custom.TeleportEntityBehavior Teleport Entity consume behavior}.
    ///
    /// This event is {@linkplain Cancelable cancellable}. When cancelled, the entity will not be teleported.
    ///
    /// This event is only fired on the **logical server side**.
    public static class RandomTeleport extends BehaviorTeleportEvent {
        private final float teleportDiameter;

        /// `BehaviorTeleportEvent.RandomTeleport` is fired before a LivingEntity randomly teleported from a
        /// {@linkplain melonystudios.variants.consumable.custom.TeleportEntityBehavior Teleport Entity consume behavior}.
        ///
        /// This event is {@linkplain Cancelable cancellable}. When cancelled, the entity will not be teleported.
        ///
        /// This event is only fired on the **logical server side**.
        /// @param stack The item stack with the Teleport Entity consume behavior.
        /// @param world The world.
        /// @param livEntity The entity running the effect.
        /// @param targetX The x-position of the target.
        /// @param targetY The y-position of the target.
        /// @param targetZ The z-position of the target.
        /// @param teleportDiameter The diameter that the entity can teleport within.
        public RandomTeleport(ItemStack stack, World world, LivingEntity livEntity, double targetX, double targetY, double targetZ, float teleportDiameter) {
            super(stack, world, livEntity, targetX, targetY, targetZ);
            this.teleportDiameter = teleportDiameter;
        }

        /// The diameter that the entity can teleport within.
        public float getTeleportDiameter() {
            return this.teleportDiameter;
        }
    }
}
