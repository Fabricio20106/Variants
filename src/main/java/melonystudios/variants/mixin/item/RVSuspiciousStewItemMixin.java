package melonystudios.variants.mixin.item;

import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SuspiciousStewItem.class)
public class RVSuspiciousStewItemMixin extends Item {
    public RVSuspiciousStewItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "saveMobEffect", at = @At("HEAD"), cancellable = true)
    private static void saveEffectToStew(ItemStack stewStack, Effect effect, int duration, CallbackInfo callback) {
        callback.cancel();
        CompoundNBT tag = stewStack.getOrCreateTag();
        ListNBT effectList = tag.getList("effects", Constants.TagTypes.COMPOUND);
        CompoundNBT effectTag = new CompoundNBT();

        effectTag.putString("id", effect.getRegistryName().toString());
        effectTag.putInt("duration", duration);
        effectList.add(effectTag);
        tag.put("effects", effectList);
    }

    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    private void finishConsumingStew(ItemStack stewStack, World world, LivingEntity livEntity, CallbackInfoReturnable<ItemStack> callback) {
        callback.cancel();
        ItemStack superStack = super.finishUsingItem(stewStack, world, livEntity);
        boolean isPlayerInCreative = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;
        CompoundNBT tag = stewStack.getTag();

        if (tag != null && tag.contains("effects", Constants.TagTypes.LIST)) {
            ListNBT effectList = tag.getList("effects", Constants.TagTypes.COMPOUND);

            for (int i = 0; i < effectList.size(); ++i) NBTUtils.addEffectsFromNBT(effectList.getCompound(i), world, livEntity);
        }
        callback.setReturnValue(isPlayerInCreative ? superStack : new ItemStack(Items.BOWL));
    }
}
