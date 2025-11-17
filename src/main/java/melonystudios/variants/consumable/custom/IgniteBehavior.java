package melonystudios.variants.consumable.custom;

import com.google.gson.JsonObject;
import melonystudios.variants.Variants;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.VSStyles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import java.util.List;

import static melonystudios.variants.util.NBTUtils.anyNumericOrIntDefault;

public class IgniteBehavior extends ConsumeBehavior {
    public static final int DEFAULT_TOOLTIP_COLOR = 0xE1A61E;
    public static final int SOUL_TOOLTIP_COLOR = 0x28CBC9;
    private final int ticksOnFire;
    private final int tooltipColor;

    public IgniteBehavior(int ticksOnFire, int tooltipColor) {
        this.ticksOnFire = ticksOnFire;
        this.tooltipColor = tooltipColor;
    }

    public IgniteBehavior(int ticksOnFire) {
        this(ticksOnFire, DEFAULT_TOOLTIP_COLOR);
    }

    public IgniteBehavior() {
        this(100);
    }

    public int ticksOnFire() {
        return this.ticksOnFire;
    }

    public int tooltipColor() {
        return this.tooltipColor;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (!world.isClientSide()) livEntity.setSecondsOnFire(this.ticksOnFire() / 20);
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        int ticksOnFire = anyNumericOrIntDefault("ticks_on_fire", propertiesTag, 100);
        int tooltipColor = MathHelper.clamp(anyNumericOrIntDefault("tooltip_color", propertiesTag, DEFAULT_TOOLTIP_COLOR), 0, 16777215);
        IgniteBehavior behavior = new IgniteBehavior(ticksOnFire, tooltipColor);
        behavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        if (this.ticksOnFire() != 100) properties.putInt("ticks_on_fire", this.ticksOnFire());
        if (this.tooltipColor() != DEFAULT_TOOLTIP_COLOR) properties.putInt("tooltip_color", this.tooltipColor());
        return properties;
    }

    @Override
    public JsonObject writeToJSON(CompoundNBT propertiesTag) {
        JsonObject properties = new JsonObject();
        if (this.ticksOnFire() != 100) properties.addProperty("ticks_on_fire", this.ticksOnFire());
        if (this.tooltipColor() != DEFAULT_TOOLTIP_COLOR) properties.addProperty("tooltip_color", this.tooltipColor());
        return properties;
    }

    @Override
    public List<ITextComponent> addToTooltip(ItemStack stack, @Nullable World world, ITooltipFlag flag) {
        List<ITextComponent> tooltip = super.addToTooltip(stack, world, flag);
        ITextComponent ablazeTooltip = new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".behavior.ignite.ablaze", StringUtils.formatTickDuration(this.ticksOnFire())).withStyle(VSStyles.getFromRGB(this.tooltipColor()));
        tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".food_effects.harmful_effect", ablazeTooltip).withStyle(TextFormatting.DARK_GRAY));
        return tooltip;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.IGNITE.get();
    }
}
