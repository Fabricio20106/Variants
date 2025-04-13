package melonystudios.variants.item.bowl;

import com.google.common.collect.Lists;
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
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static melonystudios.variants.util.VSUtils.namespace;

public class BowlType {
    public static final Codec<BowlType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            NBTUtils.ITEM_STACK_CODEC.fieldOf("item").forGetter(BowlType::getBowlStack),
            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(BowlType::getAssetID),
            Codec.STRING.fieldOf("wood_name").forGetter(BowlType::getWoodName),
            Codec.INT.fieldOf("texture_id").forGetter(BowlType::getTextureID)).apply(instance, BowlType::new));
    public static Map<ResourceLocation, BowlType> DATA_DRIVEN_TYPES = new LinkedHashMap<>();
    public static final List<Integer> TEXTURE_IDENTIFIERS = Lists.newArrayList();
    private final ItemStack bowlStack;
    private final ResourceLocation assetID;
    private final String woodName;
    private final int textureID;

    public BowlType(ItemStack bowlStack, ResourceLocation assetID, String woodName, int textureID) {
        this.bowlStack = bowlStack;
        this.assetID = assetID;
        this.woodName = woodName;
        this.textureID = textureID;
        if (TEXTURE_IDENTIFIERS.contains(textureID)) {
            LogManager.getLogger().warn(new TranslationTextComponent("error." + Variants.MOD_ID + ".bowl_type.duplicate_texture_id", woodName, textureID).getString());
        } else {
            TEXTURE_IDENTIFIERS.add(textureID);
        }
    }

    public BowlType(ItemStack bowlStack, String woodName, int textureID) {
        this(bowlStack, namespace("variants", woodName), woodName, textureID);
    }

    public ItemStack getBowlStack() {
        return this.bowlStack;
    }

    public String getWoodName() {
        return this.woodName;
    }

    public ResourceLocation getAssetID() {
        return this.assetID;
    }

    public int getTextureID() {
        return this.textureID;
    }

    public JsonObject toJSON(BowlType type) {
        JsonObject object = new JsonObject();
        object.addProperty("asset_id", type.getAssetID().toString());

        JsonObject bowlObject = new JsonObject();
        bowlObject.addProperty("id", type.getBowlStack().getItem().getRegistryName().toString());
        if (type.getBowlStack().getCount() != 1) bowlObject.addProperty("count", type.getBowlStack().getCount());
        if (type.getBowlStack().getTag() != null) bowlObject.addProperty("tags", type.getBowlStack().getTag().toString());
        object.add("bowl", bowlObject);

        object.addProperty("name", type.getWoodName());
        object.addProperty("texture_id", type.getTextureID());
        return object;
    }

    public static GsonBuilder createBowlTypeSerializer() {
        return new GsonBuilder().registerTypeAdapter(BowlType.class, new Serializer());
    }

    public static class Serializer implements JsonSerializer<BowlType>, JsonDeserializer<BowlType> {
        @Override
        public JsonElement serialize(BowlType type, Type sourceType, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("asset_id", type.getAssetID().toString());

            JsonObject bowlObject = new JsonObject();
            bowlObject.addProperty("id", type.getBowlStack().getItem().getRegistryName().toString());
            if (type.getBowlStack().getCount() != 1) bowlObject.addProperty("count", type.getBowlStack().getCount());
            if (type.getBowlStack().getTag() != null) bowlObject.addProperty("tags", type.getBowlStack().getTag().toString());
            object.add("bowl", bowlObject);

            object.addProperty("name", type.getWoodName());
            object.addProperty("texture_id", type.getTextureID());
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
                if (VSExpoStewsRecipeProvider.DEFAULT_BOWLS.contains(bowlType)) return VSExpoStewsRecipeProvider.DEFAULT_BOWLS.get(bowlType.getTextureID());
                else return bowlType;
            } else {
                throw new JsonParseException(new TranslationTextComponent("error." + Variants.MOD_ID + ".bowl_type.parsing", element.toString()).getString());
            }
        }
    }
}
