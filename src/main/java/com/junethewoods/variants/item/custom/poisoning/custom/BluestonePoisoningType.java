package com.junethewoods.variants.item.custom.poisoning.custom;

import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;

public class RedstonePoisoningType extends PoisoningType {
    public RedstonePoisoningType(RegistryObject<Effect> poisoningEffect) {
        super(poisoningEffect);
    }
}
