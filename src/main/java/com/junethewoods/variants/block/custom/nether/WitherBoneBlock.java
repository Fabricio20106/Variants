package com.junethewoods.variants.block.custom.nether;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class WitherBoneBlock extends RotatedPillarBlock {
    public WitherBoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        if (!world.isClientSide && world.getDifficulty() != Difficulty.PEACEFUL) {
            if (entity instanceof LivingEntity) {
                LivingEntity livEntity = (LivingEntity) entity;
                if (!livEntity.isInvulnerableTo(DamageSource.WITHER)) {
                    livEntity.addEffect(new EffectInstance(Effects.WITHER, 40));
                }
            }
        }
        super.stepOn(world, pos, entity);
    }
}
