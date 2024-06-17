package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
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
        livEntity.hurt(fromMessageID(toMessageID(this.source.msgId)), this.amount);
        // /give @p variants:exponential_aljan_fungi_stew{behavior:{id:"variants:damage_entity",properties:{source:"out_of_world",amount:3.0f}}}
//        CompoundNBT propertiesTag = getBehaviorProperties(stack);
//        if (propertiesTag != null && !propertiesTag.getCompound("source").isEmpty()) {
//            CompoundNBT sourceTag = propertiesTag.getCompound("source");
//            livEntity.hurt(new DamageBehaviorSource(sourceTag, livEntity), this.amount);
//        }
    }

    @Override
    public CompoundNBT writePropertiesToNBT(ItemStack stewStack) {
        CompoundNBT properties = new CompoundNBT();
        properties.putString("source", toMessageID(this.source.msgId));
        properties.putFloat("amount", this.amount);
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.DAMAGE_ENTITY.get();
    }
}
