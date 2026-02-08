package melonystudios.revaried;

import com.mojang.logging.LogUtils;
import melonystudios.revaried.block.RVBlocks;
import melonystudios.revaried.component.RVDataComponents;
import melonystudios.revaried.fluid.RVFluidTypes;
import melonystudios.revaried.fluid.RVFluids;
import melonystudios.revaried.item.RVItems;
import melonystudios.revaried.item.tab.RVCreativeTabs;
import melonystudios.revaried.misc.RVCauldronInteractions;
import melonystudios.revaried.misc.RVSounds;
import melonystudios.revaried.misc.RVStatistics;
import melonystudios.revaried.misc.particle.RVParticleTypes;
import melonystudios.revaried.option.RVCommonOptions;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(Revaried.MOD_ID)
public class Revaried {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "revaried";
    public static final String MOD_PREFIX = "rv";

    public Revaried(IEventBus eventBus, ModContainer container) {
        eventBus.addListener(this::commonSetup);

        RVBlocks.BLOCKS.register(eventBus);
        RVDataComponents.COMPONENTS.register(eventBus);
        RVItems.ITEMS.register(eventBus);
        RVCreativeTabs.TABS.register(eventBus);
        RVFluids.FLUIDS.register(eventBus);
        RVFluidTypes.FLUID_TYPES.register(eventBus);
        RVSounds.SOUNDS.register(eventBus);
        RVStatistics.STATS.register(eventBus);
        RVParticleTypes.TYPES.register(eventBus);

        container.registerConfig(ModConfig.Type.COMMON, RVCommonOptions.SPEC, "melonystudios/revaried-common.toml");
    }

    /// Creates a name for a data generator using ***Revaried***'s name.
    /// @param name The name of the generator, like *"Item Models"*.
    public static String generatorName(String name) {
        return "Revaried — " + name;
    }

    /// Creates a new resource location under ***Revaried***'s namespace.
    /// @param name The path of this resource location.
    public static ResourceLocation revaried(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    /// Creates a new resource location using the **Common** (`c`) namespace.
    /// @param name The path of this resource location.
    public static ResourceLocation common(String name) {
        return ResourceLocation.fromNamespaceAndPath("c", name);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        RVCauldronInteractions.register();
    }
}
