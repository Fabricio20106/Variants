package melonystudios.variants.event;

import melonystudios.variants.Variants;
import melonystudios.variants.block.VSBlocks;
import melonystudios.variants.entity.VSEntities;
import melonystudios.variants.event.custom.MappingManager;
import melonystudios.variants.item.RVModdedItems;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.VSWeaponry;
import melonystudios.variants.world.biome.VSBiomes;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

// Copied from Caves & Cliffs Backport (by blackgear27)
@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VSForgeBusEvents {
    @SubscribeEvent
    public static void remapBlocks(RegistryEvent.MissingMappings<Block> event) {
        MappingManager manager = MappingManager.createBlockMaps(Variants.MOD_ID, event);
        manager.remap("ender_stem", VSBlocks.ENDERWOOD_STEM.get());
        manager.remap("ender_hyphae", VSBlocks.ENDERWOOD_HYPHAE.get());
        manager.remap("stripped_ender_stem", VSBlocks.STRIPPED_ENDERWOOD_STEM.get());
        manager.remap("stripped_ender_hyphae", VSBlocks.STRIPPED_ENDERWOOD_HYPHAE.get());
        manager.remap("ender_planks", VSBlocks.ENDERWOOD_PLANKS.get());
        manager.remap("ender_bookshelf", VSBlocks.ENDERWOOD_BOOKSHELF.get());
        manager.remap("ender_stairs", VSBlocks.ENDERWOOD_STAIRS.get());
        manager.remap("ender_slab", VSBlocks.ENDERWOOD_SLAB.get());
        manager.remap("ender_fence", VSBlocks.ENDERWOOD_FENCE.get());
        manager.remap("ender_fence_gate", VSBlocks.ENDERWOOD_FENCE_GATE.get());
        manager.remap("ender_pressure_plate", VSBlocks.ENDERWOOD_PRESSURE_PLATE.get());
        manager.remap("ender_button", VSBlocks.ENDERWOOD_BUTTON.get());
        manager.remap("ender_trapdoor", VSBlocks.ENDERWOOD_TRAPDOOR.get());
        manager.remap("ender_door", VSBlocks.ENDERWOOD_DOOR.get());
        manager.remap("ender_sign", VSBlocks.ENDERWOOD_SIGN.get());
        manager.remap("ender_wall_sign", VSBlocks.ENDERWOOD_WALL_SIGN.get());
        manager.remap("mossy_purpur_block_stairs", VSBlocks.MOSSY_PURPUR_STAIRS.get());
        manager.remap("mossy_purpur_block_slab", VSBlocks.MOSSY_PURPUR_SLAB.get());
        manager.remap("mossy_purpur_block_wall", VSBlocks.MOSSY_PURPUR_WALL.get());
        manager.remap("gold_cauldron", VSBlocks.GOLDEN_CAULDRON.get());
        manager.remap("gold_beacon", VSBlocks.GOLDEN_BEACON.get());
        manager.remap("ender_sprouts", VSBlocks.END_SPROUTS.get());
        manager.remap("soul_lava_block", VSBlocks.SOUL_LAVA.get());
        manager.remap("mushroom_stew_block", VSBlocks.MUSHROOM_STEW.get());
    }

    @SubscribeEvent
    public static void remapItems(RegistryEvent.MissingMappings<Item> event) {
        MappingManager manager = MappingManager.createItemMaps(Variants.MOD_ID, event);
        manager.remap("ender_stem", VSItems.ENDERWOOD_STEM.get());
        manager.remap("ender_hyphae", VSItems.ENDERWOOD_HYPHAE.get());
        manager.remap("stripped_ender_stem", VSItems.STRIPPED_ENDERWOOD_STEM.get());
        manager.remap("stripped_ender_hyphae", VSItems.STRIPPED_ENDERWOOD_HYPHAE.get());
        manager.remap("ender_planks", VSItems.ENDERWOOD_PLANKS.get());
        manager.remap("ender_bookshelf", VSItems.ENDERWOOD_BOOKSHELF.get());
        manager.remap("ender_stairs", VSItems.ENDERWOOD_STAIRS.get());
        manager.remap("ender_slab", VSItems.ENDERWOOD_SLAB.get());
        manager.remap("ender_fence", VSItems.ENDERWOOD_FENCE.get());
        manager.remap("ender_fence_gate", VSItems.ENDERWOOD_FENCE_GATE.get());
        manager.remap("ender_pressure_plate", VSItems.ENDERWOOD_PRESSURE_PLATE.get());
        manager.remap("ender_button", VSItems.ENDERWOOD_BUTTON.get());
        manager.remap("ender_trapdoor", VSItems.ENDERWOOD_TRAPDOOR.get());
        manager.remap("ender_door", VSItems.ENDERWOOD_DOOR.get());
        manager.remap("ender_sign", VSItems.ENDERWOOD_SIGN.get());
        manager.remap("mossy_purpur_block_stairs", VSItems.MOSSY_PURPUR_STAIRS.get());
        manager.remap("mossy_purpur_block_slab", VSItems.MOSSY_PURPUR_SLAB.get());
        manager.remap("mossy_purpur_block_wall", VSItems.MOSSY_PURPUR_WALL.get());
        manager.remap("gold_cauldron", VSItems.GOLDEN_CAULDRON.get());
        manager.remap("gold_beacon", VSItems.GOLDEN_BEACON.get());
        manager.remap("ender_sprouts", VSItems.END_SPROUTS.get());

        manager.remap("old_cod_spawn_egg", VSItems.FISH_SPAWN_EGG.get());
        manager.remap("old_cod_bucket", VSItems.FISH_BUCKET.get());
        manager.remap("old_cod", VSItems.RAW_FISH.get());
        manager.remap("old_cooked_cod", VSItems.COOKED_FISH.get());
        manager.remap("crimson_bread", VSItems.CRIMSON_LOAF.get());
        manager.remap("creeper_powder_pot", VSItems.EXPLOSIVE_BLEND_POT.get());
        manager.remap("lava_glass_bottle", VSItems.LAVA_BOTTLE.get());
        manager.remap("milk_glass_bottle", VSItems.MILK_BOTTLE.get());
        if (ModList.get().isLoaded("backmath")) manager.remap("hilary_bottle", RVModdedItems.HILLARY_BOTTLE);
        manager.remap("ender_boat", VSItems.ENDERWOOD_BOAT.get());
        manager.remap("ender_stick", VSItems.ENDERWOOD_STICK.get());
        manager.remap("ender_bowl", VSItems.ENDERWOOD_BOWL.get());
        manager.remap("soul_rod", VSItems.SOUL_BLAZE_ROD.get());
        manager.remap("soul_powder", VSItems.SOUL_BLAZE_POWDER.get());
        manager.remap("gelatinous_magma_cream", Items.MAGMA_CREAM); // I don't know what to do with it / I don't know what it was supposed to do ~isa 8-1-25
        manager.remap("diamond_nugget", VSItems.DIAMOND_SHARD.get());
        manager.remap("emerald_nugget", VSItems.EMERALD_SHARD.get());
        manager.remap("quartz_nugget", VSItems.QUARTZ_SHARD.get());
        manager.remap("purple_nugget", VSItems.PURPLE_IRON_NUGGET.get());

        manager.remap("phantom_membrane_sweatchest", VSWeaponry.PHANTOM_MEMBRANE_TUNIC.get());
        manager.remap("phantom_membrane_sweater", VSWeaponry.PHANTOM_MEMBRANE_TUNIC.get());
        manager.remap("rabbit_hide_sweatchest", VSWeaponry.RABBIT_HIDE_TUNIC.get());
        manager.remap("rabbit_hide_sweater", VSWeaponry.RABBIT_HIDE_TUNIC.get());
        manager.remap("wool_sweatchest", VSWeaponry.WOOL_SWEATER.get());
        manager.remap("crystal_shears", VSWeaponry.MAGENTIC_CRYSTAL_SHEARS.get());
        manager.remap("light_magenta_shears", VSWeaponry.MAGENTIC_SHEARS.get());
    }

    @SubscribeEvent
    public static void remapEntityTypes(RegistryEvent.MissingMappings<EntityType<?>> event) {
        MappingManager manager = MappingManager.createEntityTypeMaps(Variants.MOD_ID, event);
        manager.remap("old_cod", VSEntities.FISH.get());
        manager.remap("pornhey", EntityType.PIG); // never readding this thing
    }

    @SubscribeEvent
    public static void remapBiomes(RegistryEvent.MissingMappings<Biome> event) {
        MappingManager manager = MappingManager.createBiomeMaps(Variants.MOD_ID, event);
        manager.remap("painting_wooded_forest", VSBiomes.PAINTINGWOOD_FOREST.get());
        manager.remap("ender_forest", VSBiomes.ENDERWOOD_FOREST.get());
    }
}
