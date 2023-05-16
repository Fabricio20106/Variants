package com.junethewoods.variants.core.init;

import com.junethewoods.variants.core.Variants;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.junethewoods.variants.core.VSLocations.*;

public class VSFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Variants.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOUL_LAVA = FLUIDS.register("soul_lava", () -> new ForgeFlowingFluid.Source(VSFluids.SOUL_LAVA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SOUL_LAVA = FLUIDS.register("soul_lava_flowing", () -> new ForgeFlowingFluid.Flowing(VSFluids.SOUL_LAVA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MUSHROOM_STEW = FLUIDS.register("mushroom_stew", () -> new ForgeFlowingFluid.Source(VSFluids.MUSHROOM_STEW_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_MUSHROOM_STEW = FLUIDS.register("mushroom_stew_flowing", () -> new ForgeFlowingFluid.Flowing(VSFluids.MUSHROOM_STEW_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SOUL_LAVA_PROPERTIES = new ForgeFlowingFluid.Properties(SOUL_LAVA, FLOWING_SOUL_LAVA,
            FluidAttributes.builder(SOUL_LAVA_STILL, SOUL_LAVA_FLOWING).rarity(Rarity.RARE).sound(SoundEvents.BUCKET_EMPTY).overlay(SOUL_LAVA_OVERLAY)
    ).block(() -> (LiquidBlock) VSBlocks.soul_lava.get()).bucket(VSItems.soul_lava_bucket);

    public static final ForgeFlowingFluid.Properties MUSHROOM_STEW_PROPERTIES = new ForgeFlowingFluid.Properties(MUSHROOM_STEW, FLOWING_MUSHROOM_STEW,
            FluidAttributes.builder(MUSHROOM_STEW_STILL, MUSHROOM_STILL_FLOWING).sound(SoundEvents.BUCKET_EMPTY).overlay(MUSHROOM_STEW_OVERLAY)
    ).block(() -> (LiquidBlock) VSBlocks.mushroom_stew.get()).bucket(VSItems.mushroom_stew_bucket);
}
