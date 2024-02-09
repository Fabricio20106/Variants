package com.junethewoods.variants.data.recipe;

import com.google.common.collect.ImmutableMap;
import com.junethewoods.variants.data.ExponentialBowlRecipeBuilder;
import com.junethewoods.variants.item.VSItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;

import java.util.Map;
import java.util.function.Consumer;

import static com.junethewoods.variants.item.custom.food.ExponentialSoupItem.BOWL_NAME_TO_ID;

public class VSExpoStewsRecipeProvider extends RecipeProvider {
    public static Map<Item, String> BOWL_TO_NAME = new ImmutableMap.Builder<Item, String>().put(VSItems.OAK_BOWL.get(), "oak").put(VSItems.SPRUCE_BOWL.get(), "spruce").put(VSItems.BIRCH_BOWL.get(), "birch").put(VSItems.JUNGLE_BOWL.get(), "jungle")
            .put(VSItems.ACACIA_BOWL.get(), "acacia").put(VSItems.DARK_OAK_BOWL.get(), "dark_oak").put(VSItems.PAINTING_BOWL.get(), "painting").put(VSItems.CRIMSON_BOWL.get(), "crimson").put(VSItems.WARPED_BOWL.get(), "warped").put(VSItems.ENDERWOOD_BOWL.get(),
                    "ender").build();

    public VSExpoStewsRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Variants - Exponential Stew Recipes";
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_MUSHROOM_STEW.get()), "mushroom");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_BEETROOT_SOUP.get()), "beetroot");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_RABBIT_STEW.get()), "rabbit");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_FUNGI_STEW.get()), "fungi");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_END_FUNGI_STEW.get()), "end_fungi");
    }

    private void makeExpoStewRecipe(Consumer<IFinishedRecipe> consumer, ItemStack resultStew, String resultStewName) {
        // Mushroom Stews
        if (resultStewName.equals("mushroom")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl_type");
                bowlTypeTag.putString("bowl_name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("bowl_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));

                ExponentialBowlRecipeBuilder.shapeless(resultStew).requires(Items.BROWN_MUSHROOM).requires(Items.RED_MUSHROOM).requires(bowl).group("mushroom_stew").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:bowls/mushroom/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Beetroot Soups
        if (resultStewName.equals("beetroot")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl_type");
                bowlTypeTag.putString("bowl_name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("bowl_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));

                ExponentialBowlRecipeBuilder.shapeless(resultStew).requires(bowl).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).group("beetroot_soup")
                        .unlockedBy("has_bowl", has(bowl)).save(consumer, "variants:bowls/beetroot/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Rabbit Stews
        if (resultStewName.equals("rabbit")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl_type");
                bowlTypeTag.putString("bowl_name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("bowl_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));

                ExponentialBowlRecipeBuilder.shapeless(resultStew).requires(Items.BAKED_POTATO).requires(Items.COOKED_RABBIT).requires(Items.CARROT).requires(Ingredient.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM)).requires(bowl).group("rabbit_stew")
                        .unlockedBy("has_bowl", has(bowl)).save(consumer, "variants:bowls/rabbit/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Fungi Stews
        if (resultStewName.equals("fungi")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl_type");
                bowlTypeTag.putString("bowl_name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("bowl_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));

                ExponentialBowlRecipeBuilder.shapeless(resultStew).requires(Items.CRIMSON_FUNGUS).requires(Items.WARPED_FUNGUS).requires(bowl).group("fungi_stew").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:bowls/fungi/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // End Fungi Stews
        if (resultStewName.equals("end_fungi")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl_type");
                bowlTypeTag.putString("bowl_name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("bowl_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));

                ExponentialBowlRecipeBuilder.shapeless(resultStew).requires(VSItems.ENDER_FUNGUS.get()).requires(VSItems.ENDER_FUNGUS.get()).requires(bowl).group("end_fungi_stew").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:bowls/end_fungi/" + BOWL_TO_NAME.get(bowl));
            }
        }
    }
}
