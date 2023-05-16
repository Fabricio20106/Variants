package com.junethewoods.variants.core.init;

import com.junethewoods.variants.core.Variants;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Variants.MOD_ID);

    //public static final RegistryObject<EntityType<OldCod>> old_cod = entities.register("old_cod",
    //        () -> EntityType.Builder.of(OldCod::new, MobCategory.WATER_AMBIENT).sized(0.9F, 1.3F).clientTrackingRange(4).build(
    //                "old_cod"));

    //public static final RegistryObject<EntityType<PornheyEntity>> pornhey = entities.register("pornhey",
    //        () -> EntityType.Builder.of(PornheyEntity::new, MobCategory.CREATURE).sized(0.9F, 0.9F).clientTrackingRange(10).build(
    //                "pornhey"));
}
