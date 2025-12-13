package melonystudios.variants.mixin.entity;

import melonystudios.variants.component.Equippable;
import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class RVMobEntityMixin extends LivingEntity {
    public RVMobEntityMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "getEquipmentSlotForItem", at = @At("HEAD"), cancellable = true)
    private static void getEquipmentSlotForItem(ItemStack stack, CallbackInfoReturnable<EquipmentSlotType> callback) {
        callback.cancel();

        EquipmentSlotType defaultSlotType = EquipmentSlotType.MAINHAND;
        Item item = stack.getItem();

        if (item == Items.CARVED_PUMPKIN || ((item instanceof BlockItem && ((BlockItem) item).getBlock() instanceof AbstractSkullBlock))) {
            defaultSlotType = EquipmentSlotType.HEAD;
        } else if (item instanceof ArmorItem) {
            defaultSlotType = ((ArmorItem) item).getSlot();
        } else if (item.is(VSItemTags.ELYTRA)) {
            defaultSlotType = EquipmentSlotType.CHEST;
        } else if (item.is(VSItemTags.SHIELDS) || (stack.isShield(null) && item != Items.SHIELD)) {
            defaultSlotType = EquipmentSlotType.OFFHAND;
        }

        EquipmentSlotType forgeSlotType = stack.getEquipmentSlot();
        if (forgeSlotType != null) defaultSlotType = forgeSlotType;

        callback.setReturnValue(Equippable.getSlot(stack, defaultSlotType));
    }
}
