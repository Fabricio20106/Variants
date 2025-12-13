package melonystudios.variants.data.recipe;

import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.item.bowl.BowlType;
import melonystudios.variants.item.bowl.BowlTypes;
import melonystudios.variants.item.custom.food.ConsumableItem;
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
    public static final List<BowlType> DEFAULT_BOWLS = Lists.newArrayList(BowlTypes.OAK, BowlTypes.SPRUCE, BowlTypes.BIRCH, BowlTypes.JUNGLE, BowlTypes.ACACIA, BowlTypes.DARK_OAK, BowlTypes.PAINTING, BowlTypes.CRIMSON, BowlTypes.WARPED,
            BowlTypes.ENDERWOOD);

    public VSExpoStewsRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    @Nonnull
    public String getName() {
        return Variants.generatorName("Exponential Stew Recipes");
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        this.makeMushroomStew(consumer, new ItemStack(VSItems.EXPONENTIAL_MUSHROOM_STEW.get()));
        this.makeBeetrootSoup(consumer, new ItemStack(VSItems.EXPONENTIAL_BEETROOT_SOUP.get()));
        this.makeRabbitStew(consumer, new ItemStack(VSItems.EXPONENTIAL_RABBIT_STEW.get()));
        this.makeMeltingBeetSoup(consumer, new ItemStack(VSItems.EXPONENTIAL_MELTING_BEET_SOUP.get()));
        this.makeFungiStew(consumer, new ItemStack(VSItems.EXPONENTIAL_FUNGI_STEW.get()));
        this.makeEndFungiStew(consumer, new ItemStack(VSItems.EXPONENTIAL_END_FUNGI_STEW.get()));
        this.makeWaterBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_WATER_BOWL.get()));
        this.makeLavaBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_LAVA_BOWL.get()));
        this.makeSoulLavaBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_SOUL_LAVA_BOWL.get()));
        this.makeMilkBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_MILK_BOWL.get()));
        this.makePowderSnowBowl(consumer, new ItemStack(VSItems.EXPONENTIAL_POWDER_SNOW_BOWL.get()));
    }

    private void makeMushroomStew(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.BROWN_MUSHROOM).requires(Items.RED_MUSHROOM).requires(type.bowl().getItem()).group("mushroom_stew").unlockedBy("has_bowl", has(type.bowl()
                    .getItem())).save(consumer, Variants.variants("stews/mushroom/" + type.name()));
        }
    }

    private void makeBeetrootSoup(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(Items.BEETROOT).requires(type.bowl().getItem())
                    .group("beetroot_soup").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("stews/beetroot/" + type.name()));
        }
    }

    private void makeRabbitStew(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.BAKED_POTATO).requires(Items.COOKED_RABBIT).requires(Items.CARROT).requires(Ingredient.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM)).requires(type.bowl().getItem())
                    .group("rabbit_stew").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("stews/rabbit/" + type.name()));
        }
    }

    private void makeMeltingBeetSoup(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.MELTING_BEET.get()).requires(VSItems.MELTING_BEET.get()).requires(VSItems.MELTING_BEET.get()).requires(VSItems.MELTING_BEET.get()).requires(VSItems.MELTING_BEET.get())
                    .requires(VSItems.MELTING_BEET.get()).requires(type.bowl().getItem()).group("melting_beet_soup").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("stews/melting_beet/" + type.name()));
        }
    }

    private void makeFungiStew(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.CRIMSON_FUNGUS).requires(Items.WARPED_FUNGUS).requires(type.bowl().getItem())
                    .group("fungi_stew").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("stews/fungi/" + type.name()));
        }
    }

    private void makeEndFungiStew(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.ENDER_FUNGUS.get()).requires(VSItems.ENDER_FUNGUS.get()).requires(type.bowl().getItem())
                    .group("end_fungi_stew").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("stews/end_fungi/" + type.name()));
        }
    }

    private void makeWaterBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(Items.POTION).requires(Items.POTION).requires(type.bowl().getItem())
                    .group("water_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/water/" + type.name()));
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_POTION.get()).requires(VSItems.STAINED_POTION.get()).requires(type.bowl().getItem())
                    .group("water_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/water/" + type.name() + "_stained"));
        }
    }

    private void makeLavaBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.LAVA_BOTTLE.get()).requires(VSItems.LAVA_BOTTLE.get()).requires(type.bowl().getItem())
                    .group("lava_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/lava/" + type.name()));
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_LAVA_BOTTLE.get()).requires(VSItems.STAINED_LAVA_BOTTLE.get()).requires(type.bowl().getItem())
                    .group("lava_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/lava/" + type.name() + "_stained"));
        }
    }

    private void makeSoulLavaBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.SOUL_LAVA_BOTTLE.get()).requires(VSItems.SOUL_LAVA_BOTTLE.get()).requires(type.bowl().getItem())
                    .group("soul_lava_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/soul_lava/" + type.name()));
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_SOUL_LAVA_BOTTLE.get()).requires(VSItems.STAINED_SOUL_LAVA_BOTTLE.get()).requires(type.bowl().getItem())
                    .group("soul_lava_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/soul_lava/" + type.name())+ "_stained");
        }
    }

    private void makeMilkBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.MILK_BOTTLE.get()).requires(VSItems.MILK_BOTTLE.get()).requires(type.bowl().getItem())
                    .group("milk_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/milk/" + type.name()));
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_MILK_BOTTLE.get()).requires(VSItems.STAINED_MILK_BOTTLE.get()).requires(type.bowl().getItem())
                    .group("milk_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/milk/" + type.name() + "_stained"));
        }
    }

    private void makePowderSnowBowl(Consumer<IFinishedRecipe> consumer, ItemStack stewStack) {
        for (BowlType type : DEFAULT_BOWLS) {
            ItemStack resultStack = getResultStack(stewStack, type);
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.POWDER_SNOW_BOTTLE.get()).requires(VSItems.POWDER_SNOW_BOTTLE.get()).requires(type.bowl().getItem())
                    .group("powder_snow_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/powder_snow/" + type.name()));
            NBTSavingRecipeBuilder.shapeless(resultStack).requires(VSItems.STAINED_POWDER_SNOW_BOTTLE.get()).requires(VSItems.STAINED_POWDER_SNOW_BOTTLE.get()).requires(type.bowl().getItem())
                    .group("powder_snow_bowl").unlockedBy("has_bowl", has(type.bowl().getItem())).save(consumer, Variants.variants("bowls/powder_snow/" + type.name() + "_stained"));
        }
    }

    private ItemStack getResultStack(ItemStack resultStack, BowlType type) {
        CompoundNBT consumableTag = resultStack.getOrCreateTagElement("consumable");

        consumableTag.put("use_remainder", VSUtils.saveStack(type.bowl(), new CompoundNBT()));

        if (resultStack.getItem() instanceof ConsumableItem) {
            ConsumeBehavior behavior1 = ((ConsumableItem) resultStack.getItem()).behavior();
            CompoundNBT behaviorTag = behavior1.writeProperties();
            behaviorTag.putString("id", behavior1.registryEntry().getRegistryName().toString());
            consumableTag.put("behavior", behaviorTag);
        }

        resultStack.getOrCreateTag().putInt("texture_id", type.textureID());
        return resultStack;
    }
}
