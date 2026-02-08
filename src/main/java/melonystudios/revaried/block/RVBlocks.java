package melonystudios.revaried.block;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.block.custom.SoulLavaCauldronBlock;
import melonystudios.revaried.fluid.RVFluids;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RVBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Revaried.MOD_ID);

    public static final DeferredBlock<LiquidBlock> MUSHROOM_STEW = BLOCKS.register("mushroom_stew", () -> new LiquidBlock(RVFluids.MUSHROOM_STEW.get(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).replaceable().noCollission().strength(100).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
    public static final DeferredBlock<LiquidBlock> SOUL_LAVA = BLOCKS.register("soul_lava", () -> new LiquidBlock(RVFluids.SOUL_LAVA.get(), BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).replaceable().noCollission().strength(100).pushReaction(PushReaction.DESTROY).lightLevel(state -> 15).randomTicks().noLootTable().liquid().sound(SoundType.EMPTY)));
    public static final DeferredBlock<Block> SOUL_LAVA_CAULDRON = BLOCKS.register("soul_lava_cauldron", () -> new SoulLavaCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).lightLevel(state -> 15)));
}
