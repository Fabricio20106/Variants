package melonystudios.revaried.data.misc;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.misc.particle.RVParticleTypes;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;
import org.jetbrains.annotations.NotNull;

public class RVParticleDescriptionProvider extends ParticleDescriptionProvider {
    public RVParticleDescriptionProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }

    @Override
    @NotNull
    public String getName() {
        return Revaried.generatorName("Particle Descriptions");
    }

    @Override
    protected void addDescriptions() {
        this.sprite(RVParticleTypes.SOUL_LAVA.get(), Revaried.revaried("soul_lava"));
        this.sprite(RVParticleTypes.DRIPPING_SOUL_LAVA.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        this.sprite(RVParticleTypes.FALLING_SOUL_LAVA.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        this.sprite(RVParticleTypes.LANDING_SOUL_LAVA.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        this.sprite(RVParticleTypes.DRIPPING_DRIPSTONE_SOUL_LAVA.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        this.sprite(RVParticleTypes.FALLING_DRIPSTONE_SOUL_LAVA.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
    }
}
