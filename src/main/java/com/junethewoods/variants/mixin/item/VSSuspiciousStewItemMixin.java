package com.junethewoods.variants.mixin.item;

import com.junethewoods.variants.util.NBTUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SuspiciousStewItem.class)
public class VSSuspiciousStewItemMixin extends Item {
    public VSSuspiciousStewItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "saveMobEffect", at = @At("HEAD"), cancellable = true)
    private static void saveEffectToStew(ItemStack stewStack, Effect effect, int duration, CallbackInfo ci) {
        ci.cancel();
        CompoundNBT tag = stewStack.getOrCreateTag();
        ListNBT effectList = tag.getList("effects", 9);
        CompoundNBT effectTag = new CompoundNBT();

        effectTag.putString("id", effect.getRegistryName().toString());
        effectTag.putInt("duration", duration);
        effectList.add(effectTag);
        tag.put("effects", effectList);
    }

    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    private void finishConsumingStew(ItemStack stewStack, World world, LivingEntity livEntity, CallbackInfoReturnable<ItemStack> cir) {
        cir.cancel();
        ItemStack superStack = super.finishUsingItem(stewStack, world, livEntity);
        boolean isPlayerInCreative = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;
        CompoundNBT tag = stewStack.getTag();

        if (tag != null && tag.contains("effects", NBTUtils.LIST)) {
            ListNBT effectList = tag.getList("effects", NBTUtils.COMPOUND);

            for (int i = 0; i < effectList.size(); ++i) {
                int duration = 160; // Default of 8 seconds from Suspicious Stew.
                int amplifier = 0;
                boolean ambient = false;
                boolean showParticles = true;
                boolean showIcon = true;
                boolean noCounter = true;
                CompoundNBT effectTag = effectList.getCompound(i);
                if (effectTag.contains("duration", NBTUtils.INTEGER)) duration = effectTag.getInt("duration");
                if (effectTag.contains("amplifier", NBTUtils.INTEGER)) amplifier = effectTag.getInt("amplifier");
                if (effectTag.contains("ambient", NBTUtils.BYTE)) ambient = effectTag.getBoolean("ambient");
                if (effectTag.contains("show_particles", NBTUtils.BYTE)) showParticles = effectTag.getBoolean("show_particles");
                if (effectTag.contains("show_icon", NBTUtils.BYTE)) showIcon = effectTag.getBoolean("show_icon");
                if (effectTag.contains("no_counter", NBTUtils.BYTE)) noCounter = effectTag.getBoolean("no_counter");

                Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
                if (effect != null) {
                    EffectInstance instance = new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon);
                    if (world.isClientSide) instance.setNoCounter(noCounter);
                    livEntity.addEffect(instance);
                }
            }
        }
        cir.setReturnValue(isPlayerInCreative ? superStack : new ItemStack(Items.BOWL));
    }
}
