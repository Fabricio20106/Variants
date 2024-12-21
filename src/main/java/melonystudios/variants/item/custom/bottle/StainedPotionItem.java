package melonystudios.variants.item.custom.bottle;

import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.effect.VSEffectInstance;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
import melonystudios.variants.consumable.custom.ApplyMobEffectsBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class StainedPotionItem extends StainedFullGlassBottleItem {
    public StainedPotionItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        List<EffectInstance> effects = NBTUtils.getEffectsFromNBT(null, stack);
        boolean hasInNBT = false;
        boolean hasPotionEffects = super.isFoil(stack);

        if (stack.getTag() != null && stack.getTag().contains("enchantment_glint_override", Constants.TagTypes.ANY_NUMERIC)) hasInNBT = stack.getTag().getBoolean("enchantment_glint_override");
        if (effects != null && !effects.isEmpty()) hasPotionEffects = true;

        return (hasInNBT || hasPotionEffects) && !ModList.get().isLoaded("melonylib");
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return getConsumeAnimation(stack, UseAction.DRINK);
    }

    @Override
    public SoundEvent getDefaultConsumeSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    @Nonnull
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab) && VSConfigs.COMMON_CONFIGS.populateStainedGlassBottlesInTabs.get()) {
            for (GlassType collection : BOTTLES) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");
                consumableTag.put("use_remainder", VSUtils.saveStack(collection.getBottle(), new CompoundNBT()));
                tag.putInt("texture_id", collection.getTextureIdentifier());

                for (Potion potion : ForgeRegistries.POTION_TYPES) {
                    if (potion != Potions.EMPTY) {
                        ItemStack copyStack = stack.copy();
                        CompoundNBT effectTag = new CompoundNBT();
                        List<VSEffectInstance> potionEffects = VSUtils.convertEffectList(potion.getEffects());
                        ListNBT effects = NBTUtils.writeEffectsOntoNBT(potionEffects);
                        if (!effects.isEmpty()) effectTag.put("effects", effects);
                        copyStack.getOrCreateTag().putString("potion", potion.getRegistryName().toString());
                        ExponentialStewItem.writeBehaviorToStew(copyStack, new ApplyMobEffectsBehavior(potionEffects), effectTag);
                        list.add(copyStack);
                    }
                }
            }
        } else {
            super.fillItemCategory(tab, list);
        }
    }

    @Override
    @Nonnull
    public ITextComponent getName(ItemStack stack) {
        if (this.getUseRemainder(stack).getItem() instanceof StainedEmptyGlassBottleItem) {
            StainedEmptyGlassBottleItem bottleItem = (StainedEmptyGlassBottleItem) this.getUseRemainder(stack).getItem();
            ResourceLocation colorName = bottleItem.getColorName(stack);
            return new TranslationTextComponent(this.getDescriptionId(stack) + getPotion(stack.getTag()).getName(".effect."), new TranslationTextComponent(getColorTranslation(colorName)));
        }
        return super.getName(stack);
    }

    public static Potion getPotion(@Nullable CompoundNBT tag) {
        return tag == null ? Potions.EMPTY : Potion.byName(tag.getString("potion"));
    }
}
