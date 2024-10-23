package melonystudios.variants.loot.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import melonystudios.variants.item.custom.bottle.StainedFullGlassBottleItem;
import melonystudios.variants.loot.VSLootFunctions;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;

import javax.annotation.Nonnull;

public class SetTextureIdentifier extends LootFunction {
    private final IRandomRange textureID;

    public SetTextureIdentifier(ILootCondition[] conditions, IRandomRange textureID) {
        super(conditions);
        this.textureID = textureID;
    }

    @Override
    @Nonnull
    public LootFunctionType getType() {
        return VSLootFunctions.SET_TEXTURE_IDENTIFIER;
    }

    @Override
    @Nonnull
    protected ItemStack run(ItemStack stack, LootContext context) {
        StainedFullGlassBottleItem.setTextureIdentifier(stack, this.textureID);
        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<SetTextureIdentifier> {
        @Override
        public void serialize(JsonObject object, SetTextureIdentifier function, JsonSerializationContext context) {
            super.serialize(object, function, context);
            object.add("texture_id", RandomRanges.serialize(function.textureID, context));
        }

        @Override
        @Nonnull
        public SetTextureIdentifier deserialize(JsonObject object, JsonDeserializationContext context, ILootCondition[] conditions) {
            IRandomRange randomRange = RandomRanges.deserialize(object.get("texture_id"), context);
            return new SetTextureIdentifier(conditions, randomRange);
        }
    }
}
