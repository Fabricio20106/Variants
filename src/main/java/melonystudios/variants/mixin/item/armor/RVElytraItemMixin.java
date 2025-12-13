package melonystudios.variants.mixin.item.armor;

import melonystudios.variants.item.custom.Swappable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElytraItem.class)
public class RVElytraItemMixin extends Item implements Swappable {
    public RVElytraItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void use(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult<ItemStack>> callback) {
        callback.setReturnValue(this.equipOrSwapItem(this, world, player, hand));
    }
}
