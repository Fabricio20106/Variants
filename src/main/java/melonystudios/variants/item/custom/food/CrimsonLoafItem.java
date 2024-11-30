package melonystudios.variants.item.custom.food;

import melonystudios.variants.consumable.ConsumeBehavior;

public class CrimsonLoafItem extends ConsumableItem {
    public CrimsonLoafItem(ConsumeBehavior behavior, Properties properties) {
        super(true, behavior, properties);
        this.populateBehavior = true;
    }
}
