package melonystudios.variants.data.recipe;

import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.bottle.GlassType;
import melonystudios.variants.item.custom.bottle.StainedFullGlassBottleItem;
import melonystudios.variants.util.VSUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class VSStainedBottlesRecipeProvider extends RecipeProvider {
    public VSStainedBottlesRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Stained Bottle Recipes";
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        for (GlassType type : StainedFullGlassBottleItem.BOTTLES) {
            {
                ItemStack honeyBottle = new ItemStack(VSItems.STAINED_HONEY_BOTTLE.get(), 4);
                CompoundNBT consumableTag = honeyBottle.getOrCreateTagElement("consumable");
                consumableTag.put("use_remainder", VSUtils.saveStack(type.getBottle(), new CompoundNBT()));
                honeyBottle.getOrCreateTag().putInt("texture_id", type.getTextureIdentifier());

                NBTSavingRecipeBuilder.shapeless(honeyBottle).requires(Items.HONEY_BLOCK).requires(type.getBottle().getItem()).requires(type.getBottle().getItem()).requires(type.getBottle().getItem()).requires(type.getBottle().getItem())
                        .group("honey_bottle").unlockedBy("has_honey_block", has(Items.HONEY_BLOCK)).save(consumer, "variants:stained_bottle/honey/" + type.getName());
            }
            {
                ItemStack lavaBottle = new ItemStack(VSItems.STAINED_LAVA_BOTTLE.get(), 4);
                CompoundNBT consumableTag = lavaBottle.getOrCreateTagElement("consumable");
                consumableTag.put("use_remainder", VSUtils.saveStack(type.getBottle(), new CompoundNBT()));
                lavaBottle.getOrCreateTag().putInt("texture_id", type.getTextureIdentifier());

                NBTSavingRecipeBuilder.shapeless(lavaBottle).requires(Items.LAVA_BUCKET).requires(type.getBottle().getItem()).requires(type.getBottle().getItem()).requires(type.getBottle().getItem()).requires(type.getBottle().getItem())
                        .group("lava_bottle").unlockedBy("has_lava_bucket", has(Items.LAVA_BUCKET)).save(consumer, "variants:stained_bottle/lava/" + type.getName());
            }
            {
                ItemStack soulLavaBottle = new ItemStack(VSItems.STAINED_SOUL_LAVA_BOTTLE.get(), 4);
                CompoundNBT consumableTag = soulLavaBottle.getOrCreateTagElement("consumable");
                consumableTag.put("use_remainder", VSUtils.saveStack(type.getBottle(), new CompoundNBT()));
                soulLavaBottle.getOrCreateTag().putInt("texture_id", type.getTextureIdentifier());

                NBTSavingRecipeBuilder.shapeless(soulLavaBottle).requires(VSItems.SOUL_LAVA_BUCKET.get()).requires(type.getBottle().getItem()).requires(type.getBottle().getItem()).requires(type.getBottle().getItem()).requires(type.getBottle().getItem())
                        .group("soul_lava_bottle").unlockedBy("has_soul_lava_bucket", has(VSItems.SOUL_LAVA_BUCKET.get())).save(consumer, "variants:stained_bottle/soul_lava/" + type.getName());
            }
            {
                ItemStack milkBottle = new ItemStack(VSItems.STAINED_MILK_BOTTLE.get(), 4);
                CompoundNBT consumableTag = milkBottle.getOrCreateTagElement("consumable");
                consumableTag.put("use_remainder", VSUtils.saveStack(type.getBottle(), new CompoundNBT()));
                milkBottle.getOrCreateTag().putInt("texture_id", type.getTextureIdentifier());

                NBTSavingRecipeBuilder.shapeless(milkBottle).requires(Items.MILK_BUCKET).requires(type.getBottle().getItem()).requires(type.getBottle().getItem()).requires(type.getBottle().getItem()).requires(type.getBottle().getItem())
                        .group("milk_bottle").unlockedBy("has_milk_bucket", has(Items.MILK_BUCKET)).save(consumer, "variants:stained_bottle/milk/" + type.getName());
            }
            // TODO: Make a conditional recipe builder for powder snow bottles.
            // (and also a shaped recipe builder for Sophie Potions).
        }
    }
}
