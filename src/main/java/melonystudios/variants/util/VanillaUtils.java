package melonystudios.variants.util;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class VanillaUtils {
    public static ItemStack setPotion(ItemStack stack, Potion potion) {
        ResourceLocation potionLocation = ForgeRegistries.POTION_TYPES.getKey(potion);
        if (potion == Potions.EMPTY) stack.removeTagKey("potion");
        else stack.getOrCreateTag().putString("potion", potionLocation.toString());

        if (!potion.getEffects().isEmpty()) NBTUtils.writeEffectsOntoNBT(VSUtils.convertEffectList(potion.getEffects()));

        return stack;
    }
}
