package com.junethewoods.variants.item.custom;

import com.google.common.collect.Lists;
import com.junethewoods.variants.Variants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class EnchantedKnowledgeBookItem extends EnchantedBookItem {
    public EnchantedKnowledgeBookItem(Properties properties) {
        super(properties);
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        CompoundNBT handStackTag = handStack.getTag();
        if (!player.abilities.instabuild) {
            player.setItemInHand(hand, ItemStack.EMPTY);
        }

        if (handStackTag != null && handStackTag.contains("Recipes", 9)) {
            if (!world.isClientSide) {
                ListNBT nbtList = handStackTag.getList("Recipes", 8);
                List<IRecipe<?>> recipesList = Lists.newArrayList();
                RecipeManager recipeManager = world.getServer().getRecipeManager();

                for(int listString = 0; listString < nbtList.size(); ++listString) {
                    String nbtListString = nbtList.getString(listString);
                    Optional<? extends IRecipe<?>> optionalRecipe = recipeManager.byKey(new ResourceLocation(nbtListString));
                    if (!optionalRecipe.isPresent()) {
                        Variants.LOGGER.error("Variants: Invalid recipe for Enchanted Knowledge Book: {}", nbtListString);
                        return ActionResult.fail(handStack);
                    }

                    recipesList.add(optionalRecipe.get());
                }

                player.awardRecipes(recipesList);
                player.awardStat(Stats.ITEM_USED.get(this));
            }

            return ActionResult.sidedSuccess(handStack, world.isClientSide());
        } else {
            Variants.LOGGER.error("Variants: Enchanted Knowledge Book tag not valid: {}", handStackTag);
            return ActionResult.fail(handStack);
        }
    }
}
