package melonystudios.variants.mixin.network;

import melonystudios.variants.entity.misc.Leashable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.network.play.IClientPlayNetHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.SMountEntityPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetHandler.class)
public abstract class VSClientPlayNetHandlerMixin implements IClientPlayNetHandler {
    @Shadow
    private Minecraft minecraft;
    @Shadow
    private ClientWorld level;

    @Inject(method = "handleEntityLinkPacket", at = @At("HEAD"), cancellable = true)
    public void handleEntityLinkPacket(SMountEntityPacket packet, CallbackInfo ci) {
        PacketThreadUtil.ensureRunningOnSameThread(packet, this, this.minecraft);
        Entity entity = this.level.getEntity(packet.getSourceId());
        if (entity instanceof Leashable) {
            ci.cancel();
            ((Leashable) entity).setDelayedLeashHolderID(packet.getDestId());
        }
    }
}
