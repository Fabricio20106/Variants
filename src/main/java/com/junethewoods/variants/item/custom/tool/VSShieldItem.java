package com.junethewoods.variants.item.custom.tool;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

import javax.annotation.Nullable;

public class VSShieldItem extends ShieldItem {
    public VSShieldItem(Properties properties) {
        super(properties);
    }

    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return stack.is(VSTags.Items.SHIELDS);
    }
}
