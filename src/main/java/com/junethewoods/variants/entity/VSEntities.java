package com.junethewoods.variants.entity;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.entity.custom.DragonBreathBottleEntity;
import com.junethewoods.variants.entity.custom.FishEntity;
import com.junethewoods.variants.entity.custom.SmallSoulFireballEntity;
import com.junethewoods.variants.entity.custom.VSBoatEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Variants.MOD_ID);

    public static final RegistryObject<EntityType<VSBoatEntity>> VS_BOAT = ENTITIES.register("vs_boat", () -> EntityType.Builder.<VSBoatEntity>of(VSBoatEntity::new, EntityClassification.MISC).sized(1.375f, 0.5f)
            .build(Variants.resourceLoc("vs_boat").toString()));

    public static final RegistryObject<EntityType<FishEntity>> FISH = ENTITIES.register("old_cod", () -> EntityType.Builder.of(FishEntity::new, EntityClassification.WATER_AMBIENT).sized(0.5F, 0.3F)
            .clientTrackingRange(4).build(Variants.resourceLoc("old_cod").toString()));

    public static final RegistryObject<EntityType<DragonBreathBottleEntity>> DRAGON_BREATH_BOTTLE = ENTITIES.register("dragon_breath_bottle", () -> EntityType.Builder.<DragonBreathBottleEntity>of(DragonBreathBottleEntity::new, EntityClassification.MISC)
            .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(Variants.resourceLoc("dragon_breath_bottle").toString()));

    public static final RegistryObject<EntityType<SmallSoulFireballEntity>> SMALL_SOUL_FIREBALL = ENTITIES.register("small_soul_fireball", () -> EntityType.Builder.<SmallSoulFireballEntity>of(SmallSoulFireballEntity::new, EntityClassification.MISC)
            .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10).build(Variants.resourceLoc("small_soul_fireball").toString()));
}
