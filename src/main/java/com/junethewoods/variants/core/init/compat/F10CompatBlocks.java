package com.junethewoods.variants.core.init.compat;

import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.init.VSBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class F10CompatBlocks {
    public static final DeferredRegister<Block> F10PACK_ITEMS = DeferredRegister.create(ForgeRegistries.BLOCKS, Variants.MOD_ID);

    public static final RegistryObject<Block> FIRST_DOOR = F10PACK_ITEMS.register("first_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(VSBlocks.WANDERER_DOOR.get())));
    public static final RegistryObject<Block> PLAIN_BIRCH_BOOKSHELF = F10PACK_ITEMS.register("plain_birch_bookshelf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
}
