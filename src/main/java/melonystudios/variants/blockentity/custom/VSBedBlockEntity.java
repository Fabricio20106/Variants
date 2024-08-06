package melonystudios.variants.blockentity.custom;

import melonystudios.variants.blockentity.VSBlockEntities;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.BedTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public class VSBedBlockEntity extends BedTileEntity {
    public VSBedBlockEntity() {
        super();
    }

    public VSBedBlockEntity(DyeColor color) {
        super(color);
    }

    @Override
    @Nonnull
    public TileEntityType<?> getType() {
        return VSBlockEntities.VS_BED.get();
    }
}
