package com.junethewoods.variants.blockentity;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.blockentity.custom.VSBeaconBlockEntity;
import com.junethewoods.variants.blockentity.custom.VSBellBlockEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSBlockEntities {
    public static final DeferredRegister<TileEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Variants.MOD_ID);

    public static final RegistryObject<TileEntityType<VSBellBlockEntity>> VS_BELL = BLOCK_ENTITIES.register("vs_bell", () -> TileEntityType.Builder.of(VSBellBlockEntity::new,
            VSBlocks.DIAMOND_BELL.get()).build(null));

    public static final RegistryObject<TileEntityType<VSBeaconBlockEntity>> VS_BEACON = BLOCK_ENTITIES.register("vs_beacon", () -> TileEntityType.Builder.of(VSBeaconBlockEntity::new,
            VSBlocks.GOLDEN_BEACON.get()).build(null));
}