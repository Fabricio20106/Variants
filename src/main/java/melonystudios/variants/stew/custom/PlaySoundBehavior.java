package melonystudios.variants.stew.custom;

import melonystudios.variants.sound.VSSounds;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

import static melonystudios.variants.util.NBTUtils.*;

public class PlaySoundBehavior extends StewBehavior {
    private SoundEvent id;
    private SoundCategory category;
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

    public PlaySoundBehavior(SoundEvent sound, SoundCategory category, boolean playAtPlayer, float volume, float pitch) {
        this(sound, category, BlockPos.ZERO, playAtPlayer, volume, pitch);
    }

    public PlaySoundBehavior() {
        this(VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get(), SoundCategory.MASTER, BlockPos.ZERO, false, 0, 0);
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        float volume = MathHelper.clamp(this.volume, 0, Float.MAX_VALUE);
        float pitch = MathHelper.clamp(this.volume, 0, 2);
        if (this.id == null) this.id = VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get();
        if (this.category == null) this.category = SoundCategory.MASTER;

        if (this.playAtPlayer && livEntity instanceof PlayerEntity) {
            world.playSound((PlayerEntity) livEntity, this.pos, this.id, this.category, volume, pitch);
        } else {
            world.playSound(null, this.pos, this.id, this.category, volume, pitch);
        }
    }

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, CompoundNBT propertiesTag) {
        SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.tryParse(stringOrDefault("id", propertiesTag, VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get().getRegistryName().toString())));
        SoundCategory category1 = SoundCategory.valueOf(stringOrDefault("category", propertiesTag, "master").toUpperCase(Locale.ROOT));
        BlockPos pos = propertiesTag.contains("pos") ? NBTUtils.readBlockPos(propertiesTag) : livEntity.blockPosition();
        PlaySoundBehavior playSoundBehavior = new PlaySoundBehavior(sound, category1, pos, booleanOrDefault("play_at_player", propertiesTag, false), floatOrDefault("volume", propertiesTag, 0), floatOrDefault("pitch",
                propertiesTag, 0));
        playSoundBehavior.executeBehavior(stewStack, world, livEntity);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
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
