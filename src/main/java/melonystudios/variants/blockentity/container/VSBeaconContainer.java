package melonystudios.variants.blockentity.container;

import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.BeaconContainer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;

public class VSBeaconContainer extends BeaconContainer {
    private final IWorldPosCallable access;

    public VSBeaconContainer(int containerID, IInventory inventory, IIntArray intArray, IWorldPosCallable access) {
        super(containerID, inventory, intArray, access);
        this.access = access;
    }

    protected static boolean stillValid(IWorldPosCallable access, PlayerEntity player) {
        return access.evaluate((world, pos) -> world.getBlockState(pos).is(VSBlockTags.BEACONS) && player.distanceToSqr((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64, true);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(this.access, player);
    }
}
