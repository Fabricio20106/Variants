package melonystudios.variants.item.custom.food;

import melonystudios.variants.util.Constants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class BucketFoodItem extends Item {
    public BucketFoodItem(Properties properties) {
        super(properties);
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

    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Nonnull
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }

    @Nonnull
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stack, world, livEntity);
        boolean isPlayerInCreative = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;

        // For Suspicious Stew
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("effects", Constants.TagTypes.LIST)) {
            ListNBT effectList = tag.getList("effects", Constants.TagTypes.COMPOUND);

            for (int i = 0; i < effectList.size(); ++i) {
                int duration = 160; // Default of 8 seconds from Suspicious Stew.
                int amplifier = 0;
                boolean ambient = false;
                boolean showParticles = true;
                boolean showIcon = true;
                boolean noCounter = false;
                CompoundNBT effectTag = effectList.getCompound(i);
                if (effectTag.contains("duration", Constants.TagTypes.ANY_NUMERIC)) duration = effectTag.getInt("duration");
                if (effectTag.contains("amplifier", Constants.TagTypes.ANY_NUMERIC)) amplifier = effectTag.getInt("amplifier");
                if (effectTag.contains("ambient", Constants.TagTypes.ANY_NUMERIC)) ambient = effectTag.getBoolean("ambient");
                if (effectTag.contains("show_particles", Constants.TagTypes.ANY_NUMERIC)) showParticles = effectTag.getBoolean("show_particles");
                if (effectTag.contains("show_icon", Constants.TagTypes.ANY_NUMERIC)) showIcon = effectTag.getBoolean("show_icon");
                if (effectTag.contains("no_counter", Constants.TagTypes.ANY_NUMERIC)) noCounter = effectTag.getBoolean("no_counter");

                Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
                if (effect != null) {
                    EffectInstance instance = new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon);
                    if (world.isClientSide) instance.setNoCounter(noCounter);
                    livEntity.addEffect(instance);
                }
            }
        }

        return isPlayerInCreative ? superStack : new ItemStack(Items.BUCKET);
    }
}
