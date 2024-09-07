package melonystudios.variants.stew.custom;

import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.damage.custom.DamageBehaviorSource;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.util.damage.DamageSourceUtils;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EntityExplosionContext;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.*;

import static melonystudios.variants.util.NBTUtils.*;

public class ExplodeBehavior extends StewBehavior {
    private final float radius;
    private final boolean createFire;
    private final boolean spawnEffectCloud;
    private final BlockPos explosionPos;
    private final DamageSource source;
    private Explosion.Mode blockInteraction;

    public ExplodeBehavior(float radius, boolean createFire, boolean spawnEffectCloud, BlockPos explosionPos, DamageSource source, Explosion.Mode blockInteraction) {
        this.radius = radius;
        this.createFire = createFire;
        this.spawnEffectCloud = spawnEffectCloud;
        this.explosionPos = explosionPos;
        this.source = source;
        this.blockInteraction = blockInteraction;
    }

    public ExplodeBehavior(float radius, boolean createFire, boolean spawnEffectCloud, DamageSource source, Explosion.Mode blockInteraction) {
        this(radius, createFire, spawnEffectCloud, BlockPos.ZERO, source, blockInteraction);
    }

    public ExplodeBehavior() {
        this(0, false, true, BlockPos.ZERO, DamageSource.GENERIC, Explosion.Mode.NONE);
    }

    public float getExplosionRadius() {
        return this.radius;
    }

    public boolean createsFire() {
        return this.createFire;
    }

    public boolean spawnsEffectCloud() {
        return this.spawnEffectCloud;
    }

    public BlockPos getExplosionPos() {
        return this.explosionPos;
    }

    public DamageSource getSource() {
        return this.source;
    }

    public Explosion.Mode getExplosionMode() {
        return this.blockInteraction;
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        DamageSource trueSource = DamageSource.GENERIC;
        if (propertiesTag != null && propertiesTag.contains("source", Constants.TagTypes.COMPOUND)) {
            trueSource = new DamageBehaviorSource(propertiesTag, livEntity);
        } else if (propertiesTag != null && propertiesTag.contains("source", Constants.TagTypes.STRING)) {
            DamageSource source1 = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));
            if (source1 != null) trueSource = source1;
        } else {
            trueSource = this.source;
        }

        if (this.blockInteraction == null) this.blockInteraction = Explosion.Mode.NONE;

        Explosion explosion = new Explosion(world, livEntity, trueSource, new EntityExplosionContext(livEntity), this.explosionPos.getX(), this.explosionPos.getY(), this.explosionPos.getZ(), MathHelper.clamp(this.radius, 0, 128), this.createFire, this.blockInteraction);
        ForgeEventFactory.onExplosionStart(world, explosion);
        explosion.explode();
        explosion.finalizeExplosion(true);
        spawnLingeringCloud(world, stack, livEntity);
    }

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        BlockPos pos = (propertiesTag != null && propertiesTag.contains("pos", Constants.TagTypes.INTEGER_ARRAY)) ? NBTUtils.readBlockPos(propertiesTag) : livEntity.blockPosition();
        DamageSource source1 = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));
        ExplodeBehavior explodeBehavior = new ExplodeBehavior(anyNumericOrFloatDefault("radius", propertiesTag, 0), booleanOrDefault("create_fire", propertiesTag, false), booleanOrDefault("spawn_effect_cloud", propertiesTag,
                true), pos, source1, Explosion.Mode.valueOf(stringOrDefault("mode", propertiesTag, "none").toUpperCase(Locale.ROOT)));
        explodeBehavior.executeBehavior(stewStack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT properties = new CompoundNBT();
        properties.putFloat("radius", (float) MathHelper.clamp(this.radius, 0, VSConfigs.COMMON_CONFIGS.explosionRadiusUpperLimit.get()));
        if (this.createFire) properties.putBoolean("create_fire", true);
        if (this.spawnEffectCloud) properties.putBoolean("spawn_effect_cloud", true);
        NBTUtils.writeDamageSourceOntoNBT(properties, this.source);
        properties.putString("mode", this.blockInteraction.toString().toLowerCase(Locale.ROOT));
        properties.putIntArray("pos", new int[] {this.explosionPos.getX(), this.explosionPos.getY(), this.explosionPos.getZ()});
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.EXPLODE.get();
    }

    private void spawnLingeringCloud(World world, ItemStack stewStack, LivingEntity livEntity) {
        Collection<EffectInstance> effects = new ArrayList<>(Collections.emptyList());
        try {
            effects.addAll(Objects.requireNonNull(NBTUtils.getEffectsFromNBT(world, stewStack)));
        } catch (NullPointerException ignored) {}

        if (!effects.isEmpty() && this.spawnEffectCloud) {
            AreaEffectCloudEntity potionCloud = new AreaEffectCloudEntity(world, livEntity.getX(), livEntity.getY(), livEntity.getZ());
            potionCloud.setRadius(2.5F);
            potionCloud.setRadiusOnUse(-0.5F);
            potionCloud.setWaitTime(10);
            potionCloud.setDuration(potionCloud.getDuration() / 2);
            potionCloud.setRadiusPerTick(-potionCloud.getRadius() / (float) potionCloud.getDuration());

            for(EffectInstance effect : effects) potionCloud.addEffect(new EffectInstance(effect));

            world.addFreshEntity(potionCloud);
        }
    }
}
