package melonystudios.variants.consumable.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import melonystudios.variants.Variants;
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
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.play.server.SPlaySoundPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Locale;

import static melonystudios.variants.util.NBTUtils.*;

public class PlaySoundBehavior extends ConsumeBehavior {
    private ResourceLocation sound;
    private SoundCategory source;
    private final Vector3d position;
    private final boolean playAtPlayer;
    private final float volume;
    private final float pitch;
    private final boolean sendSoundPacket;

    public PlaySoundBehavior(ResourceLocation sound, SoundCategory source, Vector3d position, boolean playAtPlayer, float volume, float pitch, boolean sendSoundPacket) {
        this.sound = sound;
        this.source = source;
        this.position = position;
        this.playAtPlayer = playAtPlayer;
        this.volume = volume;
        this.pitch = pitch;
        this.sendSoundPacket = sendSoundPacket;
    }

    public PlaySoundBehavior(ResourceLocation sound, SoundCategory source, boolean playAtPlayer, float volume, float pitch) {
        this(sound, source, Vector3d.ZERO, playAtPlayer, volume, pitch, false);
    }

    public PlaySoundBehavior() {
        this(VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get().getRegistryName(), SoundCategory.MASTER, Vector3d.ZERO, false, 0, 0, false);
    }

    public ResourceLocation sound() {
        return this.sound;
    }

    public SoundCategory source() {
        return this.source;
    }

    public Vector3d position() {
        return this.position;
    }

    public boolean playAtPlayer() {
        return this.playAtPlayer;
    }

    public float volume() {
        return this.volume;
    }

    public float pitch() {
        return this.pitch;
    }

    public boolean sendsSoundPacket() {
        return this.sendSoundPacket;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        float volume = MathHelper.clamp(this.volume(), 0, Float.MAX_VALUE);
        float pitch = (float) MathHelper.clamp(this.pitch(), 0, Variants.INSTANCE.getConfig().soundPitchUpperLimit);
        if (this.sound() == null) this.sound = VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get().getRegistryName();
        if (this.source() == null) this.source = SoundCategory.MASTER;
        SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(this.sound());

        if (this.sendsSoundPacket() && livEntity instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) livEntity).connection.send(new SPlaySoundPacket(this.sound(), this.source(), livEntity.position(), volume, pitch));
        } else {
            if (soundEvent == null) return;
            if (this.playAtPlayer() && livEntity instanceof PlayerEntity) {
                world.playSound((PlayerEntity) livEntity, this.position().x, this.position().y, this.position().z, soundEvent, this.source(), volume, pitch);
            } else {
                world.playSound(null, this.position().x, this.position().y, this.position().z, soundEvent, this.source(), volume, pitch);
            }
        }
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        ResourceLocation soundLocation = ResourceLocation.tryParse(stringOrDefault("sound", propertiesTag, VSSounds.PLAY_SOUND_BEHAVIOR_DEFAULT.get().getRegistryName().toString()));
        boolean useSoundPacket = ForgeRegistries.SOUND_EVENTS.containsKey(soundLocation) || booleanOrDefault("send_sound_packet", propertiesTag, false);

        SoundCategory source = SoundCategory.valueOf(stringOrDefault("source", propertiesTag, "master").toUpperCase(Locale.ROOT));
        Vector3d position = propertiesTag != null && propertiesTag.contains("position", Constants.TagTypes.LIST) ? NBTUtils.readVec3(propertiesTag, "position") : livEntity.position();
        PlaySoundBehavior playSoundBehavior = new PlaySoundBehavior(soundLocation, source, position,
                booleanOrDefault("play_at_player", propertiesTag, false),
                anyNumericOrFloatDefault("volume", propertiesTag, 0),
                anyNumericOrFloatDefault("pitch", propertiesTag, 0), useSoundPacket);
        playSoundBehavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        ListNBT position = new ListNBT();
        position.add(DoubleNBT.valueOf(this.position().x));
        position.add(DoubleNBT.valueOf(this.position().y));
        position.add(DoubleNBT.valueOf(this.position().z));
        properties.put("position", position);

        properties.putString("sound", this.sound().toString());
        properties.putString("source", this.source().getName());
        if (this.playAtPlayer()) properties.putBoolean("play_at_player", true);
        properties.putFloat("volume", MathHelper.clamp(this.volume(), 0, Float.MAX_VALUE));
        properties.putFloat("pitch", (float) MathHelper.clamp(this.pitch(), 0, Variants.INSTANCE.getConfig().soundPitchUpperLimit));
        if (this.sendsSoundPacket()) properties.putBoolean("send_sound_packet", true);
        return properties;
    }

    @Override
    public JsonObject writeToJSON(CompoundNBT propertiesTag) {
        JsonObject properties = new JsonObject();
        JsonArray position = new JsonArray();
        position.add(this.position().x);
        position.add(this.position().y);
        position.add(this.position().z);
        properties.add("position", position);

        properties.addProperty("sound", this.sound().toString());
        properties.addProperty("source", this.source().getName());
        if (this.playAtPlayer()) properties.addProperty("play_at_player", true);
        properties.addProperty("volume", MathHelper.clamp(this.volume, 0, Float.MAX_VALUE));
        properties.addProperty("pitch", (float) MathHelper.clamp(this.pitch, 0, Variants.INSTANCE.getConfig().soundPitchUpperLimit));
        if (this.sendsSoundPacket()) properties.addProperty("send_sound_packet", true);
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.PLAY_SOUND.get();
    }
}
