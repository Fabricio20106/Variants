package melonystudios.variants.util.tag;

import melonystudios.registryless.tag.INamedRegistrylessTag;
import melonystudios.registryless.tag.RegistrylessTagManager;
import melonystudios.registryless.tag.entry.RegistrylessEntry;
import melonystudios.variants.Variants;
import melonystudios.variants.util.damage.DamageSourceUtils;
import net.minecraft.util.DamageSource;

public class DamageSourceTags extends RegistrylessEntry<DamageSource> {
    public static final INamedRegistrylessTag<DamageSource> IS_EXPLOSION = melony("is_explosion");

    private static INamedRegistrylessTag<DamageSource> melony(String name) {
        return RegistrylessTagManager.createWrapperTag(Variants.variants("damage_source"), Variants.variants(name), DamageSource.GENERIC, source -> DamageSourceUtils.DATA_DRIVEN_SOURCES.containsValue(source));
    }

    @Override
    public int getID(DamageSource source) {
        return 0;
    }

    @Override
    public DamageSource byID(int identifier) {
        return null;
    }
}
