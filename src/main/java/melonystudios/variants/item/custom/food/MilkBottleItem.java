package melonystudios.variants.item.custom.food;

import melonystudios.variants.consumable.custom.ClearMobEffectsBehavior;

public class MilkBottleItem extends DrinkableContainerItem {
    public MilkBottleItem(Properties properties) {
        super(new ClearMobEffectsBehavior(), properties);
    }
}
