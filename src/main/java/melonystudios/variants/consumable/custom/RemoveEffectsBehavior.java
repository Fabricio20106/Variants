package melonystudios.variants.consumable.custom;

import com.google.common.collect.Lists;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.Constants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

import java.util.List;

public class RemoveEffectsBehavior extends ConsumeBehavior {
    private final List<Effect> effects;

    public RemoveEffectsBehavior(List<Effect> effects) {
        this.effects = effects;
    }

    public RemoveEffectsBehavior() {
        this(Lists.newArrayList());
    }

    public List<Effect> effectsToRemove() {
        return this.effects;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        for (Effect effect : this.effects) livEntity.removeEffect(effect);
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        List<Effect> effects = Lists.newArrayList(Effects.POISON);
        if (propertiesTag != null && propertiesTag.contains("effects", Constants.TagTypes.LIST)) {
            ListNBT effectTag = propertiesTag.getList("effects", Constants.TagTypes.STRING);
            for (int i = 0; i < effectTag.size(); ++i) {
                String effectNameTag = effectTag.getString(i);
                ResourceLocation location = new ResourceLocation(effectNameTag);
                if (ForgeRegistries.POTIONS.containsKey(location)) effects.add(ForgeRegistries.POTIONS.getValue(location));
            }
        }
        RemoveEffectsBehavior behavior = new RemoveEffectsBehavior(effects);
        behavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        ListNBT effectTag = new ListNBT();
        for (Effect effect : this.effects) {
            StringNBT tag = StringNBT.valueOf(effect.getRegistryName().toString());
            effectTag.add(tag);
        }
        properties.put("effects", effectTag);
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.REMOVE_EFFECTS.get();
    }
}
