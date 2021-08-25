package com.junethewoods.variants.common.block;

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
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote && worldIn.getDifficulty() != Difficulty.PEACEFUL) {
            if (entityIn instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entityIn;
                if (!livingEntity.isInvulnerableTo(DamageSource.WITHER)) {
                    livingEntity.addPotionEffect(new EffectInstance(Effects.WITHER, 40));
                }
            }
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
