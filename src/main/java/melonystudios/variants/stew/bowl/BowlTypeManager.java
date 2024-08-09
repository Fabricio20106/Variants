package melonystudios.variants.stew.bowl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import melonystudios.variants.Variants;
import melonystudios.variants.util.damage.DamageSourceUtils;
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

public class BowlTypeManager extends JsonReloadListener {
    private static final Gson GSON = BowlType.createBowlTypeSerializer().create();
    public static final Logger LOGGER = LogManager.getLogger();

    public BowlTypeManager() {
        super(GSON, "bowl_type");
    }

    @Override
    protected void apply(@Nonnull Map<ResourceLocation, JsonElement> resourceList, @Nonnull IResourceManager manager, @Nonnull IProfiler profiler) {
        ImmutableMap.Builder<ResourceLocation, BowlType> builder = ImmutableMap.builder();

        resourceList.forEach((location, element) -> {
            try {
                if (element.isJsonObject()) {
                    BowlType type = GSON.fromJson(element, BowlType.class);
                    builder.put(location, type);
                }
            } catch (Exception exception) {
                LOGGER.error(new TranslationTextComponent("error." + Variants.MOD_ID + ".bowl_type.parsing", location).getString(), exception);
            }
        });
        BowlType.DATA_DRIVEN_TYPES.putAll(builder.build());
        LOGGER.info(new TranslationTextComponent("console." + Variants.MOD_ID + ".bowl_type.loaded", builder.build().size()).getString());
    }
}
