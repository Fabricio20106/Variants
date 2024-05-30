package com.junethewoods.variants.mixin.block;

import com.junethewoods.variants.block.property.Orientation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DispenserBlock.class)
public class VSDispenserBlockMixin extends Block {
    @Shadow
    @Final
    public static DirectionProperty FACING;
    @Shadow
    @Final
    public static BooleanProperty TRIGGERED;
    @Unique
    private static final EnumProperty<Orientation> ORIENTATION = EnumProperty.create("orientation", Orientation.class);

    public VSDispenserBlockMixin(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ORIENTATION, Orientation.UP_NORTH));
    }

    @Inject(method = "getStateForPlacement", at = @At("HEAD"), cancellable = true)
    public void getStateForPlacement(BlockItemUseContext context, CallbackInfoReturnable<BlockState> cir) {
        Direction opposite = context.getNearestLookingDirection().getOpposite();
        Direction direction = Direction.UP;
        switch (opposite) {
            case DOWN:
                direction = context.getHorizontalDirection().getOpposite();
                break;
            case UP:
                direction = context.getHorizontalDirection();
                break;
            default:
                break;
        }
        cir.setReturnValue(this.defaultBlockState().setValue(ORIENTATION, Orientation.fromFrontAndTop(direction, opposite)).setValue(FACING, opposite));
    }

    @Inject(method = "rotate", at = @At("HEAD"), cancellable = true)
    public void rotate(BlockState state, Rotation rotation, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(state.setValue(ORIENTATION, rotate(rotation, state.getValue(ORIENTATION))).setValue(FACING, rotation.rotate(state.getValue(FACING))));
    }

    @Inject(method = "mirror", at = @At("HEAD"), cancellable = true)
    public void mirror(BlockState state, Mirror mirror, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(state.setValue(ORIENTATION, rotate(mirror, state.getValue(ORIENTATION))).setValue(FACING, mirror.rotation().rotate(state.getValue(FACING))));
    }

    @Unique
    public Orientation rotate(Rotation rotation, Orientation frontAndTop) {
        return Orientation.fromFrontAndTop(rotation.rotate(frontAndTop.front()), rotation.rotate(frontAndTop.top()));
    }

    @Unique
    public Orientation rotate(Mirror mirror, Orientation frontAndTop) {
        return Orientation.fromFrontAndTop(mirror.rotation().rotate(frontAndTop.front()), mirror.rotation().rotate(frontAndTop.top()));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ORIENTATION, FACING, TRIGGERED);
    }
}
