package melonystudios.variants.util.tag;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class VSEntityTypeTags {
    public static final ITag.INamedTag<EntityType<?>> CAN_SPAWN_ON_LEAVES = melony("can_spawn_on_leaves");

    public static ITag.INamedTag<EntityType<?>> melony(String name) {
        return EntityTypeTags.bind(new ResourceLocation("melony", name).toString());
    }
}
