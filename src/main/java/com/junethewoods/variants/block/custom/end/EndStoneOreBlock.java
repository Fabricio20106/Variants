package com.junethewoods.variants.block.custom.end;

import com.junethewoods.variants.Variants;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

public class EndStoneOreBlock extends EndStoneBlock {
    private final int minXP;
    private final int maxXP;

    public EndStoneOreBlock(int minXP, int maxXP, Properties properties) {
        super(properties);
        this.minXP = minXP;
        this.maxXP = maxXP;
    }

    public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.spawnAfterBreak(state, world, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silkTouch) {
        return silkTouch == 0 ? MathHelper.nextInt(RANDOM, minXP, maxXP) : 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".ore_experience_drops", minXP, maxXP).withStyle(TextFormatting.GRAY));
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
