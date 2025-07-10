package melonystudios.variants.mixin.block;

import melonystudios.variants.block.VSBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NyliumBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;

@Mixin(NyliumBlock.class)
public class RVNyliumBlockMixin extends Block {
    public RVNyliumBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (stack.getItem() instanceof HoeItem) {
            if (state.is(Blocks.CRIMSON_NYLIUM)) {
                return VSBlocks.CRIMSON_FARMLAND.get().defaultBlockState();
            }
            if (state.is(Blocks.WARPED_NYLIUM)) {
                return VSBlocks.WARPED_FARMLAND.get().defaultBlockState();
            }
        }
        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }
}
