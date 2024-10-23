package melonystudios.variants.block.custom;

import melonystudios.variants.Variants;
import melonystudios.variants.util.VSStyles;
import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
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
        return state.is(VSBlockTags.BOOKSHELVES) ? this.enchantingPower : 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (flag.isAdvanced()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".bookshelf_enchanting_power", new StringTextComponent("" + this.enchantingPower).withStyle(VSStyles.EXPERIENCE))
                .withStyle(TextFormatting.GRAY));
    }
}
