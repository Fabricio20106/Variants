package melonystudios.variants.mixin.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.entity.misc.LeashRenderer;
import melonystudios.variants.entity.misc.Leashable;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(BoatRenderer.class)
public abstract class VSBoatRendererMixin extends EntityRenderer<BoatEntity> implements LeashRenderer {
    public VSBoatRendererMixin(EntityRendererManager manager) {
        super(manager);
    }

    @Inject(method = "render(Lnet/minecraft/entity/item/BoatEntity;FFLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V", at = @At("TAIL"))
    public void render(BoatEntity boat, float yaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight, CallbackInfo ci) {
        Entity leasher = ((Leashable) boat).getLeashHolder();
        if (leasher != null) renderLeash(boat, partialTicks, stack, buffer, leasher, (boat1, boatEyePos) -> this.getBlockLightLevel(boat, boatEyePos));
    }
}
