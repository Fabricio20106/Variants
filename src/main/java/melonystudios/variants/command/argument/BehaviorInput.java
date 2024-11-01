package melonystudios.variants.command.argument;

import melonystudios.variants.consumable.ConsumeBehavior;
import net.minecraft.nbt.CompoundNBT;

import java.util.function.Predicate;

public class BehaviorInput implements Predicate<ConsumeBehavior> {
    public final ConsumeBehavior behavior;
    public final CompoundNBT properties;

    public BehaviorInput(ConsumeBehavior behavior, CompoundNBT properties) {
        this.behavior = behavior;
        this.properties = properties;
    }

    @Override
    public boolean test(ConsumeBehavior behavior) {
        return false;
    }
}
