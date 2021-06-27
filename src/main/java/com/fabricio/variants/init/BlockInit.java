package com.fabricio.variants.init;

import com.fabricio.variants.Variants;
import com.fabricio.variants.blocks.GoldenCarrotBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class BlockInit {
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, Variants.mod_id);

    public static final RegistryObject<Block> potted_sugar_cane = blocks.register("potted_sugar_cane", () -> new FlowerPotBlock(Blocks.SUGAR_CANE, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
    public static final RegistryObject<Block> golden_carrots = blocks.register("golden_carrots", () -> new GoldenCarrotBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP)));
    public static final RegistryObject<Block> gold_cauldron = blocks.register("gold_cauldron", () -> new CauldronBlock(AbstractBlock.Properties.from(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> gold_beacon = blocks.register("gold_beacon", () -> new BeaconBlock(AbstractBlock.Properties.from(Blocks.BEACON)));
    public static final RegistryObject<Block> quartz_ore = blocks.register("quartz_ore", () -> new OreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
    public static final RegistryObject<Block> raw_debris_block = blocks.register("raw_debris_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLACK).setRequiresTool().hardnessAndResistance(30.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS)));
    public static final RegistryObject<Block> glow_black_tulip = blocks.register("glow_black_tulip", () -> new FlowerBlock(Effects.GLOWING, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.CROP)));
}
