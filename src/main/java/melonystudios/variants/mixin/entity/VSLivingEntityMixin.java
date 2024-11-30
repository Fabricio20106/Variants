package melonystudios.variants.mixin.entity;

import melonystudios.variants.component.Consumable;
import melonystudios.variants.component.Equippable;
import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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
            cir.setReturnValue(configurableItem.getConsumeSound(stack, stack.getDrinkingSound()));
        }
    }

    @Inject(method = "getEatingSound", at = @At("HEAD"), cancellable = true)
    public void getEatingSound(ItemStack stack, CallbackInfoReturnable<SoundEvent> cir) {
        if (Consumable.validConsumableClass(stack.getItem())) {
            Consumable configurableItem = (Consumable) stack.getItem();
            cir.setReturnValue(configurableItem.getConsumeSound(stack, stack.getEatingSound()));
        }
    }

    @Inject(method = "playEquipSound", at = @At("HEAD"), cancellable = true)
    protected void playEquipSound(ItemStack stack, CallbackInfo ci) {
        ci.cancel();
        if (!stack.isEmpty()) {
            SoundEvent defaultSound = SoundEvents.ARMOR_EQUIP_GENERIC;
            Item item = stack.getItem();

            if (item instanceof ArmorItem) {
                defaultSound = ((ArmorItem) item).getMaterial().getEquipSound();
            } else if (item.is(VSItemTags.ELYTRA)) {
                defaultSound = SoundEvents.ARMOR_EQUIP_ELYTRA;
            }

            this.playSound(Equippable.getEquipSound(stack, defaultSound), 1, 1);
        }
    }
}
