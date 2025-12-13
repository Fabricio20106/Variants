package melonystudios.variants.mixin.item;

import melonystudios.variants.Variants;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class RVItemStackMixin /*extends CapabilityProvider<ItemStack>*/ {
    /*public RVItemStackMixin(Class<ItemStack> stackClass) {
        super(stackClass);
    }

    @Shadow
    public abstract Item getItem();
    @Shadow
    private int count;
    @Shadow
    private CompoundNBT tag;*/

    @SuppressWarnings("deprecation")
    @Inject(method = "appendEnchantmentNames", at = @At("HEAD"), cancellable = true)
    private static void appendEnchantmentNames(List<ITextComponent> tooltip, ListNBT enchantments, CallbackInfo callback) {
        if (!Variants.revaried().settings().updatedEnchantmentTooltips) return;
        callback.cancel();

        for (int i = 0; i < enchantments.size(); ++i) {
            CompoundNBT enchantmentTag = enchantments.getCompound(i);
            Registry.ENCHANTMENT.getOptional(ResourceLocation.tryParse(enchantmentTag.getString("id")))
                    .ifPresent(enchantment -> tooltip.add(new TranslationTextComponent("tooltip.variants.enchant.arrow")
                            .withStyle(TextFormatting.YELLOW)
                            .append(enchantment.getFullname(enchantmentTag.getInt("lvl"))))
                    );
        }
    }

    /*@Inject(method = "save", at = @At("HEAD"), cancellable = true)
    private void save(CompoundNBT tag, CallbackInfoReturnable<CompoundNBT> cir) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(this.getItem());
        tag.putString("id", location == null ? "minecraft:air" : location.toString());
        tag.putInt("count", this.count);
        if (this.tag != null) tag.put("tags", this.tag.copy());
        CompoundNBT capabilitiesTag = this.serializeCaps();
        if (capabilitiesTag != null && !capabilitiesTag.isEmpty()) tag.put("forge_capabilities", capabilitiesTag);
        cir.setReturnValue(tag);
    }

    @Inject(method = "of", at = @At("HEAD"), cancellable = true)
    private static void of(CompoundNBT tag, CallbackInfoReturnable<ItemStack> cir) {
        try {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("id")));
            int count;
            if (tag.contains("Count", Constants.TagTypes.BYTE)) {
                count = tag.getByte("Count");
                tag.remove("Count");
            } else {
                count = tag.getInt("count");
            }
            ItemStack stack = new ItemStack(item, count);

            if (tag.contains("tags", Constants.TagTypes.COMPOUND)) {
                stack.setTag(tag.getCompound("tags"));
                stack.getItem().verifyTagAfterLoad(tag);
            }
            if (stack.getItem().isDamageable(stack)) stack.setDamageValue(stack.getDamageValue());
            cir.setReturnValue(stack);
        } catch (RuntimeException exception) {
            Variants.LOGGER.debug("Tried to load invalid item: {}", tag, exception);
            cir.setReturnValue(new ItemStack(null));
        }
    }*/
}
