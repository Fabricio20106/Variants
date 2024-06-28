package com.junethewoods.variants.data;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.junethewoods.variants.item.custom.food.ExponentialStewItem;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.custom.*;
import com.junethewoods.variants.util.JSONUtils;
import com.junethewoods.variants.util.NBTUtils;
import com.junethewoods.variants.util.VSRegistries;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class ExponentialStewRecipeBuilder {
    private final ItemStack result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private String group;

    public ExponentialStewRecipeBuilder(ItemStack result, int count) {
        this.result = result;
        this.count = count;
    }

    public static ExponentialStewRecipeBuilder shapeless(ItemStack result) {
        return new ExponentialStewRecipeBuilder(result, 1);
    }

    public ExponentialStewRecipeBuilder requires(IItemProvider item) {
        return this.requires(item, 1);
    }

    public ExponentialStewRecipeBuilder requires(IItemProvider item, int count) {
        for(int i = 0; i < count; ++i) {
            this.requires(Ingredient.of(item));
        }
        return this;
    }

    public ExponentialStewRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public ExponentialStewRecipeBuilder requires(Ingredient ingredient, int count) {
        for(int i = 0; i < count; ++i) {
            this.ingredients.add(ingredient);
        }
        return this;
    }

    public ExponentialStewRecipeBuilder unlockedBy(String name, ICriterionInstance criteria) {
        this.advancement.addCriterion(name, criteria);
        return this;
    }

    public ExponentialStewRecipeBuilder group(String groupName) {
        this.group = groupName;
        return this;
    }

    public void save(Consumer<IFinishedRecipe> consumer, String name) {
        ResourceLocation itemLocation = Registry.ITEM.getKey(this.result.getItem());
        if (new ResourceLocation(name).equals(itemLocation)) {
            throw new IllegalStateException(new TranslationTextComponent("error.variants.expo_stew_recipe_builder.remove_save_argument", name).getString());
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
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException(new TranslationTextComponent("error.variants.expo_stew_recipe_builder.recipe_unobtainable", name).getString());
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
            if (!this.group.isEmpty()) object.addProperty("group", this.group);

            JsonArray array = new JsonArray();

            for(Ingredient ingredient : this.ingredients) {
                array.add(ingredient.toJson());
            }

            object.add("ingredients", array);
            JsonObject result = new JsonObject();
            result.addProperty("item", Registry.ITEM.getKey(this.result.getItem()).toString());
            if (this.count > 1) {
                result.addProperty("count", this.count);
            }
            if (this.result.hasTag() && this.result.getTagElement("bowl") != null) {
                result.add("nbt", this.serializeNBT());
            }

            object.add("result", result);
        }

        public JsonElement serializeNBT() {
            JsonObject object = new JsonObject();
            object.add("bowl", this.serializeBowlType());
            object.add("behavior", this.serializeStewBehavior());
            return object;
        }

        private JsonObject serializeBowlType() {
            CompoundNBT bowlTypeTag = this.result.getOrCreateTagElement("bowl");
            ResourceLocation containerItem = new ResourceLocation(bowlTypeTag.getString("name"));
            int containerID = bowlTypeTag.getInt("texture_id");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", ForgeRegistries.ITEMS.getValue(containerItem).getRegistryName().toString());
            jsonObject.addProperty("texture_id", containerID);
            return jsonObject;
        }

        private JsonObject serializeStewBehavior() {
            CompoundNBT behaviorTag = this.result.getOrCreateTagElement("behavior");
            CompoundNBT propertiesTag = behaviorTag.getCompound("properties");
            StewBehavior behavior = VSRegistries.STEW_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));

            JsonObject behaviorObj = new JsonObject();
            JsonObject propertiesObj = new JsonObject();

            behaviorObj.addProperty("id", behaviorTag.getString("id"));

            if (this.result.getItem() instanceof ExponentialStewItem) {
                ExponentialStewItem expoStew = (ExponentialStewItem) this.result.getItem();

                // Behaviors
                if (behavior instanceof ApplyMobEffectsBehavior) {
                    ApplyMobEffectsBehavior behavior1 = (ApplyMobEffectsBehavior) expoStew.getBehavior();
                    JsonArray effectsList = new JsonArray();
                    for (EffectInstance instance : behavior1.getEffects()) {
                        JsonObject effectObj = new JsonObject();
                        effectObj.addProperty("id", instance.getEffect().getRegistryName().toString());
                        effectObj.addProperty("duration", instance.getDuration());
                        effectObj.addProperty("amplifier", instance.getAmplifier());
                        effectsList.add(effectObj);
                    }
                    propertiesObj.add("effects", effectsList);
                } else if (behavior instanceof ClearMobEffectsBehavior) {
                    JSONUtils.writeItemFromNBT("curative_item", propertiesTag, propertiesObj);
                } else if (behavior instanceof DamageEntityBehavior) {
                    JSONUtils.writeDamageSourceFromNBT(propertiesTag, propertiesObj);
                } else if (behavior instanceof ExplodeBehavior) {
                    JSONUtils.writeExplosionFromNBT(propertiesTag, propertiesObj);
                } else if (behavior instanceof IgniteBehavior && this.result.getItem() instanceof ExponentialStewItem) {
                    IgniteBehavior igniteBehavior = (IgniteBehavior) expoStew.getBehavior();
                    propertiesObj.addProperty("ticks_on_fire", igniteBehavior.getTicksOnFire());
                }
            }
            behaviorObj.add("properties", propertiesObj);
            return behaviorObj;
        }

        @Nonnull
        public IRecipeSerializer<?> getType() {
            return IRecipeSerializer.SHAPELESS_RECIPE;
        }

        @Nonnull
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
