package com.junethewoods.variants.item.custom.tool;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.tags.ITag;

import javax.annotation.Nullable;

public class VSShieldItem extends ShieldItem {
    private final ITag.INamedTag<Item> repairTag;

    public VSShieldItem(ITag.INamedTag<Item> repairTag, Properties properties) {
        super(properties);
        this.repairTag = repairTag;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack stack1) {
        return stack1.getItem().is(this.repairTag);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return stack.getItem().is(VSTags.Items.SHIELDS);
    }
}
