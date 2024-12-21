package melonystudios.variants.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import melonystudios.variants.Variants;
import melonystudios.variants.entity.misc.LeashRenderer;
import melonystudios.variants.entity.misc.Leashable;
import melonystudios.variants.entity.custom.VSBoatEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class VSBoatRenderer extends EntityRenderer<VSBoatEntity> implements LeashRenderer {
    private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[] {Variants.variants("textures/entity/boat/painting.png"),Variants.variants("textures/entity/boat/crimson.png"),
            Variants.variants("textures/entity/boat/warped.png"), Variants.variants("textures/entity/boat/enderwood.png")};
    protected final BoatModel model = new BoatModel();

    public VSBoatRenderer(EntityRendererManager manager) {
        super(manager);
        this.shadowRadius = 0.8F;
    }

    @Override
    protected boolean shouldShowName(VSBoatEntity boat) {
        return super.shouldShowName(boat) && (boat.shouldShowName() || boat.hasCustomName() && boat == this.entityRenderDispatcher.crosshairPickEntity);
    }

    @Override
    public boolean shouldRender(VSBoatEntity boat, ClippingHelper helper, double camX, double camY, double camZ) {
        if (super.shouldRender(boat, helper, camX, camY, camZ)) {
            return true;
        } else {
            Entity entity = ((Leashable) boat).getLeashHolder();
            return entity != null && helper.isVisible(entity.getBoundingBoxForCulling());
        }
    }

    @Override
    public void render(VSBoatEntity boat, float yaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
        stack.pushPose();
        stack.translate(0, 0.375D, 0);
        stack.mulPose(Vector3f.YP.rotationDegrees(180 - yaw));
        float hurtTime = (float) boat.getHurtTime() - partialTicks;
        float damage = boat.getDamage() - partialTicks;
        if (damage < 0) {
            damage = 0;
        }

        if (hurtTime > 0) {
            stack.mulPose(Vector3f.XP.rotationDegrees(MathHelper.sin(hurtTime) * hurtTime * damage / 10 * (float) boat.getHurtDir()));
        }

        float bubbleAngle = boat.getBubbleAngle(partialTicks);
        if (!MathHelper.equal(bubbleAngle, 0)) {
            stack.mulPose(new Quaternion(new Vector3f(1, 0, 1), boat.getBubbleAngle(partialTicks), true));
        }

        stack.scale(-1, -1, 1);
        stack.mulPose(Vector3f.YP.rotationDegrees(90));
        this.model.setupAnim(boat, partialTicks, 0, -0.1F, 0, 0);
        IVertexBuilder vertexBuilder = buffer.getBuffer(this.model.renderType(this.getTextureLocation(boat)));
        this.model.renderToBuffer(stack, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        if (!boat.isUnderWater()) {
            IVertexBuilder waterMaskBuffer = buffer.getBuffer(RenderType.waterMask());
            this.model.waterPatch().render(stack, waterMaskBuffer, packedLight, OverlayTexture.NO_OVERLAY);
        }

        stack.popPose();

        Entity leasher = ((Leashable) boat).getLeashHolder();
        if (leasher != null) renderLeash(boat, partialTicks, stack, buffer, leasher, (boat1, boatEyePos) -> this.getBlockLightLevel(boat, boatEyePos));

        super.render(boat, yaw, partialTicks, stack, buffer, packedLight);
    }

    @Override
    @Nonnull
    public ResourceLocation getTextureLocation(VSBoatEntity entity) {
        switch (entity.getWoodType()) {
            case "crimson":
                return BOAT_TEXTURES[1];
            case "warped":
                return BOAT_TEXTURES[2];
            case "ender":
            case "enderwood":
                return BOAT_TEXTURES[3];
            case "painting":
            default:
                return BOAT_TEXTURES[0];
        }
    }
}
