package melonystudios.variants.consumable.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.Constants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.potion.Effect;
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

    public List<Effect> effects() {
        return this.effects;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (world.isClientSide()) return;
        for (Effect effect : this.effects()) livEntity.removeEffect(effect);
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        List<Effect> effects = Lists.newArrayList();
        if (propertiesTag != null && propertiesTag.contains("effects", Constants.TagTypes.LIST)) {
            ListNBT effectList = propertiesTag.getList("effects", Constants.TagTypes.STRING);
            for (int i = 0; i < effectList.size(); ++i) {
                String effect = effectList.getString(i);
                ResourceLocation effectLocation = new ResourceLocation(effect);
                if (ForgeRegistries.POTIONS.containsKey(effectLocation)) effects.add(ForgeRegistries.POTIONS.getValue(effectLocation));
            }
        }
        RemoveEffectsBehavior behavior = new RemoveEffectsBehavior(effects);
        behavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        ListNBT effects = new ListNBT();
        for (Effect effect : this.effects()) effects.add(StringNBT.valueOf(effect.getRegistryName().toString()));
        properties.put("effects", effects);
        return properties;
    }

    @Override
    public JsonObject writeToJSON(CompoundNBT propertiesTag) {
        JsonObject properties = new JsonObject();
        JsonArray effects = new JsonArray();
        for (Effect effect : this.effects()) effects.add(effect.getRegistryName().toString());
        properties.add("effects", effects);
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.REMOVE_EFFECTS.get();
    }
}
