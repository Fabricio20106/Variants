package com.junethewoods.variants.common.register;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.custom.GoldenCarrotBlock;
import com.junethewoods.variants.block.custom.VSOreBlock;
import com.junethewoods.variants.block.custom.WarpedWartBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, Variants.mod_id);

    public static final RegistryObject<Block> potted_sugar_cane = blocks.register("potted_sugar_cane", () -> new FlowerPotBlock(Blocks.SUGAR_CANE, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> golden_carrots = blocks.register("golden_carrots", () -> new GoldenCarrotBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> gold_cauldron = blocks.register("gold_cauldron", () -> new CauldronBlock(AbstractBlock.Properties.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> gold_beacon = blocks.register("gold_beacon", () -> new BeaconBlock(AbstractBlock.Properties.copy(Blocks.BEACON)));
    public static final RegistryObject<Block> quartz_ore = blocks.register("quartz_ore", () -> new VSOreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 9.0F)));
    public static final RegistryObject<Block> end_quartz_ore = blocks.register("end_quartz_ore", () -> new VSOreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> raw_debris_block = blocks.register("raw_debris_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(30.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS)));
    public static final RegistryObject<Block> glow_black_tulip = blocks.register("glow_black_tulip", () -> new FlowerBlock(Effects.GLOWING, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> warped_wart = blocks.register("warped_wart", () -> new WarpedWartBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.WARPED_WART_BLOCK).noCollission().randomTicks().sound(SoundType.FUNGUS)));
    public static final RegistryObject<Block> potted_glow_black_tulip = blocks.register("potted_glow_black_tulip", () -> new FlowerPotBlock(glow_black_tulip.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> glow_berry_bush = blocks.register("glow_berry_bush", () -> new SweetBerryBushBlock(AbstractBlock.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> painting_planks = blocks.register("painting_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> painting_slab = blocks.register("painting_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(painting_planks.get())));
    public static final RegistryObject<Block> painting_stairs = blocks.register("painting_stairs", () -> new StairsBlock(painting_planks.get().defaultBlockState(), AbstractBlock.Properties.copy(painting_planks.get())));
    public static final RegistryObject<Block> painting_fence = blocks.register("painting_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(painting_planks.get())));
    public static final RegistryObject<Block> painting_fence_gate = blocks.register("painting_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(painting_planks.get())));
    public static final RegistryObject<Block> painting_pressure_plate = blocks.register("painting_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(painting_planks.get())));
    public static final RegistryObject<Block> painting_button = blocks.register("painting_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(painting_planks.get())));
    public static final RegistryObject<Block> painting_trapdoor = blocks.register("painting_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(painting_planks.get())));
    public static final RegistryObject<Block> painting_door = blocks.register("painting_door", () -> new DoorBlock(AbstractBlock.Properties.copy(painting_planks.get())));
    public static final RegistryObject<Block> wanderer_door = blocks.register("wanderer_door", () -> new DoorBlock(AbstractBlock.Properties.copy(painting_planks.get())));

}
