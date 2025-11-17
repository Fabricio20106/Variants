package melonystudios.variants.consumable.custom;

import com.google.gson.JsonObject;
import melonystudios.variants.Variants;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.JSONUtils;
import melonystudios.variants.util.damage.custom.DamageBehaviorSource;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.damage.DamageSourceUtils;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.EntityExplosionContext;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.*;

import static melonystudios.variants.util.NBTUtils.*;

public class ExplodeBehavior extends ConsumeBehavior {
    private final float radius;
    private final boolean createFire;
    private final boolean spawnEffectCloud;
    private final Vector3d position;
    private final DamageSource source;
    private Explosion.Mode blockInteraction;

    public ExplodeBehavior(float radius, boolean createFire, boolean spawnEffectCloud, Vector3d position, DamageSource source, Explosion.Mode blockInteraction) {
        this.radius = radius;
        this.createFire = createFire;
        this.spawnEffectCloud = spawnEffectCloud;
        this.position = position;
        this.source = source;
        this.blockInteraction = blockInteraction;
    }

    public ExplodeBehavior(float radius, boolean createFire, boolean spawnEffectCloud, DamageSource source, Explosion.Mode blockInteraction) {
        this(radius, createFire, spawnEffectCloud, Vector3d.ZERO, source, blockInteraction);
    }

    public ExplodeBehavior() {
        this(0, false, true, Vector3d.ZERO, DamageSource.GENERIC, Explosion.Mode.NONE);
    }

    public float radius() {
        return this.radius;
    }

    public boolean createFire() {
        return this.createFire;
    }

    public boolean spawnEffectCloud() {
        return this.spawnEffectCloud;
    }

    public Vector3d position() {
        return this.position;
    }

    public DamageSource damageSource() {
        return this.source;
    }

    public Explosion.Mode blockInteraction() {
        return this.blockInteraction;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        DamageSource trueSource = DamageSource.GENERIC;
        if (propertiesTag != null && propertiesTag.contains("source", Constants.TagTypes.COMPOUND)) {
            trueSource = new DamageBehaviorSource(propertiesTag, livEntity);
        } else if (propertiesTag != null && propertiesTag.contains("source", Constants.TagTypes.STRING)) {
            DamageSource source1 = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));
            if (source1 != null) trueSource = source1;
        } else {
            trueSource = this.damageSource();
        }

        if (this.blockInteraction() == null) this.blockInteraction = Explosion.Mode.NONE;

        Explosion explosion = new Explosion(world, livEntity, trueSource, new EntityExplosionContext(livEntity), this.position().x, this.position().y, this.position().z, MathHelper.clamp(this.radius(), 0, 128), this.createFire(), this.blockInteraction());
        ForgeEventFactory.onExplosionStart(world, explosion);
        explosion.explode();
        explosion.finalizeExplosion(true);
        spawnLingeringCloud(world, stack, livEntity);
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        Vector3d position = propertiesTag != null && propertiesTag.contains("position", Constants.TagTypes.LIST) ? NBTUtils.readVec3(propertiesTag, "position") : livEntity.position();
        DamageSource source = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));

        ExplodeBehavior explodeBehavior = new ExplodeBehavior(
                anyNumericOrFloatDefault("radius", propertiesTag, 0),
                booleanOrDefault("create_fire", propertiesTag, false),
                booleanOrDefault("spawn_effect_cloud", propertiesTag, true),
                position,
                source,
                NBTUtils.parseBlockInteractionFromString(stringOrDefault("mode", propertiesTag, "none"))
        );
        explodeBehavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        ListNBT position = new ListNBT();
        position.add(DoubleNBT.valueOf(this.position().x));
        position.add(DoubleNBT.valueOf(this.position().y));
        position.add(DoubleNBT.valueOf(this.position().z));
        properties.put("position", position);

        properties.putFloat("radius", (float) MathHelper.clamp(this.radius(), 0, Variants.INSTANCE.getConfig().explosionRadiusUpperLimit));
        if (this.createFire()) properties.putBoolean("create_fire", true);
        if (!this.spawnEffectCloud()) properties.putBoolean("spawn_effect_cloud", false);
        NBTUtils.writeDamageSourceOntoNBT(properties, this.damageSource());
        properties.putString("mode", this.blockInteraction().toString().toLowerCase(Locale.ROOT));
        return properties;
    }

    @Override
    public JsonObject writeToJSON(CompoundNBT propertiesTag) {
        return JSONUtils.writeExplosionToJSON(propertiesTag, new JsonObject());
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.EXPLODE.get();
    }

    private void spawnLingeringCloud(World world, ItemStack stewStack, LivingEntity livEntity) {
        Collection<EffectInstance> effects = new ArrayList<>(Collections.emptyList());
        try {
            effects.addAll(Objects.requireNonNull(NBTUtils.getEffectsFromNBT(world, stewStack)));
        } catch (NullPointerException ignored) {}

        if (!effects.isEmpty() && this.spawnEffectCloud()) {
            AreaEffectCloudEntity potionCloud = new AreaEffectCloudEntity(world, livEntity.getX(), livEntity.getY(), livEntity.getZ());
            potionCloud.setRadius(2.5F);
            potionCloud.setRadiusOnUse(-0.5F);
            potionCloud.setWaitTime(10);
            potionCloud.setDuration(potionCloud.getDuration() / 2);
            potionCloud.setRadiusPerTick(-potionCloud.getRadius() / (float) potionCloud.getDuration());

            for (EffectInstance effect : effects) potionCloud.addEffect(new EffectInstance(effect));

            world.addFreshEntity(potionCloud);
        }
    }
}
