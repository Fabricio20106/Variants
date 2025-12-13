package melonystudios.variants.screen.button;

import net.minecraft.client.AbstractOption;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

// copied from Mellow UI 5.0.0-beta3 ~isa 12-12-25
public class SeparatorOption extends AbstractOption {
    private final ITextComponent captionComponent;

    public SeparatorOption(ITextComponent captionComponent) {
        super(captionComponent.getString());
        this.captionComponent = captionComponent;
    }

    @Override
    @Nonnull
    protected ITextComponent getCaption() {
        return this.captionComponent;
    }

    @Nonnull
    public Widget createButton(GameSettings options, int x, int y, int width) {
        SeparatorWidget widget = new SeparatorWidget(x, y, width, 20, this.getCaption());
        if (this.captionComponent.getStyle().getColor() != null) widget.setFGColor(this.captionComponent.getStyle().getColor().getValue());
        return widget;
    }
}
