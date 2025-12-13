package melonystudios.variants.criterion;

import melonystudios.variants.criterion.custom.ModLoadedTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class RVCriteriaTriggers {
    public static final ModLoadedTrigger MOD_LOADED = CriteriaTriggers.register(new ModLoadedTrigger());

    public static void init() {}
}
