package melonystudios.variants.item.custom.food;

import melonystudios.variants.consumable.custom.IgniteBehavior;

public class LavaBottleItem extends DrinkableContainerItem {
    public LavaBottleItem(int secondsOnFire, Properties properties) {
        super(new IgniteBehavior(secondsOnFire * 20), properties);
    }
}
