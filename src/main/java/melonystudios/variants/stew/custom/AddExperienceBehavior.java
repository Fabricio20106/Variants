package melonystudios.variants.stew.custom;

import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static melonystudios.variants.util.NBTUtils.anyNumericOrIntDefault;
import static melonystudios.variants.util.NBTUtils.booleanOrDefault;

public class AddExperienceBehavior extends StewBehavior {
    private final int amount;
    private final boolean levels;

    public AddExperienceBehavior(int amount, boolean levels) {
        this.amount = amount;
        this.levels = levels;
    }

    public AddExperienceBehavior() {
        this(0, false);
    }

    public int getExperienceAmount() {
        return this.amount;
    }

    public boolean addsLevels() {
        return this.levels;
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (livEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livEntity;
            if (this.levels) player.giveExperienceLevels(this.amount);
            else player.giveExperiencePoints(this.amount);
        }
    }

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        AddExperienceBehavior behavior = new AddExperienceBehavior(anyNumericOrIntDefault("amount", propertiesTag, 0), booleanOrDefault("levels", propertiesTag, false));
        behavior.executeBehavior(stewStack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT properties = new CompoundNBT();
        properties.putInt("amount", this.amount);
        properties.putBoolean("levels", this.levels);
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.ADD_EXPERIENCE.get();
    }
}
