package melonystudios.variants.consumable.custom;

import com.google.gson.JsonObject;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EatItemBehavior extends ConsumeBehavior {
    private final ItemStack item;

    public EatItemBehavior(ItemStack item) {
        this.item = item;
    }

    public EatItemBehavior() {
        this(ItemStack.EMPTY);
    }

    public ItemStack item() {
        return this.item;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (this.item() != null) {
            livEntity.eat(world, this.item());
            this.item().getItem().finishUsingItem(this.item(), world, livEntity);
        }
    }

    @Override
    public void loadFromNBT(ItemStack stewStack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        ItemStack stack = VSUtils.loadStack(NBTUtils.compoundOrDefault("item", propertiesTag, VSUtils.saveStack(ItemStack.EMPTY, new CompoundNBT())));
        EatItemBehavior behavior = new EatItemBehavior(stack);
        behavior.runBehavior(stewStack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT propertiesTag = new CompoundNBT();
        propertiesTag.put("item", VSUtils.saveStack(this.item(), new CompoundNBT()));
        return propertiesTag;
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
        return VSConsumeBehaviors.EAT_ITEM.get();
    }
}
