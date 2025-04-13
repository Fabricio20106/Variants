package melonystudios.variants.block;

import net.minecraft.block.Block;
import net.minecraft.state.IntegerProperty;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class RVModdedBlocks {
    public static final IntegerProperty WATER_LEVEL = IntegerProperty.create("level", 1, 3);
    @ObjectHolder("themato:water_cauldron")
    public static final Block WATER_CAULDRON = null;
    @ObjectHolder("themato:cauldron")
    public static final Block CAULDRON = null;

    public static boolean isPresent(Block block) {
        return block != null && ForgeRegistries.BLOCKS.containsValue(block);
    }
}
