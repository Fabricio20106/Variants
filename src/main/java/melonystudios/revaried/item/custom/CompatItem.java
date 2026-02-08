package melonystudios.revaried.item.custom;

import melonystudios.reutilities.api.ReAPI;
import melonystudios.revaried.Revaried;
import melonystudios.revaried.util.ModTarget;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class CompatItem extends Item {
    private final ModTarget mod;

    public CompatItem(ModTarget mod, Properties properties) {
        super(properties);
        this.mod = mod;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        if (ReAPI.shouldDisplay(stack, Revaried.revaried("compat_mod"))) {
            tooltip.add(Component.translatable("tooltip.revaried.compat_item_from", this.mod.getDisplayName()).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }
}
