package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class PlaySoundBehavior extends StewBehavior {
    private final SoundEvent id;
    private final SoundCategory category;
    private final BlockPos pos;
    private final boolean playAtPlayer;
    private final float volume;
    private final float pitch;

    public PlaySoundBehavior(SoundEvent sound, SoundCategory category, BlockPos pos, boolean playAtPlayer, float volume, float pitch) {
        this.id = sound;
        this.category = category;
        this.pos = pos;
        this.playAtPlayer = playAtPlayer;
        this.volume = volume;
        this.pitch = pitch;
    }

    public PlaySoundBehavior() {
        this(SoundEvents.COD_AMBIENT, SoundCategory.MASTER, BlockPos.ZERO, false, 0, 0);
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        float volume = MathHelper.clamp(this.volume, 0, Float.MAX_VALUE);
        float pitch = MathHelper.clamp(this.volume, 0, 2);
        if (this.playAtPlayer && livEntity instanceof PlayerEntity) {
            world.playSound((PlayerEntity) livEntity, this.pos, this.id, this.category, volume, pitch);
        } else {
            world.playSound(null, this.pos, this.id, this.category, volume, pitch);
        }
    }

    @Override
    public CompoundNBT writePropertiesToNBT(ItemStack stewStack) {
        CompoundNBT properties = new CompoundNBT();
        properties.putString("id", this.id.getRegistryName().toString());
        properties.putString("category", this.category.getName());
        properties.putIntArray("pos", new int[] {this.pos.getX(), this.pos.getY(), this.pos.getZ()});
        properties.putBoolean("play_at_player", this.playAtPlayer);
        properties.putFloat("volume", MathHelper.clamp(this.volume, 0, Float.MAX_VALUE));
        properties.putFloat("pitch", MathHelper.clamp(this.pitch, 0, 2));
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.PLAY_SOUND.get();
    }
}
