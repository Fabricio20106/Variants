package com.junethewoods.variants.common.block.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WitherBoneBlock extends RotatedPillarBlock {
    public WitherBoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && level.getDifficulty() != Difficulty.PEACEFUL) {
            if (entity instanceof LivingEntity) {
                LivingEntity livEntity = (LivingEntity) entity;
                if (!livEntity.isInvulnerableTo(DamageSource.WITHER)) {
                    livEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40));
                }
            }
        }
    }
}
