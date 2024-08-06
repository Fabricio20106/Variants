package melonystudios.variants.data.recipe;

import com.google.common.collect.ImmutableMap;
import melonystudios.variants.data.ExponentialStewRecipeBuilder;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Consumer;

import static melonystudios.variants.item.custom.food.ExponentialStewItem.BOWL_NAME_TO_ID;

public class VSExpoStewsRecipeProvider extends RecipeProvider {
    public static Map<Item, String> BOWL_TO_NAME = new ImmutableMap.Builder<Item, String>().put(VSItems.OAK_BOWL.get(), "oak").put(VSItems.SPRUCE_BOWL.get(), "spruce").put(VSItems.BIRCH_BOWL.get(), "birch").put(VSItems.JUNGLE_BOWL.get(), "jungle")
            .put(VSItems.ACACIA_BOWL.get(), "acacia").put(VSItems.DARK_OAK_BOWL.get(), "dark_oak").put(VSItems.PAINTING_BOWL.get(), "painting").put(VSItems.CRIMSON_BOWL.get(), "crimson").put(VSItems.WARPED_BOWL.get(), "warped").put(VSItems.ENDERWOOD_BOWL.get(),
                    "ender").build();

    public VSExpoStewsRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Exponential Stew Recipes";
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_MUSHROOM_STEW.get()), VSStewBehaviors.DEFAULT.get(), "mushroom");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_BEETROOT_SOUP.get()), VSStewBehaviors.DEFAULT.get(), "beetroot");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_RABBIT_STEW.get()), VSStewBehaviors.DEFAULT.get(), "rabbit");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_FUNGI_STEW.get()), VSStewBehaviors.DEFAULT.get(), "fungi");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_END_FUNGI_STEW.get()), VSStewBehaviors.DEFAULT.get(), "end_fungi");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_WATER_BOWL.get()), VSStewBehaviors.APPLY_MOB_EFFECTS.get(), "water");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_LAVA_BOWL.get()), VSStewBehaviors.IGNITE.get(), "lava");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_SOUL_LAVA_BOWL.get()), VSStewBehaviors.IGNITE.get(), "soul_lava");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_MILK_BOWL.get()), VSStewBehaviors.CLEAR_MOB_EFFECTS.get(), "milk");
        makeExpoStewRecipe(consumer, new ItemStack(VSItems.EXPONENTIAL_POWDER_SNOW_BOWL.get()), VSStewBehaviors.DEFAULT.get(), "powder_snow");
    }

    private void makeExpoStewRecipe(Consumer<IFinishedRecipe> consumer, ItemStack resultStew, StewBehavior behavior, String resultStewName) {
        // Mushroom Stews
        if (resultStewName.equals("mushroom")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(Items.BROWN_MUSHROOM).requires(Items.RED_MUSHROOM).requires(bowl).group("mushroom_stew").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:stews/mushroom/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Beetroot Soups
        if (resultStewName.equals("beetroot")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(bowl).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).group("beetroot_soup")
                        .unlockedBy("has_bowl", has(bowl)).save(consumer, "variants:stews/beetroot/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Rabbit Stews
        if (resultStewName.equals("rabbit")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(Items.BAKED_POTATO).requires(Items.COOKED_RABBIT).requires(Items.CARROT).requires(Ingredient.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM)).requires(bowl).group("rabbit_stew")
                        .unlockedBy("has_bowl", has(bowl)).save(consumer, "variants:stews/rabbit/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Fungi Stews
        if (resultStewName.equals("fungi")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(Items.CRIMSON_FUNGUS).requires(Items.WARPED_FUNGUS).requires(bowl).group("fungi_stew").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:stews/fungi/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // End Fungi Stews
        if (resultStewName.equals("end_fungi")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(VSItems.ENDER_FUNGUS.get()).requires(VSItems.ENDER_FUNGUS.get()).requires(bowl).group("end_fungi_stew").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:stews/end_fungi/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Water Bowls
        if (resultStewName.equals("water")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(Items.POTION).requires(Items.POTION).requires(bowl).group("water_bowl").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:bowls/water/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Lava Bowls
        if (resultStewName.equals("lava")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(VSItems.LAVA_BOTTLE.get()).requires(VSItems.LAVA_BOTTLE.get()).requires(bowl).group("lava_bowl").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:bowls/lava/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Soul Lava Bowls
        if (resultStewName.equals("soul_lava")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(VSItems.SOUL_LAVA_BOTTLE.get()).requires(VSItems.SOUL_LAVA_BOTTLE.get()).requires(bowl).group("soul_lava_bowl").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:bowls/soul_lava/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Milk Bowls
        if (resultStewName.equals("milk")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(VSItems.MILK_BOTTLE.get()).requires(VSItems.MILK_BOTTLE.get()).requires(bowl).group("milk_bowl").unlockedBy("has_bowl", has(bowl)).save(consumer,
                        "variants:bowls/milk/" + BOWL_TO_NAME.get(bowl));
            }
        }

        // Powder Snow Bowls
        if (resultStewName.equals("powder_snow")) {
            for (Item bowl : BOWL_TO_NAME.keySet()) {
                CompoundNBT bowlTypeTag = resultStew.getOrCreateTagElement("bowl");
                bowlTypeTag.putString("name", "variants:" + BOWL_TO_NAME.get(bowl) + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(BOWL_TO_NAME.get(bowl)));
                CompoundNBT behaviorTag = resultStew.getOrCreateTagElement("behavior");
                behaviorTag.putString("id", behavior.getRegistryName().toString());
                behaviorTag.put("properties", behavior.writePropertiesToNBT());

                ExponentialStewRecipeBuilder.shapeless(resultStew).requires(VSItems.POWDER_SNOW_BOTTLE.get()).requires(VSItems.POWDER_SNOW_BOTTLE.get()).requires(bowl).group("powder_snow_bowl").unlockedBy("has_bowl", has(bowl))
                        .save(consumer, "variants:bowls/powder_snow/" + BOWL_TO_NAME.get(bowl));
            }
        }
    }
}
