package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.stew.custom.DefaultStewBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BucketFoodItem extends TagConfigurableFoodItem {
    public BucketFoodItem(StewBehavior behavior, Properties properties) {
        super(false, behavior, properties);
    }

    public BucketFoodItem(Properties properties) {
        this(new DefaultStewBehavior(), properties);
    }

    public static void writeEffectToBucket(ItemStack stack, Effect effect, int duration) {
        CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");
        CompoundNBT behaviorTag = new CompoundNBT();
        ListNBT effectList = new ListNBT();
        CompoundNBT effectTag = new CompoundNBT();

        effectTag.putString("id", effect.getRegistryName().toString());
        effectTag.putInt("duration", duration);
        effectList.add(effectTag);

        behaviorTag.put("effects", effectList);
        behaviorTag.putString("id", VSStewBehaviors.APPLY_MOB_EFFECTS.get().getRegistryName().toString());

        consumableTag.put("behavior", behaviorTag);
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return getConsumeAnimation(stack, UseAction.DRINK);
    }

    @Override
    public SoundEvent getDefaultConsumeSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public ItemStack getDefaultUseRemainder() {
        return new ItemStack(Items.BUCKET);
    }

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }
}
