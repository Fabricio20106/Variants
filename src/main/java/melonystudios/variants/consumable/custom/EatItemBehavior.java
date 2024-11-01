package melonystudios.variants.consumable.custom;

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
    private final ItemStack consumableStack;

    public EatItemBehavior(ItemStack stack) {
        this.consumableStack = stack;
    }

    public EatItemBehavior() {
        this(ItemStack.EMPTY);
    }

    public ItemStack getConsumableItem() {
        return this.consumableStack;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (this.consumableStack != null) {
            livEntity.eat(world, this.consumableStack);
            this.consumableStack.getItem().finishUsingItem(this.consumableStack, world, livEntity);
        }
    }

    @Override
    public void loadFromNBT(ItemStack stewStack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        ItemStack stack = VSUtils.loadStack(NBTUtils.compoundOrDefault("consumable_item", propertiesTag, VSUtils.saveStack(ItemStack.EMPTY, new CompoundNBT())));
        EatItemBehavior behavior = new EatItemBehavior(stack);
        behavior.runBehavior(stewStack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT propertiesTag = new CompoundNBT();
        propertiesTag.put("consumable_item", VSUtils.saveStack(this.consumableStack, new CompoundNBT()));
        return propertiesTag;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.EAT_ITEM.get();
    }
}
