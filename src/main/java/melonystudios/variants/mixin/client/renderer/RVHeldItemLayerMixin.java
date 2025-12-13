package melonystudios.variants.mixin.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemLayer.class)
public abstract class RVHeldItemLayerMixin<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {
    public RVHeldItemLayerMixin(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void renderSpyglassInArm(LivingEntity livEntity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide side, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, CallbackInfo callback) {
        if (stack.getItem().is(VSItemTags.SPYGLASSES) && livEntity.getUseItem() == stack && livEntity.swingTime == 0) {
            callback.cancel();
            this.renderSpyglass(livEntity, stack, side, matrixStack, buffer, packedLight);
        }
    }

    @Unique
    private void renderSpyglass(LivingEntity livEntity, ItemStack stack, HandSide side, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        matrixStack.pushPose();
        ModelRenderer renderer = ((IHasHead) this.getParentModel()).getHead();
        float xRotation = renderer.xRot;
        renderer.xRot = MathHelper.clamp(renderer.xRot, -0.5235988F, 1.5707964F);
        renderer.translateAndRotate(matrixStack);
        renderer.xRot = xRotation;
        this.translate(matrixStack);
        boolean isLeftHanded = side == HandSide.LEFT;
        matrixStack.translate(((isLeftHanded ? -2.5F : 2.5F) / 16), -0.0625, 0);
        Minecraft.getInstance().getItemInHandRenderer().renderItem(livEntity, stack, ItemCameraTransforms.TransformType.HEAD, false, matrixStack, buffer, packedLight);
        matrixStack.popPose();
    }

    @Unique
    private void translate(MatrixStack stack) {
        stack.translate(0, -0.25, 0);
        stack.mulPose(Vector3f.YP.rotationDegrees(180));
        stack.scale(0.625F, -0.625F, -0.625F);
    }
}
