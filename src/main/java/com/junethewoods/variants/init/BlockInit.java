package com.junethewoods.variants.init;

import com.junethewoods.variants.common.Variants;
import com.junethewoods.variants.blocks.GoldenCarrotBlock;
import com.junethewoods.variants.blocks.VariantOreBlock;
import com.junethewoods.variants.blocks.WarpedWartBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, Variants.mod_id);

    public static final RegistryObject<Block> potted_sugar_cane = blocks.register("potted_sugar_cane", () -> new FlowerPotBlock(Blocks.SUGAR_CANE, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
    public static final RegistryObject<Block> golden_carrots = blocks.register("golden_carrots", () -> new GoldenCarrotBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP)));
    public static final RegistryObject<Block> gold_cauldron = blocks.register("gold_cauldron", () -> new CauldronBlock(AbstractBlock.Properties.from(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> gold_beacon = blocks.register("gold_beacon", () -> new BeaconBlock(AbstractBlock.Properties.from(Blocks.BEACON)));
    public static final RegistryObject<Block> quartz_ore = blocks.register("quartz_ore", () -> new VariantOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).setRequiresTool().hardnessAndResistance(3.0F, 9.0F)));
    public static final RegistryObject<Block> end_quartz_ore = blocks.register("end_quartz_ore", () -> new VariantOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
    public static final RegistryObject<Block> raw_debris_block = blocks.register("raw_debris_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLACK).setRequiresTool().hardnessAndResistance(30.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS)));
    public static final RegistryObject<Block> glow_black_tulip = blocks.register("glow_black_tulip", () -> new FlowerBlock(Effects.GLOWING, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.CROP)));
    public static final RegistryObject<Block> warped_wart = blocks.register("warped_wart", () -> new WarpedWartBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.WARPED_WART).doesNotBlockMovement().tickRandomly().sound(SoundType.FUNGUS)));
    public static final RegistryObject<Block> potted_glow_black_tulip = blocks.register("potted_glow_black_tulip", () -> new FlowerPotBlock(glow_black_tulip.get(), AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
    public static final RegistryObject<Block> glow_berry_bush = blocks.register("glow_berry_bush", () -> new SweetBerryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> painting_planks = blocks.register("painting_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> painting_slab = blocks.register("painting_slab", () -> new SlabBlock(AbstractBlock.Properties.from(painting_planks.get())));
    public static final RegistryObject<Block> painting_stairs = blocks.register("painting_stairs", () -> new StairsBlock(painting_planks.get().getDefaultState(), AbstractBlock.Properties.from(painting_planks.get())));
    public static final RegistryObject<Block> painting_fence = blocks.register("painting_fence", () -> new FenceBlock(AbstractBlock.Properties.from(painting_planks.get())));
    public static final RegistryObject<Block> painting_fence_gate = blocks.register("painting_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(painting_planks.get())));
    public static final RegistryObject<Block> painting_pressure_plate = blocks.register("painting_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(painting_planks.get())));
    public static final RegistryObject<Block> painting_button = blocks.register("painting_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(painting_planks.get())));
    public static final RegistryObject<Block> painting_trapdoor = blocks.register("painting_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(painting_planks.get())));
    public static final RegistryObject<Block> painting_door = blocks.register("painting_door", () -> new DoorBlock(AbstractBlock.Properties.from(painting_planks.get())));
    public static final RegistryObject<Block> wanderer_door = blocks.register("wanderer_door", () -> new DoorBlock(AbstractBlock.Properties.from(painting_planks.get())));
    public static final RegistryObject<Block> graham_door = blocks.register("graham_door", () -> new DoorBlock(AbstractBlock.Properties.from(wanderer_door.get())));

}
