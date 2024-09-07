package melonystudios.variants.data.armorcolor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import cpw.mods.modlauncher.api.LamdbaExceptionUtils;
import melonystudios.variants.item.custom.armor.color.WoolArmorColor;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.Tuple;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class WoolArmorColorProvider implements IDataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Map<String, Tuple<WoolArmorColor, JsonObject>> toSerialize = new HashMap<>();
    private final DataGenerator generator;
    private final String modID;

    public WoolArmorColorProvider(DataGenerator generator, String modID) {
        this.generator = generator;
        this.modID = modID;
    }

    @Override
    @Nonnull
    public String getName() {
        return "Wool Armor Colors: " + this.modID;
    }

    protected abstract void addWoolArmorColors();

    @Override
    public void run(DirectoryCache cache) {
        this.addWoolArmorColors();
        String variantsPath = "data/" + this.modID + "/wool_armor_color/";

        this.toSerialize.forEach(LamdbaExceptionUtils.rethrowBiConsumer((name, pair) -> {
            Path variantFile = this.generator.getOutputFolder().resolve(variantsPath + name + ".json");
            IDataProvider.save(GSON, cache, pair.getB(), variantFile);
        }));
    }

    public void add(String name, WoolArmorColor armorColor) {
        this.toSerialize.put(name, new Tuple<>(armorColor, armorColor.toJSON(armorColor)));
    }
}
