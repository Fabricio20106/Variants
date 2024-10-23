package melonystudios.variants.item.custom;

import melonystudios.variants.util.ComponentUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VSItem extends Item {
    public VSItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return ComponentUtils.enchantmentGlintOverride(stack, super.isFoil(stack));
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return ComponentUtils.maxStackSize(stack, super.getItemStackLimit(stack));
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return ComponentUtils.maxDamage(stack, super.getMaxDamage(stack));
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return ComponentUtils.enchantable(stack, super.getItemEnchantability(stack));
    }

    @Override
    public int getEntityLifespan(ItemStack stack, World world) {
        return ComponentUtils.itemEntityLifespan(stack, world, super.getEntityLifespan(stack, world));
    }
}
