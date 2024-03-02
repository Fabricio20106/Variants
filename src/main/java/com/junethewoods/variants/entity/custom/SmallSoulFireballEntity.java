package com.junethewoods.variants.entity.custom;

import com.junethewoods.variants.entity.VSEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class SmallSoulFireballEntity extends AbstractFireballEntity {
    public SmallSoulFireballEntity(EntityType<? extends SmallSoulFireballEntity> soulFireball, World world) {
        super(soulFireball, world);
    }

    public SmallSoulFireballEntity(World world, LivingEntity livEntity, double x, double y, double z) {
        super(VSEntities.SMALL_SOUL_FIREBALL.get(), livEntity, x, y, z, world);
    }

    public SmallSoulFireballEntity(World p_i1772_1_, double x, double y, double z, double xPower, double yPower, double zPower) {
        super(VSEntities.SMALL_SOUL_FIREBALL.get(), x, y, z, xPower, yPower, zPower, p_i1772_1_);
    }

    protected void onHitEntity(EntityRayTraceResult hitResult) {
        super.onHitEntity(hitResult);
        if (!this.level.isClientSide) {
            Entity target = hitResult.getEntity();
            if (!target.fireImmune()) {
                Entity owner = this.getOwner();
                int ticksOnFire = target.getRemainingFireTicks();
                target.setSecondsOnFire(10);
                boolean hurtTarget = target.hurt(DamageSource.fireball(this, owner), 5);
                if (!hurtTarget) {
                    target.setRemainingFireTicks(ticksOnFire);
                } else if (owner instanceof LivingEntity) {
                    this.doEnchantDamageEffects((LivingEntity) owner, target);
                }
            }
        }
    }

    protected void onHitBlock(BlockRayTraceResult hitResult) {
        super.onHitBlock(hitResult);
        if (!this.level.isClientSide) {
            Entity owner = this.getOwner();
            if (!(owner instanceof MobEntity) || ForgeEventFactory.getMobGriefingEvent(this.level, this.getEntity())) {
                BlockPos relativePos = hitResult.getBlockPos().relative(hitResult.getDirection());
                if (this.level.isEmptyBlock(relativePos)) {
                    this.level.setBlockAndUpdate(relativePos, Blocks.SOUL_FIRE.defaultBlockState());
                }
            }
        }
    }

    protected void onHit(RayTraceResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.remove();
        }
    }

    public boolean isPickable() {
        return false;
    }

    public boolean hurt(DamageSource source, float amount) {
        return false;
    }
}
