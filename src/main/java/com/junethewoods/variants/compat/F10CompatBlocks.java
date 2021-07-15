package com.junethewoods.variants.compat;

import com.junethewoods.variants.common.Variants;
import com.junethewoods.variants.init.BlockInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class F10CompatBlocks {
    public static final DeferredRegister<Block> fabricio2010packCompat = DeferredRegister.create(ForgeRegistries.BLOCKS, Variants.mod_id);

    public static final RegistryObject<Block> first_door = fabricio2010packCompat.register("first_door",
            () -> new DoorBlock(AbstractBlock.Properties.from(BlockInit.wanderer_door.get())));
    public static final RegistryObject<Block> plain_birch_bookshelf = fabricio2010packCompat.register("plain_birch_bookshelf",
            () -> new Block(AbstractBlock.Properties.from(Blocks.BOOKSHELF)));
}
