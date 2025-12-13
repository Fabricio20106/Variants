package melonystudios.variants.mixin.item;

import melonystudios.variants.Variants;
import melonystudios.variants.util.VSStyles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpectralArrowItem;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(SpectralArrowItem.class)
public class RVSpectralArrowItemMixin extends Item {
    public RVSpectralArrowItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".food_effects.neutral_effect", new TranslationTextComponent("potion.withDuration",
                new TranslationTextComponent("effect.minecraft.glowing"), "0:10").withStyle(VSStyles.getFromRGB(Effects.GLOWING.getColor())))
                .withStyle(TextFormatting.DARK_GRAY));
    }
}
