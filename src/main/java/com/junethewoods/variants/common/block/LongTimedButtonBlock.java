package com.junethewoods.variants.common.block;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.ButtonBlock;

public class LongTimedButtonBlock extends ButtonBlock {
    public LongTimedButtonBlock(Properties properties) {
        super(false, properties);
    }

    //@Override
    //public int getPressDuration() {
    //    return 40;
    //}

    @Override
    protected SoundEvent getSound(boolean isClick) {
        return isClick ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }
}
