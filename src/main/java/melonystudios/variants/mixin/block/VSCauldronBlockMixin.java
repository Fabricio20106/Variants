package melonystudios.variants.mixin.block;

import melonystudios.variants.item.custom.armor.DyeableArmorItem;
import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static melonystudios.variants.block.RVModdedBlocks.*;

@SuppressWarnings("deprecation")
@Mixin(CauldronBlock.class)
public class VSCauldronBlockMixin extends Block {
    @Unique
    private static final IntegerProperty LEVEL = CauldronBlock.LEVEL;
    @Unique
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public VSCauldronBlockMixin(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(LEVEL, 0));
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hitResult, CallbackInfoReturnable<ActionResultType> callback) {
        if (player.getItemInHand(hand).getItem() instanceof DyeableArmorItem) {
            ItemStack stack = player.getItemInHand(hand);
            DyeableArmorItem armorItem = (DyeableArmorItem) stack.getItem();
            if (state.is(VSBlockTags.CAULDRONS) && state.hasProperty(BlockStateProperties.LEVEL_CAULDRON) && !world.isClientSide) {
                int level = state.getValue(BlockStateProperties.LEVEL_CAULDRON);
                if ((armorItem.hasCustomColor(stack) || DyeableArmorItem.hasArmorDesign(stack)) && level > 0) {
                    if (armorItem.hasCustomColor(stack)) armorItem.clearColor(stack);
                    if (DyeableArmorItem.hasArmorDesign(stack)) DyeableArmorItem.clearArmorDesign(stack);
                    DyeableArmorItem.clearColorName(stack);
                    ((CauldronBlock) state.getBlock()).setWaterLevel(world, pos, state, level - 1);
                    player.awardStat(Stats.CLEAN_ARMOR);
                    callback.setReturnValue(ActionResultType.SUCCESS);
                }
            } else if (isPresent(WATER_CAULDRON) && state.is(WATER_CAULDRON) && !world.isClientSide) {
                callback.setReturnValue(this.clearArmorWithTheMatoCauldron(state, pos, world, stack, player));
            }
        }
    }

    @Unique
    private ActionResultType clearArmorWithTheMatoCauldron(BlockState state, BlockPos pos, World world, ItemStack stack, @Nullable PlayerEntity player) {
        int level = state.getValue(WATER_LEVEL);
        DyeableArmorItem armorItem = (DyeableArmorItem) stack.getItem();
        if ((armorItem.hasCustomColor(stack) || DyeableArmorItem.hasArmorDesign(stack)) && level > 1) {
            if (armorItem.hasCustomColor(stack)) armorItem.clearColor(stack);
            if (DyeableArmorItem.hasArmorDesign(stack)) DyeableArmorItem.clearArmorDesign(stack);
            DyeableArmorItem.clearColorName(stack);
            if (level == 1) world.setBlockAndUpdate(pos, CAULDRON.defaultBlockState());
            else state.setValue(WATER_LEVEL, level - 1);
            if (player != null) player.awardStat(Stats.CLEAN_ARMOR);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Nonnull
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nonnull
    public BlockState updateShape(BlockState state, Direction direction, BlockState neightborState, IWorld world, BlockPos pos, BlockPos neightborPos) {
        if (state.getValue(WATERLOGGED)) world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        return super.updateShape(state, direction, neightborState, world, pos, neightborPos);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState state = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, state.is(FluidTags.WATER) && state.getAmount() == 8);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, LEVEL);
    }
}
