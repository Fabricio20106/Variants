package com.junethewoods.variants.core.init;

import com.junethewoods.variants.common.block.*;
import com.junethewoods.variants.common.block.machine.MixerMachineBlock;
import com.junethewoods.variants.common.block.plant.*;
import com.junethewoods.variants.common.block.plant.theend.*;
import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.sound.VSSoundTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VSBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Variants.MOD_ID);

    public static final RegistryObject<Block> POTTED_SUGAR_CANE = BLOCKS.register("potted_sugar_cane", () -> new FlowerPotBlock(Blocks.SUGAR_CANE, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> GOLDEN_CARROTS = BLOCKS.register("golden_carrots", () -> new GoldenCarrotsBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> POTTED_GOLDEN_CARROTS = BLOCKS.register("potted_golden_carrots", () -> new FlowerPotBlock(VSBlocks.GOLDEN_CARROTS.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));
    public static final RegistryObject<Block> GOLD_CAULDRON = BLOCKS.register("gold_cauldron", () -> new CauldronBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> GOLD_BEACON = BLOCKS.register("gold_beacon", () -> new BeaconBlock(BlockBehaviour.Properties.copy(Blocks.BEACON)));
    public static final RegistryObject<Block> QUARTZ_CAULDRON = BLOCKS.register("quartz_cauldron", () -> new CauldronBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));
    public static final RegistryObject<Block> QUARTZ_BEACON = BLOCKS.register("quartz_beacon", () -> new BeaconBlock(BlockBehaviour.Properties.copy(Blocks.BEACON)));
    public static final RegistryObject<Block> QUARTZ_ORE = BLOCKS.register("quartz_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 9.0F)));
    public static final RegistryObject<Block> END_QUARTZ_ORE = BLOCKS.register("end_quartz_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> NETHER_COAL_ORE = BLOCKS.register("nether_coal_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 9.0F).sound(SoundType.NETHER_GOLD_ORE)));
    public static final RegistryObject<Block> RAW_DEBRIS_BLOCK = BLOCKS.register("raw_debris_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(30.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS)));
    public static final RegistryObject<Block> GLOW_BLACK_TULIP = BLOCKS.register("glow_black_tulip", () -> new FlowerBlock(MobEffects.DAMAGE_BOOST, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> WARPED_WART = BLOCKS.register("warped_wart", () -> new WarpedWartBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.WARPED_WART_BLOCK).randomTicks().noCollission().sound(SoundType.FUNGUS)));
    public static final RegistryObject<Block> POTTED_WARPED_WART = BLOCKS.register("potted_warped_wart", () -> new FlowerPotBlock(VSBlocks.WARPED_WART.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_GLOW_BLACK_TULIP = BLOCKS.register("potted_glow_black_tulip", () -> new FlowerPotBlock(VSBlocks.GLOW_BLACK_TULIP.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> SUNNY_FLOWER = BLOCKS.register("sunny_flower", () -> new FlowerBlock(MobEffects.GLOWING, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> POTTED_SUNNY_FLOWER = BLOCKS.register("potted_sunny_flower", () -> new FlowerPotBlock(VSBlocks.SUNNY_FLOWER.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> GLOW_BERRY_BUSH = BLOCKS.register("glow_berry_bush", () -> new GlowBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> PAINTING_SAPLING = BLOCKS.register("painting_sapling", () -> new SaplingBlock(new OakTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> PAINTING_LOG = BLOCKS.register("painting_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PAINTING_WOOD = BLOCKS.register("painting_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_PAINTING_LOG = BLOCKS.register("stripped_painting_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_PAINTING_WOOD = BLOCKS.register("stripped_painting_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PAINTING_LEAVES = BLOCKS.register("painting_leaves", () -> new LeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> PAINTING_PLANKS = BLOCKS.register("painting_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PAINTING_SLAB = BLOCKS.register("painting_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> PAINTING_STAIRS = BLOCKS.register("painting_stairs", () -> new StairBlock(() -> PAINTING_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> PAINTING_FENCE = BLOCKS.register("painting_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> PAINTING_FENCE_GATE = BLOCKS.register("painting_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> PAINTING_PRESSURE_PLATE = BLOCKS.register("painting_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> PAINTING_BUTTON = BLOCKS.register("painting_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR = BLOCKS.register("painting_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<Block> PAINTING_DOOR = BLOCKS.register("painting_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<Block> ENDER_WART_BLOCK = BLOCKS.register("ender_wart_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_WART_BLOCK)));
    public static final RegistryObject<Block> ENDER_BOOKSHELF = BLOCKS.register("ender_bookshelf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> ENDER_PLANKS = BLOCKS.register("ender_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)));
    public static final RegistryObject<Block> ENDER_STEM = BLOCKS.register("ender_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_STEM)));
    public static final RegistryObject<Block> STRIPPED_ENDER_STEM = BLOCKS.register("stripped_ender_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_STEM)));
    public static final RegistryObject<Block> ENDER_HYPHAE = BLOCKS.register("ender_hyphae", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_HYPHAE)));
    public static final RegistryObject<Block> STRIPPED_ENDER_HYPHAE = BLOCKS.register("stripped_ender_hyphae", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_HYPHAE)));
    public static final RegistryObject<Block> ENDER_NYLIUM = BLOCKS.register("ender_nylium", () -> new EnderNyliumBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM)));
    public static final RegistryObject<Block> ENDER_STAIRS = BLOCKS.register("ender_stairs", () -> new StairBlock(() -> ENDER_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.WARPED_STAIRS)));
    public static final RegistryObject<Block> ENDER_SLAB = BLOCKS.register("ender_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_SLAB)));
    public static final RegistryObject<Block> ENDER_FENCE = BLOCKS.register("ender_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE)));
    public static final RegistryObject<Block> ENDER_FENCE_GATE = BLOCKS.register("ender_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE_GATE)));
    public static final RegistryObject<Block> ENDER_TRAPDOOR = BLOCKS.register("ender_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_TRAPDOOR)));
    public static final RegistryObject<Block> ENDER_DOOR = BLOCKS.register("ender_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_DOOR)));
    public static final RegistryObject<Block> ENDER_FUNGUS = BLOCKS.register("ender_fungus", () -> new EnderFungusBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FUNGUS), () -> TreeFeatures.CRIMSON_FUNGUS_PLANTED));
    public static final RegistryObject<Block> ENDER_ROOTS = BLOCKS.register("ender_roots", () -> new EnderRootsBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_ROOTS)));
    public static final RegistryObject<Block> ENDER_SPROUTS = BLOCKS.register("ender_sprouts", () -> new EnderSproutsBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_SPROUTS)));
    public static final RegistryObject<Block> WARPING_VINES_PLANT = BLOCKS.register("warping_vines_plant", () -> new WarpingVinesBlock(BlockBehaviour.Properties.copy(Blocks.WEEPING_VINES_PLANT)));
    public static final RegistryObject<Block> WARPING_VINES = BLOCKS.register("warping_vines", () -> new WarpingVinesTopBlock(BlockBehaviour.Properties.copy(Blocks.WEEPING_VINES)));
    public static final RegistryObject<Block> POTTED_ENDER_ROOTS = BLOCKS.register("potted_ender_roots", () -> new FlowerPotBlock(VSBlocks.ENDER_ROOTS.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_CRIMSON_ROOTS)));
    public static final RegistryObject<Block> POTTED_ENDER_FUNGUS = BLOCKS.register("potted_ender_fungus", () -> new FlowerPotBlock(VSBlocks.ENDER_FUNGUS.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_CRIMSON_FUNGUS)));
    public static final RegistryObject<Block> WANDERER_DOOR = BLOCKS.register("wanderer_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> GRAHAM_DOOR = BLOCKS.register("graham_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(WANDERER_DOOR.get())));
    public static final RegistryObject<Block> GLOW_BLACK_WOOL = BLOCKS.register("glow_black_wool", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> GLOW_BLACK_CARPET = BLOCKS.register("glow_black_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> DIAMOND_BELL = BLOCKS.register("diamond_bell", () -> new BellBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.DIAMOND).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.ANVIL)));
    public static final RegistryObject<Block> POTTED_TORCH = BLOCKS.register("potted_torch", () -> new FlowerPotBlock(Blocks.TORCH, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion().lightLevel((state) -> 15)));
    public static final RegistryObject<Block> POTTED_SOUL_TORCH = BLOCKS.register("potted_soul_torch", () -> new FlowerPotBlock(Blocks.SOUL_TORCH, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion().lightLevel((state) -> 10)));
    public static final RegistryObject<Block> ELDER_PRISMARINE = BLOCKS.register("elder_prismarine", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_STAIRS = BLOCKS.register("elder_prismarine_stairs", () -> new StairBlock(() -> ELDER_PRISMARINE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_SLAB = BLOCKS.register("elder_prismarine_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_WALL = BLOCKS.register("elder_prismarine_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_BRICKS = BLOCKS.register("elder_prismarine_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_BRICK_STAIRS = BLOCKS.register("elder_prismarine_brick_stairs", () -> new StairBlock(() -> ELDER_PRISMARINE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)));
    public static final RegistryObject<Block> ELDER_PRISMARINE_BRICK_SLAB = BLOCKS.register("elder_prismarine_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)));
    public static final RegistryObject<Block> DARK_ELDER_PRISMARINE = BLOCKS.register("dark_elder_prismarine", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistryObject<Block> DARK_ELDER_PRISMARINE_STAIRS = BLOCKS.register("dark_elder_prismarine_stairs", () -> new StairBlock(() -> DARK_ELDER_PRISMARINE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistryObject<Block> DARK_ELDER_PRISMARINE_SLAB = BLOCKS.register("dark_elder_prismarine_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistryObject<Block> ELDER_SEA_LANTERN = BLOCKS.register("elder_sea_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN)));
    public static final RegistryObject<Block> AZURE_BLUET_LEAVES = BLOCKS.register("azure_bluet_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> ALBAN_TRAPDOOR = BLOCKS.register("alban_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> AZTEC_TRAPDOOR = BLOCKS.register("aztec_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> AZTEC2_TRAPDOOR = BLOCKS.register("aztec2_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> BOMB_TRAPDOOR = BLOCKS.register("bomb_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> KEBAB_TRAPDOOR = BLOCKS.register("kebab_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> PLANT_TRAPDOOR = BLOCKS.register("plant_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> WASTELAND_TRAPDOOR = BLOCKS.register("wasteland_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> WITHER_BONE_BLOCK = BLOCKS.register("wither_bone_block", () -> new WitherBoneBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)));
    public static final RegistryObject<Block> POTTED_GRASS = BLOCKS.register("potted_grass", () -> new FlowerPotBlock(Blocks.GRASS, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> CHISELED_END_STONE_BRICKS = BLOCKS.register("chiseled_end_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));
    public static final RegistryObject<Block> CHISELED_PURPUR_BLOCK = BLOCKS.register("chiseled_purpur_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> END_STONE_PILLAR = BLOCKS.register("end_stone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));
    public static final RegistryObject<Block> MOSSY_END_STONE_BRICKS = BLOCKS.register("mossy_end_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));
    public static final RegistryObject<Block> MOSSY_NETHER_BRICKS = BLOCKS.register("mossy_nether_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static final RegistryObject<Block> MOSSY_PURPUR_BLOCK = BLOCKS.register("mossy_purpur_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> MOSSY_END_STONE_BRICK_STAIRS = BLOCKS.register("mossy_end_stone_brick_stairs", () -> new StairBlock(() -> MOSSY_END_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));
    public static final RegistryObject<Block> MOSSY_NETHER_BRICK_STAIRS = BLOCKS.register("mossy_nether_brick_stairs", () -> new StairBlock(() -> MOSSY_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static final RegistryObject<Block> MOSSY_PURPUR_STAIRS = BLOCKS.register("mossy_purpur_stairs", () -> new StairBlock(() -> MOSSY_PURPUR_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> MOSSY_END_STONE_BRICK_SLAB = BLOCKS.register("mossy_end_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));
    public static final RegistryObject<Block> MOSSY_NETHER_BRICK_SLAB = BLOCKS.register("mossy_nether_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static final RegistryObject<Block> MOSSY_PURPUR_SLAB = BLOCKS.register("mossy_purpur_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> NETHER_BRICK_PILLAR = BLOCKS.register("nether_brick_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static final RegistryObject<Block> SMOOTH_PURPUR = BLOCKS.register("smooth_purpur", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> SMOOTH_PURPUR_SLAB = BLOCKS.register("smooth_purpur_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> RED_NETHER_BRICK_FENCE = BLOCKS.register("red_nether_brick_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.RED_NETHER_BRICKS)));
    public static final RegistryObject<Block> QUARTZ_LADDER = BLOCKS.register("quartz_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER).noOcclusion().sound(VSSoundTypes.QUARTZ_LADDER)));
    public static final RegistryObject<Block> QUARTZ_GLASS = BLOCKS.register("quartz_glass", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static final RegistryObject<Block> QUARTZ_GLASS_PANE = BLOCKS.register("quartz_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE)));
    public static final RegistryObject<Block> QUARTZ_CHAIN = BLOCKS.register("quartz_chain", () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN)));
    public static final RegistryObject<Block> QUARTZ_BARS = BLOCKS.register("quartz_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BARS)));
    public static final RegistryObject<Block> EMERALD_CHAIN = BLOCKS.register("emerald_chain", () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN)));
    public static final RegistryObject<Block> DIAMOND_CHAIN = BLOCKS.register("diamond_chain", () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN)));
    public static final RegistryObject<Block> NETHERRACK_LEVER = BLOCKS.register("netherrack_lever", () -> new LeverBlock(BlockBehaviour.Properties.copy(Blocks.LEVER)));
    public static final RegistryObject<Block> END_STONE_LEVER = BLOCKS.register("end_stone_lever", () -> new LeverBlock(BlockBehaviour.Properties.copy(Blocks.LEVER)));
    public static final RegistryObject<Block> SOUL_BREWING_STAND = BLOCKS.register("soul_brewing_stand", () -> new BrewingStandBlock(BlockBehaviour.Properties.copy(Blocks.BREWING_STAND)));
    public static final RegistryObject<Block> CRIMSON_FARMLAND = BLOCKS.register("crimson_farmland", () -> new NetherFarmlandBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.CRIMSON_NYLIUM).randomTicks().strength(0.6F).sound(SoundType.NYLIUM).isViewBlocking(VSBlocks::needsPostProcessing).isSuffocating(VSBlocks::needsPostProcessing).randomTicks()));
    public static final RegistryObject<Block> WARPED_FARMLAND = BLOCKS.register("warped_farmland", () -> new NetherFarmlandBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.WARPED_NYLIUM).randomTicks().strength(0.6F).sound(SoundType.NYLIUM).isViewBlocking(VSBlocks::needsPostProcessing).isSuffocating(VSBlocks::needsPostProcessing).randomTicks()));
    public static final RegistryObject<Block> ENDER_FARMLAND = BLOCKS.register("ender_farmland", () -> new EndFarmlandBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).randomTicks().strength(3.0F, 9.0F).isViewBlocking(VSBlocks::needsPostProcessing).isSuffocating(VSBlocks::needsPostProcessing).randomTicks()));
    public static final RegistryObject<Block> ACACIA_TRAPDOOR_DOOR = BLOCKS.register("acacia_trapdoor_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> CRIMSON_TRAPDOOR_DOOR = BLOCKS.register("crimson_trapdoor_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> DARK_OAK_TRAPDOOR_DOOR = BLOCKS.register("dark_oak_trapdoor_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> OAK_TRAPDOOR_DOOR = BLOCKS.register("oak_trapdoor_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(PAINTING_PLANKS.get())));
    public static final RegistryObject<Block> SOUL_LAVA = BLOCKS.register("soul_lava_block", () -> new LiquidBlock(VSFluids.SOUL_LAVA, BlockBehaviour.Properties.of(Material.WATER).strength(100.0f).noDrops()));
    public static final RegistryObject<Block> MUSHROOM_STEW = BLOCKS.register("mushroom_stew_block", () -> new LiquidBlock(VSFluids.MUSHROOM_STEW, BlockBehaviour.Properties.of(Material.WATER).strength(100.0f).noDrops()));
    public static final RegistryObject<Block> CRIMSON_WHEAT = BLOCKS.register("crimson_wheat", () -> new CrimsonWheatBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> WARPED_POTATOES = BLOCKS.register("warped_potatoes", () -> new WarpedPotatoesBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> SOUL_CARROTS = BLOCKS.register("soul_carrots", () -> new SoulCarrotsBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_CRIMSON_WHEAT = BLOCKS.register("wild_crimson_wheat", () -> new WildCrimsonWheatBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_WARPED_POTATOES = BLOCKS.register("wild_warped_potatoes", () -> new WildWarpedPotatoesBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_SOUL_CARROTS = BLOCKS.register("wild_soul_carrots", () -> new WildSoulCarrotsBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.CROP)));
    //public static final RegistryObject<Block> SHORT_TIMED_BUTTON = blocks.register("short_timed_button", () -> new ShortTimedButtonBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE)));
    //public static final RegistryObject<Block> LONG_TIMED_BUTTON = blocks.register("long_timed_button", () -> new LongTimedButtonBlock(BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)));
    public static final RegistryObject<Block> CRYSTALLIZED_MAGMA_CREAM_ORE = BLOCKS.register("crystallized_magma_cream_ore", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> MIXER = BLOCKS.register("mixer", () -> new MixerMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    private static boolean needsPostProcessing(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }
}
