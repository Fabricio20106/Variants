package com.junethewoods.variants.blockentity.custom;

import com.junethewoods.variants.blockentity.VSBlockEntities;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.BedTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class VSBedBlockEntity extends BedTileEntity {
    public VSBedBlockEntity() {
        super();
    }

    public VSBedBlockEntity(DyeColor color) {
        super(color);
    }

    @Override
    public TileEntityType<?> getType() {
        return VSBlockEntities.VS_BED.get();
    }
}
