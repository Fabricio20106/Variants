package melonystudios.revaried.tag;

import melonystudios.revaried.Revaried;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class RVFluidTags {
    public static final TagKey<Fluid> SOUL_LAVA = common("soul_lava");

    public static TagKey<Fluid> common(String name) {
        return TagKey.create(Registries.FLUID, Revaried.common(name));
    }
}
