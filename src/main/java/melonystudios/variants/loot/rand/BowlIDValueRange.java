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

public class BowlIDValueRange implements IRandomRange {
    private final int value;

    public BowlIDValueRange(int value) {
        this.value = MathHelper.clamp(value, 0, BowlType.TEXTURE_IDENTIFIERS.size() - 1);
    }

    @Override
    public int getInt(Random rand) {
        return MathHelper.clamp(this.value, 0, BowlType.TEXTURE_IDENTIFIERS.size() - 1);
    }

    @Override
    @Nonnull
    public ResourceLocation getType() {
        return Variants.variants("texture_id");
    }

    public static BowlIDValueRange range(int value) {
        return new BowlIDValueRange(value);
    }

    public static class Serializer implements JsonDeserializer<BowlIDValueRange>, JsonSerializer<BowlIDValueRange> {
        @Override
        public BowlIDValueRange deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            return new BowlIDValueRange(JSONUtils.convertToInt(element, "value"));
        }

        @Override
        public JsonElement serialize(BowlIDValueRange source, Type sourceType, JsonSerializationContext context) {
            return new JsonPrimitive(source.value);
        }
    }
}
