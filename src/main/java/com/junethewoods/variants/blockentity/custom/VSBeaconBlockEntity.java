package com.junethewoods.variants.blockentity.custom;

import com.google.common.collect.Lists;
import com.junethewoods.variants.blockentity.VSBlockEntities;
import com.junethewoods.variants.blockentity.container.VSBeaconContainer;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.LockCode;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VSBeaconBlockEntity extends BeaconTileEntity {
    public static final Effect[][] BEACON_EFFECTS = new Effect[][] {{Effects.MOVEMENT_SPEED, Effects.DIG_SPEED}, {Effects.DAMAGE_RESISTANCE, Effects.JUMP}, {Effects.DAMAGE_BOOST}, {Effects.REGENERATION}};
    private static final Set<Effect> VALID_EFFECTS = Arrays.stream(BEACON_EFFECTS).flatMap(Arrays::stream).collect(Collectors.toSet());
    private List<BeamSegment> beamSections = Lists.newArrayList();
    private int levels;
    @Nullable
    private Effect primaryPower;
    @Nullable
    private Effect secondaryPower;
    private final IIntArray dataAccess = new IIntArray() {
        public int get(int level) {
            switch(level) {
                case 0:
                    return VSBeaconBlockEntity.this.levels;
                case 1:
                    return Effect.getId(VSBeaconBlockEntity.this.primaryPower);
                case 2:
                    return Effect.getId(VSBeaconBlockEntity.this.secondaryPower);
                default:
                    return 0;
            }
        }

        public void set(int currentLevel, int newLevel) {
            switch(currentLevel) {
                case 0:
                    VSBeaconBlockEntity.this.levels = newLevel;
                    break;
                case 1:
                    if (!VSBeaconBlockEntity.this.level.isClientSide && !VSBeaconBlockEntity.this.beamSections.isEmpty()) {
                        VSBeaconBlockEntity.this.playSound(SoundEvents.BEACON_POWER_SELECT);
                    }

                    VSBeaconBlockEntity.this.primaryPower = VSBeaconBlockEntity.getValidEffectById(newLevel);
                    break;
                case 2:
                    VSBeaconBlockEntity.this.secondaryPower = VSBeaconBlockEntity.getValidEffectById(newLevel);
            }
        }

        public int getCount() {
            return 3;
        }
    };

    public VSBeaconBlockEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return VSBlockEntities.VS_BEACON.get();
    }

    @Nullable
    private static Effect getValidEffectById(int effectID) {
        Effect effect = Effect.byId(effectID);
        return VALID_EFFECTS.contains(effect) ? effect : null;
    }

    @Nullable
    @Override
    public Container createMenu(int whatever, PlayerInventory playerInventory, PlayerEntity player) {
        return LockableTileEntity.canUnlock(player, LockCode.NO_LOCK, this.getDisplayName()) ? new VSBeaconContainer(whatever, playerInventory, this.dataAccess, IWorldPosCallable.create(this.level, this.getBlockPos())) : null;
    }

    @Override
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
                aabb = new AxisAlignedBB(pos.offset(-1, 0, -1), pos.offset(1, 1, 1));
            }
            if (aabb != null) bb = aabb;
        }
        return bb;
    }
}
