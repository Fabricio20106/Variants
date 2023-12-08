package com.junethewoods.variants.item.custom.tool;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class MagmaSwordItem extends SwordItem {
    public MagmaSwordItem(Tier material, int damage, float swingSpeed, Properties properties) {
        super(material, damage, swingSpeed, properties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livEntity = (LivingEntity) entity;
            if (!livEntity.isInvulnerableTo(player.level().damageSources().inFire()) || !livEntity.isInvulnerableTo(player.level().damageSources().onFire())) {
                livEntity.setSecondsOnFire(10);
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }
}
