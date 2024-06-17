package com.junethewoods.variants.effect.source;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

public class DamageBehaviorSource extends DamageSource {
    private final CompoundNBT sourceTag;
    private final LivingEntity livEntity;

    public DamageBehaviorSource(CompoundNBT sourceTag, LivingEntity livEntity) {
        super(sourceTag.getString("message_id").isEmpty() ? "generic" : sourceTag.getString("message_id"));
        this.sourceTag = sourceTag;
        this.livEntity = livEntity;
        if (this.sourceTag.getBoolean("is_explosion")) this.setExplosion();
        if (this.sourceTag.getBoolean("is_projectile")) this.setProjectile();
        if (this.sourceTag.getBoolean("is_magic")) this.setMagic();
        if (this.sourceTag.getBoolean("is_fire")) this.setIsFire();
        if (this.sourceTag.getBoolean("scales_with_difficulty")) this.setScalesWithDifficulty();
        if (this.sourceTag.getBoolean("bypasses_armor")) this.bypassArmor();
        if (this.sourceTag.getBoolean("bypasses_invulnerability")) this.bypassInvul();
        if (this.sourceTag.getBoolean("bypasses_magic")) this.bypassMagic();
    }

    @Override
    public float getFoodExhaustion() {
        return this.sourceTag.getFloat("food_exhaustion");
    }

    @Nullable
    @Override
    public Entity getDirectEntity() {
        return this.livEntity;
    }
}
