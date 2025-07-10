package melonystudios.variants.mixin.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShovelItem.class)
public class RVShovelItemMixin extends Item {
    public RVShovelItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "getShovelPathingState", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getShovelPathingState(BlockState originalState, CallbackInfoReturnable<BlockState> callback) {
        if (originalState.is(Blocks.DIRT) || originalState.is(Blocks.COARSE_DIRT) || originalState.is(Blocks.MYCELIUM) || originalState.is(Blocks.PODZOL)) {
            callback.setReturnValue(Blocks.GRASS_PATH.defaultBlockState());
        }
    }
}
