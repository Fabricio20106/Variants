package com.junethewoods.variants.mixin.entity;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class EntityTameItemMixins {
    @Mixin(CatEntity.class)
    public static class VSCatEntityMixin {
        @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
        private void isFood(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(stack.getItem().is(VSTags.Items.CATLIKE_TAME_ITEMS));
        }
    }

    @Mixin(OcelotEntity.class)
    public static class VSOcelotEntityMixin {
        @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
        private void isFood(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(stack.getItem().is(VSTags.Items.CATLIKE_TAME_ITEMS));
        }
    }
}
