package melonystudios.variants.util.damage;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import melonystudios.variants.Variants;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import java.util.Map;

public class DamageSourceManager extends JsonReloadListener {
    private static final Gson GSON = createDamageSourceSerializer().create();

    public DamageSourceManager() {
        super(GSON, "damage_source");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, @Nonnull IResourceManager manager, @Nonnull IProfiler profiler) {
        ImmutableMap.Builder<ResourceLocation, DamageSource> builder = ImmutableMap.builder();

        resourceList.forEach((location, element) -> {
            try {
                if (element.isJsonObject()) builder.put(location, GSON.fromJson(element, DamageSource.class));
            } catch (Exception exception) {
                Variants.LOGGER.error(new TranslationTextComponent("error.variants.damage_source.parsing", location).getString(), exception);
            }
        });
        DamageSourceUtils.DATA_DRIVEN_SOURCES.clear();
        DamageSourceUtils.DATA_DRIVEN_SOURCES.putAll(builder.build());
        Variants.LOGGER.info(new TranslationTextComponent("console.variants.damage_source.loaded", builder.build().size()).getString());
    }

    private static GsonBuilder createDamageSourceSerializer() {
        return new GsonBuilder().registerTypeAdapter(DamageSource.class, new DamageSourceUtils.Serializer());
    }
}
