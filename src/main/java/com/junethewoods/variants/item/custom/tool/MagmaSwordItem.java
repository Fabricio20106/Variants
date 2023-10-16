package com.junethewoods.variants.item.custom.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;

public class MagmaSwordItem extends SwordItem {
    public MagmaSwordItem(IItemTier material, int damage, float swingSpeed, Properties properties) {
        super(material, damage, swingSpeed, properties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livEntity = (LivingEntity) entity;
            if (!livEntity.isInvulnerableTo(DamageSource.IN_FIRE) || !livEntity.isInvulnerableTo(DamageSource.ON_FIRE)) {
                livEntity.setSecondsOnFire(10);
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }
}
