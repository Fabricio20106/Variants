package melonystudios.variants.blockentity.custom;

import melonystudios.variants.blockentity.VSBlockEntities;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public class VSSignBlockEntity extends SignTileEntity {
    public VSSignBlockEntity() {
        super();
    }

    @Override
    @Nonnull
    public TileEntityType<?> getType() {
        return VSBlockEntities.VS_SIGN.get();
    }
}
