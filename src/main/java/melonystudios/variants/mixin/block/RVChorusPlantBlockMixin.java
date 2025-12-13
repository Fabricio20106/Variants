package melonystudios.variants.mixin.block;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.SixWayBlock.*;

@Mixin(value = ChorusPlantBlock.class, priority = 1100)
public class RVChorusPlantBlockMixin extends Block {
    public RVChorusPlantBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "getStateForPlacement(Lnet/minecraft/world/IBlockReader;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", at = @At("HEAD"), cancellable = true)
    public void replacePlantableBlocks(IBlockReader world, BlockPos pos, CallbackInfoReturnable<BlockState> callback) {
        if (ModList.get().isLoaded("endergetic")) return;
        Block blockBelow = world.getBlockState(pos.below()).getBlock();
        Block blockAbove = world.getBlockState(pos.above()).getBlock();
        Block northBlock = world.getBlockState(pos.north()).getBlock();
        Block eastBlock = world.getBlockState(pos.east()).getBlock();
        Block southBlock = world.getBlockState(pos.south()).getBlock();
        Block westBlock = world.getBlockState(pos.west()).getBlock();

        callback.setReturnValue(this.defaultBlockState()
                .setValue(DOWN, blockBelow.is(VSBlockTags.CHORUS_PLANT_MAY_PLACE_ON) || blockBelow.is(VSBlockTags.CHORUS_FLOWER_MAY_PLACE_ON))
                .setValue(UP, blockAbove.is(VSBlockTags.CHORUS_PLANT_MAY_PLACE_ON))
                .setValue(NORTH, northBlock.is(VSBlockTags.CHORUS_PLANT_MAY_PLACE_ON))
                .setValue(EAST, eastBlock.is(VSBlockTags.CHORUS_PLANT_MAY_PLACE_ON))
                .setValue(SOUTH, southBlock.is(VSBlockTags.CHORUS_PLANT_MAY_PLACE_ON))
                .setValue(WEST, westBlock.is(VSBlockTags.CHORUS_PLANT_MAY_PLACE_ON)));
    }

    @Inject(method = "updateShape", at = @At("HEAD"), cancellable = true)
    public void updateShape(BlockState state, Direction direction, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> callback) {
        if (ModList.get().isLoaded("endergetic")) return;
        if (!state.canSurvive(world, pos)) {
            world.getBlockTicks().scheduleTick(pos, this, 1);
            callback.setReturnValue(super.updateShape(state, direction, neighborState, world, pos, neighborPos));
        } else {
            boolean isPlantable = neighborState.is(VSBlockTags.CHORUS_PLANT_MAY_PLACE_ON) || direction == Direction.DOWN && neighborState.is(VSBlockTags.CHORUS_FLOWER_MAY_PLACE_ON);
            callback.setReturnValue(state.setValue(PROPERTY_BY_DIRECTION.get(direction), isPlantable));
        }
    }

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    public void canSurvive(BlockState state, IWorldReader world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
        if (ModList.get().isLoaded("endergetic")) return;
        BlockState belowState = world.getBlockState(pos.below());
        boolean isAirAboveAndBelow = !world.getBlockState(pos.above()).isAir() && !belowState.isAir();

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos relativePos = pos.relative(direction);
            Block relativeBlock = world.getBlockState(relativePos).getBlock();
            if (relativeBlock == this) {
                if (isAirAboveAndBelow) callback.setReturnValue(false);

                Block relativeBlockBelow = world.getBlockState(relativePos.below()).getBlock();
                if (relativeBlockBelow == this || relativeBlockBelow.is(VSBlockTags.CHORUS_FLOWER_MAY_PLACE_ON)) {
                    callback.setReturnValue(true);
                }
            }
        }

        Block belowBlock = belowState.getBlock();
        callback.setReturnValue(belowBlock == this || belowBlock.is(VSBlockTags.CHORUS_FLOWER_MAY_PLACE_ON));
    }
}
