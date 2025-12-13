package melonystudios.variants.mixin.entity;

import melonystudios.variants.entity.misc.Leashable;
import melonystudios.variants.util.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.HangingEntity;
import net.minecraft.entity.item.LeashKnotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SMountEntityPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

@Mixin(BoatEntity.class)
public abstract class RVBoatEntityMixin extends Entity implements Leashable {
    @Nullable
    @Unique
    private Entity leashHolder;
    @Unique
    private int delayedLeashHolderID;
    @Nullable
    @Unique
    private CompoundNBT leashInfoTag;

    public RVBoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tickLeash(CallbackInfo callback) {
        this.tickLeash();
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResultType> callback) {
        if (this.getLeashHolder() == player) {
            this.dropLeash(true, !player.abilities.instabuild);
            callback.setReturnValue(ActionResultType.sidedSuccess(this.level.isClientSide));
        } else if (player.getItemInHand(hand).getItem() == Items.LEAD && this.canBeLeashed()) {
            this.setLeashedTo(player, true);
            player.getItemInHand(hand).shrink(1);
            callback.setReturnValue(ActionResultType.sidedSuccess(this.level.isClientSide));
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    protected void addAdditionalSaveData(CompoundNBT tag, CallbackInfo callback) {
        if (this.leashHolder != null) {
            CompoundNBT leashTag = new CompoundNBT();
            if (this.leashHolder instanceof LivingEntity) {
                UUID uuid = this.leashHolder.getUUID();
                leashTag.putUUID("UUID", uuid);
            } else if (this.leashHolder instanceof HangingEntity) {
                BlockPos entityPos = ((HangingEntity) this.leashHolder).getPos();
                leashTag.putInt("X", entityPos.getX());
                leashTag.putInt("Y", entityPos.getY());
                leashTag.putInt("Z", entityPos.getZ());
            }

            tag.put("Leash", leashTag);
        } else if (this.leashInfoTag != null) {
            tag.put("Leash", this.leashInfoTag.copy());
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void readAdditionalSaveData(CompoundNBT tag, CallbackInfo callback) {
        if (tag.contains("Leash", Constants.TagTypes.COMPOUND)) {
            this.leashInfoTag = tag.getCompound("Leash");
        }
    }

    @Unique
    @Nonnull
    @Override
    public Vector3d getLeashOffset() {
        return super.getLeashOffset().add(0, 0, 0.45);
    }

    @Unique
    protected void tickLeash() {
        if (this.leashInfoTag != null) {
            this.restoreLeashFromSave();
        }

        if (this.leashHolder != null) {
            if (!this.isAlive() || !this.leashHolder.isAlive()) {
                this.dropLeash(true, true);
            }
        }

        Entity entity = this.getLeashHolder();
        if (entity != null && entity.level == this.level) {
            float distanceToLeasher = this.distanceTo(entity);

            if (distanceToLeasher > 10) {
                this.dropLeash(true, true);
            } else if (distanceToLeasher > 6) {
                double x = (entity.getX() - this.getX()) / (double) distanceToLeasher;
                double y = (entity.getY() - this.getY()) / (double) distanceToLeasher;
                double z = (entity.getZ() - this.getZ()) / (double) distanceToLeasher;
                this.setDeltaMovement(this.getDeltaMovement().add(Math.copySign(x * x * 0.4D, x), Math.copySign(y * y * 0.4D, y), Math.copySign(z * z * 0.4D, z)));
            } else {
                Vector3d vec3D = new Vector3d(entity.getX() - this.getX(), entity.getY() - this.getY(), entity.getZ() - this.getZ()).normalize().scale(Math.max(distanceToLeasher - 2, 0));
                this.setDeltaMovement(vec3D);
            }
        }
    }

    @Unique
    public void dropLeash(boolean broadcastPacket, boolean dropLeash) {
        if (this.leashHolder != null) {
            this.forcedLoading = false;
            if (!(this.leashHolder instanceof PlayerEntity)) {
                this.leashHolder.forcedLoading = false;
            }

            this.leashHolder = null;
            this.leashInfoTag = null;
            if (!this.level.isClientSide && dropLeash) this.spawnAtLocation(Items.LEAD);

            if (!this.level.isClientSide && broadcastPacket && this.level instanceof ServerWorld) {
                ((ServerWorld) this.level).getChunkSource().broadcast(this, new SMountEntityPacket(this, null));
            }
        }
    }

    @Unique
    public boolean canBeLeashed() {
        return !this.isLeashed();
    }

    @Unique
    public boolean isLeashed() {
        return this.leashHolder != null;
    }

    @Override
    @Nullable
    @Unique
    public Entity getLeashHolder() {
        if (this.leashHolder == null && this.delayedLeashHolderID != 0 && this.level.isClientSide) {
            this.leashHolder = this.level.getEntity(this.delayedLeashHolderID);
        }

        return this.leashHolder;
    }

    @Override
    @Unique
    public void setLeashedTo(Entity entity, boolean broadcastPacket) {
        this.leashHolder = entity;
        this.leashInfoTag = null;
        this.forcedLoading = true;
        if (!(this.leashHolder instanceof PlayerEntity)) this.leashHolder.forcedLoading = true;

        if (!this.level.isClientSide && broadcastPacket && this.level instanceof ServerWorld) {
            ((ServerWorld) this.level).getChunkSource().broadcast(this, new SMountEntityPacket(this, this.leashHolder));
        }

        if (this.isPassenger()) this.stopRiding();
    }

    @OnlyIn(Dist.CLIENT)
    @Unique
    public void setDelayedLeashHolderID(int leashHolderID) {
        this.delayedLeashHolderID = leashHolderID;
        this.dropLeash(false, false);
    }

    @Override
    public boolean startRiding(Entity entity, boolean force) {
        boolean isRiding = super.startRiding(entity, force);
        if (isRiding && this.isLeashed()) {
            this.dropLeash(true, true);
        }

        return isRiding;
    }

    @Unique
    private void restoreLeashFromSave() {
        if (this.leashInfoTag != null && this.level instanceof ServerWorld) {
            if (this.leashInfoTag.hasUUID("UUID")) {
                UUID leashUUID = this.leashInfoTag.getUUID("UUID");
                Entity leashEntity = ((ServerWorld) this.level).getEntity(leashUUID);
                if (leashEntity != null) {
                    this.setLeashedTo(leashEntity, true);
                    return;
                }
            } else if (this.leashInfoTag.contains("X", Constants.TagTypes.ANY_NUMERIC) && this.leashInfoTag.contains("Y", Constants.TagTypes.ANY_NUMERIC)
                    && this.leashInfoTag.contains("Z", Constants.TagTypes.ANY_NUMERIC)) {
                BlockPos pos = new BlockPos(this.leashInfoTag.getInt("X"), this.leashInfoTag.getInt("Y"),this.leashInfoTag.getInt("Z"));
                this.setLeashedTo(LeashKnotEntity.getOrCreateKnot(this.level, pos), true);
                return;
            }

            if (this.tickCount > 100) {
                this.spawnAtLocation(Items.LEAD);
                this.leashInfoTag = null;
            }
        }
    }
}
