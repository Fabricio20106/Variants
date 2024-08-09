package melonystudios.variants.stew.bowl;

import com.google.common.collect.Lists;
import com.google.gson.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import melonystudios.variants.Variants;
import melonystudios.variants.loot.function.SetStewBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static melonystudios.variants.util.VSUtils.namespace;

public class BowlType {
    public static Map<ResourceLocation, BowlType> DATA_DRIVEN_TYPES = new HashMap<>();
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
            LogManager.getLogger().warn(new TranslationTextComponent("error." + Variants.MOD_ID + ".bowl_type.duplicate_texture_id", woodName));
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

    @Nullable
    public static BowlType fromTypesMap(ResourceLocation name) {
        return DATA_DRIVEN_TYPES.get(name);
    }

    public static boolean hasType(BowlType type) {
        return DATA_DRIVEN_TYPES.containsValue(type);
    }

    public static boolean hasTypeFromLocation(ResourceLocation name) {
        return DATA_DRIVEN_TYPES.containsKey(name);
    }

    public JsonObject toJSON(BowlType type) {
        JsonObject object = new JsonObject();
        object.addProperty("asset_id", type.getAssetID().toString());

        JsonObject bowlObject = new JsonObject();
        bowlObject.addProperty("id", type.getBowlStack().getItem().getRegistryName().toString());
        bowlObject.addProperty("count", type.getBowlStack().getCount());
        if (type.getBowlStack().getTag() != null)
            bowlObject.addProperty("tag", type.getBowlStack().getTag().toString());
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
            bowlObject.addProperty("count", type.getBowlStack().getCount());
            if (type.getBowlStack().getTag() != null)
                bowlObject.addProperty("tag", type.getBowlStack().getTag().toString());
            object.add("bowl", bowlObject);

            object.addProperty("name", type.getWoodName());
            object.addProperty("texture_id", type.getTextureID());
            return object;
        }

        @Override
        public BowlType deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (element.isJsonObject()) {
                JsonObject object = element.getAsJsonObject();
                JsonObject bowlObject = object.get("bowl").getAsJsonObject();
                CompoundNBT tagTag;
                try {
                    tagTag = JsonToNBT.parseTag(SetStewBehavior.Serializer.convertToString(bowlObject.get("tag"), "tag"));
                } catch (CommandSyntaxException exception) {
                    tagTag = new CompoundNBT();
                }
                CompoundNBT stackTag = new CompoundNBT();
                if (!tagTag.isEmpty()) stackTag.put("tag", tagTag);
                stackTag.putString("id", object.get("bowl").getAsJsonObject().get("id").getAsString());
                stackTag.putByte("Count", object.get("bowl").getAsJsonObject().get("count").getAsByte());

                ItemStack bowlStack = ItemStack.of(stackTag);
                String woodName = JSONUtils.getAsString(object, "name");
                ResourceLocation assetID = new ResourceLocation(JSONUtils.getAsString(object, "asset_id"));
                int textureID = JSONUtils.getAsInt(object, "texture_id");
                return new BowlType(bowlStack, assetID, woodName, textureID);
            } else {
                throw new JsonParseException(new TranslationTextComponent("error." + Variants.MOD_ID + ".bowl_type.parsing", element.toString()).getString());
            }
        }
    }
}
