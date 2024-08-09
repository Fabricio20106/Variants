package melonystudios.variants.dispenser.vanilla;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayerFactory;

import javax.annotation.Nonnull;

public class BoneMealDispenseBehavior extends OptionalDispenseBehavior {
    @Override
    @Nonnull
    protected ItemStack execute(IBlockSource source, ItemStack stack) {
        this.setSuccess(true);
        ServerWorld world = source.getLevel();
        BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
        if (!BoneMealItem.applyBonemeal(stack, world, pos, FakePlayerFactory.getMinecraft(world)) && !BoneMealItem.growWaterPlant(stack, world, pos, null)) {
            this.setSuccess(false);
        } else if (!world.isClientSide) {
            world.levelEvent(Constants.WorldEvents.BONEMEAL_PARTICLES, pos, 0);
        }
        return stack;
    }
}
