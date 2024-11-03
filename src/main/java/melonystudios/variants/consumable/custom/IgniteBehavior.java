package melonystudios.variants.consumable.custom;

import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static melonystudios.variants.util.NBTUtils.anyNumericOrIntDefault;

public class IgniteBehavior extends ConsumeBehavior {
    private final int ticksOnFire;

    public IgniteBehavior(int ticksOnFire) {
        this.ticksOnFire = ticksOnFire;
    }

    public IgniteBehavior() {
        this(100);
    }

    public int ticksOnFire() {
        return this.ticksOnFire;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (!world.isClientSide) livEntity.setSecondsOnFire(this.ticksOnFire / 20);
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        IgniteBehavior igniteBehavior = new IgniteBehavior(anyNumericOrIntDefault("ticks_on_fire", propertiesTag, 100));
        igniteBehavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        properties.putInt("ticks_on_fire", this.ticksOnFire);
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.IGNITE.get();
    }
}
