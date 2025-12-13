package melonystudios.variants.mixin.item;

import melonystudios.variants.Variants;
import melonystudios.variants.component.Consumable;
import melonystudios.variants.util.tag.VSEnchantmentTags;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.UseAction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShieldItem.class)
public class RVShieldItemMixin extends Item implements Consumable {
    public RVShieldItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void addTagDisplayTooltip(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo callback) {
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) { // ah yes, enchamentment (June 30/6/24)
        return Variants.revaried().settings().enchantableShields ? super.canApplyAtEnchantingTable(stack, enchantment) || enchantment.isIn(VSEnchantmentTags.APPLICABLE_TO_SHIELDS) :
                super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public int getEnchantmentValue() {
        return Variants.revaried().settings().enchantableShields ? 15 : 0;
    }

    @Inject(method = "getUseAnimation", at = @At("HEAD"), cancellable = true)
    @OnlyIn(Dist.CLIENT)
    private void getUseAnimation(ItemStack stack, CallbackInfoReturnable<UseAction> callback) {
        callback.setReturnValue(this.getConsumeAnimation(stack, UseAction.BLOCK));
    }
}
