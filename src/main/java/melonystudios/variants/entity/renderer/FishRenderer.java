package melonystudios.variants.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.Variants;
import melonystudios.variants.entity.custom.FishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CodModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FishRenderer extends MobRenderer<FishEntity, CodModel<FishEntity>> {
    public FishRenderer(EntityRendererManager manager) {
        super(manager, new CodModel<>(), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(FishEntity entity) {
        return Variants.variants("textures/entity/fish.png");
    }

    @Override
    protected void setupRotations(FishEntity fish, MatrixStack stack, float ageInTicks, float yaw, float partialTicks) {
        super.setupRotations(fish, stack, ageInTicks, yaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        stack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!fish.isInWater()) {
            stack.translate(0.1F, 0.1F, -0.1F);
            stack.mulPose(Vector3f.ZP.rotationDegrees(90));
        }
    }
}
