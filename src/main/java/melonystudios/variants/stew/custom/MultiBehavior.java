package melonystudios.variants.stew.custom;

import com.google.common.collect.Lists;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
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

public class MultiBehavior extends StewBehavior {
    private final List<StewBehavior> behaviors;

    public MultiBehavior(List<StewBehavior> behaviors) {
        this.behaviors = behaviors;
    }

    public MultiBehavior() {
        this(Lists.newArrayList());
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {}

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        assert propertiesTag != null;
        if (propertiesTag.contains("behaviors", Constants.TagTypes.LIST)) {
            ListNBT behaviorList = propertiesTag.getList("behaviors", Constants.TagTypes.COMPOUND);
            for (int i = 0; i < behaviorList.size(); ++i) {
                CompoundNBT behaviorTag = behaviorList.getCompound(i);
                if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                    StewBehavior behavior = VSRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                    if (behavior != null) behavior.executeFromStewNBT(stewStack, world, livEntity, behaviorTag);
                }
            }
        }
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT propertiesTag = new CompoundNBT();
        ListNBT behaviorList = new ListNBT();

        for (StewBehavior behavior : this.behaviors) {
            CompoundNBT tag = behavior.writePropertiesToNBT();
            tag.putString("id", behavior.getBehaviorRegistry().getRegistryName().toString());
            behaviorList.add(tag);
        }
        propertiesTag.put("behaviors", behaviorList);
        return propertiesTag;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.MULTI_BEHAVIOR.get();
    }
}
