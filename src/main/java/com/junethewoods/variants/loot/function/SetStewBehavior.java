package com.junethewoods.variants.loot.function;

import com.google.gson.*;
import com.junethewoods.variants.item.custom.food.ExponentialStewItem;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.loot.VSLootFunctions;
import com.junethewoods.variants.util.JSONUtils;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;

import javax.annotation.Nonnull;

public class SetStewBehavior extends LootFunction {
    private final StewBehavior behavior;
    private final CompoundNBT properties;

    public SetStewBehavior(ILootCondition[] conditions, StewBehavior behavior, CompoundNBT properties) {
        super(conditions);
        this.behavior = behavior;
        this.properties = properties;
    }

    @Override
    @Nonnull
    public LootFunctionType getType() {
        return VSLootFunctions.SET_STEW_BEHAVIOR;
    }

    @Override
    @Nonnull
    public ItemStack run(ItemStack stack, LootContext context) {
        if (stack.getItem() instanceof ExponentialStewItem) ExponentialStewItem.writeBehaviorToStew(stack, this.behavior, this.properties);
        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<SetStewBehavior> {
        @Override
        public void serialize(JsonObject object, SetStewBehavior function, JsonSerializationContext context) {
            super.serialize(object, function, context);
            object.addProperty("behavior", function.behavior.getRegistryName().toString());
            object.addProperty("properties", function.properties.toString());
        }

        @Override
        @Nonnull
        public SetStewBehavior deserialize(JsonObject object, JsonDeserializationContext context, ILootCondition[] conditions) {
            StewBehavior jsonBehavior = JSONUtils.getAsBehavior(object, "behavior");
            CompoundNBT jsonProperties;
            try {
                jsonProperties = JsonToNBT.parseTag(convertToString(object.get("properties"), "properties"));
            } catch (CommandSyntaxException exception) {
                jsonProperties = new CompoundNBT();
            }
            return new SetStewBehavior(conditions, jsonBehavior, jsonProperties);
        }

        public static String convertToString(JsonElement element, String objectName) {
            if (element.isJsonPrimitive()) {
                return element.getAsString();
            } else {
                throw new JsonSyntaxException("Expected " + objectName + " to be a string, was an object (" + element + ")");
            }
        }
    }
}
