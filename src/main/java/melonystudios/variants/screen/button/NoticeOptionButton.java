package melonystudios.variants.screen.button;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.AbstractOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.OptionButton;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

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

    @Override
    public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer font = minecraft.font;
        minecraft.getTextureManager().bind(WIDGETS_LOCATION);
        GL11.glColor4f(1, 1, 1, this.alpha);
        int yImage = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(stack, this.x, this.y, 0, 46 + yImage * 20, this.width / 2, this.height);
        this.blit(stack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + yImage * 20, this.width / 2, this.height);
        this.renderBg(stack, minecraft, mouseX, mouseY);
        int textColor = this.changed() ? 0xFFC55F : this.getFGColor();
        drawCenteredString(stack, font, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, textColor | MathHelper.ceil(this.alpha * 255) << 24);
    }
}
