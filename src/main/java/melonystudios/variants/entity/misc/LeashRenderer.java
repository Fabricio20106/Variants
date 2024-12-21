package melonystudios.variants.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.LightType;

import java.util.function.BiFunction;

public interface LeashRenderer {
    default <E extends Entity> void renderLeash(BoatEntity boat, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, E leasher, BiFunction<BoatEntity, BlockPos, Integer> lightLevelFunc) {
        stack.pushPose();
        Vector3d leashHoldPosition = leasher.getRopeHoldPosition(partialTicks);
        double rotation = (double) (MathHelper.lerp(partialTicks, boat.yRot, boat.yRotO) * ((float) Math.PI / 180F)) + (Math.PI / 2D);
        Vector3d leashOffset = boat.getLeashOffset();
        double rotationCos = Math.cos(rotation) * leashOffset.z + Math.sin(rotation) * leashOffset.x;
        double rotationSin = Math.sin(rotation) * leashOffset.z - Math.cos(rotation) * leashOffset.x;
        double x = MathHelper.lerp(partialTicks, boat.xo, boat.getX()) + rotationCos;
        double y = MathHelper.lerp(partialTicks, boat.yo, boat.getY()) + leashOffset.y;
        double z = MathHelper.lerp(partialTicks, boat.zo, boat.getZ()) + rotationSin;
        stack.translate(rotationCos, leashOffset.y, rotationSin);
        float xOffset = (float) (leashHoldPosition.x - x);
        float yOffset = (float) (leashHoldPosition.y - y);
        float zOffset = (float) (leashHoldPosition.z - z);
        IVertexBuilder leashBuffer = buffer.getBuffer(RenderType.leash());
        Matrix4f lastPose = stack.last().pose();
        float f4 = MathHelper.fastInvSqrt(xOffset * xOffset + zOffset * zOffset) * 0.025F / 2;
        float f5 = zOffset * f4;
        float f6 = xOffset * f4;
        BlockPos boatEyePos = new BlockPos(boat.getEyePosition(partialTicks));
        BlockPos leasherEyePos = new BlockPos(leasher.getEyePosition(partialTicks));
        int lightLevel = lightLevelFunc.apply(boat, boatEyePos);
        int blockLightLevel = leasher.isOnFire() ? 15 : leasher.level.getBrightness(LightType.BLOCK, leasherEyePos);
        int boatSkyBrightness = boat.level.getBrightness(LightType.SKY, boatEyePos);
        int leasherSkyBrightness = boat.level.getBrightness(LightType.SKY, leasherEyePos);
        MobRenderer.renderSide(leashBuffer, lastPose, xOffset, yOffset, zOffset, lightLevel, blockLightLevel, boatSkyBrightness, leasherSkyBrightness, 0.025F, 0.025F, f5, f6);
        MobRenderer.renderSide(leashBuffer, lastPose, xOffset, yOffset, zOffset, lightLevel, blockLightLevel, boatSkyBrightness, leasherSkyBrightness, 0.025F, 0, f5, f6);
        stack.popPose();
    }
}
