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

    public DamageSource damageSource() {
        return this.source;
    }

    public float amount() {
        return this.amount;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (this.source != null) livEntity.hurt(this.source, this.amount);
    }

    // just optimizing this behavior because why did I copy it in both methods?
    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (propertiesTag == null) return;
        DamageSource source;

        if (propertiesTag.contains("source", Constants.TagTypes.COMPOUND)) {
            source = new DamageBehaviorSource(propertiesTag, livEntity);
            DamageSourceUtils.DATA_DRIVEN_SOURCES.put(namespace("damage_behavior", stringOrDefault("message_id", propertiesTag.getCompound("source"), "generic")), source);
        } else if (propertiesTag.contains("source", Constants.TagTypes.STRING)) {
            source = DamageSourceUtils.fromLocationWithKiller(livEntity, ResourceLocation.tryParse(stringOrDefault("source", propertiesTag, "minecraft:generic")));
        } else {
            source = this.source;
        }

        DamageEntityBehavior behavior = new DamageEntityBehavior(source, anyNumericOrFloatDefault("amount", propertiesTag, 0));
        behavior.runBehavior(stack, world, livEntity, propertiesTag);
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
