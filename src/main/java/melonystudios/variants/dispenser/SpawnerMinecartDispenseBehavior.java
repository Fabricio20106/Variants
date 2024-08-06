package melonystudios.variants.dispenser;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.item.minecart.SpawnerMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.RailShape;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SpawnerMinecartDispenseBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

    @Override
    @Nonnull
    public ItemStack execute(IBlockSource source, ItemStack stack) {
        Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
        World world = source.getLevel();
        double x = source.x() + (double) direction.getStepX() * 1.125D;
        double y = Math.floor(source.y()) + (double) direction.getStepY();
        double z = source.z() + (double) direction.getStepZ() * 1.125D;
        BlockPos relativePos = source.getPos().relative(direction);
        BlockState relativeState = world.getBlockState(relativePos);
        RailShape railShape = relativeState.getBlock() instanceof AbstractRailBlock ? ((AbstractRailBlock) relativeState.getBlock()).getRailDirection(relativeState, world, relativePos, null) : RailShape.NORTH_SOUTH;
        double heightAddition;

        if (relativeState.is(BlockTags.RAILS)) {
            if (railShape.isAscending()) {
                heightAddition = 0.6D;
            } else {
                heightAddition = 0.1D;
            }
        } else {
            if (!relativeState.isAir() || !world.getBlockState(relativePos.below()).is(BlockTags.RAILS)) return this.defaultDispenseItemBehavior.dispense(source, stack);

            BlockState belowState = world.getBlockState(relativePos.below());
            RailShape belowShape = belowState.getBlock() instanceof AbstractRailBlock ? belowState.getValue(((AbstractRailBlock) belowState.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;
            if (direction != Direction.DOWN && belowShape.isAscending()) {
                heightAddition = -0.4D;
            } else {
                heightAddition = -0.9D;
            }
        }

        SpawnerMinecartEntity spawnerMinecart = (SpawnerMinecartEntity) AbstractMinecartEntity.createMinecart(world, x, y + heightAddition, z, AbstractMinecartEntity.Type.SPAWNER);
        if (stack.hasCustomHoverName()) spawnerMinecart.setCustomName(stack.getHoverName());
        spawnerMinecart.spawner.load(stack.getOrCreateTagElement("spawn_data"));

        world.addFreshEntity(spawnerMinecart);
        stack.shrink(1);
        return stack;
    }

    @Override
    protected void playSound(IBlockSource source) {
        source.getLevel().levelEvent(1000, source.getPos(), 0);
    }
}
