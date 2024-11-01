package melonystudios.variants.mixin.item;

import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(HorseArmorItem.class)
public class VSHorseArmorItemMixin extends Item {
    public VSHorseArmorItemMixin(Properties properties) {
        super(properties);
    }

    @Unique
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (VSConfigs.COMMON_CONFIGS.horseArmorArmorPointsOnTooltip.get() && NBTUtils.shouldNotHideTooltip("hide_horse_armor_armor_points", stack)) {
            HorseArmorItem horseArmor = ((HorseArmorItem) stack.getItem());

            tooltip.add(new StringTextComponent(""));
            tooltip.add(new TranslationTextComponent("tooltip.variants.horse_armor.when_on_horse").withStyle(TextFormatting.GRAY));
            tooltip.add(new TranslationTextComponent("tooltip.variants.horse_armor.armor_stats", horseArmor.getProtection()).withStyle(TextFormatting.BLUE));
        }
    }
}
