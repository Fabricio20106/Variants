package melonystudios.revaried.misc;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.block.RVBlocks;
import melonystudios.revaried.item.RVItems;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;

public class RVCauldronInteractions {
    public static final String SOUL_LAVA_NAME = Revaried.revaried("soul_lava").toString();
    public static final CauldronInteraction.InteractionMap SOUL_LAVA = CauldronInteraction.newInteractionMap(SOUL_LAVA_NAME);

    public static final CauldronInteraction FILL_SOUL_LAVA = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.emptyBucket(world, pos, player, hand, stack, RVBlocks.SOUL_LAVA_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY_LAVA);
    public static final CauldronInteraction EMPTY_SOUL_LAVA = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, RVItems.SOUL_LAVA_BUCKET.toStack(), state1 -> true, SoundEvents.BUCKET_FILL_LAVA);

    public static void register() {
        for (CauldronInteraction.InteractionMap map : CauldronInteraction.INTERACTIONS.values()) {
            map.map().put(RVItems.SOUL_LAVA_BUCKET.get(), FILL_SOUL_LAVA); // vanilla allows you to override filled cauldrons... weird ~isa 08-02-26
        }

        Map<Item, CauldronInteraction> soulLava = SOUL_LAVA.map();
        soulLava.put(Items.BUCKET, EMPTY_SOUL_LAVA);
    }
}
