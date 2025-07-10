package melonystudios.variants.data.bowltype;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import cpw.mods.modlauncher.api.LamdbaExceptionUtils;
import melonystudios.variants.item.bowl.BowlType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.Tuple;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class BowlTypesProvider implements IDataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Map<String, Tuple<BowlType, JsonObject>> toSerialize = new HashMap<>();
    private final DataGenerator generator;
    private final String modID;

    public BowlTypesProvider(DataGenerator generator, String modID) {
        this.generator = generator;
        this.modID = modID;
    }

    @Override
    @Nonnull
    public String getName() {
        return "Bowl Types: " + this.modID;
    }

    protected abstract void addTypes();

    @Override
    public void run(DirectoryCache cache) {
        this.addTypes();
        String filePath = "data/" + this.modID + "/bowl_type/";

        this.toSerialize.forEach(LamdbaExceptionUtils.rethrowBiConsumer((name, pair) -> {
            Path bowlTypeFile = this.generator.getOutputFolder().resolve(filePath + name + ".json");
            IDataProvider.save(GSON, cache, pair.getB(), bowlTypeFile);
        }));
    }

    public void add(String name, BowlType type) {
        this.toSerialize.put(name, new Tuple<>(type, type.toJSON(type)));
    }
}
