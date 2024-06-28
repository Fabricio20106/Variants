package com.junethewoods.variants.item.custom.poisoning;

import com.junethewoods.variants.util.VSRegistries;
import net.minecraft.potion.Effect;
import net.minecraft.tags.ITag;
import net.minecraft.util.Util;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class PoisoningType extends ForgeRegistryEntry<PoisoningType> {
    private final RegistryObject<Effect> poisoning;
    @Nullable
    private String descriptionId;

    public PoisoningType(RegistryObject<Effect> poisoning) {
        this.poisoning = poisoning;
    }

    public boolean is(ITag<PoisoningType> poisoningTag) {
        return poisoningTag.contains(this);
    }

    public RegistryObject<Effect> getPoisoningEffect() {
        return this.poisoning;
    }

    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.REDSTONE.get();
    }

    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) this.descriptionId = Util.makeDescriptionId("poisoning_type", VSRegistries.POISONING_TYPE.getKey(this));
        return this.descriptionId;
    }

    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }
}
