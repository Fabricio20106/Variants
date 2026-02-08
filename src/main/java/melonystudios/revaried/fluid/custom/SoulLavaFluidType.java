package melonystudios.revaried.fluid.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class SoulLavaFluidType extends ExtendedFluidType {
    public SoulLavaFluidType(ResourceLocation stillTexture, ResourceLocation flowingTexture, ResourceLocation overlayTexture, int tintColor, Vector3f fogColor, Properties properties) {
        super(stillTexture, flowingTexture, overlayTexture, tintColor, fogColor, properties);
    }

    @Override
    public double motionScale(Entity entity) {
        return entity.level().dimensionType().ultraWarm() ? 0.007D : 0.0023333333333333335D;
    }

    @Override
    public void setItemMovement(ItemEntity entity) {
        Vec3 deltaMovement = entity.getDeltaMovement();
        entity.setDeltaMovement(deltaMovement.x() * (double) 0.95F, deltaMovement.y() + (double) (deltaMovement.y() < (double) 0.06F ? 5E-4F : 0), deltaMovement.z() * (double) 0.95F);
    }

    @Override
    public boolean move(FluidState state, LivingEntity livEntity, Vec3 movementVector, double gravity) {
        return false;
    }
}
