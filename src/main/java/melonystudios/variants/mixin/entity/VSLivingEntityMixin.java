package melonystudios.variants.mixin.entity;

import melonystudios.variants.item.custom.food.Consumable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class VSLivingEntityMixin extends Entity {
    public VSLivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "getDrinkingSound", at = @At("HEAD"), cancellable = true)
    protected void getDrinkingSound(ItemStack stack, CallbackInfoReturnable<SoundEvent> cir) {
        if (Consumable.validConsumableClass(stack.getItem())) {
            Consumable configurableItem = (Consumable) stack.getItem();
            cir.setReturnValue(configurableItem.getConsumeSound(stack, configurableItem.getDefaultConsumeSound()));
        }
    }

    @Inject(method = "getEatingSound", at = @At("HEAD"), cancellable = true)
    public void getEatingSound(ItemStack stack, CallbackInfoReturnable<SoundEvent> cir) {
        if (Consumable.validConsumableClass(stack.getItem())) {
            Consumable configurableItem = (Consumable) stack.getItem();
            cir.setReturnValue(configurableItem.getConsumeSound(stack, configurableItem.getDefaultConsumeSound()));
        }
    }
}
