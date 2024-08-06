package melonystudios.variants.mixin.loot;

import com.google.common.collect.Iterables;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.util.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.SetStewEffect;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Random;

@Mixin(SetStewEffect.class)
public class VSSetStewEffectMixin {
    @Shadow
    @Final
    private Map<Effect, RandomValueRange> effectDurationMap;

    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    private void run(ItemStack stewStack, LootContext context, CallbackInfoReturnable<ItemStack> cir) {
        if (stewStack.getItem() instanceof ExponentialStewItem && !this.effectDurationMap.isEmpty()) {
            Random rand = context.getRandom();
            int mapSize = rand.nextInt(this.effectDurationMap.size());
            Map.Entry<Effect, RandomValueRange> effectEntry = Iterables.get(this.effectDurationMap.entrySet(), mapSize);
            Effect effect = effectEntry.getKey();
            int duration = effectEntry.getValue().getInt(rand);
            if (!effect.isInstantenous()) duration *= 20;

            cir.setReturnValue(writeEffectToStew(stewStack, effect, duration));
        }
    }

    @Unique
    private static ItemStack writeEffectToStew(ItemStack stewStack, Effect effect, int duration) {
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        CompoundNBT propertiesTag = behaviorTag.getCompound("properties");
        ListNBT effectList = propertiesTag.getList("effects", Constants.TagTypes.LIST);

        CompoundNBT tag = new CompoundNBT();
        tag.putString("id", effect.getRegistryName().toString());
        tag.putInt("duration", duration);
        effectList.add(tag);
        propertiesTag.put("effects", effectList);
        behaviorTag.putString("id", VSStewBehaviors.APPLY_MOB_EFFECTS.get().getRegistryName().toString());
        behaviorTag.put("properties", propertiesTag);
        return stewStack;
    }
}
