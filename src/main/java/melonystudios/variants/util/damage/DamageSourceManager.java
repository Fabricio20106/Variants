package melonystudios.variants.util.damage;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Map;

public class DamageSourceManager extends JsonReloadListener {
    private static final Gson GSON = createDamageSourceSerializer().create();
    public static final Logger LOGGER = LogManager.getLogger();

    public DamageSourceManager() {
        super(GSON, "damage_source");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, @Nonnull IResourceManager manager, @Nonnull IProfiler profiler) {
        ImmutableMap.Builder<ResourceLocation, DamageSource> builder = ImmutableMap.builder();

        resourceList.forEach((location, element) -> {
            try {
                if (element.isJsonObject()) {
                    DamageSource source = GSON.fromJson(element, DamageSource.class);
                    builder.put(location, source);
                }
            } catch (Exception exception) {
                LOGGER.error(new TranslationTextComponent("error.variants.damage_source.parsing", location).getString(), exception);
            }
        });
        DamageSourceUtils.DATA_DRIVEN_SOURCES.putAll(builder.build());
        LOGGER.info(new TranslationTextComponent("console.variants.damage_source.loaded", builder.build().size()).getString());
    }

    public static GsonBuilder createDamageSourceSerializer() {
        return new GsonBuilder().registerTypeAdapter(DamageSource.class, new DamageSourceUtils.Serializer());
    }
}
