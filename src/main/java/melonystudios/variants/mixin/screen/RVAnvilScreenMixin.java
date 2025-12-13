package melonystudios.variants.mixin.screen;

import melonystudios.variants.Variants;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public class RVAnvilScreenMixin {
    @Shadow
    private TextFieldWidget name;

    // Fixes https://bugs.mojang.com/browse/MC-203757 ("Anvil character limit is too low for items with long names")
    @Inject(method = "subInit", at = @At("TAIL"))
    protected void increaseCharacterLimit(CallbackInfo callback) {
        this.name.setMaxLength(Variants.revaried().settings().anvilCharacterLimit);
    }
}
