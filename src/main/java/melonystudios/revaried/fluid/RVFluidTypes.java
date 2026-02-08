package melonystudios.revaried.fluid;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.block.RVBlocks;
import melonystudios.revaried.fluid.custom.ExtendedFluidType;
import melonystudios.revaried.fluid.custom.SoulLavaFluidType;
import melonystudios.revaried.misc.particle.RVParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;

import static melonystudios.revaried.util.RVResourceLocations.*;

public class RVFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Revaried.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> MUSHROOM_STEW = FLUID_TYPES.register("mushroom_stew", () ->
            new ExtendedFluidType(STILL_MUSHROOM_STEW, FLOWING_MUSHROOM_STEW, MUSHROOM_STEW_OVERLAY, 0xFFFFFFFF, new Vector3f(
                    220 / 255F, 146 / 255F, 95 / 255F), FluidType.Properties.create().canSwim(false).canDrown(true).viscosity(500)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)));
    public static final DeferredHolder<FluidType, FluidType> SOUL_LAVA = FLUID_TYPES.register("soul_lava", () ->
            new SoulLavaFluidType(STILL_SOUL_LAVA, FLOWING_SOUL_LAVA, SOUL_LAVA_OVERLAY, 0xFFFFFFFF, new Vector3f(
                    48 / 255F, 233 / 255F, 219 / 255F), FluidType.Properties.create().canSwim(false).canDrown(false).pathType(PathType.LAVA).adjacentPathType(null)
                    .lightLevel(15).density(3000).viscosity(6000).temperature(2500).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA).addDripstoneDripping(PointedDripstoneBlock.LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK,
                            RVParticleTypes.DRIPPING_DRIPSTONE_SOUL_LAVA.get(), RVBlocks.SOUL_LAVA_CAULDRON.get(), SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON)));
}
