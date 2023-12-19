package com.junethewoods.variants.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class PottedTorchBlock extends FlowerPotBlock {
    protected final ParticleOptions flameParticle;

    public PottedTorchBlock(Block flower, Properties properties, ParticleOptions torchParticle) {
        super(flower, properties);
        this.flameParticle = torchParticle;
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
        double addedX = (double) pos.getX() + 0.5d;
        double addedY = (double) pos.getY() + 1;
        double addedZ = (double) pos.getZ() + 0.5d;

        level.addParticle(ParticleTypes.SMOKE, addedX, addedY, addedZ, 0, 0, 0);
        level.addParticle(this.flameParticle, addedX, addedY, addedZ, 0, 0, 0);
    }
}
