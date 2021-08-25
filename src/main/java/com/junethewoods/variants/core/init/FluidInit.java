package com.junethewoods.variants.core.init;

import com.junethewoods.variants.core.Variants;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit {
    public static final ResourceLocation soul_lava_still_rl = new ResourceLocation(Variants.mod_id, "block/soul_lava_still");
    public static final ResourceLocation soul_lava_flowing_rl = new ResourceLocation(Variants.mod_id, "block/soul_lava_flowing");
    public static final ResourceLocation soul_lava_overlay_rl = new ResourceLocation(Variants.mod_id, "block/soul_lava_overlay");
    public static final ResourceLocation mushroom_stew_still_rl = new ResourceLocation(Variants.mod_id, "block/mushroom_stew_still");
    public static final ResourceLocation mushroom_stew_flowing_rl = new ResourceLocation(Variants.mod_id, "block/mushroom_stew_flowing");
    public static final ResourceLocation mushroom_stew_overlay_rl = new ResourceLocation(Variants.mod_id, "block/mushroom_stew_overlay");

    public static final DeferredRegister<Fluid> fluids = DeferredRegister.create(ForgeRegistries.FLUIDS, Variants.mod_id);

    public static final RegistryObject<FlowingFluid> soul_lava = fluids.register("soul_lava", () -> new ForgeFlowingFluid.Source(FluidInit.soul_lava_properties));
    public static final RegistryObject<FlowingFluid> soul_lava_flowing = fluids.register("soul_lava_flowing", () -> new ForgeFlowingFluid.Flowing(FluidInit.soul_lava_properties));
    public static final RegistryObject<FlowingFluid> mushroom_stew = fluids.register("mushroom_stew", () -> new ForgeFlowingFluid.Source(FluidInit.mushroom_stew_properties));
    public static final RegistryObject<FlowingFluid> mushroom_stew_flowing = fluids.register("mushroom_stew_flowing", () -> new ForgeFlowingFluid.Flowing(FluidInit.mushroom_stew_properties));

    public static final ForgeFlowingFluid.Properties soul_lava_properties = new ForgeFlowingFluid.Properties(() -> soul_lava.get(), () -> soul_lava_flowing.get(),
            FluidAttributes.builder(soul_lava_still_rl, soul_lava_flowing_rl).rarity(Rarity.RARE).sound(SoundEvents.ITEM_BUCKET_EMPTY).overlay(soul_lava_overlay_rl)
    ).block(() -> FluidInit.soul_lava_block.get()).bucket(() -> StuffInit.soul_lava_bucket.get());

    public static final ForgeFlowingFluid.Properties mushroom_stew_properties = new ForgeFlowingFluid.Properties(() -> mushroom_stew.get(), () -> mushroom_stew_flowing.get(),
            FluidAttributes.builder(mushroom_stew_still_rl, mushroom_stew_flowing_rl).sound(SoundEvents.ITEM_BUCKET_EMPTY).overlay(mushroom_stew_overlay_rl)
    ).block(() -> FluidInit.mushroom_stew_block.get()).bucket(() -> StuffInit.mushroom_stew_bucket.get());

    public static final RegistryObject<FlowingFluidBlock> soul_lava_block = BlockInit.blocks.register("soul_lava_block", () -> new FlowingFluidBlock(() ->
            FluidInit.soul_lava.get(), AbstractBlock.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops()));

    public static final RegistryObject<FlowingFluidBlock> mushroom_stew_block = BlockInit.blocks.register("mushroom_stew_block", () -> new FlowingFluidBlock(() ->
            FluidInit.mushroom_stew.get(), AbstractBlock.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops()));

}
