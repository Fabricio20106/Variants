package com.junethewoods.variants.item.custom;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;

public interface IPoisoningType {
    RegistryObject<Effect> getPoisoningEffect();
}
