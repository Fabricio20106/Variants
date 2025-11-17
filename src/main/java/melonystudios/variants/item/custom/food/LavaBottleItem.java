package melonystudios.variants.item.custom.food;

import melonystudios.variants.consumable.custom.IgniteBehavior;

public class LavaBottleItem extends DrinkableContainerItem {
    public LavaBottleItem(int ticksOnFire, Properties properties) {
        this(ticksOnFire, IgniteBehavior.DEFAULT_TOOLTIP_COLOR, properties);
    }

    public LavaBottleItem(int ticksOnFire, int tooltipColor, Properties properties) {
        super(new IgniteBehavior(ticksOnFire, tooltipColor), properties);
    }
}
