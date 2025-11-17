package melonystudios.variants.consumable.custom;

import com.google.gson.JsonObject;
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

    public int experienceAmount() {
        return this.amount;
    }

    public boolean addsLevels() {
        return this.levels;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (!world.isClientSide() && livEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livEntity;
            if (this.addsLevels()) {
                player.giveExperienceLevels(this.experienceAmount());
            } else {
                player.giveExperiencePoints(this.experienceAmount());
            }
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
        if (this.experienceAmount() != 0) properties.putInt("amount", this.experienceAmount());
        if (this.addsLevels()) properties.putBoolean("levels", true);
        return properties;
    }

    @Override
    public JsonObject writeToJSON(CompoundNBT propertiesTag) {
        JsonObject properties = new JsonObject();
        if (this.experienceAmount() != 0) properties.addProperty("amount", this.experienceAmount());
        if (this.addsLevels()) properties.addProperty("levels", true);
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.ADD_EXPERIENCE.get();
    }
}
