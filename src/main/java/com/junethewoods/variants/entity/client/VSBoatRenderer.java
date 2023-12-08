package com.junethewoods.variants.entity.client;

import com.google.common.collect.ImmutableMap;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.entity.custom.VSBoat;
import com.junethewoods.variants.entity.custom.VSChestBoat;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Map;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class VSBoatRenderer extends BoatRenderer {
    private final Map<VSBoat.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public VSBoatRenderer(EntityRendererProvider.Context context, boolean isChestBoat) {
        super(context, isChestBoat);
        this.boatResources = Stream.of(VSBoat.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type,
                type -> Pair.of(Variants.resourceLoc(getTextureLocation(type, isChestBoat)), this.createBoatModel(context, type, isChestBoat))));
    }

    private static String getTextureLocation(VSBoat.Type woodType, boolean isChestBoat) {
        return isChestBoat ? "textures/entity/chest_boat/" + woodType.getName() + ".png" : "textures/entity/boat/" + woodType.getName() + ".png";
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, VSBoat.Type woodType, boolean isChestBoat) {
        ModelLayerLocation modellayerlocation = isChestBoat ? VSBoatRenderer.createChestBoatModelName(woodType) : VSBoatRenderer.createBoatModelName(woodType);
        ModelPart modelpart = context.bakeLayer(modellayerlocation);
        return isChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    public static ModelLayerLocation createBoatModelName(VSBoat.Type woodType) {
        return createLocation("boat/" + woodType.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(VSBoat.Type woodType) {
        return createLocation("chest_boat/" + woodType.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(Variants.resourceLoc(path), model);
    }

    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if(boat instanceof VSBoat modBoat) {
            return this.boatResources.get(modBoat.getModVariant());
        } else if(boat instanceof VSChestBoat modChestBoatEntity) {
            return this.boatResources.get(modChestBoatEntity.getModVariant());
        } else {
            return null;
        }
    }
}
