package com.junethewoods.variants.blockentity.custom;

import com.junethewoods.variants.blockentity.VSBlockEntities;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class VSSignBlockEntity extends SignTileEntity {
    public VSSignBlockEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return VSBlockEntities.VS_SIGN.get();
    }
}
