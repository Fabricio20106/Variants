package melonystudios.variants.blockentity.custom;

import melonystudios.variants.blockentity.VSBlockEntities;
import net.minecraft.tileentity.BellTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public class VSBellBlockEntity extends BellTileEntity {
    public VSBellBlockEntity() {
        super();
    }

    @Override
    @Nonnull
    public TileEntityType<?> getType() {
        return VSBlockEntities.VS_BELL.get();
    }
}
