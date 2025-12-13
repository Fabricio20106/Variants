package melonystudios.variants.item.custom.food;

import melonystudios.variants.item.VSItems;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VSUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class SimpleStewItem extends ExponentialStewItem {
    public SimpleStewItem(ConsumeBehavior behavior, Properties properties) {
        super(behavior, properties);
    }

    @Override
    public ItemStack getBowlFromNBT(ItemStack stewStack, @Nullable LivingEntity livEntity) {
        if (livEntity != null) livEntity.eat(livEntity.level, stewStack);
        CompoundNBT consumableTag = stewStack.getTagElement("consumable");

        if (consumableTag != null && consumableTag.contains("use_remainder", Constants.TagTypes.COMPOUND)) {
            CompoundNBT remainderTag = consumableTag.getCompound("use_remainder");
            if (remainderTag.contains("id", Constants.TagTypes.STRING) && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(remainderTag.getString("id")))) return VSUtils.loadStack(remainderTag);
        }

        return stewStack.getItem() == VSItems.END_FUNGI_STEW.get() ? new ItemStack(VSItems.ENDERWOOD_BOWL.get()) : this.getDefaultUseRemainder();
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) {
            ItemStack stack = new ItemStack(this);
            CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");
            consumableTag.put("use_remainder", VSUtils.saveStack(this.getBowlFromNBT(stack, null), new CompoundNBT()));
            list.add(stack);
        }
    }
}
