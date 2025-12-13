package melonystudios.variants.item.custom.armor.color;

import com.google.gson.*;
import melonystudios.variants.Variants;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalInt;

import static melonystudios.variants.Variants.variants;
import static melonystudios.variants.util.VSUtils.minecraft;

public class WoolArmorColor {
    public static Map<ResourceLocation, WoolArmorColor> DATA_DRIVEN_COLORS = new HashMap<>();
    public static final WoolArmorColor WHITE = new WoolArmorColor(minecraft("white"), 16383998, "color.minecraft.white");
    public static final WoolArmorColor INNO = new WoolArmorColor(new ResourceLocation("f10elements:inno"), 15457757, "color.f10elements.inno");
    public static final WoolArmorColor LIGHT_GRAY = new WoolArmorColor(minecraft("light_gray"), 10329495, "color.minecraft.light_gray");
    public static final WoolArmorColor GRAY = new WoolArmorColor(minecraft("gray"), 4673362, "color.minecraft.gray");
    public static final WoolArmorColor BLACK = new WoolArmorColor(minecraft("black"), 1908001, "color.minecraft.black");
    public static final WoolArmorColor BROWN = new WoolArmorColor(minecraft("brown"), 8606770, "color.minecraft.brown");
    public static final WoolArmorColor RED = new WoolArmorColor(minecraft("red"), 11546150, "color.minecraft.red");
    public static final WoolArmorColor ORANGE = new WoolArmorColor(minecraft("orange"), 16351261, "color.minecraft.orange");
    public static final WoolArmorColor YELLOW = new WoolArmorColor(minecraft("yellow"), 16701501, "color.minecraft.yellow");
    public static final WoolArmorColor LIME = new WoolArmorColor(minecraft("lime"), 8439583, "color.minecraft.lime");
    public static final WoolArmorColor GREEN = new WoolArmorColor(minecraft("green"), 6192150, "color.minecraft.green");
    public static final WoolArmorColor CYAN = new WoolArmorColor(minecraft("cyan"), 1481884, "color.minecraft.cyan");
    public static final WoolArmorColor LIGHT_BLUE = new WoolArmorColor(minecraft("light_blue"), 3847130, "color.minecraft.light_blue");
    public static final WoolArmorColor GLOW_BLACK = new WoolArmorColor(variants("glow_black"), 8454080, "color.variants.glow_black");
    public static final WoolArmorColor BLUE = new WoolArmorColor(minecraft("blue"), 3949738, "color.minecraft.blue");
    public static final WoolArmorColor PURPLE = new WoolArmorColor(minecraft("purple"), 8991416, "color.minecraft.purple");
    public static final WoolArmorColor MAGENTA = new WoolArmorColor(minecraft("magenta"), 13061821, "color.minecraft.magenta");
    public static final WoolArmorColor PINK = new WoolArmorColor(minecraft("pink"), 15961002, "color.minecraft.pink");
    public static final WoolArmorColor NULL_DESIGN = new WoolArmorColor(variants("null_design"), 1);
    private final ResourceLocation assetID;
    @Nullable
    private final Integer armorDesign;
    private final int color;
    private final String colorName;
    @Nullable
    private String descriptionID;

    public WoolArmorColor(ResourceLocation assetID, int color, String colorName) {
        this.assetID = assetID;
        this.armorDesign = null;
        this.color = color;
        this.colorName = colorName;
    }

    public WoolArmorColor(ResourceLocation assetID, int armorDesign) {
        this.assetID = assetID;
        this.armorDesign = armorDesign;
        this.color = 0;
        this.colorName = "color.minecraft.black";
    }

    public ResourceLocation getAssetID() {
        return this.assetID;
    }

    public OptionalInt getArmorDesign() {
        return this.armorDesign != null ? OptionalInt.of(this.armorDesign) : OptionalInt.empty();
    }

    public int getColor() {
        return this.color;
    }

    public String getColorName() {
        return this.colorName;
    }

    public String getOrCreateDescriptionID() {
        if (this.descriptionID == null) this.descriptionID = Util.makeDescriptionId(this.armorDesign != null ? "armor_design" : "color", this.assetID);
        return this.descriptionID;
    }

    public JsonObject toJSON(WoolArmorColor armorColor) {
        JsonObject object = new JsonObject();
        object.addProperty("asset_id", armorColor.getAssetID().toString());
        if (armorColor.getArmorDesign().isPresent()) {
            object.addProperty("armor_design", armorColor.getArmorDesign().getAsInt());
        } else {
            object.addProperty("color", "#" + Integer.toHexString(armorColor.getColor()).toUpperCase(Locale.ROOT));
            object.addProperty("color_name", armorColor.getColorName());
        }
        return object;
    }

    public static class Serializer implements JsonDeserializer<WoolArmorColor>, JsonSerializer<WoolArmorColor> {
        @Override
        public WoolArmorColor deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (element.isJsonObject()) {
                JsonObject object = element.getAsJsonObject();
                ResourceLocation assetID = new ResourceLocation(JSONUtils.getAsString(object, "asset_id"));
                if (object.has("armor_design")) {
                    int armorDesign = JSONUtils.getAsInt(object, "armor_design");
                    return new WoolArmorColor(assetID, armorDesign);
                } else {
                    int color = Integer.decode(JSONUtils.getAsString(object, "color"));
                    String colorName = JSONUtils.getAsString(object, "color_name");
                    return new WoolArmorColor(assetID, color, colorName);
                }
            } else {
                throw new JsonParseException(new TranslationTextComponent("error." + Variants.MOD_ID + ".wool_armor_color.parsing", element.toString()).getString());
            }
        }

        @Override
        public JsonElement serialize(WoolArmorColor armorColor, Type type, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("asset_id", armorColor.assetID.toString());
            if (armorColor.armorDesign != null) {
                object.addProperty("armor_design", armorColor.armorDesign);
            } else {
                object.addProperty("color", "#" + Integer.toHexString(armorColor.color).toUpperCase(Locale.ROOT));
                object.addProperty("color_name", armorColor.colorName);
            }
            return object;
        }
    }
}
