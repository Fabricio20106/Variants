package melonystudios.variants.stew.custom;

import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import static melonystudios.variants.util.NBTUtils.writeEffectsOntoNBT;

public class ApplyMobEffectsBehavior extends StewBehavior {
    private final EffectInstance[] effects;

    public ApplyMobEffectsBehavior(EffectInstance... effects) {
        this.effects = effects;
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {}

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, CompoundNBT propertiesTag) {
        executeBehavior(stewStack, world, livEntity);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT properties = new CompoundNBT();
        properties.put("effects", writeEffectsOntoNBT(this.effects));
        return properties;
    }

    @Override
    public EffectInstance[] getEffects() {
        return this.effects;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.APPLY_MOB_EFFECTS.get();
    }
}
