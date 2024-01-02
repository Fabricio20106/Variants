package com.junethewoods.variants.entity.custom;

import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.sound.VSSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class FishEntity extends AbstractGroupFishEntity {
    public FishEntity(EntityType<? extends FishEntity> entity, World world) {
        super(entity, world);
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(VSItems.FISH_BUCKET.get());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return VSSounds.FISH_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return VSSounds.FISH_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return VSSounds.FISH_HURT.get();
    }

    @Override
    protected SoundEvent getFlopSound() {
        return VSSounds.FISH_FLOP.get();
    }
}
