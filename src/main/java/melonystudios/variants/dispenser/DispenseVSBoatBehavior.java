package melonystudios.variants.dispenser;

import melonystudios.variants.entity.custom.VSBoatEntity;
import melonystudios.variants.util.VSUtils;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;

public class DispenseVSBoatBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior defaultDispenseBehavior = new DefaultDispenseItemBehavior();
    private final String woodType;

    public DispenseVSBoatBehavior(String woodType) {
        this.woodType = woodType;
    }

    @Override
    @Nonnull
    public ItemStack execute(IBlockSource source, ItemStack stack) {
        Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
        World world = source.getLevel();
        double x = source.x() + (double) ((float) direction.getStepX() * 1.125F);
        double y = source.y() + (double) ((float) direction.getStepY() * 1.125F);
        double z = source.z() + (double) ((float) direction.getStepZ() * 1.125F);
        BlockPos pos = source.getPos().relative(direction);
        double boatHeight;
        if (world.getFluidState(pos).is(FluidTags.WATER)) {
            boatHeight = 1;
        } else {
            if (!world.getBlockState(pos).isAir() || !world.getFluidState(pos.below()).is(FluidTags.WATER)) return this.defaultDispenseBehavior.dispense(source, stack);
            boatHeight = 0;
        }

        VSBoatEntity vsBoat = new VSBoatEntity(world, x, y + boatHeight, z);
        vsBoat.setWoodType(VSUtils.getBoatType(stack, this.woodType));
        vsBoat.yRot = direction.toYRot();
        world.addFreshEntity(vsBoat);
        stack.shrink(1);
        return stack;
    }

    @Override
    protected void playSound(IBlockSource source) {
        source.getLevel().levelEvent(Constants.WorldEvents.DISPENSER_DISPENSE_SOUND, source.getPos(), 0);
    }
}
