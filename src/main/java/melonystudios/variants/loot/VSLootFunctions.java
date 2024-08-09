package melonystudios.variants.loot;

import melonystudios.variants.Variants;
import melonystudios.variants.loot.function.SetStewBehavior;
import melonystudios.variants.loot.function.SetStewBowl;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.util.registry.Registry;

public class VSLootFunctions {
    public static final LootFunctionType SET_STEW_BEHAVIOR = register("set_stew_behavior", new SetStewBehavior.Serializer());
    public static final LootFunctionType SET_STEW_BOWL = register("set_stew_bowl", new SetStewBowl.Serializer());

    private static LootFunctionType register(String name, ILootSerializer<? extends ILootFunction> serializer) {
        return Registry.register(Registry.LOOT_FUNCTION_TYPE, Variants.variants(name), new LootFunctionType(serializer));
    }

    public static void init() {}
}
