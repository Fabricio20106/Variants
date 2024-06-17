package com.junethewoods.variants.command.argument;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import net.minecraft.nbt.CompoundNBT;

import java.util.function.Predicate;

public class BehaviorInput implements Predicate<StewBehavior> {
    public final StewBehavior behavior;
    public final CompoundNBT properties;

    public BehaviorInput(StewBehavior behavior, CompoundNBT properties) {
        this.behavior = behavior;
        this.properties = properties;
    }

    @Override
    public boolean test(StewBehavior behavior) {
        return false;
    }
}
