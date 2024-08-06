package melonystudios.variants.item.custom.dispenser;

import melonystudios.variants.dispenser.SoulChargeDispenseBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.FireChargeItem;

public class SoulChargeItem extends FireChargeItem {
    public SoulChargeItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new SoulChargeDispenseBehavior());
    }
}
