package melonystudios.variants.blockentity.custom;

import melonystudios.variants.blockentity.VSBlockEntities;
import net.minecraft.tileentity.BrewingStandTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public class VSBrewingStandBlockEntity extends BrewingStandTileEntity {
    public VSBrewingStandBlockEntity() {
        super();
    }

    @Override
    @Nonnull
    public TileEntityType<?> getType() {
        return VSBlockEntities.VS_BREWING_STAND.get();
    }
}
