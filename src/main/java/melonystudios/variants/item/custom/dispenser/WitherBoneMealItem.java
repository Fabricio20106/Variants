package melonystudios.variants.item.custom.dispenser;

import melonystudios.variants.dispenser.vanilla.BoneMealDispenseBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.BoneMealItem;

public class WitherBoneMealItem extends BoneMealItem {
    public WitherBoneMealItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new BoneMealDispenseBehavior());
    }
}
