package com.junethewoods.variants.item.custom.tool;

import com.junethewoods.variants.sound.VSSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SpyglassItem extends Item {
    public SpyglassItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 1200;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.playSound(VSSounds.SPYGLASS_USE.get(), 1, 1);
        player.awardStat(Stats.ITEM_USED.get(this));
        return DrinkHelper.useDrink(world, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        this.stopUsing(livEntity);
        return stack;
    }

    @Override
    public void releaseUsing(ItemStack stack, World world, LivingEntity livEntity, int i) {
        this.stopUsing(livEntity);
    }

    private void stopUsing(LivingEntity livEntity) {
        livEntity.playSound(VSSounds.SPYGLASS_STOP_USING.get(), 1, 1);
    }
}
