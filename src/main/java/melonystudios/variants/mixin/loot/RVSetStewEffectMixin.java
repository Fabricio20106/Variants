package melonystudios.variants.mixin.loot;

import com.google.common.collect.Iterables;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
import melonystudios.variants.consumable.VSConsumeBehaviors;
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
public class RVSetStewEffectMixin {
    @Shadow
    @Final
    private Map<Effect, RandomValueRange> effectDurationMap;

    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    private void run(ItemStack stack, LootContext context, CallbackInfoReturnable<ItemStack> callback) {
        if (stack.getItem() instanceof ExponentialStewItem && !this.effectDurationMap.isEmpty()) {
            Random rand = context.getRandom();
            int mapSize = rand.nextInt(this.effectDurationMap.size());
            Map.Entry<Effect, RandomValueRange> effectEntry = Iterables.get(this.effectDurationMap.entrySet(), mapSize);
            Effect effect = effectEntry.getKey();
            int duration = effectEntry.getValue().getInt(rand);
            if (!effect.isInstantenous()) duration *= 20;

            callback.setReturnValue(writeEffectToStew(stack, effect, duration));
        }
    }

    @Unique
    private static ItemStack writeEffectToStew(ItemStack stewStack, Effect effect, int duration) {
        CompoundNBT consumableTag = stewStack.getOrCreateTagElement("consumable");
        CompoundNBT behaviorTag = new CompoundNBT();
        ListNBT effectList = new ListNBT();
        CompoundNBT effectTag = new CompoundNBT();

        effectTag.putString("id", effect.getRegistryName().toString());
        effectTag.putInt("duration", duration);
        effectList.add(effectTag);

        behaviorTag.put("effects", effectList);
        behaviorTag.putString("id", VSConsumeBehaviors.APPLY_MOB_EFFECTS.get().getRegistryName().toString());

        consumableTag.put("behavior", behaviorTag);
        return stewStack;
    }
}
