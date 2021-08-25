package com.junethewoods.variants.core.init;

import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.entity.VariantBoatEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> entities = DeferredRegister.create(ForgeRegistries.ENTITIES, Variants.mod_id);

    public static final RegistryObject<EntityType<VariantBoatEntity>> variant_boat = entities.register("variant_boat",
            () -> EntityType.Builder.<VariantBoatEntity>create(VariantBoatEntity::new, EntityClassification.MISC).setTrackingRange(80)
                    .setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375F, 0.5625F)
                    .setCustomClientFactory(VariantBoatEntity::new).build("variant_boat"));

    /*public static final RegistryObject<EntityType<OldCodEntity>> old_cod = entities.register("old_cod",
            () -> EntityType.Builder.create(OldCodEntity::new, EntityClassification.WATER_AMBIENT).setTrackingRange(4)
                    .size(0.5F, 0.3F).build("old_cod"));*/
}
