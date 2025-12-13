package melonystudios.variants.mixin.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.entity.misc.LeashRenderer;
import melonystudios.variants.entity.misc.Leashable;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatRenderer.class)
public abstract class RVBoatRendererMixin extends EntityRenderer<BoatEntity> implements LeashRenderer {
    public RVBoatRendererMixin(EntityRendererManager manager) {
        super(manager);
    }

    @Inject(method = "render(Lnet/minecraft/entity/item/BoatEntity;FFLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V", at = @At("TAIL"))
    public void renderLeash(BoatEntity boat, float yaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight, CallbackInfo callback) {
        Entity leasher = ((Leashable) boat).getLeashHolder();
        if (leasher != null) this.renderLeash(boat, partialTicks, stack, buffer, leasher, (boat1, boatEyePos) -> this.getBlockLightLevel(boat, boatEyePos));
    }
}
