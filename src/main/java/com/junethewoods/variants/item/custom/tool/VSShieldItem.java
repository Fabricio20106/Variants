package com.junethewoods.variants.item.custom.tool;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

import javax.annotation.Nullable;

public class VSShieldItem extends ShieldItem {
    public VSShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return stack.getItem().is(VSTags.Items.SHIELDS);
    }
}
