package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class VSFluidTags {
    // Variants Tags
    public static final ITag.INamedTag<Fluid> SOUL_LAVA = mod("soul_lava");
    public static final ITag.INamedTag<Fluid> MUSHROOM_STEW = mod("mushroom_stew");

    // Melony (convention) Tags
    public static final ITag.INamedTag<Fluid> HYDRATES_WATER_BASED_FARMLAND = melony("hydrates_farmland/water");
    public static final ITag.INamedTag<Fluid> HYDRATES_LAVA_BASED_FARMLAND = melony("hydrates_farmland/lava");

    public static ITag.INamedTag<Fluid> mod(String name) {
        return FluidTags.bind(Variants.resourceLoc(name).toString());
    }

    public static ITag.INamedTag<Fluid> melony(String name) {
        return FluidTags.bind(new ResourceLocation("melony", name).toString());
    }
}
