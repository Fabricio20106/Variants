package melonystudios.revaried;

import com.mojang.logging.LogUtils;
import melonystudios.revaried.item.RVItems;
import melonystudios.revaried.item.tab.RVCreativeTabs;
import melonystudios.revaried.misc.RVSounds;
import melonystudios.revaried.util.RVUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(Revaried.MOD_ID)
public class Revaried {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "revaried";

    public Revaried(IEventBus eventBus, ModContainer container) {
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);

        RVItems.ITEMS.register(eventBus);
        RVCreativeTabs.TABS.register(eventBus);
        RVSounds.SOUNDS.register(eventBus);

        container.registerConfig(ModConfig.Type.COMMON, RVConfigs.SPEC, "melonystudios/revaried-common.toml");
        container.registerExtensionPoint(IConfigScreenFactory.class, (minecraft, lastScreen) -> new ConfigurationScreen(container, lastScreen));
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
        RVUtils.makeSpawnerMinecart(RVItems.SPAWNER_MINECART);
    }

    private void clientSetup(final FMLClientSetupEvent event) {}
}
