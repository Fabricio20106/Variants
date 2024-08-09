package melonystudios.variants.item.custom.food;

import melonystudios.variants.util.Constants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class LavaBottleItem extends DrinkableContainerItem {
    private final int secondsOnFire;

    public LavaBottleItem(int secondsOnFire, Properties properties) {
        super(properties);
        this.secondsOnFire = secondsOnFire;
    }

    @Override
    public void executeFunctionality(ItemStack containerStack, ItemStack bottleStack, World world, LivingEntity livEntity) {
        this.containerItem = new ItemStack(Items.GLASS_BOTTLE);
        if (!world.isClientSide) {
            CompoundNBT tag = bottleStack.getTag();
            if (tag != null && tag.contains("seconds_on_fire", Constants.TagTypes.ANY_NUMERIC)) {
                livEntity.setSecondsOnFire(tag.getInt("seconds_on_fire"));
            } else {
                livEntity.setSecondsOnFire(this.secondsOnFire);
            }
        }
    }
}
