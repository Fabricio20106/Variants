package com.junethewoods.variants.block;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.custom.*;
import com.junethewoods.variants.block.custom.end.*;
import com.junethewoods.variants.block.custom.nether.WarpedWartBlock;
import com.junethewoods.variants.block.custom.nether.WitherBoneBlock;
import com.junethewoods.variants.fluid.VSFluids;
import com.junethewoods.variants.util.VSWoodTypes;
import com.junethewoods.variants.world.feature.VSFeatures;
import com.junethewoods.variants.world.tree.PaintingTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Variants.MOD_ID);

    public static final RegistryObject<Block> PLAIN_BIRCH_BOOKSHELF = BLOCKS.register("plain_birch_bookshelf", () -> new VSBookshelfBlock(1, AbstractBlock.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> PAINTING_LOG = BLOCKS.register("painting_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> PAINTING_WOOD = BLOCKS.register("painting_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_PAINTING_LOG = BLOCKS.register("stripped_painting_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_PAINTING_WOOD = BLOCKS.register("stripped_painting_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> PAINTING_LEAVES = BLOCKS.register("painting_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> PAINTING_SAPLING = BLOCKS.register("painting_sapling", () -> new SaplingBlock(new PaintingTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> PAINTING_PLANKS = BLOCKS.register("painting_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PAINTING_STAIRS = BLOCKS.register("painting_stairs", () -> new StairsBlock(() -> VSBlocks.PAINTING_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> PAINTING_SLAB = BLOCKS.register("painting_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> PAINTING_FENCE = BLOCKS.register("painting_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> PAINTING_FENCE_GATE = BLOCKS.register("painting_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(Blocks.OAK_FENCE_GATE)));
    public static final RegistryObject<Block> PAINTING_PRESSURE_PLATE = BLOCKS.register("painting_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final RegistryObject<Block> PAINTING_BUTTON = BLOCKS.register("painting_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(Blocks.OAK_BUTTON)));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR = BLOCKS.register("painting_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR_ALBAN = BLOCKS.register("alban_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR_AZTEC = BLOCKS.register("aztec_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR_AZTEC2 = BLOCKS.register("aztec2_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR_BOMB = BLOCKS.register("bomb_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR_KEBAB = BLOCKS.register("kebab_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR_PLANT = BLOCKS.register("plant_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR_WASTELAND = BLOCKS.register("wasteland_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PAINTING_DOOR = BLOCKS.register("painting_door", () -> new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<Block> PAINTING_DOOR_WANDERER = BLOCKS.register("wanderer_door", () -> new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<Block> PAINTING_DOOR_GRAHAM = BLOCKS.register("graham_door", () -> new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<Block> PAINTING_DOOR_FIRST = BLOCKS.register("first_door", () -> new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<Block> PAINTING_SIGN = BLOCKS.register("painting_sign", () -> new VSStandingSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_SIGN), VSWoodTypes.PAINTING));
    public static final RegistryObject<Block> PAINTING_WALL_SIGN = BLOCKS.register("painting_wall_sign", () -> new VSWallSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_WALL_SIGN).lootFrom(VSBlocks.PAINTING_SIGN), VSWoodTypes.PAINTING));
    public static final RegistryObject<Block> ENDERWOOD_STEM = BLOCKS.register("ender_stem", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.WARPED_STEM)));
    public static final RegistryObject<Block> ENDERWOOD_HYPHAE = BLOCKS.register("ender_hyphae", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.WARPED_HYPHAE)));
    public static final RegistryObject<Block> STRIPPED_ENDERWOOD_STEM = BLOCKS.register("stripped_ender_stem", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_WARPED_STEM)));
    public static final RegistryObject<Block> STRIPPED_ENDERWOOD_HYPHAE = BLOCKS.register("stripped_ender_hyphae", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_WARPED_HYPHAE)));
    public static final RegistryObject<Block> ENDER_WART_BLOCK = BLOCKS.register("ender_wart_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.WARPED_WART_BLOCK)));
    public static final RegistryObject<Block> ENDERWOOD_PLANKS = BLOCKS.register("ender_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.WARPED_PLANKS)));
    public static final RegistryObject<Block> ENDERWOOD_BOOKSHELF = BLOCKS.register("ender_bookshelf", () -> new VSBookshelfBlock(2, AbstractBlock.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> ENDERWOOD_STAIRS = BLOCKS.register("ender_stairs", () -> new StairsBlock(() -> VSBlocks.ENDERWOOD_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.WARPED_STAIRS)));
    public static final RegistryObject<Block> ENDERWOOD_SLAB = BLOCKS.register("ender_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.WARPED_SLAB)));
    public static final RegistryObject<Block> ENDERWOOD_FENCE = BLOCKS.register("ender_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(Blocks.WARPED_FENCE)));
    public static final RegistryObject<Block> ENDERWOOD_FENCE_GATE = BLOCKS.register("ender_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(Blocks.WARPED_FENCE_GATE)));
    public static final RegistryObject<Block> ENDERWOOD_PRESSURE_PLATE = BLOCKS.register("ender_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(Blocks.WARPED_PRESSURE_PLATE)));
    public static final RegistryObject<Block> ENDERWOOD_BUTTON = BLOCKS.register("ender_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(Blocks.WARPED_BUTTON)));
    public static final RegistryObject<Block> ENDERWOOD_TRAPDOOR = BLOCKS.register("ender_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.WARPED_TRAPDOOR)));
    public static final RegistryObject<Block> ENDERWOOD_DOOR = BLOCKS.register("ender_door", () -> new DoorBlock(AbstractBlock.Properties.copy(Blocks.WARPED_DOOR)));
    public static final RegistryObject<Block> ENDERWOOD_SIGN = BLOCKS.register("ender_sign", () -> new VSStandingSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_SIGN), VSWoodTypes.ENDERWOOD));
    public static final RegistryObject<Block> ENDERWOOD_WALL_SIGN = BLOCKS.register("ender_wall_sign", () -> new VSWallSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_WALL_SIGN).lootFrom(VSBlocks.ENDERWOOD_SIGN), VSWoodTypes.ENDERWOOD));
    public static final RegistryObject<Block> ELDER_SEA_LANTERN = BLOCKS.register("elder_sea_lantern", () -> new ConduitFrameBlock(AbstractBlock.Properties.copy(Blocks.SEA_LANTERN).emissiveRendering((a, b, c) -> true)));
    public static final RegistryObject<Block> ELDER_PRISMARINE = BLOCKS.register("elder_prismarine", () -> new ConduitFrameBlock(AbstractBlock.Properties.copy(Blocks.PRISMARINE)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_STAIRS = BLOCKS.register("elder_prismarine_stairs", () -> new StairsBlock(() -> VSBlocks.ELDER_PRISMARINE.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.PRISMARINE_STAIRS)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_SLAB = BLOCKS.register("elder_prismarine_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.PRISMARINE_SLAB)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_WALL = BLOCKS.register("elder_prismarine_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.PRISMARINE_WALL)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_BRICKS = BLOCKS.register("elder_prismarine_bricks", () -> new ConduitFrameBlock(AbstractBlock.Properties.copy(Blocks.PRISMARINE_BRICKS)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_BRICK_STAIRS = BLOCKS.register("elder_prismarine_brick_stairs", () -> new StairsBlock(() -> VSBlocks.ELDER_PRISMARINE_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.PRISMARINE_BRICK_STAIRS)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_BRICK_SLAB = BLOCKS.register("elder_prismarine_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.PRISMARINE_BRICK_SLAB)));
    public static final RegistryObject<Block> DARK_ELDER_PRISMARINE = BLOCKS.register("dark_elder_prismarine", () -> new ConduitFrameBlock(AbstractBlock.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistryObject<Block> DARK_ELDER_PRISMARINE_STAIRS = BLOCKS.register("dark_elder_prismarine_stairs", () -> new StairsBlock(() -> VSBlocks.DARK_ELDER_PRISMARINE.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.DARK_PRISMARINE_STAIRS)));
    public static final RegistryObject<Block> DARK_ELDER_PRISMARINE_SLAB = BLOCKS.register("dark_elder_prismarine_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.DARK_PRISMARINE_SLAB)));
    public static final RegistryObject<Block> CHISELED_END_STONE_BRICKS = BLOCKS.register("chiseled_end_stone_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.END_STONE_BRICKS)));
    public static final RegistryObject<Block> INFESTED_CHISELED_END_STONE_BRICKS = BLOCKS.register("infested_chiseled_end_stone_bricks", () -> new SilverfishBlock(VSBlocks.CHISELED_END_STONE_BRICKS.get(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(0, 0.75F)));
    public static final RegistryObject<Block> CHISELED_PURPUR_BLOCK = BLOCKS.register("chiseled_purpur_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> INFESTED_CHISELED_PURPUR_BLOCK = BLOCKS.register("infested_chiseled_purpur_block", () -> new SilverfishBlock(VSBlocks.CHISELED_PURPUR_BLOCK.get(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_MAGENTA).strength(0, 0.75F)));
    public static final RegistryObject<Block> RED_NETHER_BRICK_FENCE = BLOCKS.register("red_nether_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(Blocks.NETHER_BRICK_FENCE)));
    public static final RegistryObject<Block> WITHER_BONE_BLOCK = BLOCKS.register("wither_bone_block", () -> new WitherBoneBlock(AbstractBlock.Properties.copy(Blocks.BONE_BLOCK)));
    public static final RegistryObject<Block> RAW_DEBRIS_BLOCK = BLOCKS.register("raw_debris_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(30f, 1200f).sound(SoundType.ANCIENT_DEBRIS)));
    public static final RegistryObject<Block> QUARTZ_ORE = BLOCKS.register("quartz_ore", () -> new VSOreBlock(2, 5, AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3f, 3f)));
    public static final RegistryObject<Block> DEEPSLATE_QUARTZ_ORE = BLOCKS.register("deepslate_quartz_ore", () -> new VSOreBlock(2, 5, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(4.5f, 3f)));
    public static final RegistryObject<Block> END_QUARTZ_ORE = BLOCKS.register("end_quartz_ore", () -> new VSOreBlock(2, 5, AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(3f, 9f)));
    public static final RegistryObject<Block> GOLDEN_CAULDRON = BLOCKS.register("gold_cauldron", () -> new CauldronBlock(AbstractBlock.Properties.copy(Blocks.GOLD_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> GOLDEN_BEACON = BLOCKS.register("gold_beacon", () -> new VSBeaconBlock(DyeColor.YELLOW, AbstractBlock.Properties.copy(Blocks.BEACON)));
    public static final RegistryObject<Block> DIAMOND_BELL = BLOCKS.register("diamond_bell", () -> new VSBellBlock(AbstractBlock.Properties.copy(Blocks.BELL)));
    public static final RegistryObject<Block> NETHERRACK_LEVER = BLOCKS.register("netherrack_lever", () -> new LeverBlock(AbstractBlock.Properties.copy(Blocks.LEVER).sound(SoundType.NETHERRACK)));
    public static final RegistryObject<Block> END_STONE_LEVER = BLOCKS.register("end_stone_lever", () -> new LeverBlock(AbstractBlock.Properties.copy(Blocks.LEVER).sound(SoundType.STONE)));
    public static final RegistryObject<Block> GLOW_BLACK_WOOL = BLOCKS.register("glow_black_wool", () -> new Block(AbstractBlock.Properties.copy(Blocks.LIGHT_BLUE_WOOL)));
    public static final RegistryObject<Block> GLOW_BLACK_CARPET = BLOCKS.register("glow_black_carpet", () -> new VSCarpetBlock(AbstractBlock.Properties.copy(Blocks.LIGHT_BLUE_CARPET)));
    public static final RegistryObject<Block> GLOW_BLACK_BED = BLOCKS.register("glow_black_bed", () -> new VSBedBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Properties.copy(Blocks.LIGHT_BLUE_BED)));
    public static final RegistryObject<Block> GLOW_BLACK_STAINED_GLASS = BLOCKS.register("glow_black_stained_glass", () -> new CustomBeamGlassBlock(0x4BDEBA, AbstractBlock.Properties.copy(Blocks.LIGHT_BLUE_STAINED_GLASS)));
    public static final RegistryObject<Block> GLOW_BLACK_STAINED_GLASS_PANE = BLOCKS.register("glow_black_stained_glass_pane", () -> new CustomBeamGlassPaneBlock(0x4BDEBA, AbstractBlock.Properties.copy(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE)));
    public static final RegistryObject<Block> QUARTZ_GLASS = BLOCKS.register("quartz_glass", () -> new CustomBeamGlassBlock(0xDDD4C6, AbstractBlock.Properties.copy(Blocks.GLASS)));
    public static final RegistryObject<Block> QUARTZ_GLASS_PANE = BLOCKS.register("quartz_glass_pane", () -> new CustomBeamGlassPaneBlock(0xDDD4C6, AbstractBlock.Properties.copy(Blocks.GLASS_PANE)));
    public static final RegistryObject<Block> QUARTZ_BARS = BLOCKS.register("quartz_bars", () -> new PaneBlock(AbstractBlock.Properties.copy(Blocks.IRON_BARS)));
    public static final RegistryObject<Block> QUARTZ_CHAIN = BLOCKS.register("quartz_chain", () -> new ChainBlock(AbstractBlock.Properties.copy(Blocks.CHAIN)));
    public static final RegistryObject<Block> GOLDEN_CHAIN = BLOCKS.register("golden_chain", () -> new ChainBlock(AbstractBlock.Properties.copy(Blocks.CHAIN)));
    public static final RegistryObject<Block> DIAMOND_CHAIN = BLOCKS.register("diamond_chain", () -> new ChainBlock(AbstractBlock.Properties.copy(Blocks.CHAIN)));
    public static final RegistryObject<Block> EMERALD_CHAIN = BLOCKS.register("emerald_chain", () -> new ChainBlock(AbstractBlock.Properties.copy(Blocks.CHAIN)));
    public static final RegistryObject<Block> ENDER_NYLIUM = BLOCKS.register("ender_nylium", () -> new EnderNyliumBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_NYLIUM)));
    public static final RegistryObject<Block> ENDER_FARMLAND = BLOCKS.register("ender_farmland", () -> new EnderFarmlandBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_NYLIUM).randomTicks().isViewBlocking((state, world, pos) -> true).isSuffocating((state, world, pos) -> true)));
    public static final RegistryObject<Block> ENDER_ROOTS = BLOCKS.register("ender_roots", () -> new EnderRootsBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_ROOTS)));
    public static final RegistryObject<Block> END_SPROUTS = BLOCKS.register("ender_sprouts", () -> new EndSproutsBlock(AbstractBlock.Properties.copy(Blocks.NETHER_SPROUTS)));
    public static final RegistryObject<Block> ENDER_FUNGUS = BLOCKS.register("ender_fungus", () -> new EnderFungusBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_FUNGUS), () -> VSFeatures.PLANTED_ENDERWOOD_FUNGI));
    public static final RegistryObject<Block> WARPING_VINES = BLOCKS.register("warping_vines", () -> new WarpingVinesTopBlock(AbstractBlock.Properties.copy(Blocks.WEEPING_VINES)));
    public static final RegistryObject<Block> WARPING_VINES_PLANT = BLOCKS.register("warping_vines_plant", () -> new WarpingVinesBlock(AbstractBlock.Properties.copy(Blocks.WEEPING_VINES_PLANT)));
    public static final RegistryObject<Block> GOLDEN_CARROTS = BLOCKS.register("golden_carrots", () -> new GoldenCarrotsBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> GLOW_BLACK_TULIP = BLOCKS.register("glow_black_tulip", () -> new FlowerBlock(Effects.GLOWING, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> SUNNY_FLOWER = BLOCKS.register("sunny_flower", () -> new FlowerBlock(Effects.INVISIBILITY, 9, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> GLOW_BERRY_BUSH = BLOCKS.register("glow_berry_bush", () -> new SweetBerryBushBlock(AbstractBlock.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> WARPED_WART = BLOCKS.register("warped_wart", () -> new WarpedWartBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.WARPED_WART_BLOCK).noCollission().randomTicks().sound(SoundType.FUNGUS)));
    public static final RegistryObject<Block> ENDER_WART = BLOCKS.register("ender_wart", () -> new EnderWartBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE).noCollission().randomTicks().sound(SoundType.FUNGUS)));
    public static final RegistryObject<Block> POTTED_GRASS = BLOCKS.register("potted_grass", () -> new FlowerPotBlock(Blocks.GRASS, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_SUGAR_CANE = BLOCKS.register("potted_sugar_cane", () -> new FlowerPotBlock(Blocks.SUGAR_CANE, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_GOLDEN_CARROTS = BLOCKS.register("potted_golden_carrots", () -> new FlowerPotBlock(VSBlocks.GOLDEN_CARROTS.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_GLOW_BLACK_TULIP = BLOCKS.register("potted_glow_black_tulip", () -> new FlowerPotBlock(VSBlocks.GLOW_BLACK_TULIP.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_SUNNY_FLOWER = BLOCKS.register("potted_sunny_flower", () -> new FlowerPotBlock(VSBlocks.SUNNY_FLOWER.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_PAINTING_SAPLING = BLOCKS.register("potted_painting_sapling", () -> new FlowerPotBlock(VSBlocks.PAINTING_SAPLING.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_ENDER_ROOTS = BLOCKS.register("potted_ender_roots", () -> new FlowerPotBlock(VSBlocks.ENDER_ROOTS.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_ENDER_FUNGUS = BLOCKS.register("potted_ender_fungus", () -> new FlowerPotBlock(VSBlocks.ENDER_FUNGUS.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_NETHER_WART = BLOCKS.register("potted_nether_wart", () -> new FlowerPotBlock(Blocks.NETHER_WART, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_WARPED_WART = BLOCKS.register("potted_warped_wart", () -> new FlowerPotBlock(VSBlocks.WARPED_WART.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_ENDER_WART = BLOCKS.register("potted_ender_wart", () -> new FlowerPotBlock(VSBlocks.ENDER_WART.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<FlowingFluidBlock> SOUL_LAVA = BLOCKS.register("soul_lava", () -> new FlowingFluidBlock(VSFluids.SOUL_LAVA, AbstractBlock.Properties.of(Material.LAVA, MaterialColor.COLOR_LIGHT_BLUE).noCollission().randomTicks().strength(100).lightLevel((light) -> 15).noDrops()));
}
