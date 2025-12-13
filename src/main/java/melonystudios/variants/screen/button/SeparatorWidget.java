package melonystudios.variants.screen.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import melonystudios.variants.Variants;
import melonystudios.variants.util.VSStyles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.ModList;

// copied from Mellow UI 5.0.0-beta3 ~isa 12-12-25
public class SeparatorWidget extends Widget {
    private static final ResourceLocation SEPARATOR = ModList.get().isLoaded("mellowui") ?
            new ResourceLocation("mellowui", "textures/gui/widget/separator.png") :
            Variants.variants("textures/gui/widget/separator.png");
    private static final ResourceLocation SEPARATOR_HIGHLIGHTED = ModList.get().isLoaded("mellowui") ?
            new ResourceLocation("mellowui", "textures/gui/widget/separator_highlighted.png") :
            Variants.variants("textures/gui/widget/separator_highlighted.png");

    public SeparatorWidget(int x, int y, int width, int height, ITextComponent text) {
        super(x, y, width, height, text);
    }

    @Override
    public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        this.renderBg(stack, minecraft, mouseX, mouseY);
        int color = this.getFGColor();
        int height = this.y + (this.height / 2);
        int textWidth = minecraft.font.width(this.getMessage());

        if (this.active) {
            // Background
            RenderSystem.enableBlend();
            minecraft.getTextureManager().bind(this.isFocused() ? SEPARATOR_HIGHLIGHTED : SEPARATOR);
            RenderSystem.color4f(1, 1, 1, this.alpha);
            blit(stack, this.x, this.y, 0, 0, this.width / 2, this.height, 200, 20);
            blit(stack, this.x + this.width / 2, this.y, 200 - this.width / 2, 0, this.width / 2, this.height, 200, 20);
            RenderSystem.color4f(1, 1, 1, 1);
            RenderSystem.disableBlend();
        }

        // Lines
        int textColor = color | MathHelper.ceil(this.alpha * 255F) << 24;
        fill(stack, this.x + 2, height, (this.x + this.width / 2) - (textWidth / 2) - 4, height + 1, textColor);
        fill(stack, this.x + 3, height + 1, (this.x + this.width / 2) - (textWidth / 2) - 3, height + 2, VSStyles.darkenColor(color, this.alpha, 0.25F));

        fill(stack, (this.x + this.width / 2) + (textWidth / 2) + 4, height, this.x + this.width - 2, height + 1, textColor);
        fill(stack, (this.x + this.width / 2) + (textWidth / 2) + 5, height + 1, this.x + this.width - 1, height + 2, VSStyles.darkenColor(color, this.alpha, 0.25F));

        drawCenteredString(stack, minecraft.font, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, textColor);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }
}
