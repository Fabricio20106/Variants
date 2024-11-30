package melonystudios.variants.block.property;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Util;

import javax.annotation.Nonnull;

public enum DispenserOrientation implements IStringSerializable {
    DOWN_EAST("down_east", Direction.DOWN, Direction.EAST),
    DOWN_NORTH("down_north", Direction.DOWN, Direction.NORTH),
    DOWN_SOUTH("down_south", Direction.DOWN, Direction.SOUTH),
    DOWN_WEST("down_west", Direction.DOWN, Direction.WEST),
    UP_EAST("up_east", Direction.UP, Direction.EAST),
    UP_NORTH("up_north", Direction.UP, Direction.NORTH),
    UP_SOUTH("up_south", Direction.UP, Direction.SOUTH),
    UP_WEST("up_west", Direction.UP, Direction.WEST),
    WEST_UP("west_up", Direction.WEST, Direction.UP),
    EAST_UP("east_up", Direction.EAST, Direction.UP),
    NORTH_UP("north_up", Direction.NORTH, Direction.UP),
    SOUTH_UP("south_up", Direction.SOUTH, Direction.UP);

    private static final Int2ObjectMap<DispenserOrientation> LOOKUP_TOP_FRONT = Util.make(new Int2ObjectOpenHashMap<>(values().length), (hashMap) -> {
        DispenserOrientation[] orientations = values();
        for (DispenserOrientation orientation : orientations) hashMap.put(lookupKey(orientation.front, orientation.top), orientation);
    });
    private final String name;
    private final Direction top;
    private final Direction front;

    private static int lookupKey(Direction front, Direction top) {
        return top.ordinal() << 3 | front.ordinal();
    }

    DispenserOrientation(String name, Direction top, Direction front) {
        this.name = name;
        this.top = top;
        this.front = front;
    }

    @Override
    @Nonnull
    public String getSerializedName() {
        return this.name;
    }

    public static DispenserOrientation fromFrontAndTop(Direction front, Direction top) {
        int lookupKey = lookupKey(front, top);
        return LOOKUP_TOP_FRONT.get(lookupKey);
    }

    public Direction front() {
        return this.front;
    }

    public Direction top() {
        return this.top;
    }
}