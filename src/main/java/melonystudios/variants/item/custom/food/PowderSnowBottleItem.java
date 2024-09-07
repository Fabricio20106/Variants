package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.custom.DefaultStewBehavior;

public class PowderSnowBottleItem extends DrinkableContainerItem {
    public PowderSnowBottleItem(Properties properties) {
        super(new DefaultStewBehavior(), properties);
    }
}
