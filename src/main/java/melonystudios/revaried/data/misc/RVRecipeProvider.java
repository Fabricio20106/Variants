package melonystudios.revaried.data.misc;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.tag.RVItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.DifferenceIngredient;

import java.util.concurrent.CompletableFuture;

import static melonystudios.revaried.item.RVItems.*;

public class RVRecipeProvider extends RecipeProvider {
    public RVRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output, HolderLookup.Provider lookup) {
        // Blocks

        // Items
        // Shulker shells
        makeShulkerShell(output, WHITE_SHULKER_SHELL, Tags.Items.DYES_WHITE);
        makeShulkerShell(output, LIGHT_GRAY_SHULKER_SHELL, Tags.Items.DYES_LIGHT_GRAY);
        makeShulkerShell(output, GRAY_SHULKER_SHELL, Tags.Items.DYES_GRAY);
        makeShulkerShell(output, BLACK_SHULKER_SHELL, Tags.Items.DYES_BLACK);
        makeShulkerShell(output, BROWN_SHULKER_SHELL, Tags.Items.DYES_BROWN);
        makeShulkerShell(output, RED_SHULKER_SHELL, Tags.Items.DYES_RED);
        makeShulkerShell(output, ORANGE_SHULKER_SHELL, Tags.Items.DYES_ORANGE);
        makeShulkerShell(output, YELLOW_SHULKER_SHELL, Tags.Items.DYES_YELLOW);
        makeShulkerShell(output, LIME_SHULKER_SHELL, Tags.Items.DYES_LIME);
        makeShulkerShell(output, GREEN_SHULKER_SHELL, Tags.Items.DYES_GREEN);
        makeShulkerShell(output, CYAN_SHULKER_SHELL, Tags.Items.DYES_CYAN);
        makeShulkerShell(output, LIGHT_BLUE_SHULKER_SHELL, Tags.Items.DYES_LIGHT_BLUE);
        makeShulkerShell(output, BLUE_SHULKER_SHELL, Tags.Items.DYES_BLUE);
        makeShulkerShell(output, PURPLE_SHULKER_SHELL, Tags.Items.DYES_PURPLE);
        makeShulkerShell(output, MAGENTA_SHULKER_SHELL, Tags.Items.DYES_MAGENTA);
        makeShulkerShell(output, PINK_SHULKER_SHELL, Tags.Items.DYES_PINK);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.SHULKER_SHELL).requires(DifferenceIngredient.of(Ingredient.of(RVItemTags.SHULKER_SHELLS), Ingredient.of(Items.SHULKER_SHELL)))
                .requires(Items.INK_SAC).unlockedBy("has_ink_sac", has(Items.INK_SAC))
                .group("shulker_shell").save(output, Revaried.revaried("shulker_shell_from_uncoloring"));

        // Shulker Boxes
        makeShulkerBox(output, WHITE_SHULKER_SHELL, Items.WHITE_SHULKER_BOX);
        makeShulkerBox(output, LIGHT_GRAY_SHULKER_SHELL, Items.LIGHT_GRAY_SHULKER_BOX);
        makeShulkerBox(output, GRAY_SHULKER_SHELL, Items.GRAY_SHULKER_BOX);
        makeShulkerBox(output, BLACK_SHULKER_SHELL, Items.BLACK_SHULKER_BOX);
        makeShulkerBox(output, BROWN_SHULKER_SHELL, Items.BROWN_SHULKER_BOX);
        makeShulkerBox(output, RED_SHULKER_SHELL, Items.RED_SHULKER_BOX);
        makeShulkerBox(output, ORANGE_SHULKER_SHELL, Items.ORANGE_SHULKER_BOX);
        makeShulkerBox(output, YELLOW_SHULKER_SHELL, Items.YELLOW_SHULKER_BOX);
        makeShulkerBox(output, LIME_SHULKER_SHELL, Items.LIME_SHULKER_BOX);
        makeShulkerBox(output, GREEN_SHULKER_SHELL, Items.GREEN_SHULKER_BOX);
        makeShulkerBox(output, CYAN_SHULKER_SHELL, Items.CYAN_SHULKER_BOX);
        makeShulkerBox(output, LIGHT_BLUE_SHULKER_SHELL, Items.LIGHT_BLUE_SHULKER_BOX);
        makeShulkerBox(output, BLUE_SHULKER_SHELL, Items.BLUE_SHULKER_BOX);
        makeShulkerBox(output, PURPLE_SHULKER_SHELL, Items.PURPLE_SHULKER_BOX);
        makeShulkerBox(output, MAGENTA_SHULKER_SHELL, Items.MAGENTA_SHULKER_BOX);
        makeShulkerBox(output, PINK_SHULKER_SHELL, Items.PINK_SHULKER_BOX);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SHULKER_BOX).define('#', Items.SHULKER_SHELL).define('C', DifferenceIngredient.of(Ingredient.of(Tags.Items.CHESTS_WOODEN), Ingredient.of(Tags.Items.CHESTS_TRAPPED)))
                .pattern("#").pattern("C").pattern("#").unlockedBy("has_shulker_shell", has(Items.SHULKER_SHELL))
                .group("shulker_box").save(output);
    }

    protected static void makeShulkerShell(RecipeOutput output, ItemLike shulkerShell, TagKey<Item> dyesTag) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, shulkerShell).requires(dyesTag).requires(DifferenceIngredient.of(Ingredient.of(RVItemTags.SHULKER_SHELLS), Ingredient.of(shulkerShell)))
                .unlockedBy("has_needed_dye", has(dyesTag)).group("shulker_shell")
                .save(output, Revaried.revaried("dye_" + BuiltInRegistries.ITEM.getKey(shulkerShell.asItem()).getPath()));
    }

    protected static void makeShulkerBox(RecipeOutput output, ItemLike shulkerShell, ItemLike shulkerBox) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, shulkerBox).define('#', shulkerShell).define('C', DifferenceIngredient.of(Ingredient.of(Tags.Items.CHESTS_WOODEN), Ingredient.of(Tags.Items.CHESTS_TRAPPED)))
                .pattern("#").pattern("C").pattern("#").unlockedBy("has_shulker_shell", has(shulkerShell))
                .group("shulker_box").save(output, Revaried.revaried(BuiltInRegistries.ITEM.getKey(shulkerBox.asItem()).getPath()));
    }
}
