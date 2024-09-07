package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.custom.IgniteBehavior;

public class LavaBottleItem extends DrinkableContainerItem {
    public LavaBottleItem(int secondsOnFire, Properties properties) {
        super(new IgniteBehavior(secondsOnFire * 20), properties);
    }
}
