package melonystudios.variants.item.custom.food;

import melonystudios.variants.effect.VSEffectInstance;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.custom.ApplyMobEffectsBehavior;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class StylisedPotItem extends DrinkableContainerItem {
    @Nullable
    private String compatMod;

    public StylisedPotItem(Properties properties, List<VSEffectInstance> instances) {
        this(new ApplyMobEffectsBehavior(instances), properties);
    }

    public StylisedPotItem(Properties properties, @Nullable String compatMod, List<VSEffectInstance> instances) {
        this(new ApplyMobEffectsBehavior(instances), properties);
        this.compatMod = compatMod;
    }

    public StylisedPotItem(StewBehavior behavior, Properties properties) {
        super(behavior, properties);
        this.populateBehavior = true;
    }

    @Override
    public ItemStack getDefaultUseRemainder() {
        return new ItemStack(VSItems.STYLISED_POT.get());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (this.compatMod != null && NBTUtils.shouldNotHideTooltip("hide_compat_mod", stack)) {
            tooltip.add(new TranslationTextComponent("tooltip.variants.compat_item_from", this.compatMod).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        }
    }
}
