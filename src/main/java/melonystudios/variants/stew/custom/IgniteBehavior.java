package melonystudios.variants.stew.custom;

import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import static melonystudios.variants.util.NBTUtils.anyNumericOrIntDefault;

public class IgniteBehavior extends StewBehavior {
    private final int ticksOnFire;

    public IgniteBehavior(int ticksOnFire) {
        this.ticksOnFire = ticksOnFire;
    }

    public IgniteBehavior() {
        this(100);
    }

    public int getTicksOnFire() {
        return this.ticksOnFire;
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        if (!world.isClientSide) livEntity.setSecondsOnFire(this.ticksOnFire * 20);
    }

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, CompoundNBT propertiesTag) {
        IgniteBehavior igniteBehavior = new IgniteBehavior(anyNumericOrIntDefault("ticks_on_fire", propertiesTag, 100));
        igniteBehavior.executeBehavior(stewStack, world, livEntity);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT properties = new CompoundNBT();
        properties.putInt("ticks_on_fire", this.ticksOnFire);
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.IGNITE.get();
    }
}
