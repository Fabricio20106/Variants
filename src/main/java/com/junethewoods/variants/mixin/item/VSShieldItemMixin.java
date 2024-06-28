package com.junethewoods.variants.mixin.item;

import com.junethewoods.variants.config.VSConfigs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ShieldItem.class)
public class VSShieldItemMixin extends Item {
    public VSShieldItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo ci) {
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchamentment) {
        return VSConfigs.COMMON_CONFIGS.enchantableShields.get() ? super.canApplyAtEnchantingTable(stack, enchamentment) || enchamentment == Enchantments.UNBREAKING :
                super.canApplyAtEnchantingTable(stack, enchamentment);
    }

    @Override
    public int getEnchantmentValue() {
        return VSConfigs.COMMON_CONFIGS.enchantableShields.get() ? 15 : 0;
    }
}
