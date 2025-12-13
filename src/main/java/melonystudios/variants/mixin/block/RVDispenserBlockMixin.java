package melonystudios.variants.mixin.block;

import melonystudios.variants.block.property.DispenserOrientation;
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
public class RVDispenserBlockMixin extends Block {
    @Shadow
    @Final
    public static DirectionProperty FACING;
    @Shadow
    @Final
    public static BooleanProperty TRIGGERED;
    @Unique
    private static final EnumProperty<DispenserOrientation> ORIENTATION = EnumProperty.create("orientation", DispenserOrientation.class);

    public RVDispenserBlockMixin(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ORIENTATION, DispenserOrientation.UP_NORTH));
    }

    @Inject(method = "getStateForPlacement", at = @At("HEAD"), cancellable = true)
    public void getStateForPlacement(BlockItemUseContext context, CallbackInfoReturnable<BlockState> callback) {
        Direction opposite = context.getNearestLookingDirection().getOpposite();
        Direction direction = Direction.UP;
        switch (opposite) {
            case DOWN:
                direction = context.getHorizontalDirection().getOpposite();
                break;
            case UP:
                direction = context.getHorizontalDirection();
                break;
            default: break;
        }
        callback.setReturnValue(this.defaultBlockState().setValue(ORIENTATION, DispenserOrientation.fromFrontAndTop(direction, opposite)).setValue(FACING, opposite));
    }

    @Inject(method = "rotate", at = @At("HEAD"), cancellable = true)
    public void rotate(BlockState state, Rotation rotation, CallbackInfoReturnable<BlockState> callback) {
        callback.setReturnValue(state.setValue(ORIENTATION, rotate(rotation, state.getValue(ORIENTATION))).setValue(FACING, rotation.rotate(state.getValue(FACING))));
    }

    @Inject(method = "mirror", at = @At("HEAD"), cancellable = true)
    public void mirror(BlockState state, Mirror mirror, CallbackInfoReturnable<BlockState> callback) {
        callback.setReturnValue(state.setValue(ORIENTATION, rotate(mirror, state.getValue(ORIENTATION))).setValue(FACING, mirror.rotation().rotate(state.getValue(FACING))));
    }

    @Unique
    public DispenserOrientation rotate(Rotation rotation, DispenserOrientation orientation) {
        return DispenserOrientation.fromFrontAndTop(rotation.rotate(orientation.front()), rotation.rotate(orientation.top()));
    }

    @Unique
    public DispenserOrientation rotate(Mirror mirror, DispenserOrientation orientation) {
        return DispenserOrientation.fromFrontAndTop(mirror.rotation().rotate(orientation.front()), mirror.rotation().rotate(orientation.top()));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ORIENTATION, FACING, TRIGGERED);
    }
}
