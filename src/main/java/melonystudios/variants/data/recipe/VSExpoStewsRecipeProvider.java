package melonystudios.variants.data.recipe;

import com.google.common.collect.Lists;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.stew.bowl.BowlType;
import melonystudios.variants.stew.bowl.BowlTypes;
import melonystudios.variants.util.VSUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

public class VSExpoStewsRecipeProvider extends RecipeProvider {
    public static List<BowlType> DEFAULT_BOWLS = Lists.newArrayList(BowlTypes.OAK, BowlTypes.SPRUCE, BowlTypes.BIRCH, BowlTypes.JUNGLE, BowlTypes.ACACIA, BowlTypes.DARK_OAK, BowlTypes.PAINTING, BowlTypes.CRIMSON, BowlTypes.WARPED,
            BowlTypes.ENDERWOOD);

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
        makeMushroomStew(consumer, new ItemStack(VSItems.EXPONENTIAL_MUSHROOM_STEW.get()), VSStewBehaviors.DEFAULT.get());
        makeBeetrootSoup(consumer, new ItemStack(VSItems.EXPONENTIAL_BEETROOT_SOUP.get()), VSStewBehaviors.DEFAULT.get());
        makeRabbitStew(consumer, new ItemStack(VSItems.EXPONENTIAL_RABBIT_STEW.get()), VSStewBehaviors.DEFAULT.get());
        makeFungiStew(consumer, new ItemStack(VSItems.EXPONENTIAL_FUNGI_STEW.get()), VSStewBehaviors.DEFAULT.get());
        makeEndFungiStew(consumer, new ItemStack(VSItems.EXPONENTIAL_END_FUNGI_STEW.get()), VSStewBehaviors.DEFAULT.get());
        makeWaterBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_WATER_BOWL.get()), VSStewBehaviors.APPLY_MOB_EFFECTS.get());
        makeLavaBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_LAVA_BOWL.get()), VSStewBehaviors.IGNITE.get());
        makeSoulLavaBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_SOUL_LAVA_BOWL.get()), VSStewBehaviors.IGNITE.get());
        makeMilkBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_MILK_BOWL.get()), VSStewBehaviors.CLEAR_MOB_EFFECTS.get());
        makePowderSnowBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_POWDER_SNOW_BOWL.get()), VSStewBehaviors.DEFAULT.get());
    }

    private void makeMushroomStew(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.BROWN_MUSHROOM).requires(Items.RED_MUSHROOM).requires(type.getBowlStack().getItem()).group("mushroom_stew").unlockedBy("has_bowl", has(type.getBowlStack()
                    .getItem())).save(consumer, "variants:stews/mushroom/" + type.getWoodName());
        }
    }

    private void makeBeetrootSoup(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(type.getBowlStack().getItem())
                    .group("beetroot_soup").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:stews/beetroot/" + type.getWoodName());
        }
    }

    private void makeRabbitStew(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.BAKED_POTATO).requires(Items.COOKED_RABBIT).requires(Items.CARROT).requires(Ingredient.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM)).requires(type.getBowlStack().getItem())
                    .group("rabbit_stew").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:stews/rabbit/" + type.getWoodName());
        }
    }

    private void makeFungiStew(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.CRIMSON_FUNGUS).requires(Items.WARPED_FUNGUS).requires(type.getBowlStack().getItem())
                    .group("fungi_stew").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:stews/fungi/" + type.getWoodName());
        }
    }

    private void makeEndFungiStew(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.ENDER_FUNGUS.get()).requires(VSItems.ENDER_FUNGUS.get()).requires(type.getBowlStack().getItem())
                    .group("end_fungi_stew").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:stews/end_fungi/" + type.getWoodName());
        }
    }

    private void makeWaterBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.POTION).requires(Items.POTION).requires(type.getBowlStack().getItem())
                    .group("water_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/water/" + type.getWoodName());
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_POTION.get()).requires(VSItems.STAINED_POTION.get()).requires(type.getBowlStack().getItem())
                    .group("water_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/water/" + type.getWoodName() + "_stained");
        }
    }

    private void makeLavaBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.LAVA_BOTTLE.get()).requires(VSItems.LAVA_BOTTLE.get()).requires(type.getBowlStack().getItem())
                    .group("lava_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/lava/" + type.getWoodName());
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_LAVA_BOTTLE.get()).requires(VSItems.STAINED_LAVA_BOTTLE.get()).requires(type.getBowlStack().getItem())
                    .group("lava_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/lava/" + type.getWoodName() + "_stained");
        }
    }

    private void makeSoulLavaBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.SOUL_LAVA_BOTTLE.get()).requires(VSItems.SOUL_LAVA_BOTTLE.get()).requires(type.getBowlStack().getItem())
                    .group("soul_lava_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/soul_lava/" + type.getWoodName());
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_SOUL_LAVA_BOTTLE.get()).requires(VSItems.STAINED_SOUL_LAVA_BOTTLE.get()).requires(type.getBowlStack().getItem())
                    .group("soul_lava_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/soul_lava/" + type.getWoodName() + "_stained");
        }
    }

    private void makeMilkBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.MILK_BOTTLE.get()).requires(VSItems.MILK_BOTTLE.get()).requires(type.getBowlStack().getItem())
                    .group("milk_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/milk/" + type.getWoodName());
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_MILK_BOTTLE.get()).requires(VSItems.STAINED_MILK_BOTTLE.get()).requires(type.getBowlStack().getItem())
                    .group("milk_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/milk/" + type.getWoodName() + "_stained");
        }
    }

    private void makePowderSnowBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack, StewBehavior behavior) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type, behavior);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.POWDER_SNOW_BOTTLE.get()).requires(VSItems.POWDER_SNOW_BOTTLE.get()).requires(type.getBowlStack().getItem())
                    .group("powder_snow_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/powder_snow/" + type.getWoodName());
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_POWDER_SNOW_BOTTLE.get()).requires(VSItems.STAINED_POWDER_SNOW_BOTTLE.get()).requires(type.getBowlStack().getItem())
                    .group("powder_snow_bowl").unlockedBy("has_bowl", has(type.getBowlStack().getItem())).save(consumer, "variants:bowls/powder_snow/" + type.getWoodName() + "_stained");
        }
    }

    private ItemStack getResultStack(ItemStack resultStack, BowlType type, StewBehavior behavior) {
        CompoundNBT consumableTag = resultStack.getOrCreateTagElement("consumable");

        consumableTag.put("use_remainder", VSUtils.saveStack(type.getBowlStack(), new CompoundNBT()));

        CompoundNBT behaviorTag = behavior.writePropertiesToNBT();
        behaviorTag.putString("id", behavior.getBehaviorRegistry().getRegistryName().toString());
        consumableTag.put("behavior", behaviorTag);

        resultStack.getOrCreateTag().putInt("texture_id", type.getTextureID());
        return resultStack;
    }
}
