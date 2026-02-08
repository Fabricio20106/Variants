package melonystudios.revaried.item.custom.food;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class RVFoods {
    public static final FoodProperties MUSHROOM_STEW_BUCKET = bucketStew(6).build();
    public static final FoodProperties BEETROOT_SOUP_BUCKET = bucketStew(6).build();
    public static final FoodProperties RABBIT_STEW_BUCKET = bucketStew(10).build();
    public static final FoodProperties SUSPICIOUS_STEW_BUCKET = bucketStew(6).alwaysEdible().build();

    public static FoodProperties.Builder bucketStew(int nutrition) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(0.6F).usingConvertsTo(Items.BUCKET);
    }
}
