package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.effect.source.DamageBehaviorSource;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import com.junethewoods.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import static com.junethewoods.variants.util.NBTUtils.fromMessageID;
import static com.junethewoods.variants.util.NBTUtils.toMessageID;

public class DamageEntityBehavior extends StewBehavior {
    private final DamageSource source;
    private final float amount;

    public DamageEntityBehavior(DamageSource source, float amount) {
        this.source = source;
        this.amount = amount;
    }

    public DamageEntityBehavior() {
        this(DamageSource.GENERIC, 1);
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        CompoundNBT propertiesTag = getBehaviorProperties(stack);
        if (propertiesTag != null && propertiesTag.contains("source", NBTUtils.COMPOUND)) {
            CompoundNBT sourceTag = propertiesTag.getCompound("source");
            livEntity.hurt(new DamageBehaviorSource(sourceTag, livEntity), this.amount);
        } else if (propertiesTag != null && propertiesTag.contains("source", NBTUtils.STRING)) {
            livEntity.hurt(fromMessageID(livEntity, toMessageID(this.source.msgId)), this.amount);
        }
    }

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, CompoundNBT propertiesTag) {
        if (propertiesTag.contains("source", NBTUtils.COMPOUND)) {
            DamageEntityBehavior damageBehavior = new DamageEntityBehavior(new DamageBehaviorSource(propertiesTag, livEntity), propertiesTag.getFloat("amount"));
            damageBehavior.executeBehavior(stewStack, world, livEntity);
        } else if (propertiesTag.contains("source", NBTUtils.STRING)) {
            DamageEntityBehavior damageBehavior = new DamageEntityBehavior(NBTUtils.fromMessageID(livEntity, propertiesTag.getString("source")), propertiesTag.getFloat("amount"));
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
