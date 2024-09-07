package melonystudios.variants.data.recipes;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.custom.*;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.JSONUtils;
import melonystudios.variants.util.VSRegistries;
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
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
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
        for (int i = 0; i < count; ++i) this.requires(Ingredient.of(item));
        return this;
    }

    public ExponentialStewRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public ExponentialStewRecipeBuilder requires(Ingredient ingredient, int count) {
        for (int i = 0; i < count; ++i) this.ingredients.add(ingredient);
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

    public void save(Consumer<IFinishedRecipe> consumer, String name) throws IllegalStateException {
        ResourceLocation itemLocation = ForgeRegistries.ITEMS.getKey(this.result.getItem());
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
            for (Ingredient ingredient : this.ingredients) array.add(ingredient.toJson());

            object.add("ingredients", array);
            JsonObject result = new JsonObject();
            result.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result.getItem()).toString());
            if (this.count > 1) result.addProperty("count", this.count);
            if (this.result.getTag() != null) result.add("nbt", this.serializeNBT());

            object.add("result", result);
        }

        public JsonElement serializeNBT() {
            JsonObject rootObj = new JsonObject();
            JsonObject consumableObj = new JsonObject();
            consumableObj.add("use_remainder", this.serializeBowl());
            consumableObj.add("behavior", this.serializeConsumeBehavior());
            rootObj.add("consumable", consumableObj);
            CompoundNBT tag = this.result.getTag();
            if (tag != null && tag.contains("texture_id", Constants.TagTypes.ANY_NUMERIC)) {
                rootObj.addProperty("texture_id", tag.getInt("texture_id"));
            }
            return rootObj;
        }

        private JsonObject serializeBowl() {
            CompoundNBT consumableTag = this.result.getOrCreateTagElement("consumable");
            CompoundNBT remainderTag = consumableTag.getCompound("use_remainder");
            JsonObject object = new JsonObject();
            JsonObject consumableObj = new JsonObject();
            JsonObject remainderObj = new JsonObject();

            remainderObj.addProperty("id", remainderTag.getString("id"));
            if (remainderTag.getInt("count") > 1) remainderObj.addProperty("count", remainderTag.getInt("count"));
            consumableObj.add("use_remainder", remainderObj);

            object.add("consumable", consumableObj);
            object.addProperty("texture_id", this.result.getOrCreateTag().getInt("texture_id"));
            return remainderObj;
        }

        private JsonObject serializeConsumeBehavior() {
            CompoundNBT consumableTag = this.result.getOrCreateTagElement("consumable");
            CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
            StewBehavior behavior = VSRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));

            JsonObject behaviorObj = new JsonObject();

            behaviorObj.addProperty("id", behaviorTag.getString("id"));

            if (this.result.getItem() instanceof ExponentialStewItem) {
                ExponentialStewItem expoStew = (ExponentialStewItem) this.result.getItem();

                // Behaviors
                if (behavior instanceof ApplyMobEffectsBehavior) {
                    ApplyMobEffectsBehavior applyEffectsBehavior = (ApplyMobEffectsBehavior) expoStew.getBehavior();
                    JsonArray effectsList = new JsonArray();
                    for (EffectInstance instance : applyEffectsBehavior.getEffects()) {
                        JsonObject effectObj = new JsonObject();
                        effectObj.addProperty("id", instance.getEffect().getRegistryName().toString());
                        effectObj.addProperty("duration", instance.getDuration());
                        if (instance.getAmplifier() > 0) effectObj.addProperty("amplifier", instance.getAmplifier());
                        if (instance.isAmbient()) effectObj.addProperty("ambient", true);
                        if (!instance.isVisible()) effectObj.addProperty("show_particle", true);
                        if (!instance.showIcon()) effectObj.addProperty("show_icon", true);
                        if (instance.isNoCounter()) effectObj.addProperty("no_counter", true);
                        effectsList.add(effectObj);
                    }
                    behaviorObj.add("effects", effectsList);
                } else if (behavior instanceof ClearMobEffectsBehavior) {
                    ClearMobEffectsBehavior clearEffectsBehavior = (ClearMobEffectsBehavior) expoStew.getBehavior();
                    JsonObject curativeObject = new JsonObject();
                    curativeObject.addProperty("id", clearEffectsBehavior.getCurativeItem().getItem().getRegistryName().toString());
                    if (clearEffectsBehavior.getCurativeItem().getCount() != 1) curativeObject.addProperty("count", clearEffectsBehavior.getCurativeItem().getCount());
                    if (clearEffectsBehavior.getCurativeItem().getTag() != null) curativeObject.addProperty("components", clearEffectsBehavior.getCurativeItem().getTag().toString());
                    behaviorObj.add("curative_item", curativeObject);
                } else if (behavior instanceof DamageEntityBehavior) {
                    JSONUtils.writeDamageSourceFromNBT(behaviorTag, behaviorObj);
                } else if (behavior instanceof ExplodeBehavior) {
                    JSONUtils.writeExplosionFromNBT(behaviorTag, behaviorObj);
                } else if (behavior instanceof IgniteBehavior) {
                    IgniteBehavior igniteBehavior = (IgniteBehavior) expoStew.getBehavior();
                    behaviorObj.addProperty("ticks_on_fire", igniteBehavior.getTicksOnFire());
                } else if (behavior instanceof AddExperienceBehavior) {
                    AddExperienceBehavior addExperienceBehavior = (AddExperienceBehavior) expoStew.getBehavior();
                    behaviorObj.addProperty("amount", addExperienceBehavior.getExperienceAmount());
                    behaviorObj.addProperty("levels", addExperienceBehavior.addsLevels());
                } else if (behavior instanceof TeleportEntityBehavior) {
                    TeleportEntityBehavior teleportBehavior = (TeleportEntityBehavior) expoStew.getBehavior();
                    if (teleportBehavior.randomlyTeleports()) {
                        behaviorObj.addProperty("random_teleport", true);
                        behaviorObj.addProperty("teleport_diameter", teleportBehavior.getTeleportDiameter());
                    } else {
                        behaviorObj.addProperty("random_teleport", false);
                        JsonArray posArray = new JsonArray();
                        posArray.add(teleportBehavior.getTeleportPosition().getX());
                        posArray.add(teleportBehavior.getTeleportPosition().getY());
                        posArray.add(teleportBehavior.getTeleportPosition().getZ());
                        behaviorObj.add("teleport_position", posArray);
                    }
                } else if (behavior instanceof RemoveEffectsBehavior) {
                    RemoveEffectsBehavior removeEffectsBehavior = (RemoveEffectsBehavior) expoStew.getBehavior();
                    JsonArray effectArray = new JsonArray();
                    for (Effect effect : removeEffectsBehavior.getEffectsToRemove()) effectArray.add(effect.getRegistryName().toString());
                    behaviorObj.add("effects", effectArray);
                }
            }
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
