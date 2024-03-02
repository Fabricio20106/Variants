package com.junethewoods.variants.entity.custom;

import com.junethewoods.variants.entity.VSEntities;
import com.junethewoods.variants.item.VSItems;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class DragonBreathBottleEntity extends ProjectileItemEntity {
    public DragonBreathBottleEntity(EntityType<? extends DragonBreathBottleEntity> breathBottle, World world) {
        super(breathBottle, world);
    }

    public DragonBreathBottleEntity(World world, LivingEntity livEntity) {
        super(VSEntities.DRAGON_BREATH_BOTTLE.get(), livEntity, world);
    }

    public DragonBreathBottleEntity(World world, double x, double y, double z) {
        super(VSEntities.DRAGON_BREATH_BOTTLE.get(), x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return VSItems.SPLASH_DRAGON_BREATH.get();
    }

    @Override
    protected float getGravity() {
        return 0.07F;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void onHit(RayTraceResult hitResult) {
        super.onHit(hitResult);
        Entity owner = this.getOwner();
        if (hitResult.getType() != RayTraceResult.Type.ENTITY || !((EntityRayTraceResult) hitResult).getEntity().is(owner)) {
            if (!this.level.isClientSide) {
                List<LivingEntity> livEntitiesWithin = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4, 2, 4));
                AreaEffectCloudEntity breathCloud = getBreathCloud(owner);
                breathCloud.addEffect(new EffectInstance(Effects.HARM, 1, 1));
                if (!livEntitiesWithin.isEmpty()) {
                    for(LivingEntity livEntity : livEntitiesWithin) {
                        double distanceToLivEntity = this.distanceToSqr(livEntity);
                        if (distanceToLivEntity < 16) {
                            breathCloud.setPos(livEntity.getX(), livEntity.getY(), livEntity.getZ());
                            break;
                        }
                    }
                }

                this.level.levelEvent(2006, this.blockPosition(), this.isSilent() ? -1 : 1);
                this.level.addFreshEntity(breathCloud);
                this.remove();
            }
        }
    }

    private AreaEffectCloudEntity getBreathCloud(Entity entity) {
        AreaEffectCloudEntity effectCloud = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
        if (entity instanceof LivingEntity) effectCloud.setOwner((LivingEntity) entity);

        effectCloud.setParticle(ParticleTypes.DRAGON_BREATH);
        effectCloud.setRadius(3);
        effectCloud.setDuration(600);
        effectCloud.setRadiusPerTick((7 - effectCloud.getRadius()) / (float) effectCloud.getDuration());
        return effectCloud;
    }
}
