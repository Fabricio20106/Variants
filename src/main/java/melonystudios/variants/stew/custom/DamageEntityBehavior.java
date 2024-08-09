package melonystudios.variants.stew.custom;

import melonystudios.variants.util.Constants;
import melonystudios.variants.util.damage.custom.DamageBehaviorSource;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.util.damage.DamageSourceUtils;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import static melonystudios.variants.util.NBTUtils.*;
import static melonystudios.variants.util.VSUtils.namespace;

public class DamageEntityBehavior extends StewBehavior {
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
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        CompoundNBT propertiesTag = getBehaviorProperties(stack);
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
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, CompoundNBT propertiesTag) {
        if (propertiesTag.contains("source", Constants.TagTypes.COMPOUND)) {
            DamageEntityBehavior damageBehavior = new DamageEntityBehavior(new DamageBehaviorSource(propertiesTag, livEntity), anyNumericOrFloatDefault("amount", propertiesTag, 0));
            damageBehavior.executeBehavior(stewStack, world, livEntity);
        } else if (propertiesTag.contains("source", Constants.TagTypes.STRING)) {
            DamageSource source1 = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));
            DamageEntityBehavior damageBehavior = new DamageEntityBehavior(source1, anyNumericOrFloatDefault("amount", propertiesTag, 0));
            damageBehavior.executeBehavior(stewStack, world, livEntity);
        }
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT properties = new CompoundNBT();
        NBTUtils.writeDamageSourceOntoNBT(properties, this.source);
        properties.putFloat("amount", this.amount);
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.DAMAGE_ENTITY.get();
    }
}
