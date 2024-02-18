package com.junethewoods.variants.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class PottedTorchBlock extends FlowerPotBlock {
    protected final IParticleData flameParticle;

    public PottedTorchBlock(Block torch, IParticleData torchParticle, Properties properties) {
        super(torch, properties);
        this.flameParticle = torchParticle;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        double posX = (double) pos.getX() + 0.5D;
        double posY = (double) pos.getY() + 0.9D;
        double posZ = (double) pos.getZ() + 0.5D;
        world.addParticle(ParticleTypes.SMOKE, posX, posY, posZ, 0, 0, 0);
        world.addParticle(this.flameParticle, posX, posY, posZ, 0, 0, 0);
    }
}
