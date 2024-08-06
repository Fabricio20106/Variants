package melonystudios.variants.entity.renderer;

import melonystudios.variants.entity.custom.DebugArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DebugArrowRenderer extends ArrowRenderer<DebugArrowEntity> {
    public DebugArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(DebugArrowEntity arrow) {
        return new ResourceLocation("textures/entity/projectiles/arrow.png");
    }
}
