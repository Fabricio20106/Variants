package com.fabricio.variants.init;

import com.fabricio.variants.Variants;
import com.fabricio.variants.blocks.GoldenCarrotBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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

    public static final RegistryObject<Block> potted_sugar_cane = blocks.register("potted_sugar_cane", () -> new FlowerPotBlock(Blocks.SUGAR_CANE, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> golden_carrots = blocks.register("golden_carrots", () -> new GoldenCarrotBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> iForgeRegistry = event.getRegistry();

        // for each block we registered above...
        blocks.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
            // make an item properties object that puts it in your creative tab
            final Item.Properties properties = new Item.Properties().tab(VariantsTab.variant);

            // make a block item that places the block
            final BlockItem blockItem = new BlockItem(block, properties);

            // register the block item with the same name as the block
            blockItem.setRegistryName(block.getRegistryName());
            iForgeRegistry.register(blockItem);
        });
    }
}
