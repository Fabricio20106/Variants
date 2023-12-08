package com.junethewoods.variants.item.custom;

import com.google.common.collect.Lists;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.VSItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class EnchantedKnowledgeBookItem extends EnchantedBookItem {
    public EnchantedKnowledgeBookItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        CompoundTag handStackTag = handStack.getTag();
        if (!player.getAbilities().instabuild) {
            player.setItemInHand(hand, ItemStack.EMPTY);
        }

        if (handStackTag != null && handStackTag.contains("Recipes", 9)) {
            if (!level.isClientSide) {
                ListTag nbtList = handStackTag.getList("Recipes", 8);
                List<RecipeHolder<?>> recipesList = Lists.newArrayList();
                RecipeManager recipeManager = level.getServer().getRecipeManager();

                for(int listString = 0; listString < nbtList.size(); ++listString) {
                    String nbtListString = nbtList.getString(listString);
                    Optional<RecipeHolder<?>> optionalRecipe = recipeManager.byKey(new ResourceLocation(nbtListString));
                    if (!optionalRecipe.isPresent()) {
                        Variants.LOGGER.error("Variants: Invalid recipe for Enchanted Knowledge Book: {}", nbtListString);
                        return InteractionResultHolder.fail(handStack);
                    }

                    recipesList.add(optionalRecipe.get());
                }

                player.awardRecipes(recipesList);
                player.awardStat(Stats.ITEM_USED.get(this));
            }

            return InteractionResultHolder.sidedSuccess(handStack, level.isClientSide());
        } else {
            Variants.LOGGER.error("Variants: Enchanted Knowledge Book tag not valid: {}", handStackTag);
            return InteractionResultHolder.fail(handStack);
        }
    }

    public static void addEnchantment(ItemStack stack, EnchantmentInstance enchInstance) {
        ListTag enchantsList = getEnchantments(stack);
        boolean flag = true;
        ResourceLocation enchantLocations = EnchantmentHelper.getEnchantmentId(enchInstance.enchantment);

        for(int i = 0; i < enchantsList.size(); ++i) {
            CompoundTag tag = enchantsList.getCompound(i);
            ResourceLocation enchantFromTag = EnchantmentHelper.getEnchantmentId(tag);
            if (enchantFromTag != null && enchantFromTag.equals(enchantLocations)) {
                if (EnchantmentHelper.getEnchantmentLevel(tag) < enchInstance.level) {
                    EnchantmentHelper.setEnchantmentLevel(tag, enchInstance.level);
                }

                flag = false;
                break;
            }
        }

        if (flag) {
            enchantsList.add(EnchantmentHelper.storeEnchantment(enchantLocations, enchInstance.level));
        }

        stack.getOrCreateTag().put("StoredEnchantments", enchantsList);
    }

    public static ItemStack createForEnchantment(EnchantmentInstance enchInstance) {
        ItemStack bookStack = new ItemStack(VSItems.ENCHANTED_KNOWLEDGE_BOOK.get());
        addEnchantment(bookStack, enchInstance);
        return bookStack;
    }
}
