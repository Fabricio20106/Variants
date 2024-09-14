package melonystudios.variants.loot;

import melonystudios.variants.Variants;
import melonystudios.variants.loot.function.SetConsumeBehavior;
import melonystudios.variants.loot.function.SetStewBowl;
import melonystudios.variants.loot.function.SetWoolArmorColor;
import melonystudios.variants.loot.function.SetWoolArmorDesign;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.util.registry.Registry;

public class VSLootFunctions {
    public static final LootFunctionType SET_CONSUME_BEHAVIOR = register("set_consume_behavior", new SetConsumeBehavior.Serializer());
    public static final LootFunctionType SET_STEW_BOWL = register("set_stew_bowl", new SetStewBowl.Serializer());
    public static final LootFunctionType SET_WOOL_ARMOR_COLOR = register("set_wool_armor_color", new SetWoolArmorColor.Serializer());
    public static final LootFunctionType SET_WOOL_ARMOR_DESIGN = register("set_wool_armor_design", new SetWoolArmorDesign.Serializer());

    private static LootFunctionType register(String name, ILootSerializer<? extends ILootFunction> serializer) {
        return Registry.register(Registry.LOOT_FUNCTION_TYPE, Variants.variants(name), new LootFunctionType(serializer));
    }

    public static void init() {}
}
