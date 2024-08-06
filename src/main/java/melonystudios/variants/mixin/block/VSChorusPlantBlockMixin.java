package melonystudios.variants.mixin.block;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.SixWayBlock.*;

@Mixin(ChorusPlantBlock.class)
public class VSChorusPlantBlockMixin extends Block {
    public VSChorusPlantBlockMixin(Properties properties) {
        super(properties);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getStateForPlacement(context.getLevel(), context.getClickedPos());
    }

    public BlockState getStateForPlacement(IBlockReader world, BlockPos pos) {
        Block block = world.getBlockState(pos.below()).getBlock();
        Block block1 = world.getBlockState(pos.above()).getBlock();
        Block block2 = world.getBlockState(pos.north()).getBlock();
        Block block3 = world.getBlockState(pos.east()).getBlock();
        Block block4 = world.getBlockState(pos.south()).getBlock();
        Block block5 = world.getBlockState(pos.west()).getBlock();
        return this.defaultBlockState().setValue(DOWN, block.is(VSBlockTags.CHORUS_PLANT_PLANTABLE_ON) || block.is(VSBlockTags.CHORUS_FLOWER_PLANTABLE_ON)).setValue(UP, block1.is(VSBlockTags.CHORUS_PLANT_PLANTABLE_ON))
                .setValue(NORTH, block2.is(VSBlockTags.CHORUS_PLANT_PLANTABLE_ON)).setValue(EAST, block3.is(VSBlockTags.CHORUS_PLANT_PLANTABLE_ON)).setValue(SOUTH, block4.is(VSBlockTags.CHORUS_PLANT_PLANTABLE_ON))
                .setValue(WEST, block5.is(VSBlockTags.CHORUS_PLANT_PLANTABLE_ON));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, IWorld world, BlockPos pos, BlockPos pos1) {
        if (!state.canSurvive(world, pos)) {
            world.getBlockTicks().scheduleTick(pos, this, 1);
            return super.updateShape(state, direction, state1, world, pos, pos1);
        } else {
            boolean canBePlacedOn = state1.is(VSBlockTags.CHORUS_PLANT_PLANTABLE_ON) || direction == Direction.DOWN && state1.is(VSBlockTags.CHORUS_FLOWER_PLANTABLE_ON);
            return state.setValue(PROPERTY_BY_DIRECTION.get(direction), canBePlacedOn);
        }
    }

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    public void canSurvive(BlockState state, IWorldReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState belowState = world.getBlockState(pos.below());
        boolean isAirAboveAndBelow = !world.getBlockState(pos.above()).isAir() && !belowState.isAir();

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos relativePos = pos.relative(direction);
            Block relativeBlock = world.getBlockState(relativePos).getBlock();
            if (relativeBlock == this) {
                if (isAirAboveAndBelow) {
                    cir.setReturnValue(false);
                }

                Block relativeBlockBelow = world.getBlockState(relativePos.below()).getBlock();
                if (relativeBlockBelow == this || relativeBlockBelow.is(VSBlockTags.CHORUS_FLOWER_PLANTABLE_ON)) {
                    cir.setReturnValue(true);
                }
            }
        }

        Block belowBlock = belowState.getBlock();
        cir.setReturnValue(belowBlock == this || belowBlock.is(VSBlockTags.CHORUS_FLOWER_PLANTABLE_ON));
    }
}
