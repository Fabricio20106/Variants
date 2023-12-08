package com.junethewoods.variants.blockentity.custom;

import com.junethewoods.variants.blockentity.VSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class VSBeaconBlockEntity extends BeaconBlockEntity {
    public VSBeaconBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return VSBlockEntities.VS_BEACON.get();
    }

    /*@Nullable
    @Override
    public Container createMenu(int whatever, PlayerInventory playerInventory, PlayerEntity player) {
        return LockableTileEntity.canUnlock(player, LockCode.NO_LOCK, this.getDisplayName()) ? new VSBeaconContainer(whatever, playerInventory, this.dataAccess,
                IWorldPosCallable.create(this.level, this.getBlockPos())) : null;
    }*/

    /*@Override
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        BlockState state = getTileEntity().getBlockState();
        BlockPos pos = getTileEntity().getBlockPos();

        if (getBlockState().is(VSTags.Blocks.BEACONS)) {
            AxisAlignedBB aabb = null;
            try {
                VoxelShape collisionShape = state.getCollisionShape(getTileEntity().getLevel(), pos);
                if (!collisionShape.isEmpty()) {
                    aabb = collisionShape.bounds().move(pos);
                }
            }
            catch (Exception exception) {
                aabb = new net.minecraft.util.math.AxisAlignedBB(pos.offset(-1, 0, -1), pos.offset(1, 1, 1));
            }
            if (aabb != null) bb = aabb;
        }
        return bb;
    }*/
}
