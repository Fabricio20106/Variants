package melonystudios.revaried.data.loot;

import melonystudios.revaried.Revaried;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.stream.Collectors;

import static melonystudios.revaried.block.RVBlocks.*;

public class RVBlockLootSubProvider extends BlockLootSubProvider {
    public RVBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.dropOther(SOUL_LAVA_CAULDRON.get(), Items.CAULDRON);
    }

    @Override
    @Nonnull
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream().filter(block -> Revaried.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace())).collect(Collectors.toSet());
    }
}
