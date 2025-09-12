package melonystudios.revaried.data.model;

import com.google.common.collect.ImmutableMap;
import melonystudios.reutilities.data.model.ReItemModelProvider;
import melonystudios.revaried.Revaried;
import melonystudios.revaried.util.RVUtils;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class RVItemModelProvider extends ReItemModelProvider {
    public static final Map<String, Integer> DEFAULT_MOB_MAP = new ImmutableMap.Builder<String, Integer>().put("zombie", 1).put("skeleton", 2).put("spider", 3).put("cave_spider", 4).put("silverfish", 5).put("blaze", 6).put("magma_cube", 7).put("pig", 8).build();

    public RVItemModelProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, Revaried.MOD_ID, fileHelper);
    }

    @Override
    @NotNull
    public String getName() {
        return "Revaried - Item Models";
    }

    @Override
    protected void registerModels() {
        // Blocks

        // Items
        standard("white_shulker_shell");
        standard("inno_shulker_shell");
        standard("light_gray_shulker_shell");
        standard("gray_shulker_shell");
        standard("black_shulker_shell");
        standard("brown_shulker_shell");
        standard("red_shulker_shell");
        standard("orange_shulker_shell");
        standard("yellow_shulker_shell");
        standard("lime_shulker_shell");
        standard("green_shulker_shell");
        standard("cyan_shulker_shell");
        standard("light_blue_shulker_shell");
        standard("glow_black_shulker_shell");
        standard("blue_shulker_shell");
        standard("purple_shulker_shell");
        standard("magenta_shulker_shell");
        standard("pink_shulker_shell");
        standard("shulker_spectrum_icon");
        standard("music_disc_dog");
        spawnerMinecart("spawner_minecart", DEFAULT_MOB_MAP);
    }

    public void spawnerMinecart(String name, Map<String, Integer> mobToIDMap) {
        for (String mob : mobToIDMap.keySet()) getBuilder(name + "_" + mob).parent(this.generated).texture("layer0", modLoc("item/" + name + "_" + mob));

        ItemModelBuilder spawnerMinecart = getBuilder(name).parent(this.generated).texture("layer0", "item/" + name);
        for (String mob : mobToIDMap.keySet()) spawnerMinecart.override().predicate(RVUtils.mobID(), mobToIDMap.get(mob)).model(getExistingFile(modLoc("item/" + name + "_" + mob))).end();
    }
}
