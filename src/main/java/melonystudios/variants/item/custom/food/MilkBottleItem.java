package melonystudios.variants.item.custom.food;

import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VSUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class MilkBottleItem extends DrinkableContainerItem {
    public MilkBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void executeFunctionality(ItemStack containerStack, ItemStack bottleStack, World world, LivingEntity livEntity) {
        this.containerItem = new ItemStack(Items.GLASS_BOTTLE);
        if (!world.isClientSide) {
            CompoundNBT tag = bottleStack.getTag();
            if (tag != null && tag.contains("curative_item", Constants.TagTypes.COMPOUND)) {
                livEntity.curePotionEffects(VSUtils.loadStack(tag.getCompound("curative_item")));
            } else {
                livEntity.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
            }
        }
    }
}
