package melonystudios.variants.loot.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
import melonystudios.variants.loot.VSLootFunctions;
import melonystudios.variants.util.VSUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;

import javax.annotation.Nonnull;

public class SetStewBowl extends LootFunction {
    private final ItemStack bowl;
    private final IRandomRange textureID;

    public SetStewBowl(ILootCondition[] conditions, ItemStack bowl, IRandomRange textureID) {
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

    public static SetStewBowl.Builder setStewBowl(ItemStack bowl, IRandomRange textureID) {
        return new SetStewBowl.Builder(bowl, textureID);
    }

    public static class Builder extends LootFunction.Builder<SetStewBowl.Builder> {
        private final ItemStack bowl;
        private final IRandomRange textureID;

        public Builder(ItemStack bowl, IRandomRange textureID) {
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
            JsonObject itemObject = new JsonObject();
            itemObject.addProperty("id", function.bowl.getItem().getRegistryName().toString());
            if (function.bowl.getCount() != 1) itemObject.addProperty("count", function.bowl.getCount());
            object.add("item", itemObject);
            object.add("texture_id", RandomRanges.serialize(function.textureID, context));
        }

        @Override
        @Nonnull
        public SetStewBowl deserialize(JsonObject object, JsonDeserializationContext context, ILootCondition[] conditions) {
            IRandomRange textureID = RandomRanges.deserialize(object.get("texture_id"), context);
            JsonObject itemObject = JSONUtils.getAsJsonObject(object, "item");
            CompoundNBT stackTag = new CompoundNBT();
            stackTag.putString("id", itemObject.get("id").getAsString());
            stackTag.putInt("count", itemObject.get("count").getAsInt());
            if (itemObject.has("components")) {
                stackTag.putString("components", itemObject.get("components").getAsString());
            }

            ItemStack bowlStack = VSUtils.loadStack(stackTag);
            return new SetStewBowl(conditions, bowlStack, textureID);
        }
    }
}
