package melonystudios.revaried.dispenser;

import melonystudios.revaried.component.RVDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class SpawnerMinecartDispenseBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

    @Override
    @NotNull
    public ItemStack execute(BlockSource source, ItemStack stack) {
        Direction facing = source.state().getValue(DispenserBlock.FACING);
        ServerLevel level = source.level();
        Vec3 centerPos = source.center();
        double x = centerPos.x() + (double) facing.getStepX() * 1.125;
        double y = Math.floor(centerPos.y()) + (double) facing.getStepY();
        double z = centerPos.z() + (double) facing.getStepZ() * 1.125;
        BlockPos relativePos = source.pos().relative(facing);
        BlockState state = level.getBlockState(relativePos);
        RailShape shape = state.getBlock() instanceof BaseRailBlock baseRail ? baseRail.getRailDirection(state, level, relativePos, null) : RailShape.NORTH_SOUTH;
        double heightAddition;

        if (state.is(BlockTags.RAILS)) {
            if (shape.isAscending()) {
                heightAddition = 0.6;
            } else {
                heightAddition = 0.1;
            }
        } else {
            if (!state.isAir() || !level.getBlockState(relativePos.below()).is(BlockTags.RAILS)) {
                return this.defaultBehavior.dispense(source, stack);
            }

            BlockState belowState = level.getBlockState(relativePos.below());
            RailShape belowShape = belowState.getBlock() instanceof BaseRailBlock baseRail ? baseRail.getRailDirection(belowState, level, relativePos.below(), null) : RailShape.NORTH_SOUTH;
            if (facing != Direction.DOWN && belowShape.isAscending()) {
                heightAddition = -0.4;
            } else {
                heightAddition = -0.9;
            }
        }

        BlockPos minecartPos = new BlockPos((int) x, (int) (y + heightAddition), (int) z);
        MinecartSpawner spawnerMinecart = (MinecartSpawner) MinecartSpawner.createMinecart(level, x, y + heightAddition, z, AbstractMinecart.Type.SPAWNER, stack, null);
        if (stack.has(DataComponents.CUSTOM_NAME)) spawnerMinecart.setCustomName(stack.getHoverName());
        if (stack.has(RVDataComponents.SPAWNER_DATA.get())) spawnerMinecart.getSpawner().load(level, minecartPos, stack.get(RVDataComponents.SPAWNER_DATA.get()).copyTag());

        level.addFreshEntity(spawnerMinecart);
        stack.shrink(1);
        return stack;
    }

    @Override
    protected void playSound(BlockSource source) {
        source.level().levelEvent(1000, source.pos(), 0);
    }
}
