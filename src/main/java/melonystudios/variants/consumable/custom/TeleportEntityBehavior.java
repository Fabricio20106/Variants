package melonystudios.variants.consumable.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.event.custom.BehaviorTeleportEvent;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Random;

import static melonystudios.variants.util.NBTUtils.anyNumericOrFloatDefault;
import static melonystudios.variants.util.NBTUtils.booleanOrDefault;

public class TeleportEntityBehavior extends ConsumeBehavior {
    private final boolean teleportRandomly;
    private final float diameter;
    private final Vector3d position;
    @Nullable
    private CompoundNBT properties;

    public TeleportEntityBehavior(float diameter) {
        this(true, diameter, Vector3d.ZERO);
    }

    public TeleportEntityBehavior(Vector3d position) {
        this(false, 16, position);
    }

    public TeleportEntityBehavior(boolean teleportRandomly, float diameter, Vector3d position) {
        this.teleportRandomly = teleportRandomly;
        this.diameter = diameter;
        this.position = position;
    }

    public TeleportEntityBehavior() {
        this(true, 16, Vector3d.ZERO);
    }

    public boolean teleportRandomly() {
        return this.teleportRandomly;
    }

    public float diameter() {
        return this.diameter;
    }

    public Vector3d position() {
        return this.position;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        Vector3d pos = this.position();
        Random rand = livEntity.getRandom();

        // spawns particles
        if (!isAllowedToTeleportTarget(livEntity)) return;
        for (int i = 0; i < 32; ++i) {
            world.addParticle(ParticleTypes.PORTAL, pos.x, pos.y + rand.nextDouble() * 2, pos.z, rand.nextGaussian(), 0, rand.nextGaussian());
        }
        if (world.isClientSide()) return;

        if (this.teleportRandomly()) {
            teleportWithinDiameter(stack, world, livEntity, this.diameter());
        } else {
            if (livEntity.isPassenger()) livEntity.unRide();

            // cancel the teleportation if the event was cancelled
            BehaviorTeleportEvent event = VSUtils.exactTeleportThroughBehavior(stack, world, livEntity, pos);
            if (event.isCanceled()) return;

            SoundEvent teleportSound = livEntity instanceof FoxEntity ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
            livEntity.teleportTo(pos.x, pos.y, pos.z);
            livEntity.fallDistance = 0;
            world.playSound(null, pos.x, pos.y, pos.z, teleportSound, livEntity instanceof FoxEntity ? SoundCategory.NEUTRAL : SoundCategory.PLAYERS, 1, 1);
        }
    }

    /// Teleports an entity to any valid position within the specified diameter.
    /// @param stack The item stack with the "teleport entity" behavior.
    /// @param world The world.
    /// @param livEntity The entity running the effect.
    /// @param diameter The diameter that the entity can teleport within.
    public static void teleportWithinDiameter(ItemStack stack, World world, LivingEntity livEntity, float diameter) {
        Random rand = livEntity.getRandom();
        for (int attempts = 0; attempts < 16; ++attempts) {
            double x = livEntity.getX() + (rand.nextDouble() - 0.5) * diameter;
            double y = MathHelper.clamp(
                    livEntity.getY() + (rand.nextInt((int) diameter) - (diameter / 2)),
                    0, // min build height
                    world.getHeight() - 1
            );
            double z = livEntity.getZ() + (rand.nextDouble() - 0.5) * diameter;

            // cancel the teleportation if the event was cancelled
            BehaviorTeleportEvent event = VSUtils.randomTeleportThroughBehavior(stack, world, livEntity, x, y, z, diameter);
            if (event.isCanceled()) return;

            if (livEntity.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                SoundEvent sound = SoundEvents.CHORUS_FRUIT_TELEPORT;
                SoundCategory source = SoundCategory.PLAYERS;

                if (livEntity instanceof FoxEntity) {
                    sound = SoundEvents.FOX_TELEPORT;
                    source = SoundCategory.NEUTRAL;
                }

                world.playSound(null, livEntity.getX(), livEntity.getY(), livEntity.getZ(), sound, source, 1, 1);
                livEntity.fallDistance = 0;
                break;
            }
        }
    }

    /// Whether an entity is allowed to be teleported.
    /// @param livEntity The entity running the effect.
    public static boolean isAllowedToTeleportTarget(LivingEntity livEntity) {
        return livEntity.isAlive() && !livEntity.isSleeping();
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        this.properties = propertiesTag;
        Vector3d position = propertiesTag != null && propertiesTag.contains("position", Constants.TagTypes.LIST) ? NBTUtils.readVec3(propertiesTag, "position") : livEntity.position();
        TeleportEntityBehavior behavior = new TeleportEntityBehavior(booleanOrDefault("teleport_randomly", propertiesTag, true), anyNumericOrFloatDefault("diameter", propertiesTag, 16), position);
        behavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        if (this.teleportRandomly()) {
            properties.putFloat("diameter", this.diameter());
        } else {
            properties.putBoolean("teleport_randomly", false);
            ListNBT position = new ListNBT();
            position.add(DoubleNBT.valueOf(this.position().x));
            position.add(DoubleNBT.valueOf(this.position().y));
            position.add(DoubleNBT.valueOf(this.position().z));
            properties.put("position", position);
        }
        return properties;
    }

    @Override
    public JsonObject writeToJSON(CompoundNBT propertiesTag) {
        JsonObject properties = new JsonObject();
        if (this.teleportRandomly()) {
            properties.addProperty("diameter", this.diameter());
        } else {
            properties.addProperty("teleport_randomly", false);
            JsonArray position = new JsonArray();
            position.add(this.position().x);
            position.add(this.position().y);
            position.add(this.position().z);
            properties.add("position", position);
        }
        return properties;
    }

    @Override
    public List<ITextComponent> addToTooltip(ItemStack stack, @Nullable World world, ITooltipFlag flag) {
        List<ITextComponent> tooltip = super.addToTooltip(stack, world, flag);
        if (!flag.isAdvanced() || this.properties == null) return tooltip;

        if (booleanOrDefault("teleport_randomly", this.properties, true)) {
            tooltip.add(new TranslationTextComponent("tooltip.variants.behavior.teleport_entity.diameter", (int) this.properties.getFloat("teleport_diameter")).withStyle(TextFormatting.GRAY));
        } else {
            Vector3d position = this.properties.contains("position", Constants.TagTypes.LIST) ? NBTUtils.readVec3(this.properties, "position") : null;
            if (position != null) {
                tooltip.add(new TranslationTextComponent("tooltip.variants.behavior.teleport_entity.position", position.x, position.y, position.z).withStyle(TextFormatting.GRAY));
            }
        }
        return tooltip;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.TELEPORT_ENTITY.get();
    }
}
