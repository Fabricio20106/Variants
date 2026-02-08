package melonystudios.revaried.item.custom.food;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

public class PlaceableBucketFoodItem extends BucketItem {
    public PlaceableBucketFoodItem(Fluid content, Properties properties) {
        super(content, properties);
    }

    @Override
    @NotNull
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        FoodProperties properties = player.getItemInHand(hand).getFoodProperties(player);
        InteractionResultHolder<ItemStack> holder = super.use(level, player, hand);

        if (!holder.getResult().consumesAction() && properties != null && player.canEat(properties.canAlwaysEat())) {
            return ItemUtils.startUsingInstantly(level, player, hand);
        }
        return holder;
    }

    @Override
    @NotNull
    public SoundEvent getEatingSound() {
        return this.getDrinkingSound();
    }
}
