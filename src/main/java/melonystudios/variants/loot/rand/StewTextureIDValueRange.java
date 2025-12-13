package melonystudios.variants.loot.rand;

import com.google.gson.*;
import melonystudios.variants.Variants;
import melonystudios.variants.item.bowl.BowlType;
import net.minecraft.loot.IRandomRange;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;
import java.lang.reflect.Type;
import java.util.Random;

public class StewTextureIDValueRange implements IRandomRange {
    private final int value;

    public StewTextureIDValueRange(int value) {
        this.value = MathHelper.clamp(value, 0, BowlType.TEXTURE_IDENTIFIERS.size() - 1);
    }

    @Override
    public int getInt(Random rand) {
        return MathHelper.clamp(this.value, 0, BowlType.TEXTURE_IDENTIFIERS.size() - 1);
    }

    @Override
    @Nonnull
    public ResourceLocation getType() {
        return Variants.variants("stew_texture_id");
    }

    public static StewTextureIDValueRange range(int value) {
        return new StewTextureIDValueRange(value);
    }

    public static class Serializer implements JsonDeserializer<StewTextureIDValueRange>, JsonSerializer<StewTextureIDValueRange> {
        @Override
        public StewTextureIDValueRange deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            return new StewTextureIDValueRange(JSONUtils.convertToInt(element, "value"));
        }

        @Override
        public JsonElement serialize(StewTextureIDValueRange source, Type sourceType, JsonSerializationContext context) {
            return new JsonPrimitive(source.value);
        }
    }
}
