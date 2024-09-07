package melonystudios.variants.stew.custom;

import melonystudios.variants.stew.StewBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class DefaultStewBehavior extends StewBehavior {
    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {}

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        executeBehavior(stewStack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        return new CompoundNBT();
    }
}
