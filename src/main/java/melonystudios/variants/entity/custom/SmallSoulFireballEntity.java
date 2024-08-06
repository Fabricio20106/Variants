package melonystudios.variants.entity.custom;

import melonystudios.variants.entity.VSEntities;
import melonystudios.variants.item.VSItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;

public class SmallSoulFireballEntity extends AbstractFireballEntity implements IRendersAsItem {
    public SmallSoulFireballEntity(EntityType<? extends SmallSoulFireballEntity> soulFireball, World world) {
        super(soulFireball, world);
    }

    public SmallSoulFireballEntity(World world, double x, double y, double z, double xPower, double yPower, double zPower) {
        super(VSEntities.SMALL_SOUL_FIREBALL.get(), x, y, z, xPower, yPower, zPower, world);
    }

    @Override
    public ItemStack getItem() {
        ItemStack rawStack = this.getItemRaw();
        return rawStack.isEmpty() ? new ItemStack(VSItems.SOUL_O_CHARGE.get()) : rawStack;
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

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
