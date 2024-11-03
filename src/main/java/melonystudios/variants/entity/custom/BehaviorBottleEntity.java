package melonystudios.variants.entity.custom;

import melonystudios.variants.entity.VSEntities;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.consumable.ConsumeBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class BehaviorBottleEntity extends ProjectileItemEntity implements PotionParticleMaker {
    private static final DataParameter<Integer> PARTICLE_COLOR = EntityDataManager.defineId(BehaviorBottleEntity.class, DataSerializers.INT);

    public BehaviorBottleEntity(EntityType<? extends BehaviorBottleEntity> type, World world) {
        super(type, world);
    }

    public BehaviorBottleEntity(World world, LivingEntity livEntity) {
        super(VSEntities.BEHAVIOR_BOTTLE.get(), livEntity, world);
    }

    public BehaviorBottleEntity(World world, LivingEntity livEntity, int particleColor) {
        super(VSEntities.BEHAVIOR_BOTTLE.get(), livEntity, world);
        this.entityData.set(PARTICLE_COLOR, particleColor);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PARTICLE_COLOR, 3694022);
    }

    @Override
    @Nonnull
    protected Item getDefaultItem() {
        return VSItems.SPLASH_LAVA_BOTTLE.get();
    }

    @Override
    protected float getGravity() {
        return 0.05F;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("particle_color", this.entityData.get(PARTICLE_COLOR));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(PARTICLE_COLOR, tag.getInt("particle_color"));
    }

    @Override
    protected void onHit(RayTraceResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.spawnParticles(this.position(), this.level, this.getItem(), this.entityData.get(PARTICLE_COLOR));
            ConsumeBehavior.runAreaEffectBehavior(this.getItem(), this, ConsumeBehavior::runBehavior);
            this.remove();
        }
    }

    @Override
    @Nonnull
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
