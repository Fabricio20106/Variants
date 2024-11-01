package melonystudios.variants.consumable.custom;

import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.sound.VSSounds;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SPlaySoundPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Locale;

import static melonystudios.variants.util.NBTUtils.*;

public class PlaySoundBehavior extends ConsumeBehavior {
    private ResourceLocation id;
    private SoundCategory category;
    private final BlockPos pos;
    private final boolean playAtPlayer;
    private final float volume;
    private final float pitch;
    private final boolean useSoundPacket;

    public PlaySoundBehavior(ResourceLocation sound, SoundCategory category, BlockPos pos, boolean playAtPlayer, float volume, float pitch, boolean useSoundPacket) {
        this.id = sound;
        this.category = category;
        this.pos = pos;
        this.playAtPlayer = playAtPlayer;
        this.volume = volume;
        this.pitch = pitch;
        this.useSoundPacket = useSoundPacket;
    }

    public ResourceLocation getSoundLocation() {
        return this.id;
    }

    public SoundCategory getCategory() {
        return this.category;
    }

    public BlockPos getPlayPosition() {
        return this.pos;
    }

    public boolean playsAtPlayer() {
        return this.playAtPlayer;
    }

    public float getVolume() {
        return this.volume;
    }

    public float getPitch() {
        return this.pitch;
    }

    public PlaySoundBehavior(ResourceLocation sound, SoundCategory category, boolean playAtPlayer, float volume, float pitch) {
        this(sound, category, BlockPos.ZERO, playAtPlayer, volume, pitch, false);
    }

    public PlaySoundBehavior() {
        this(VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get().getRegistryName(), SoundCategory.MASTER, BlockPos.ZERO, false, 0, 0, false);
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        float volume = MathHelper.clamp(this.volume, 0, Float.MAX_VALUE);
        float pitch = MathHelper.clamp(this.volume, 0, 2);
        if (this.id == null) this.id = VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get().getRegistryName();
        if (this.category == null) this.category = SoundCategory.MASTER;
        SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(this.id);

        if (this.useSoundPacket && livEntity instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) livEntity).connection.send(new SPlaySoundPacket(this.id, this.category, livEntity.position(), volume, pitch));
        } else {
            assert soundEvent != null;
            if (this.playAtPlayer && livEntity instanceof PlayerEntity) {
                world.playSound((PlayerEntity) livEntity, this.pos, soundEvent, this.category, volume, pitch);
            } else {
                world.playSound(null, this.pos, soundEvent, this.category, volume, pitch);
            }
        }
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        boolean useSoundPacket = true;
        ResourceLocation soundLocation = ResourceLocation.tryParse(stringOrDefault("sound", propertiesTag, VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get().getRegistryName().toString()));
        if (ForgeRegistries.SOUND_EVENTS.containsKey(soundLocation)) useSoundPacket = false;

        SoundCategory category1 = SoundCategory.valueOf(stringOrDefault("category", propertiesTag, "master").toUpperCase(Locale.ROOT));
        BlockPos pos = (propertiesTag != null && propertiesTag.contains("pos", Constants.TagTypes.INTEGER_ARRAY)) ? NBTUtils.readBlockPos(propertiesTag) : livEntity.blockPosition();
        PlaySoundBehavior playSoundBehavior = new PlaySoundBehavior(soundLocation, category1, pos, booleanOrDefault("play_at_player", propertiesTag, false), anyNumericOrFloatDefault("volume", propertiesTag, 0), anyNumericOrFloatDefault(
                "pitch", propertiesTag, 0), useSoundPacket);
        playSoundBehavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        properties.putString("sound", this.id.toString());
        properties.putString("category", this.category.getName());
        properties.putIntArray("pos", new int[] {this.pos.getX(), this.pos.getY(), this.pos.getZ()});
        if (this.playAtPlayer) properties.putBoolean("play_at_player", true);
        properties.putFloat("volume", MathHelper.clamp(this.volume, 0, Float.MAX_VALUE));
        properties.putFloat("pitch", (float) MathHelper.clamp(this.pitch, 0, VSConfigs.COMMON_CONFIGS.soundPitchUpperLimit.get()));
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.PLAY_SOUND.get();
    }
}
