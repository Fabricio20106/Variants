package melonystudios.variants.item.bowl;

import com.google.gson.*;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import melonystudios.variants.Variants;
import melonystudios.variants.data.recipe.VSExpoStewsRecipeProvider;
import melonystudios.variants.util.JSONDeserializer;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import static melonystudios.variants.util.VSUtils.namespace;

public class BowlType {
    public static final Codec<BowlType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            NBTUtils.ITEM_STACK_CODEC.fieldOf("item").forGetter(BowlType::bowl),
            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(BowlType::assetID),
            Codec.STRING.fieldOf("wood_name").forGetter(BowlType::name),
            Codec.INT.fieldOf("texture_id").forGetter(BowlType::textureID)).apply(instance, BowlType::new));
    public static Map<ResourceLocation, BowlType> DATA_DRIVEN_TYPES = new LinkedHashMap<>();
    public static final Map<Integer, ResourceLocation> TEXTURE_IDENTIFIERS = new LinkedHashMap<>();
    private final ItemStack bowlStack;
    private final ResourceLocation assetID;
    private final String woodName;
    private final int textureID;

    public BowlType(ItemStack bowlStack, ResourceLocation assetID, String woodName, int textureID) {
        this.bowlStack = bowlStack;
        this.assetID = assetID;
        this.woodName = woodName;
        this.textureID = textureID;
        if (TEXTURE_IDENTIFIERS.containsKey(textureID) && !TEXTURE_IDENTIFIERS.get(textureID).equals(assetID)) {
            Variants.LOGGER.warn(new TranslationTextComponent("error." + Variants.MOD_ID + ".bowl_type.duplicate_texture_id", woodName, textureID).getString());
        } else {
            TEXTURE_IDENTIFIERS.put(textureID, assetID);
        }
    }

    public BowlType(ItemStack bowlStack, String woodName, int textureID) {
        this(bowlStack, namespace("variants", woodName), woodName, textureID);
    }

    public ItemStack bowl() {
        return this.bowlStack;
    }

    public String name() {
        return this.woodName;
    }

    public ResourceLocation assetID() {
        return this.assetID;
    }

    public int textureID() {
        return this.textureID;
    }

    public JsonObject toJSON(BowlType type) {
        JsonObject object = new JsonObject();
        object.addProperty("asset_id", type.assetID().toString());

        JsonObject bowlObject = new JsonObject();
        bowlObject.addProperty("id", type.bowl().getItem().getRegistryName().toString());
        if (type.bowl().getCount() != 1) bowlObject.addProperty("count", type.bowl().getCount());
        if (type.bowl().getTag() != null) bowlObject.addProperty("tags", type.bowl().getTag().toString());
        object.add("bowl", bowlObject);

        object.addProperty("name", type.name());
        object.addProperty("texture_id", type.textureID());
        return object;
    }

    public static class Serializer implements JsonSerializer<BowlType>, JsonDeserializer<BowlType> {
        @Override
        public JsonElement serialize(BowlType type, Type sourceType, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("asset_id", type.assetID().toString());

            JsonObject bowlObject = new JsonObject();
            bowlObject.addProperty("id", type.bowl().getItem().getRegistryName().toString());
            if (type.bowl().getCount() != 1) bowlObject.addProperty("count", type.bowl().getCount());
            if (type.bowl().getTag() != null) bowlObject.addProperty("tags", type.bowl().getTag().toString());
            object.add("bowl", bowlObject);

            object.addProperty("name", type.name());
            object.addProperty("texture_id", type.textureID());
            return object;
        }

        @Override
        public BowlType deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (element.isJsonObject()) {
                JsonObject object = element.getAsJsonObject();
                ItemStack bowlStack = JSONDeserializer.loadStack("bowl", object);
                String woodName = JSONUtils.getAsString(object, "name");
                ResourceLocation assetID = ResourceLocation.tryParse(JSONUtils.getAsString(object, "asset_id"));
                int textureID = JSONDeserializer.nonNegativeInteger(object, "texture_id");

                BowlType bowlType = new BowlType(bowlStack, assetID, woodName, textureID);
                if (VSExpoStewsRecipeProvider.DEFAULT_BOWLS.contains(bowlType)) return VSExpoStewsRecipeProvider.DEFAULT_BOWLS.get(bowlType.textureID());
                else return bowlType;
            } else {
                throw new JsonParseException(new TranslationTextComponent("error." + Variants.MOD_ID + ".bowl_type.parsing", element.toString()).getString());
            }
        }
    }
}
