package melonystudios.variants.item.bowl;

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

public class BowlTypeManager extends JsonReloadListener {
    private static final Gson GSON = createBowlTypeSerializer().create();

    public BowlTypeManager() {
        super(GSON, "bowl_type");
    }

    @Override
    protected void apply(@Nonnull Map<ResourceLocation, JsonElement> resourceList, @Nonnull IResourceManager manager, @Nonnull IProfiler profiler) {
        ImmutableMap.Builder<ResourceLocation, BowlType> builder = ImmutableMap.builder();

        resourceList.forEach((location, element) -> {
            try {
                if (element.isJsonObject()) builder.put(location, GSON.fromJson(element, BowlType.class));
            } catch (Exception exception) {
                Variants.LOGGER.error(new TranslationTextComponent("error." + Variants.MOD_ID + ".bowl_type.parsing", location).getString(), exception);
            }
        });
        BowlType.DATA_DRIVEN_TYPES.clear();
        BowlType.DATA_DRIVEN_TYPES.putAll(builder.build());
        Variants.LOGGER.info(new TranslationTextComponent("console." + Variants.MOD_ID + ".bowl_type.loaded", builder.build().size()).getString());
    }

    private static GsonBuilder createBowlTypeSerializer() {
        return new GsonBuilder().registerTypeAdapter(BowlType.class, new BowlType.Serializer());
    }
}
