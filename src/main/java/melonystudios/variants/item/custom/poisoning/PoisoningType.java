package melonystudios.variants.item.custom.poisoning;

import net.minecraft.potion.Effect;
import net.minecraft.tags.ITag;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class PoisoningType extends ForgeRegistryEntry<PoisoningType> {
    private final RegistryObject<Effect> poisoning;

    public PoisoningType(RegistryObject<Effect> poisoning) {
        this.poisoning = poisoning;
    }

    public boolean is(ITag<PoisoningType> poisoningTag) {
        return poisoningTag.contains(this);
    }

    public RegistryObject<Effect> getPoisoningEffect() {
        return this.poisoning;
    }

    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.REDSTONE.get();
    }
}
