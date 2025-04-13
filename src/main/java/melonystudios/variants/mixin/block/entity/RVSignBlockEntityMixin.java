package melonystudios.variants.mixin.block.entity;

import melonystudios.variants.sound.VSSounds;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignTileEntity.class)
public abstract class RVSignBlockEntityMixin extends TileEntity {
    @Shadow
    public abstract DyeColor getColor();

    public RVSignBlockEntityMixin(TileEntityType<?> type) {
        super(type);
    }

    @Inject(method = "setColor", at = @At("HEAD"))
    public void setColor(DyeColor color, CallbackInfoReturnable<Boolean> callback) {
        if (color != this.getColor() && this.level != null) {
            this.level.playSound(null, this.getBlockPos(), VSSounds.DYE_STAIN.get(), SoundCategory.BLOCKS, 1, 1);
        }
    }
}
