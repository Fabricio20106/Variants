package melonystudios.variants.mixin.block;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FarmlandBlock.class)
public class RVFarmlandBlockMixin extends Block {
    public RVFarmlandBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    public void canSurvive(BlockState state, IWorldReader world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
        BlockState aboveState = world.getBlockState(pos.above());
        callback.setReturnValue(!aboveState.getMaterial().isSolid() || aboveState.is(VSBlockTags.FARMLAND_TRANSPARENT));
    }
}
