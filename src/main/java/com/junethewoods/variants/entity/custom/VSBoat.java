package com.junethewoods.variants.entity.custom;

import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.entity.VSEntities;
import com.junethewoods.variants.item.VSItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.IntFunction;

public class VSBoat extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(VSBoat.class, EntityDataSerializers.INT);

    public VSBoat(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public VSBoat(Level level, double pX, double pY, double pZ) {
        this(VSEntities.VS_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            default -> VSItems.PAINTING_BOAT.get();
            case CRIMSON -> VSItems.CRIMSON_BOAT.get();
            case WARPED -> VSItems.WARPED_BOAT.get();
        };
    }

    public void setVariant(Type woodType) {
        this.entityData.set(DATA_ID_TYPE, woodType.ordinal());
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.CRIMSON.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("Type", 8)) {
            this.setVariant(Type.byName(tag.getString("Type")));
        }
    }

    public enum Type implements StringRepresentable {
        PAINTING(VSBlocks.PAINTING_PLANKS.get(), "painting"),
        CRIMSON(Blocks.CRIMSON_PLANKS, "crimson"),
        WARPED(Blocks.WARPED_PLANKS, "warped");

        private final String name;
        private final Block planks;
        public static final EnumCodec<Type> CODEC = StringRepresentable.fromEnum(Type::values);
        private static final IntFunction<Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        Type(Block planks, String name) {
            this.name = name;
            this.planks = planks;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        // Get a boat type by its enum ordinal
        public static Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static Type byName(String pName) {
            return CODEC.byName(pName, CRIMSON);
        }
    }
}
