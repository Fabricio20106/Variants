package melonystudios.variants.mixin.entity;

import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatEntity.class)
public class RVCatEntityMixin {
    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    private void isFood(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {
        callback.setReturnValue(stack.getItem().is(VSItemTags.CATLIKE_TAME_ITEMS));
    }
}
