package melonystudios.revaried.misc;

import melonystudios.revaried.Revaried;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RVStatistics {
    public static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(Registries.CUSTOM_STAT, Revaried.MOD_ID);

    public static final DeferredHolder<ResourceLocation, ResourceLocation> SPAWNERS_PICKED_UP = STATS.register("spawners_picked_up", () -> Revaried.revaried("spawners_picked_up"));
}
