package com.junethewoods.variants.blockentity.custom;

import com.junethewoods.variants.blockentity.VSBlockEntities;
import net.minecraft.tileentity.BellTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class VSBellBlockEntity extends BellTileEntity {
    public VSBellBlockEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return VSBlockEntities.VS_BELL.get();
    }
}
