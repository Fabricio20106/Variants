package melonystudios.variants.screen;

import melonystudios.variants.Variants;
import melonystudios.variants.config.VSJSONConfig;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.util.text.TranslationTextComponent;

public class VSConfigEntries {
    public static final VSJSONConfig CONFIG = Variants.INSTANCE.getConfig();

    public static final BooleanOption FLOWER_PATCHES = new BooleanOption("config.variants.flower_patches", new TranslationTextComponent("config.variants.flower_patches.desc"), settings -> CONFIG.flowerPatches, (settings, newValue) -> CONFIG.flowerPatches = newValue);
    public static final BooleanOption GENERATE_QUARTZ_ORE = new BooleanOption("config.variants.quartz_ore", new TranslationTextComponent("config.variants.quartz_ore.desc"), settings -> CONFIG.quartzOre, (settings, newValue) -> CONFIG.quartzOre = newValue);
    public static final BooleanOption GENERATE_END_QUARTZ_ORE = new BooleanOption("config.variants.end_quartz_ore", new TranslationTextComponent("config.variants.end_quartz_ore.desc"), settings -> CONFIG.endQuartzOre, (settings, newValue) -> CONFIG.endQuartzOre = newValue);
    public static final BooleanOption SOUL_LAVA_SPRINGS = new BooleanOption("config.variants.soul_lava_springs", new TranslationTextComponent("config.variants.soul_lava_springs.desc"), settings -> CONFIG.soulLavaSprings, (settings, newValue) -> CONFIG.soulLavaSprings = newValue);
    public static final BooleanOption END_CAVES_AND_RAVINES = new BooleanOption("config.variants.end_caves_and_ravines", new TranslationTextComponent("config.variants.end_caves_and_ravines.desc"), settings -> CONFIG.endCavesAndRavines, (settings, newValue) -> CONFIG.endCavesAndRavines = newValue);
}
