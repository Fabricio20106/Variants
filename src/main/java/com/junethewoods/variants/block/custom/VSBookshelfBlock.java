package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;
import java.util.List;

public class VSBookshelfBlock extends Block {
    private final int enchantingPower;

    public VSBookshelfBlock(int enchantingPower, Properties properties) {
        super(properties);
        this.enchantingPower = enchantingPower;
    }

    public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
        return state.is(VSTags.Blocks.BOOKSHELVES) ? this.enchantingPower : 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".bookshelf_enchanting_power", enchantingPower));
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
