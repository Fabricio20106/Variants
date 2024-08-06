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
}
