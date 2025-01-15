package melonystudios.variants.consumable.custom;

import melonystudios.variants.Variants;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.VSStyles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import java.util.List;

import static melonystudios.variants.util.NBTUtils.anyNumericOrIntDefault;

public class IgniteBehavior extends ConsumeBehavior {
    private final int ticksOnFire;

    public IgniteBehavior(int ticksOnFire) {
        this.ticksOnFire = ticksOnFire;
    }

    public IgniteBehavior() {
        this(100);
    }

    public int ticksOnFire() {
        return this.ticksOnFire;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (!world.isClientSide) livEntity.setSecondsOnFire(this.ticksOnFire / 20);
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        IgniteBehavior behavior = new IgniteBehavior(anyNumericOrIntDefault("ticks_on_fire", propertiesTag, 100));
        behavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        properties.putInt("ticks_on_fire", this.ticksOnFire);
        return properties;
    }

    @Override
    public List<ITextComponent> addToTooltip(ItemStack stack, @Nullable World world, ITooltipFlag flag) {
        List<ITextComponent> tooltip = super.addToTooltip(stack, world, flag);
        tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".food_effects.harmful_effect", new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".behavior.ignite.ablaze",
                StringUtils.formatTickDuration(this.ticksOnFire)).withStyle(VSStyles.getFromRGB(0xE1A61E))).withStyle(TextFormatting.DARK_GRAY));
        return tooltip;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.IGNITE.get();
    }
}
