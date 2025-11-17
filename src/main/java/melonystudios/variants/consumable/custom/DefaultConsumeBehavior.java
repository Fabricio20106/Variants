package melonystudios.variants.consumable.custom;

import com.google.gson.JsonObject;
import melonystudios.variants.consumable.ConsumeBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class DefaultConsumeBehavior extends ConsumeBehavior {
    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {}

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        return new CompoundNBT();
    }

    @Override
    public JsonObject writeToJSON(CompoundNBT propertiesTag) {
        return new JsonObject();
    }
}
