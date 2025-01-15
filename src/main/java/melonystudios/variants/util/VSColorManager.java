package melonystudios.variants.util;

import melonystudios.variants.Variants;
import melonystudios.variants.block.VSBlocks;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.VSWeaponry;
import melonystudios.variants.item.custom.armor.DyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VSColorManager {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColorHandlers(final ColorHandlerEvent.Block event) {
        event.getBlockColors().register((state, reader, pos, tint) -> reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1, VSBlocks.GOLDEN_CAULDRON.get(), VSBlocks.QUARTZ_CAULDRON.get());

        //                                                                                                                                                                                                                                    |- Default color for leaf blocks.
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
        List<EffectInstance> effects = NBTUtils.getEffectsFromNBT(null, stack);
        int color = 3694022;
        if (effects == null) return color;

        if (ModList.get().isLoaded("melonylib")) {
            if (effects.size() == 1) {
                if (effects.get(0).getEffect() == Effects.MOVEMENT_SPEED) color = 0x33EBFF;
                if (effects.get(0).getEffect() == Effects.MOVEMENT_SLOWDOWN) color = 0x8BAFE0;
                if (effects.get(0).getEffect() == Effects.DAMAGE_BOOST) color = 0xFFC700;
                if (effects.get(0).getEffect() == Effects.HEAL) color = 0xF82423;
                if (effects.get(0).getEffect() == Effects.HARM) color = 0xA9656A;
                if (effects.get(0).getEffect() == Effects.JUMP) color = 0xFDFF84;
                if (effects.get(0).getEffect() == Effects.REGENERATION) color = 0xCD5CAB;
                if (effects.get(0).getEffect() == Effects.DAMAGE_RESISTANCE) color = 0x9146F0;
                if (effects.get(0).getEffect() == Effects.FIRE_RESISTANCE) color = 0xFF9900;
                if (effects.get(0).getEffect() == Effects.WATER_BREATHING) color = 0x98DAC0;
                if (effects.get(0).getEffect() == Effects.INVISIBILITY) color = 0xF6F6F6;
                if (effects.get(0).getEffect() == Effects.NIGHT_VISION) color = 0xC2FF66;
                if (effects.get(0).getEffect() == Effects.WEAKNESS) color = 0x484D48;
                if (effects.get(0).getEffect() == Effects.POISON) color = 0x87A363;
                if (effects.get(0).getEffect() == Effects.WITHER) color = 0x736156;
                if (effects.get(0).getEffect() == Effects.LUCK) color = 0x59C106;
                if (effects.get(0).getEffect() == Effects.SLOW_FALLING) color = 0xF3CFB9;
            } else if (effects.size() == 2) {
                if (effects.get(0).getEffect() == Effects.POISON && effects.get(1).getEffect() == Effects.BLINDNESS)
                    color = 0xC9DFDF;
            }
        } else {
            color = PotionUtils.getColor(effects);
        }

        return color;
    }
}
