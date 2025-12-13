package melonystudios.variants.mixin.item.nbt;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FilledMapItem.class)
public class RVFilledMapItemMixin extends Item {
    public RVFilledMapItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void addTagDisplayTooltip(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo callback) {
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
