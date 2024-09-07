package melonystudios.variants.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface Equippable extends IVanishable {
    default ActionResult<ItemStack> swapWithEquipmentSlot(Item item, World world, PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        EquipmentSlotType slot = MobEntity.getEquipmentSlotForItem(handStack);
        ItemStack slotStack = player.getItemBySlot(slot);
        if ((!EnchantmentHelper.hasBindingCurse(slotStack) || player.isCreative()) && !ItemStack.matches(handStack, slotStack)) {
            if (!world.isClientSide) player.awardStat(Stats.ITEM_USED.get(item));
            ItemStack stack1 = slotStack.isEmpty() ? handStack : copyAndClear(slotStack);
            ItemStack copyHandStack = copyAndClear(handStack);
            player.setItemSlot(slot, copyHandStack);
            return ActionResult.sidedSuccess(stack1, world.isClientSide);
        } else {
            return ActionResult.fail(handStack);
        }
    }

    default ItemStack copyAndClear(ItemStack stack) {
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            ItemStack copyStack = stack.copy();
            stack.setCount(0);
            return copyStack;
        }
    }
}
