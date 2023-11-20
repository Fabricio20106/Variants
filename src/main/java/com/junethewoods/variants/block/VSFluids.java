package com.junethewoods.variants.block;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.VSItems;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Variants.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOUL_LAVA = FLUIDS.register("soul_lava", () -> new ForgeFlowingFluid.Source(VSFluids.SOUL_LAVA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SOUL_LAVA = FLUIDS.register("flowing_soul_lava", () -> new ForgeFlowingFluid.Flowing(VSFluids.SOUL_LAVA_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SOUL_LAVA_PROPERTIES = new ForgeFlowingFluid.Properties(SOUL_LAVA, FLOWING_SOUL_LAVA,
            FluidAttributes.builder(Variants.resourceLoc("block/soul_lava_still"), Variants.resourceLoc("block/soul_lava_flowing")).rarity(Rarity.UNCOMMON).sound(SoundEvents.BUCKET_EMPTY_LAVA)
                    .overlay(Variants.resourceLoc("block/soul_lava_overlay"))).levelDecreasePerBlock(2).slopeFindDistance(2).block(VSBlocks.SOUL_LAVA).tickRate(40).bucket(VSItems.SOUL_LAVA_BUCKET);
}
