package melonystudios.variants.consumable.custom;

import com.google.common.collect.Lists;
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
        } else if (this.behaviors != null) {
            for (ConsumeBehavior behavior : this.behaviors) behavior.loadFromNBT(stack, world, livEntity, propertiesTag);
        }
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT propertiesTag = new CompoundNBT();
        ListNBT behaviorList = new ListNBT();

        for (ConsumeBehavior behavior : this.behaviors) {
            CompoundNBT tag = behavior.writeProperties();
            tag.putString("id", behavior.registryEntry().getRegistryName().toString());
            behaviorList.add(tag);
        }
        propertiesTag.put("behaviors", behaviorList);
        return propertiesTag;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.MULTI_BEHAVIOR.get();
    }
}
