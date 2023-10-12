package com.junethewoods.variants.block;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.custom.GoldenCarrotsBlock;
import com.junethewoods.variants.block.custom.VSOreBlock;
import com.junethewoods.variants.block.custom.WarpedWartBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Variants.MOD_ID);

    public static final RegistryObject<Block> POTTED_SUGAR_CANE = BLOCKS.register("potted_sugar_cane", () -> new FlowerPotBlock(Blocks.SUGAR_CANE, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> GOLDEN_CARROTS = BLOCKS.register("golden_carrots", () -> new GoldenCarrotsBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> GOLDEN_CAULDRON = BLOCKS.register("gold_cauldron", () -> new CauldronBlock(AbstractBlock.Properties.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> GOLDEN_BEACON = BLOCKS.register("gold_beacon", () -> new BeaconBlock(AbstractBlock.Properties.copy(Blocks.BEACON)));
    public static final RegistryObject<Block> QUARTZ_ORE = BLOCKS.register("quartz_ore", () -> new VSOreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3f, 9f)));
    public static final RegistryObject<Block> END_QUARTZ_ORE = BLOCKS.register("end_quartz_ore", () -> new VSOreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(3f, 3f)));
    public static final RegistryObject<Block> RAW_DEBRIS_BLOCK = BLOCKS.register("raw_debris_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(30f, 1200f).sound(SoundType.ANCIENT_DEBRIS)));
    public static final RegistryObject<Block> GLOW_BLACK_TULIP = BLOCKS.register("glow_black_tulip", () -> new FlowerBlock(Effects.GLOWING, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> WARPED_WART = BLOCKS.register("warped_wart", () -> new WarpedWartBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.WARPED_WART_BLOCK).noCollission().randomTicks().sound(SoundType.FUNGUS)));
    public static final RegistryObject<Block> POTTED_GLOW_BLACK_TULIP = BLOCKS.register("potted_glow_black_tulip", () -> new FlowerPotBlock(VSBlocks.GLOW_BLACK_TULIP.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> GLOW_BERRY_BUSH = BLOCKS.register("glow_berry_bush", () -> new SweetBerryBushBlock(AbstractBlock.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> PAINTING_PLANKS = BLOCKS.register("painting_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PAINTING_STAIRS = BLOCKS.register("painting_stairs", () -> new StairsBlock(VSBlocks.PAINTING_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> PAINTING_SLAB = BLOCKS.register("painting_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> PAINTING_FENCE = BLOCKS.register("painting_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> PAINTING_FENCE_GATE = BLOCKS.register("painting_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(Blocks.OAK_FENCE_GATE)));
    public static final RegistryObject<Block> PAINTING_PRESSURE_PLATE = BLOCKS.register("painting_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final RegistryObject<Block> PAINTING_BUTTON = BLOCKS.register("painting_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(Blocks.OAK_BUTTON)));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR = BLOCKS.register("painting_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PAINTING_DOOR = BLOCKS.register("painting_door", () -> new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<Block> WANDERER_DOOR = BLOCKS.register("wanderer_door", () -> new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
}
