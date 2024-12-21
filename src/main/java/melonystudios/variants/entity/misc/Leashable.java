package melonystudios.variants.entity.misc;

import net.minecraft.entity.Entity;

import javax.annotation.Nullable;

public interface Leashable {
    @Nullable
    Entity getLeashHolder();

    void setLeashedTo(Entity entity, boolean broadcastPacket);

    void setDelayedLeashHolderID(int leashHolderID);
}
