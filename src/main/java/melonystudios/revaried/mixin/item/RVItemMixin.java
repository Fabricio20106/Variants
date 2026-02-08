package melonystudios.revaried.mixin.item;

import melonystudios.revaried.component.RVDataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public abstract class RVItemMixin implements IItemExtension {
    @Unique
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.has(RVDataComponents.USE_REMAINDER) || IItemExtension.super.hasCraftingRemainingItem(stack);
    }

    @Unique
    @Override
    @NotNull
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        if (stack.has(RVDataComponents.USE_REMAINDER)) return stack.get(RVDataComponents.USE_REMAINDER).stack();
        return IItemExtension.super.getCraftingRemainingItem(stack);
    }
}
