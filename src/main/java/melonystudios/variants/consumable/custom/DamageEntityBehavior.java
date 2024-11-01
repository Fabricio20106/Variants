package melonystudios.variants.consumable.custom;

import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.damage.custom.DamageBehaviorSource;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.damage.DamageSourceUtils;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static melonystudios.variants.util.NBTUtils.*;
import static melonystudios.variants.util.VSUtils.namespace;

public class DamageEntityBehavior extends ConsumeBehavior {
    private final DamageSource source;
    private final float amount;

    public DamageEntityBehavior(DamageSource source, float amount) {
        this.source = source;
        this.amount = amount;
    }

    public DamageEntityBehavior() {
        this(DamageSource.GENERIC, 0);
    }

    public DamageSource getSource() {
        return this.source;
    }

    public float getAmount() {
        return this.amount;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (propertiesTag != null && propertiesTag.contains("source", Constants.TagTypes.COMPOUND)) {
            CompoundNBT sourceTag = propertiesTag.getCompound("source");
            DamageBehaviorSource behaviorSource = new DamageBehaviorSource(sourceTag, livEntity);
            DamageSourceUtils.DATA_DRIVEN_SOURCES.put(namespace("damage_behavior", stringOrDefault("message_id", sourceTag, "generic")), behaviorSource);
            livEntity.hurt(behaviorSource, this.amount);
        } else if (propertiesTag != null && propertiesTag.contains("source", Constants.TagTypes.STRING)) {
            DamageSource source1 = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));
            if (source1 != null) livEntity.hurt(source1, this.amount);
        }
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        assert propertiesTag != null;
        if (propertiesTag.contains("source", Constants.TagTypes.COMPOUND)) {
            DamageEntityBehavior damageBehavior = new DamageEntityBehavior(new DamageBehaviorSource(propertiesTag, livEntity), anyNumericOrFloatDefault("amount", propertiesTag, 0));
            damageBehavior.runBehavior(stack, world, livEntity, propertiesTag);
        } else if (propertiesTag.contains("source", Constants.TagTypes.STRING)) {
            DamageSource source1 = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));
            DamageEntityBehavior damageBehavior = new DamageEntityBehavior(source1, anyNumericOrFloatDefault("amount", propertiesTag, 0));
            damageBehavior.runBehavior(stack, world, livEntity, propertiesTag);
        }
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        NBTUtils.writeDamageSourceOntoNBT(properties, this.source);
        properties.putFloat("amount", this.amount);
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.DAMAGE_ENTITY.get();
    }
}
