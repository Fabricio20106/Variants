package com.junethewoods.variants.blockentity;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.blockentity.custom.VSBeaconBlockEntity;
import com.junethewoods.variants.blockentity.custom.VSBedBlockEntity;
import com.junethewoods.variants.blockentity.custom.VSBellBlockEntity;
import com.junethewoods.variants.blockentity.custom.VSSignBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VSBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Variants.MOD_ID);

    public static final Supplier<BlockEntityType<VSBellBlockEntity>> VS_BELL = BLOCK_ENTITIES.register("vs_bell", () -> BlockEntityType.Builder.of(VSBellBlockEntity::new,
            VSBlocks.DIAMOND_BELL.get()).build(null));

    public static final Supplier<BlockEntityType<VSBeaconBlockEntity>> VS_BEACON = BLOCK_ENTITIES.register("vs_beacon", () -> BlockEntityType.Builder.of(VSBeaconBlockEntity::new,
            VSBlocks.GOLDEN_BEACON.get()).build(null));

    public static final Supplier<BlockEntityType<VSBedBlockEntity>> VS_BED = BLOCK_ENTITIES.register("vs_bed", () -> BlockEntityType.Builder.of(VSBedBlockEntity::new,
            VSBlocks.GLOW_BLACK_BED.get()).build(null));

    public static final Supplier<BlockEntityType<VSSignBlockEntity>> VS_SIGN = BLOCK_ENTITIES.register("vs_sign", () -> BlockEntityType.Builder.of(VSSignBlockEntity::new,
            VSBlocks.ENDERWOOD_SIGN.get(), VSBlocks.ENDERWOOD_WALL_SIGN.get(), VSBlocks.PAINTING_SIGN.get(), VSBlocks.PAINTING_WALL_SIGN.get()).build(null));
}
