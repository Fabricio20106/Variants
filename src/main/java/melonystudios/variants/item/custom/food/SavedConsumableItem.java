package melonystudios.variants.item.custom.food;

import melonystudios.variants.consumable.ConsumeBehavior;

public class SavedConsumableItem extends ConsumableItem {
    public SavedConsumableItem(ConsumeBehavior behavior, Properties properties) {
        super(true, behavior, properties);
        this.populateBehavior = true;
    }
}
