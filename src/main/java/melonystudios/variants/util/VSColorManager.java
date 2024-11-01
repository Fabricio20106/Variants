package melonystudios.variants.util;

import melonystudios.variants.Variants;
import melonystudios.variants.block.VSBlocks;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.VSWeaponry;
import melonystudios.variants.item.custom.armor.DyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VSColorManager {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColorHandlers(final ColorHandlerEvent.Block event) {
        event.getBlockColors().register((state, reader, pos, tint) -> reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1, VSBlocks.GOLDEN_CAULDRON.get(), VSBlocks.QUARTZ_CAULDRON.get());

        //                                                                                                                                                                                                                                                    |- Default color for leaf blocks.
        event.getBlockColors().register((state, reader, pos, tint) -> reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : 0x48B518, VSBlocks.PAINTING_LEAVES.get());

        event.getBlockColors().register((state, reader, pos, tint) -> reader != null && pos != null ? BiomeColors.getAverageGrassColor(reader, pos) : -1, VSBlocks.POTTED_GRASS.get(), VSBlocks.POTTED_SUGAR_CANE.get());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColorHandlers(final ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, color) -> GrassColors.get(0.5D, 1), VSItems.POTTED_GRASS.get(), VSItems.POTTED_SUGAR_CANE.get(), VSItems.PAINTING_LEAVES.get());

        event.getItemColors().register((stack, color) -> {
            if (stack.getTag() != null && stack.getTag().contains("armor_design", Constants.TagTypes.ANY_NUMERIC)) {
                return -1;
            } else {
                return color > 0 ? -1 : ((DyeableArmorItem) stack.getItem()).getColor(stack);
            }
        }, VSWeaponry.WOOL_SWEATER.get());
        event.getItemColors().register((stack, color) -> color != 0 ? -1 : getColor(stack), VSItems.STAINED_POTION.get());
    }

    private static int getColor(ItemStack stack) {
        int color = 3694022;
        List<EffectInstance> effects = NBTUtils.getEffectsFromNBT(null, stack);
        if (effects != null) color = PotionUtils.getColor(effects);
        return color;
    }
}
