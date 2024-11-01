package melonystudios.variants.entity.custom;

import melonystudios.variants.entity.VSEntities;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.util.Constants.TagTypes;
import melonystudios.variants.util.VSUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class StainedExperienceBottleEntity extends ProjectileItemEntity implements PotionParticleMaker {
    private static final DataParameter<Integer> PARTICLE_COLOR = EntityDataManager.defineId(StainedExperienceBottleEntity.class, DataSerializers.INT);
    private ItemStack bottleItem = new ItemStack(VSItems.STAINED_EXPERIENCE_BOTTLE.get());

    public StainedExperienceBottleEntity(EntityType<? extends StainedExperienceBottleEntity> type, World world) {
        super(type, world);
    }

    public StainedExperienceBottleEntity(World world, LivingEntity livEntity, ItemStack stack, int particleColor) {
        super(VSEntities.STAINED_EXPERIENCE_BOTTLE.get(), livEntity, world);
        this.entityData.set(PARTICLE_COLOR, particleColor);
        this.bottleItem = stack.copy();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PARTICLE_COLOR, 3694022);
    }

    @Override
    @Nonnull
    protected Item getDefaultItem() {
        return this.bottleItem.copy().getItem();
    }

    @Override
    @Nonnull
    protected ItemStack getItemRaw() {
        return this.bottleItem.copy();
    }

    @Override
    public void setItem(ItemStack stack) {
        this.bottleItem = stack.copy();
    }

    @Override
    protected float getGravity() {
        return 0.07F;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        tag.put("item", VSUtils.saveStack(this.bottleItem, new CompoundNBT()));
        tag.putInt("particle_color", this.entityData.get(PARTICLE_COLOR));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("item", TagTypes.COMPOUND)) this.bottleItem = VSUtils.loadStack(tag.getCompound("item"));
        this.entityData.set(PARTICLE_COLOR, tag.getInt("particle_color"));
    }

    @Override
    protected void onHit(RayTraceResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.spawnParticles(this.position(), this.level, this.bottleItem, this.entityData.get(PARTICLE_COLOR));
            int i = 3 + this.level.random.nextInt(5) + this.level.random.nextInt(5);

            while (i > 0) {
                int experienceValue = ExperienceOrbEntity.getExperienceValue(i);
                i -= experienceValue;
                this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY(), this.getZ(), experienceValue));
            }

            this.remove();
        }
    }

    @Override
    @Nonnull
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
