package com.junethewoods.variants.blockentity.custom;

import com.junethewoods.variants.blockentity.VSBlockEntities;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class VSBeaconBlockEntity extends BeaconTileEntity {
    public VSBeaconBlockEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return VSBlockEntities.VS_BEACON.get();
    }

    /*@Nullable
    @Override
    public Container createMenu(int whatever, PlayerInventory playerInventory, PlayerEntity player) {
        return LockableTileEntity.canUnlock(player, LockCode.NO_LOCK, this.getDisplayName()) ? new VSBeaconContainer(whatever, playerInventory, this.dataAccess,
                IWorldPosCallable.create(this.level, this.getBlockPos())) : null;
    }*/
}
