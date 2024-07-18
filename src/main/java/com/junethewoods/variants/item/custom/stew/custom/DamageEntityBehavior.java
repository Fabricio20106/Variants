package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.effect.source.DamageBehaviorSource;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import com.junethewoods.variants.util.DamageSourceUtils;
import com.junethewoods.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import static com.junethewoods.variants.util.NBTUtils.floatOrDefault;
import static com.junethewoods.variants.util.NBTUtils.stringOrDefault;

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

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        CompoundNBT propertiesTag = getBehaviorProperties(stack);
        if (propertiesTag != null && propertiesTag.contains("source", NBTUtils.COMPOUND)) {
            CompoundNBT sourceTag = propertiesTag.getCompound("source");
            DamageBehaviorSource behaviorSource = new DamageBehaviorSource(sourceTag, livEntity);
            DamageSourceUtils.DATA_DRIVEN_SOURCES.put(damageBehavior(stringOrDefault("message_id", sourceTag, "generic")), behaviorSource);
            livEntity.hurt(behaviorSource, this.amount);
        } else if (propertiesTag != null && propertiesTag.contains("source", NBTUtils.STRING)) {
            DamageSource source1 = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));
            if (source1 != null) livEntity.hurt(source1, this.amount);
        }
    }

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, CompoundNBT propertiesTag) {
        if (propertiesTag.contains("source", NBTUtils.COMPOUND)) {
            DamageEntityBehavior damageBehavior = new DamageEntityBehavior(new DamageBehaviorSource(propertiesTag, livEntity), floatOrDefault("amount", propertiesTag, 0));
            damageBehavior.executeBehavior(stewStack, world, livEntity);
        } else if (propertiesTag.contains("source", NBTUtils.STRING)) {
            DamageSource source1 = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));
            DamageEntityBehavior damageBehavior = new DamageEntityBehavior(source1, floatOrDefault("amount", propertiesTag, 0));
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

    private static ResourceLocation damageBehavior(String name) {
        String[] location = decompose(name, ':');
        if (StringUtils.isEmpty(location[0])) {
            return new ResourceLocation("damage_behavior", location[1]);
        } else return new ResourceLocation(location[0], location[1]);
    }

    protected static String[] decompose(String location, char separator) {
        String[] stringArray = new String[] {"damage_behavior", location};
        int separatorIndex = location.indexOf(separator);
        if (separatorIndex >= 0) {
            stringArray[1] = location.substring(separatorIndex + 1);
            if (separatorIndex >= 1) stringArray[0] = location.substring(0, separatorIndex);
        }
        return stringArray;
    }
}
