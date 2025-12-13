package melonystudios.variants.mixin.item.nbt;

import melonystudios.variants.Variants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSKeys;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LingeringPotionItem.class)
public class RVLingeringPotionItemMixin extends Item {
    public RVLingeringPotionItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void addTagDisplayTooltip(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo callback) {
        if (flag.isAdvanced() && stack.getTag() != null && Variants.revaried().settings().showTagsWithAlt) {
            boolean shouldHideTooltip = NBTUtils.shouldNotHideTooltip("hide_item_tags", stack);
            if (shouldHideTooltip && !VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt", VSKeys.getTranslation(VSKeys.SHOW_TAGS_KEY).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY));
            if (shouldHideTooltip && VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt", VSKeys.getTranslation(VSKeys.SHOW_TAGS_KEY).withStyle(TextFormatting.WHITE)).withStyle(TextFormatting.DARK_GRAY));
            if (shouldHideTooltip && VSKeys.isAltDown()) NBTUtils.addItemTagsTooltip(stack, tooltip, flag);
        }
    }
}
