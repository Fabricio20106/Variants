package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.custom.DefaultStewBehavior;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SophiePotionItem extends TagConfigurableFoodItem {
    private final String compatMod;

    public SophiePotionItem(Properties properties, String compatMod) {
        super(true, new DefaultStewBehavior(), properties);
        this.compatMod = compatMod;
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return getConsumeAnimation(stack, UseAction.DRINK);
    }

    @Override
    public SoundEvent getDefaultConsumeSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (NBTUtils.shouldNotHideTooltip("hide_compat_mod", stack)) tooltip.add(new TranslationTextComponent("tooltip.variants.compat_item_from", compatMod).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
    }
}
