package melonystudios.variants.screen.button;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class NoticeBooleanOption extends BooleanOption {
    @Nullable
    private final ITextComponent tooltipText;

    public NoticeBooleanOption(String translation, Predicate<GameSettings> getter, BiConsumer<GameSettings, Boolean> setter) {
        super(translation, getter, setter);
        this.tooltipText = null;
    }

    public NoticeBooleanOption(String translation, @Nullable ITextComponent tooltipText, Predicate<GameSettings> getter, BiConsumer<GameSettings, Boolean> setter) {
        super(translation, tooltipText, getter, setter);
        this.tooltipText = tooltipText;
    }

    @Override
    @Nonnull
    public Widget createButton(GameSettings options, int x, int y, int width) {
        if (this.tooltipText != null) this.setTooltip(Minecraft.getInstance().font.split(this.tooltipText, 200));

        return new NoticeOptionButton(x, y, width, 20, this, this.getMessage(options), button -> {
            this.toggle(options);
            button.setMessage(this.getMessage(options));
        });
    }
}
