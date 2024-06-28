package com.junethewoods.variants.mixin.entity;

import com.junethewoods.variants.item.custom.tool.SpyglassItem;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Copied from Caves & Cliffs Backport (by blackgear27)
@OnlyIn(Dist.CLIENT)
@Mixin(BipedModel.class)
public abstract class VSBipedModelMixin<T extends LivingEntity> extends AgeableModel<T> implements IHasArm, IHasHead {
    @Shadow
    public ModelRenderer rightArm;
    @Shadow
    public ModelRenderer leftArm;
    @Shadow
    public ModelRenderer head;

    @Inject(method = "setupAnim(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
    private void setupAnim(T livEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (SpyglassItem.isUsingSpyglass(livEntity) && (livEntity).getUseItemRemainingTicks() > 0) {
            if (livEntity.getMainArm() == HandSide.RIGHT) {
                this.positionRightArmScope(Hand.MAIN_HAND, livEntity);
                this.positionLeftArmScope(Hand.OFF_HAND, livEntity);
            } else {
                this.positionRightArmScope(Hand.OFF_HAND, livEntity);
                this.positionLeftArmScope(Hand.MAIN_HAND, livEntity);
            }
        }
    }

    @Unique
    private void positionRightArmScope(Hand hand, LivingEntity livEntity) {
        if (livEntity.getUsedItemHand().equals(hand)) {
            this.rightArm.xRot = MathHelper.clamp(this.head.xRot - 1.9198622F - (livEntity.isShiftKeyDown() ? 0.2617994F : 0), -2.4F, 3.3F);
            this.rightArm.yRot = this.head.yRot - 0.2617994F;
        }
    }

    @Unique
    private void positionLeftArmScope(Hand hand, LivingEntity livEntity) {
        if (livEntity.getUsedItemHand().equals(hand)) {
            this.leftArm.xRot = MathHelper.clamp(this.head.xRot - 1.9198622F - (livEntity.isShiftKeyDown() ? 0.2617994F : 0), -2.4F, 3.3F);
            this.leftArm.yRot = this.head.yRot + 0.2617994F;
        }
    }
}
