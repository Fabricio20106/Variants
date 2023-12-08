package com.junethewoods.variants.entity;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.entity.custom.Fish;
import com.junethewoods.variants.entity.custom.VSBoat;
import com.junethewoods.variants.entity.custom.VSChestBoat;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VSEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Variants.MOD_ID);

    public static final Supplier<EntityType<VSBoat>> VS_BOAT = ENTITIES.register("vs_boat", () -> EntityType.Builder.<VSBoat>of(VSBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f)
            .build(Variants.resourceLoc("vs_boat").toString()));

    public static final Supplier<EntityType<VSChestBoat>> VS_CHEST_BOAT = ENTITIES.register("vs_chest_boat", () -> EntityType.Builder.<VSChestBoat>of(VSChestBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f)
            .build(Variants.resourceLoc("vs_chest_boat").toString()));

    public static final Supplier<EntityType<Fish>> FISH = ENTITIES.register("old_cod", () -> EntityType.Builder.of(Fish::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.3F)
            .clientTrackingRange(4).build("old_cod"));
}
