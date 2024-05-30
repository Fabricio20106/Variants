package com.junethewoods.variants.data.tags;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.entity.VSEntities;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VSEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public VSEntityTypeTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Entity Type Tags";
    }

    @Override
    protected void addTags() {
        this.tag(VSTags.EntityTypes.CAN_SPAWN_ON_LEAVES).add(EntityType.OCELOT).add(EntityType.PARROT);
        this.tag(EntityTypeTags.ARROWS).add(VSEntities.DEBUG_ARROW.get());
    }
}
