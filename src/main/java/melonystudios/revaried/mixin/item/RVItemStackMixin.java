package melonystudios.revaried.mixin.item;

import melonystudios.revaried.component.RVDataComponents;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class RVItemStackMixin implements DataComponentHolder {
    @Inject(method = "getUseAnimation", at = @At("HEAD"), cancellable = true)
    public void getUseAnimation(CallbackInfoReturnable<UseAnim> callback) {
        if (this.has(RVDataComponents.USE_ANIMATION)) callback.setReturnValue(this.get(RVDataComponents.USE_ANIMATION.get()));
    }
}
