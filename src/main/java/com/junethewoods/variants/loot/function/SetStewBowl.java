package com.junethewoods.variants.loot.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.junethewoods.variants.item.custom.food.ExponentialStewItem;
import com.junethewoods.variants.loot.VSLootFunctions;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.util.JSONUtils;

import javax.annotation.Nonnull;

public class SetStewBowl extends LootFunction {
    private final Item bowl;
    private final IRandomRange textureID;

    public SetStewBowl(ILootCondition[] conditions, Item bowl, IRandomRange textureID) {
        super(conditions);
        this.bowl = bowl;
        this.textureID = textureID;
    }

    @Override
    @Nonnull
    public LootFunctionType getType() {
        return VSLootFunctions.SET_STEW_BOWL;
    }

    @Override
    @Nonnull
    public ItemStack run(ItemStack stack, LootContext context) {
        if (stack.getItem() instanceof ExponentialStewItem) ExponentialStewItem.writeBowlWithTextureID(stack, this.bowl, this.textureID);
        return stack;
    }

    public static SetStewBowl.Builder setStewBowl(Item bowl, IRandomRange textureID) {
        return new SetStewBowl.Builder(bowl, textureID);
    }

    public static class Builder extends LootFunction.Builder<SetStewBowl.Builder> {
        private final Item bowl;
        private final IRandomRange textureID;

        public Builder(Item bowl, IRandomRange textureID) {
            this.bowl = bowl;
            this.textureID = textureID;
        }

        @Override
        @Nonnull
        protected Builder getThis() {
            return this;
        }

        @Override
        @Nonnull
        public ILootFunction build() {
            return new SetStewBowl(this.getConditions(), this.bowl, this.textureID);
        }
    }

    public static class Serializer extends LootFunction.Serializer<SetStewBowl> {
        @Override
        public void serialize(JsonObject object, SetStewBowl function, JsonSerializationContext context) {
            super.serialize(object, function, context);
            object.addProperty("bowl", function.bowl.getRegistryName().toString());
            object.add("texture_id", RandomRanges.serialize(function.textureID, context));
        }

        @Override
        @Nonnull
        public SetStewBowl deserialize(JsonObject object, JsonDeserializationContext context, ILootCondition[] conditions) {
            IRandomRange textureID = RandomRanges.deserialize(object.get("texture_id"), context);
            Item bowl = JSONUtils.getAsItem(object, "bowl");
            return new SetStewBowl(conditions, bowl, textureID);
        }
    }
}
