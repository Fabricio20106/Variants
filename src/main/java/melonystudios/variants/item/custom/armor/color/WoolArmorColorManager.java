package melonystudios.variants.item.custom.armor.color;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import melonystudios.variants.Variants;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import java.util.Map;

public class WoolArmorColorManager extends JsonReloadListener {
    private static final Gson GSON = createWoolArmorColorSerializer().create();

    public WoolArmorColorManager() {
        super(GSON, "wool_armor_color");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, @Nonnull IResourceManager manager, @Nonnull IProfiler profiler) {
        ImmutableMap.Builder<ResourceLocation, WoolArmorColor> builder = ImmutableMap.builder();

        resourceList.forEach((location, element) -> {
            try {
                if (element.isJsonObject()) {
                    WoolArmorColor armorColor = GSON.fromJson(element, WoolArmorColor.class);
                    builder.put(location, armorColor);
                }
            } catch (Exception exception) {
                Variants.LOGGER.error(new TranslationTextComponent("error.variants.wool_armor_color.parsing", location).getString(), exception);
            }
        });
        WoolArmorColor.DATA_DRIVEN_COLORS.putAll(builder.build());
        Variants.LOGGER.info(new TranslationTextComponent("console.variants.wool_armor_color.loaded", builder.build().size()).getString());
    }

    public static GsonBuilder createWoolArmorColorSerializer() {
        return new GsonBuilder().registerTypeAdapter(WoolArmorColor.class, new WoolArmorColor.Serializer());
    }
}
