package melonystudios.variants.item.custom.food;

import melonystudios.variants.item.VSItems;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VSUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class TagConfigurableStewItem extends ExponentialStewItem {
    public TagConfigurableStewItem(StewBehavior behavior, Properties properties) {
        super(behavior, properties);
    }

    @Override
    public ItemStack getBowlType(ItemStack stewStack, @Nullable LivingEntity livEntity) {
        CompoundNBT bowlTag = stewStack.getTagElement("bowl");
        if (livEntity != null) livEntity.eat(livEntity.level, stewStack);

        if (bowlTag != null && bowlTag.contains("item", Constants.TagTypes.COMPOUND)) {
            CompoundNBT itemTag = bowlTag.getCompound("item");
            if (itemTag.contains("id", Constants.TagTypes.STRING) && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemTag.getString("id")))) return VSUtils.loadStack(itemTag);
        }

        return stewStack.getItem() == VSItems.END_FUNGI_STEW.get() ? new ItemStack(VSItems.ENDERWOOD_BOWL.get()) : new ItemStack(Items.BOWL);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) {
            ItemStack stack = new ItemStack(this);
            CompoundNBT tag = stack.getOrCreateTag();

            CompoundNBT bowlTag = new CompoundNBT();
            bowlTag.putInt("texture_id", stack.getItem() == VSItems.END_FUNGI_STEW.get() ? 9 : 0);
            bowlTag.put("item", VSUtils.saveStack(this.getBowlType(stack, null), new CompoundNBT()));
            tag.put("bowl", bowlTag);
            list.add(stack);
        }
    }
}
