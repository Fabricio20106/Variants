package melonystudios.revaried;

import melonystudios.revaried.fluid.RVFluids;
import melonystudios.revaried.item.RVItems;
import melonystudios.revaried.util.RVUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Revaried.MOD_ID, dist = Dist.CLIENT)
public class RevariedClient {
    public RevariedClient(IEventBus eventBus, ModContainer container) {
        eventBus.addListener(this::clientSetup);

        container.registerExtensionPoint(IConfigScreenFactory.class, (minecraft, lastScreen) -> new ConfigurationScreen(container, lastScreen));
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Item properties
        RVUtils.makeSpawnerMinecart(RVItems.SPAWNER_MINECART);

        // Render types
        this.addFluidRenderTypes();
    }

    private void addFluidRenderTypes() {
        ItemBlockRenderTypes.setRenderLayer(RVFluids.MUSHROOM_STEW.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(RVFluids.FLOWING_MUSHROOM_STEW.get(), RenderType.translucent());
    }
}
