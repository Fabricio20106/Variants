package melonystudios.variants.mixin.item.nbt;

import melonystudios.variants.component.Consumable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(CrossbowItem.class)
public class VSCrossbowItemMixin extends Item implements Consumable {
    public VSCrossbowItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText", at = @At("HEAD"))
    @OnlyIn(Dist.CLIENT)
    private void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo ci) {
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Inject(method = "getUseAnimation", at = @At("HEAD"), cancellable = true)
    @OnlyIn(Dist.CLIENT)
    private void getUseAnimation(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
        cir.setReturnValue(getConsumeAnimation(stack, UseAction.CROSSBOW));
    }

    @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
    public void getUseDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(getConsumeTicks(stack, CrossbowItem.getChargeDuration(stack)));
    }
}
