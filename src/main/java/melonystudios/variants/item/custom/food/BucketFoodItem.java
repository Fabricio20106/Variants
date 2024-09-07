package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.custom.DefaultStewBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
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
        CompoundNBT tag = stack.getOrCreateTag();
        ListNBT effectList = tag.getList("effects", Constants.TagTypes.LIST);
        CompoundNBT effectTag = new CompoundNBT();

        effectTag.putString("id", effect.getRegistryName().toString());
        effectTag.putInt("duration", duration);
        effectList.add(effectTag);
        tag.put("effects", effectList);
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

    @Nonnull
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stack, world, livEntity);
        boolean isPlayerInCreative = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;

        executeConsumeBehavior(stack, world, livEntity, this.behavior);

        // For Suspicious Stew
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("effects", Constants.TagTypes.LIST)) {
            ListNBT effectList = tag.getList("effects", Constants.TagTypes.COMPOUND);
            for (int i = 0; i < effectList.size(); ++i) NBTUtils.addEffectsFromNBT(effectList.getCompound(i), world, livEntity);
        }

        return isPlayerInCreative ? superStack : getUseRemainder(stack);
    }
}
