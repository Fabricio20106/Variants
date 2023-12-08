package com.junethewoods.variants.entity.custom;

import com.junethewoods.variants.entity.VSEntities;
import com.junethewoods.variants.item.VSItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class VSChestBoat extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(VSChestBoat.class, EntityDataSerializers.INT);

    public VSChestBoat(EntityType<? extends ChestBoat> chestBoat, Level level) {
        super(chestBoat, level);
    }

    public VSChestBoat(Level level, double x, double y, double z) {
        this(VSEntities.VS_CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Item getDropItem() {
        switch (getModVariant()) {
            case CRIMSON -> {
                return VSItems.CRIMSON_BOAT.get();
            }
            case WARPED -> {
                return VSItems.WARPED_BOAT.get();
            }
            default -> {
                return VSItems.PAINTING_BOAT.get();
            }
        }
    }

    public void setVariant(VSBoat.Type woodType) {
        this.entityData.set(DATA_ID_TYPE, woodType.ordinal());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, VSBoat.Type.CRIMSON.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("Type", 8)) {
            this.setVariant(VSBoat.Type.byName(tag.getString("Type")));
        }
    }

    public VSBoat.Type getModVariant() {
        return VSBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}