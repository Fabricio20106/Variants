package melonystudios.variants.util;

import melonystudios.variants.Variants;
import melonystudios.variants.stew.StewBehavior;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public class VSRegistries {
    public static final IForgeRegistry<StewBehavior> CONSUME_BEHAVIOR = new RegistryBuilder<StewBehavior>().setType(StewBehavior.class).tagFolder("consume_behavior").setName(Variants.variants("stew_behavior")).create();

    public static void init() {}
}
