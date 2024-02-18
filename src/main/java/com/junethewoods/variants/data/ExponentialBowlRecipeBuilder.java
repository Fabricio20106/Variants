package com.junethewoods.variants.data;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class ExponentialBowlRecipeBuilder {
    private final ItemStack result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private String group;

    public ExponentialBowlRecipeBuilder(ItemStack result, int count) {
        this.result = result;
        this.count = count;
    }

    public static ExponentialBowlRecipeBuilder shapeless(ItemStack result) {
        return new ExponentialBowlRecipeBuilder(result, 1);
    }

    public ExponentialBowlRecipeBuilder requires(IItemProvider item) {
        return this.requires(item, 1);
    }

    public ExponentialBowlRecipeBuilder requires(IItemProvider item, int count) {
        for(int i = 0; i < count; ++i) {
            this.requires(Ingredient.of(item));
        }
        return this;
    }

    public ExponentialBowlRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public ExponentialBowlRecipeBuilder requires(Ingredient ingredient, int count) {
        for(int i = 0; i < count; ++i) {
            this.ingredients.add(ingredient);
        }
        return this;
    }

    public ExponentialBowlRecipeBuilder unlockedBy(String name, ICriterionInstance criteria) {
        this.advancement.addCriterion(name, criteria);
        return this;
    }

    public ExponentialBowlRecipeBuilder group(String groupName) {
        this.group = groupName;
        return this;
    }

    public void save(Consumer<IFinishedRecipe> consumer, String name) {
        ResourceLocation itemLocation = Registry.ITEM.getKey(this.result.getItem());
        if (new ResourceLocation(name).equals(itemLocation)) {
            throw new IllegalStateException("Variants: Bowl Shapeless Recipe " + name + " should remove its 'save' argument.");
        } else {
            this.save(consumer, new ResourceLocation(name));
        }
    }

    public void save(Consumer<IFinishedRecipe> consumer, ResourceLocation name) {
        this.ensureValid(name);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(name)).rewards(AdvancementRewards.Builder.recipe(name)).requirements(IRequirementsStrategy.OR);
        consumer.accept(new Result(name, this.result, this.count, this.group == null ? "" : this.group, this.ingredients, this.advancement, new ResourceLocation(name.getNamespace(), "recipes/" + this.result.getItem().getItemCategory().getRecipeFolderName() + "/"
                + name.getPath())));
    }

    private void ensureValid(ResourceLocation name) {
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("Variants: No way of obtaining recipe " + name);
    }

    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final ItemStack result;
        private final int count;
        private final String group;
        private final List<Ingredient> ingredients;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementID;

        public Result(ResourceLocation name, ItemStack result, int count, String group, List<Ingredient> ingredients, Advancement.Builder advancement, ResourceLocation advancementID) {
            this.id = name;
            this.result = result;
            this.count = count;
            this.group = group;
            this.ingredients = ingredients;
            this.advancement = advancement;
            this.advancementID = advancementID;
        }

        @Override
        public void serializeRecipeData(JsonObject object) {
            if (!this.group.isEmpty()) {
                object.addProperty("group", this.group);
            }

            JsonArray jsonArray = new JsonArray();

            for(Ingredient ingredient : this.ingredients) {
                jsonArray.add(ingredient.toJson());
            }

            object.add("ingredients", jsonArray);
            JsonObject result = new JsonObject();
            result.addProperty("item", Registry.ITEM.getKey(this.result.getItem()).toString());
            if (this.count > 1) {
                result.addProperty("count", this.count);
            }
            if (this.result.hasTag() && this.result.getTagElement("bowl_type") != null) {
                result.add("nbt", this.serializeNBT());
            }

            object.add("result", result);
        }

        public JsonElement serializeNBT() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("bowl_type", this.serializeBowlType());
            return jsonObject;
        }

        private JsonObject serializeBowlType() {
            CompoundNBT bowlTypeTag = this.result.getOrCreateTagElement("bowl_type");
            ResourceLocation containerItem = new ResourceLocation(bowlTypeTag.getString("bowl_name"));
            int containerID = bowlTypeTag.getInt("bowl_id");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bowl_name", ForgeRegistries.ITEMS.getValue(containerItem).getRegistryName().toString());
            jsonObject.addProperty("bowl_id", containerID);
            return jsonObject;
        }

        public IRecipeSerializer<?> getType() {
            return IRecipeSerializer.SHAPELESS_RECIPE;
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementID;
        }
    }
}
