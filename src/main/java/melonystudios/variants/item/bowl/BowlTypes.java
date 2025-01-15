package melonystudios.variants.item.bowl;

import melonystudios.variants.item.VSItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BowlTypes {
    public static final BowlType OAK = new BowlType(new ItemStack(VSItems.OAK_BOWL.get()), new ResourceLocation("oak"), "oak", 0);
    public static final BowlType SPRUCE = new BowlType(new ItemStack(VSItems.SPRUCE_BOWL.get()), new ResourceLocation("spruce"), "spruce", 1);
    public static final BowlType BIRCH = new BowlType(new ItemStack(VSItems.BIRCH_BOWL.get()), new ResourceLocation("birch"), "birch", 2);
    public static final BowlType JUNGLE = new BowlType(new ItemStack(VSItems.JUNGLE_BOWL.get()), new ResourceLocation("jungle"), "jungle", 3);
    public static final BowlType ACACIA = new BowlType(new ItemStack(VSItems.ACACIA_BOWL.get()), new ResourceLocation("acacia"), "acacia", 4);
    public static final BowlType DARK_OAK = new BowlType(new ItemStack(VSItems.DARK_OAK_BOWL.get()), new ResourceLocation("dark_oak"), "dark_oak", 5);
    public static final BowlType PAINTING = new BowlType(new ItemStack(VSItems.PAINTING_BOWL.get()), "painting", 6);
    public static final BowlType CRIMSON = new BowlType(new ItemStack(VSItems.CRIMSON_BOWL.get()), new ResourceLocation("crimson"), "crimson", 7);
    public static final BowlType WARPED = new BowlType(new ItemStack(VSItems.WARPED_BOWL.get()), new ResourceLocation("warped"), "warped", 8);
    public static final BowlType ENDERWOOD = new BowlType(new ItemStack(VSItems.ENDERWOOD_BOWL.get()), "enderwood", 9);
}
