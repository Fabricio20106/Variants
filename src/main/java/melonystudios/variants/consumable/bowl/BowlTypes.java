package melonystudios.variants.consumable.bowl;

import melonystudios.variants.item.VSItems;
import net.minecraft.item.ItemStack;

public class BowlTypes {
    public static final BowlType OAK = new BowlType(new ItemStack(VSItems.OAK_BOWL.get()), "oak", 0);
    public static final BowlType SPRUCE = new BowlType(new ItemStack(VSItems.SPRUCE_BOWL.get()), "spruce", 1);
    public static final BowlType BIRCH = new BowlType(new ItemStack(VSItems.BIRCH_BOWL.get()), "birch", 2);
    public static final BowlType JUNGLE = new BowlType(new ItemStack(VSItems.JUNGLE_BOWL.get()), "jungle", 3);
    public static final BowlType ACACIA = new BowlType(new ItemStack(VSItems.ACACIA_BOWL.get()), "acacia", 4);
    public static final BowlType DARK_OAK = new BowlType(new ItemStack(VSItems.DARK_OAK_BOWL.get()), "dark_oak", 5);
    public static final BowlType PAINTING = new BowlType(new ItemStack(VSItems.PAINTING_BOWL.get()), "painting", 6);
    public static final BowlType CRIMSON = new BowlType(new ItemStack(VSItems.CRIMSON_BOWL.get()), "crimson", 7);
    public static final BowlType WARPED = new BowlType(new ItemStack(VSItems.WARPED_BOWL.get()), "warped", 8);
    public static final BowlType ENDERWOOD = new BowlType(new ItemStack(VSItems.ENDERWOOD_BOWL.get()), "enderwood", 9);
}
