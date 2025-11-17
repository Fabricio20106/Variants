package melonystudios.variants.consumable.custom;

import com.google.gson.JsonObject;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ClearMobEffectsBehavior extends ConsumeBehavior {
    private final ItemStack item;

    public ClearMobEffectsBehavior(ItemStack item) {
        this.item = item;
    }

    public ClearMobEffectsBehavior() {
        this(new ItemStack(Items.MILK_BUCKET));
    }

    public ItemStack item() {
        return this.item;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (!world.isClientSide() && propertiesTag != null) {
            ItemStack curativeStack = VSUtils.loadStack(propertiesTag.getCompound("item"));
            livEntity.curePotionEffects(curativeStack);
        } else if (!world.isClientSide() && this.item() != null) {
            livEntity.curePotionEffects(this.item());
        }
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        ClearMobEffectsBehavior clearEffectsBehavior = new ClearMobEffectsBehavior(VSUtils.loadStack(NBTUtils.compoundOrDefault("item", propertiesTag, milkBucket(1))));
        clearEffectsBehavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        properties.put("item", VSUtils.saveStack(this.item(), new CompoundNBT()));
        return properties;
    }

    @Override
    public JsonObject writeToJSON(CompoundNBT propertiesTag) {
        JsonObject properties = new JsonObject();
        ItemStack item = this.item();

        // saving the item (probably need to add a JSONUtils method for this)
        JsonObject curativeObject = new JsonObject();
        curativeObject.addProperty("id", item.getItem().getRegistryName().toString());
        if (item.getCount() != 1) curativeObject.addProperty("count", item.getCount());
        if (item.getTag() != null) curativeObject.addProperty("tags", item.getTag().toString());
        properties.add("item", curativeObject);

        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.CLEAR_MOB_EFFECTS.get();
    }

    /// Creates a {@link CompoundNBT CompoundTag} of a milk bucket with a variable amount.
    /// @param count The amount of items in the stack.
    public static CompoundNBT milkBucket(int count) {
        ItemStack milkBucket = new ItemStack(Items.MILK_BUCKET, count);
        return VSUtils.saveStack(milkBucket, new CompoundNBT());
    }
}
