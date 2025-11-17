package melonystudios.variants.consumable.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VSRegistries;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MultiBehavior extends ConsumeBehavior {
    private final List<ConsumeBehavior> behaviors;

    public MultiBehavior(List<ConsumeBehavior> behaviors) {
        this.behaviors = behaviors;
    }

    public MultiBehavior() {
        this(Lists.newArrayList());
    }

    public List<ConsumeBehavior> behaviors() {
        return this.behaviors;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {}

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (propertiesTag != null && propertiesTag.contains("behaviors", Constants.TagTypes.LIST)) {
            ListNBT behaviorList = propertiesTag.getList("behaviors", Constants.TagTypes.COMPOUND);
            for (int i = 0; i < behaviorList.size(); ++i) {
                CompoundNBT behaviorTag = behaviorList.getCompound(i);
                if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                    ConsumeBehavior behavior = VSRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                    if (behavior != null) behavior.loadFromNBT(stack, world, livEntity, behaviorTag);
                }
            }
        } else if (this.behaviors() != null) {
            for (ConsumeBehavior behavior : this.behaviors()) behavior.loadFromNBT(stack, world, livEntity, propertiesTag);
        }
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        ListNBT behaviors = new ListNBT();

        for (ConsumeBehavior behavior : this.behaviors()) {
            CompoundNBT tag = behavior.writeProperties();
            tag.putString("id", behavior.registryEntry().getRegistryName().toString());
            behaviors.add(tag);
        }
        properties.put("behaviors", behaviors);
        return properties;
    }

    @Override
    public JsonObject writeToJSON(CompoundNBT propertiesTag) {
        JsonObject properties = new JsonObject();
        JsonArray behaviors = new JsonArray();

        for (int i = 0; i < this.behaviors().size(); ++i) {
            ConsumeBehavior behavior = this.behaviors().get(i);
            ListNBT behaviorsTag = propertiesTag.getList("behaviors", Constants.TagTypes.COMPOUND);
            if (behaviorsTag.isEmpty()) return properties;

            JsonObject object = behavior.writeToJSON(behaviorsTag.getCompound(i));
            object.addProperty("id", behavior.registryEntry().getRegistryName().toString());
            behaviors.add(object);
        }
        properties.add("behaviors", behaviors);
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.MULTI_BEHAVIOR.get();
    }
}
