package melonystudios.variants.consumable.custom;

import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static melonystudios.variants.util.NBTUtils.anyNumericOrIntDefault;
import static melonystudios.variants.util.NBTUtils.booleanOrDefault;

public class AddExperienceBehavior extends ConsumeBehavior {
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
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (livEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livEntity;
            if (this.levels) player.giveExperienceLevels(this.amount);
            else player.giveExperiencePoints(this.amount);
        }
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        AddExperienceBehavior behavior = new AddExperienceBehavior(anyNumericOrIntDefault("amount", propertiesTag, 0), booleanOrDefault("levels", propertiesTag, false));
        behavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        properties.putInt("amount", this.amount);
        properties.putBoolean("levels", this.levels);
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.ADD_EXPERIENCE.get();
    }
}
