package melonystudios.revaried.data.tag;

import melonystudios.reutilities.util.tag.ReItemTags;
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
        return Revaried.generatorName("Item Tags");
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

        this.tag(RVItemTags.BUCKET_SOUP_FOODS).add(MUSHROOM_STEW_BUCKET.get(), BEETROOT_SOUP_BUCKET.get(), RABBIT_STEW_BUCKET.get(), SUSPICIOUS_STEW_BUCKET.get(), MELTING_BEET_SOUP_BUCKET.get(),
                FUNGI_STEW_BUCKET.get(), END_FUNGI_STEW_BUCKET.get());
        this.tag(Tags.Items.FOODS).addTag(RVItemTags.BUCKET_SOUP_FOODS);
        this.tag(RVItemTags.MUSHROOM_STEW_BUCKETS).add(MUSHROOM_STEW_BUCKET.get());
        this.tag(RVItemTags.BEETROOT_SOUP_BUCKETS).add(BEETROOT_SOUP_BUCKET.get());
        this.tag(RVItemTags.RABBIT_STEW_BUCKETS).add(RABBIT_STEW_BUCKET.get());
        this.tag(RVItemTags.SUSPICIOUS_STEW_BUCKETS).add(SUSPICIOUS_STEW_BUCKET.get());
        this.tag(RVItemTags.MELTING_BEET_SOUP_BUCKETS).add(MELTING_BEET_SOUP_BUCKET.get());
        this.tag(RVItemTags.FUNGI_STEW_BUCKETS).add(FUNGI_STEW_BUCKET.get());
        this.tag(RVItemTags.END_FUNGI_STEW_BUCKETS).add(END_FUNGI_STEW_BUCKET.get());
        this.tag(RVItemTags.SOUL_LAVA_BUCKETS).add(SOUL_LAVA_BUCKET.get());
        this.tag(Tags.Items.BUCKETS).addTag(RVItemTags.MUSHROOM_STEW_BUCKETS).addTag(RVItemTags.BEETROOT_SOUP_BUCKETS).addTag(RVItemTags.RABBIT_STEW_BUCKETS).addTag(RVItemTags.SUSPICIOUS_STEW_BUCKETS)
                .addTag(RVItemTags.MELTING_BEET_SOUP_BUCKETS).addTag(RVItemTags.FUNGI_STEW_BUCKETS).addTag(RVItemTags.END_FUNGI_STEW_BUCKETS).addTag(RVItemTags.SOUL_LAVA_BUCKETS);

        this.tag(Tags.Items.MUSIC_DISCS).add(MUSIC_DISC_DOG.get());
        this.tag(ReItemTags.LOGOS).add(SHULKER_SPECTRUM_ICON.get());

        // Minecraft tags
        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(MUSIC_DISC_DOG.get());
    }
}
