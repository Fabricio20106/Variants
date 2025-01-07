package melonystudios.variants.criterion.custom;

import com.google.gson.JsonObject;
import melonystudios.variants.Variants;
import net.minecraft.advancements.criterion.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nonnull;

public class ModLoadedTrigger extends AbstractCriterionTrigger<ModLoadedTrigger.Instance> {
    public static final ResourceLocation IDENTIFIER = Variants.variants("mod_loaded");

    @Override
    @Nonnull
    public ResourceLocation getId() {
        return IDENTIFIER;
    }

    @Override
    @Nonnull
    protected Instance createInstance(JsonObject object, EntityPredicate.AndPredicate andPredicate, ConditionArrayParser conditionParser) {
        return new Instance(andPredicate, JSONUtils.getAsString(object, "mod_id"));
    }

    protected void trigger(ServerPlayerEntity serverPlayer, String modID) {
        this.trigger(serverPlayer, instance -> ModList.get().isLoaded(modID));
    }

    public static class Instance extends CriterionInstance {
        private final String modID;

        public Instance(EntityPredicate.AndPredicate andPredicate, String modID) {
            super(IDENTIFIER, andPredicate);
            this.modID = modID;
        }

        public static ModLoadedTrigger.Instance modLoaded(String modID) {
            return new ModLoadedTrigger.Instance(EntityPredicate.AndPredicate.ANY, modID);
        }

        @Override
        @Nonnull
        public JsonObject serializeToJson(ConditionArraySerializer serializer) {
            JsonObject object = super.serializeToJson(serializer);
            object.addProperty("mod_id", this.modID);
            return object;
        }
    }
}
