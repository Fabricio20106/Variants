package melonystudios.variants.util;

import net.minecraft.client.Minecraft;

public class Constants {
    public static final long MINECRAFT_WINDOW = Minecraft.getInstance().getWindow().getWindow();

    public static class TagTypes {
        // NBT ID Types:
        public static final int END = 0;
        public static final int BYTE = 1;
        public static final int SHORT = 2;
        public static final int INTEGER = 3;
        public static final int LONG = 4;
        public static final int FLOAT = 5;
        public static final int DOUBLE = 6;
        public static final int BYTE_ARRAY = 7;
        public static final int STRING = 8;
        public static final int LIST = 9;
        public static final int COMPOUND = 10;
        public static final int INTEGER_ARRAY = 11;
        public static final int LONG_ARRAY = 12;
        public static final int ANY_NUMERIC = 99;
    }

    public static class BlockFlags {
        public static final int NOTIFY_NEIGHBORS = 1;
        public static final int BLOCK_UPDATE = 2;
        public static final int NO_RENDERER = 4;
        public static final int RE_RENDER_MAIN_THREAD = 8;
        public static final int UPDATE_NEIGHBORS = 16;
        public static final int NO_NEIGHBOR_DROPS = 32;
        public static final int IS_MOVING = 64;

        public static final int DEFAULT_FLAG = NOTIFY_NEIGHBORS | BLOCK_UPDATE;
        public static final int DEFAULT_AND_RE_RENDER = DEFAULT_FLAG | RE_RENDER_MAIN_THREAD;
        public static final int UPDATE_DEBUG_STATE = UPDATE_NEIGHBORS | BLOCK_UPDATE;
    }
}
