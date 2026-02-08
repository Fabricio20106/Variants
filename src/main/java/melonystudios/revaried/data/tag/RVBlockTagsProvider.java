package melonystudios.revaried.data.tag;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.tag.RVBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static melonystudios.revaried.block.RVBlocks.*;

public class RVBlockTagsProvider extends BlockTagsProvider {
    public RVBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper fileHelper) {
        super(output, registries, Revaried.MOD_ID, fileHelper);
    }

    @Override
    @NotNull
    public String getName() {
        return Revaried.generatorName("Block Tags");
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Revaried tags
        this.tag(RVBlockTags.PICKUPABLE_SPAWNERS).add(Blocks.SPAWNER);
        this.tag(RVBlockTags.SPAWNER_MINECARTS_CANNOT_REPLACE).addTag(BlockTags.FEATURES_CANNOT_REPLACE).add(Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK,
                Blocks.STRUCTURE_BLOCK, Blocks.JIGSAW, Blocks.MOVING_PISTON).remove(Blocks.CHEST);

        // Common tags
        this.tag(Tags.Blocks.VILLAGER_JOB_SITES).add(SOUL_LAVA_CAULDRON.get());

        // Minecraft tags
        this.tag(BlockTags.CAULDRONS).add(SOUL_LAVA_CAULDRON.get());
    }
}
