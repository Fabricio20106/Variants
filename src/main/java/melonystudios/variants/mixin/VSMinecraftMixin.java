package melonystudios.variants.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class VSMinecraftMixin {
    @Shadow
    @Final
    private boolean allowsMultiplayer;

    // Re-enables the multiplayer button in a development environment.
    @Inject(method = "allowsMultiplayer", at = @At("HEAD"), cancellable = true)
    public void allowsMultiplayer(CallbackInfoReturnable<Boolean> callback) {
        if (Minecraft.getInstance().getLaunchedVersion().contains("melony-studios-dev")) {
            callback.cancel();
            callback.setReturnValue(this.allowsMultiplayer);
        }
    }
}
