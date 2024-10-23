package melonystudios.variants.item.custom.tool;

import melonystudios.variants.dispenser.DebugArrowDispenseBehavior;
import melonystudios.variants.entity.custom.DebugArrowEntity;
import melonystudios.variants.item.VSWeaponry;
import melonystudios.variants.util.ComponentUtils;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class DebugArrowItem extends ArrowItem {
    public DebugArrowItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new DebugArrowDispenseBehavior());
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return ComponentUtils.enchantmentGlintOverride(stack, true);
    }

    @Override
    @Nonnull
    public ItemStack getDefaultInstance() {
        ItemStack arrowStack = new ItemStack(VSWeaponry.DEBUG_ARROW.get());
        CompoundNBT tag = arrowStack.getOrCreateTag();
        tag.put("debug_arrow_state", new CompoundNBT());
        return arrowStack;
    }

    @Override
    @Nonnull
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        DebugArrowEntity arrowEntity = new DebugArrowEntity(world, shooter);
        arrowEntity.setPropertyTag(stack.getOrCreateTag().getCompound("debug_arrow_state"));
        return arrowEntity;
    }
}
