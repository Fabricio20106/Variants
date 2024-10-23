package melonystudios.variants.util.food;

import com.mojang.datafixers.util.Pair;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;

public class TagConfigurableFoodBuilder {
    public static CompoundNBT saveToNBT(Food food, ItemStack stack, CompoundNBT tag) {
        tag.putInt("nutrition", food.getNutrition());
        tag.putFloat("saturation_modifier", food.getSaturationModifier());
        if (food.isMeat()) tag.putBoolean("meat", true);
        if (food.canAlwaysEat()) tag.putBoolean("can_always_eat", true);
        if (food.isFastFood()) tag.putBoolean("fast_to_eat", true);

        CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");
        CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
        ListNBT effectList = new ListNBT();

        for (Pair<EffectInstance, Float> effectPair : food.getEffects()) {
            CompoundNBT effectTag = NBTUtils.writeEffectToNBT(effectPair.getFirst());
            if (effectPair.getSecond() != 1) effectTag.putFloat("chance", effectPair.getSecond());
            effectList.add(effectTag);
        }
        behaviorTag.put("effects", effectList);
        return tag;
    }

    public static Food loadFromNBT(CompoundNBT tag) {
        Food.Builder builder = new Food.Builder();

        builder.nutrition(tag.getInt("nutrition"));
        builder.saturationMod(tag.getFloat("saturation_modifier"));
        if (tag.contains("meat", Constants.TagTypes.ANY_NUMERIC)) builder.meat();
        if (tag.contains("can_always_eat", Constants.TagTypes.ANY_NUMERIC)) builder.alwaysEat();
        if (tag.contains("fast_to_eat", Constants.TagTypes.ANY_NUMERIC)) builder.fast();
        return builder.build();
    }
}
