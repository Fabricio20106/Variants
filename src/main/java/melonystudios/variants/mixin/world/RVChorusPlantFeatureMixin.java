package melonystudios.variants.mixin.world;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ChorusPlantFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ChorusPlantFeature.class)
public class RVChorusPlantFeatureMixin {
    @Inject(method = "place(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/world/gen/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/NoFeatureConfig;)Z", at = @At("HEAD"), cancellable = true)
    public void place(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config, CallbackInfoReturnable<Boolean> callback) {
        if (world.isEmptyBlock(pos) && world.getBlockState(pos.below()).is(VSBlockTags.CHORUS_FLOWER_MAY_PLACE_ON)) {
            ChorusFlowerBlock.generatePlant(world, pos, rand, 8);
            callback.setReturnValue(true);
        } else {
            callback.setReturnValue(false);
        }
    }
}
