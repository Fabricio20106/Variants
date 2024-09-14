package melonystudios.variants.loot.function;

import com.google.gson.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import melonystudios.variants.Variants;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
import melonystudios.variants.item.custom.food.TagConfigurableFood;
import melonystudios.variants.loot.VSLootFunctions;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.util.JSONUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

public class SetConsumeBehavior extends LootFunction {
    private final StewBehavior behavior;
    private final CompoundNBT properties;

    public SetConsumeBehavior(ILootCondition[] conditions, StewBehavior behavior, CompoundNBT properties) {
        super(conditions);
        this.behavior = behavior;
        this.properties = properties;
    }

    @Override
    @Nonnull
    public LootFunctionType getType() {
        return VSLootFunctions.SET_CONSUME_BEHAVIOR;
    }

    @Override
    @Nonnull
    public ItemStack run(ItemStack stack, LootContext context) {
        if (stack.getItem() instanceof TagConfigurableFood) ExponentialStewItem.writeBehaviorToStew(stack, this.behavior, this.properties);
        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<SetConsumeBehavior> {
        @Override
        public void serialize(JsonObject object, SetConsumeBehavior function, JsonSerializationContext context) {
            super.serialize(object, function, context);
            object.addProperty("behavior", function.behavior.getRegistryName().toString());
            object.addProperty("properties", function.properties.toString());
        }

        @Override
        @Nonnull
        public SetConsumeBehavior deserialize(JsonObject object, JsonDeserializationContext context, ILootCondition[] conditions) {
            StewBehavior jsonBehavior = JSONUtils.getAsBehavior(object, "behavior");
            CompoundNBT jsonProperties;
            try {
                jsonProperties = JsonToNBT.parseTag(convertToString(object.get("properties"), "properties"));
            } catch (CommandSyntaxException exception) {
                jsonProperties = new CompoundNBT();
            }
            return new SetConsumeBehavior(conditions, jsonBehavior, jsonProperties);
        }

        public static String convertToString(JsonElement element, String objectName) {
            if (element.isJsonPrimitive()) {
                return element.getAsString();
            } else {
                throw new JsonSyntaxException(new TranslationTextComponent("exception." + Variants.MOD_ID + ".string_conversion.not_primitive", objectName).getString());
            }
        }
    }
}
