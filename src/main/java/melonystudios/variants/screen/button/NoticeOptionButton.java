package melonystudios.variants.screen.button;

import com.google.common.collect.Lists;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.AbstractOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.OptionButton;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class NoticeOptionButton extends OptionButton {
    private boolean defaultValue;
    private boolean changed;

    public NoticeOptionButton(int x, int y, int width, int height, AbstractOption option, ITextComponent title, IPressable whenPressed) {
        super(x, y, width, height, option, title, whenPressed);
        this.defaultValue = option instanceof BooleanOption && ((BooleanOption) option).get(Minecraft.getInstance().options);
    }

    @Override
    public int getFGColor() {
        return this.changed() ? VSUtils.REVARIED_COLOR_STYLE.getColor().getValue() : super.getFGColor();
    }

    @Override
    @Nonnull
    public Optional<List<IReorderingProcessor>> getTooltip() {
        Optional<List<IReorderingProcessor>> defaultLines = super.getTooltip();
        List<IReorderingProcessor> tooltipLines = Lists.newArrayList();
        if (this.changed()) tooltipLines.addAll(Minecraft.getInstance().font.split(VSUtils.RESTART_REQUIRED, 200));
        defaultLines.ifPresent(tooltipLines::addAll);
        return Optional.of(tooltipLines);
    }

    @Override
    public void onPress() {
        super.onPress();
        boolean currentValue = this.getOption() instanceof BooleanOption && ((BooleanOption) this.getOption()).get(Minecraft.getInstance().options);
        this.setChanged(this.defaultValue != currentValue);
    }

    public boolean changed() {
        return this.changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
