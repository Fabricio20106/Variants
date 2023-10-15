package com.junethewoods.variants.blockentity.container;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.BeaconContainer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;

public class VSBeaconContainer extends BeaconContainer {
    private final IWorldPosCallable access;

    public VSBeaconContainer(int containerId, IInventory inventory, IIntArray intArray, IWorldPosCallable access) {
        super(containerId, inventory, intArray, access);
        this.access = access;
    }

    protected static boolean stillValid(IWorldPosCallable access, PlayerEntity player) {
        return access.evaluate((world, pos) -> world.getBlockState(pos).is(VSTags.Blocks.BEACONS) && player.distanceToSqr((double) pos.getX() + 0.5d,
                (double) pos.getY() + 0.5d, (double) pos.getZ() + 0.5d) <= 64, true);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(this.access, player);
    }
}
