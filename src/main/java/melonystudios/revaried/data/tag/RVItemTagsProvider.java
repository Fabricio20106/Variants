package melonystudios.revaried.data.tag;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.tag.RVItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static melonystudios.revaried.item.RVItems.*;

public class RVItemTagsProvider extends ItemTagsProvider {
    public RVItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper fileHelper) {
        super(output, registries, blockTags, Revaried.MOD_ID, fileHelper);
    }

    @Override
    @NotNull
    public String getName() {
        return "Revaried - Item Tags";
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTags(HolderLookup.Provider provider) {
        // Revaried's tags
        this.tag(RVItemTags.SHULKER_SHELLS).add(Items.SHULKER_SHELL, WHITE_SHULKER_SHELL.get(), INNO_SHULKER_SHELL.get(), LIGHT_GRAY_SHULKER_SHELL.get(), GRAY_SHULKER_SHELL.get(),
                BLACK_SHULKER_SHELL.get(), BROWN_SHULKER_SHELL.get(), RED_SHULKER_SHELL.get(), ORANGE_SHULKER_SHELL.get(), YELLOW_SHULKER_SHELL.get(), LIME_SHULKER_SHELL.get(),
                GREEN_SHULKER_SHELL.get(), CYAN_SHULKER_SHELL.get(), LIGHT_BLUE_SHULKER_SHELL.get(), GLOW_BLACK_SHULKER_SHELL.get(), BLUE_SHULKER_SHELL.get(), PURPLE_SHULKER_SHELL.get(),
                MAGENTA_SHULKER_SHELL.get(), PINK_SHULKER_SHELL.get());

        // Common tags
        this.tag(Tags.Items.DYED_WHITE).add(WHITE_SHULKER_SHELL.get());
        this.tag(RVItemTags.DYED_INNO).add(INNO_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_LIGHT_GRAY).add(LIGHT_GRAY_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_GRAY).add(GRAY_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_BLACK).add(BLACK_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_BROWN).add(BROWN_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_RED).add(RED_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_ORANGE).add(ORANGE_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_YELLOW).add(YELLOW_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_LIME).add(LIME_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_GREEN).add(GREEN_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_CYAN).add(CYAN_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_LIGHT_BLUE).add(LIGHT_BLUE_SHULKER_SHELL.get());
        this.tag(RVItemTags.DYED_GLOW_BLACK).add(GLOW_BLACK_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_BLUE).add(BLUE_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_PURPLE).add(PURPLE_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_MAGENTA).add(MAGENTA_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED_PINK).add(PINK_SHULKER_SHELL.get());
        this.tag(Tags.Items.DYED).addTags(RVItemTags.DYED_INNO, RVItemTags.DYED_GLOW_BLACK);

        this.tag(Tags.Items.MUSIC_DISCS).add(MUSIC_DISC_DOG.get());

        // Minecraft tags
        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(MUSIC_DISC_DOG.get());
    }
}
