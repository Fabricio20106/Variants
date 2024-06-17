package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import com.junethewoods.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EntityExplosionContext;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Locale;

public class ExplodeBehavior extends StewBehavior {
    private final float radius;
    private final boolean createFire;
    private final BlockPos explosionPos;
    private final DamageSource source;
    private final Explosion.Mode blockInteraction;

    public ExplodeBehavior(float radius, boolean createFire, BlockPos explosionPos, DamageSource source, Explosion.Mode blockInteraction) {
        this.radius = radius;
        this.createFire = createFire;
        this.explosionPos = explosionPos;
        this.source = source;
        this.blockInteraction = blockInteraction;
    }

    public ExplodeBehavior() {
        this(0, false, BlockPos.ZERO, DamageSource.GENERIC, Explosion.Mode.NONE);
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        Explosion explosion = new Explosion(world, livEntity, this.source, new EntityExplosionContext(livEntity), this.explosionPos.getX(), this.explosionPos.getY(), this.explosionPos.getZ(), MathHelper.clamp(this.radius, 0, 128), this.createFire, this.blockInteraction);
        ForgeEventFactory.onExplosionStart(world, explosion);
        explosion.explode();
        explosion.finalizeExplosion(true);
    }

    @Override
    public CompoundNBT writePropertiesToNBT(ItemStack stewStack) {
        CompoundNBT properties = new CompoundNBT();
        properties.putFloat("radius", MathHelper.clamp(this.radius, 0, 128));
        properties.putBoolean("create_fire", this.createFire);
        properties.putString("source", NBTUtils.toMessageID(this.source.msgId));
        properties.putString("mode", this.blockInteraction.toString().toLowerCase(Locale.ROOT));
        properties.putIntArray("pos", new int[] {this.explosionPos.getX(), this.explosionPos.getY(), this.explosionPos.getZ()});
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.EXPLODE.get();
    }
}
