package melonystudios.variants.mixin.item;

import com.mojang.blaze3d.systems.RenderSystem;
import melonystudios.variants.Variants;
import melonystudios.variants.component.Equippable;
import melonystudios.variants.component.Consumable;
import melonystudios.variants.item.fix.TagFix;
import melonystudios.variants.util.*;
import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public abstract class RVItemMixin implements Consumable, Equippable, IForgeItem {
    @OnlyIn(Dist.CLIENT)
    @Inject(method = "appendHoverText", at = @At("HEAD"))
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo callback) {
        if (flag.isAdvanced() && stack.getTag() != null && Variants.revaried().settings().showTagsWithAlt) {
            boolean shouldHideTooltip = NBTUtils.shouldNotHideTooltip("hide_item_tags", stack);
            if (shouldHideTooltip && !VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt", VSKeys.getTranslation(VSKeys.SHOW_TAGS_KEY).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY));
            if (shouldHideTooltip && VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt", VSKeys.getTranslation(VSKeys.SHOW_TAGS_KEY).withStyle(TextFormatting.WHITE)).withStyle(TextFormatting.DARK_GRAY));
            if (shouldHideTooltip && VSKeys.isAltDown()) NBTUtils.addItemTagsTooltip(stack, tooltip, flag);
        }
        if (stack.getItem().getFoodProperties() != null && !stack.getItem().getFoodProperties().getEffects().isEmpty() && Variants.revaried().settings().foodEffectsTooltip) {
            if (!ItemTags.getAllTags().getAllTags().isEmpty()) {
                if (!stack.getItem().is(VSItemTags.HIDE_EFFECT_TOOLTIP)) VSUtils.addEffectsTooltip(stack, tooltip, 1);
            }
        }
    }

    @Inject(method = "verifyTagAfterLoad", at = @At("HEAD"))
    public void applyLoadTagFixes(CompoundNBT tag, CallbackInfoReturnable<Boolean> callback) {
        TagFix.getApplicableFixesOnLoad(this.getItem()).forEach(fix -> fix.applyFix(tag));
    }

    @Inject(method = "isFoil", at = @At("HEAD"), cancellable = true)
    public void isFoil(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {
        callback.setReturnValue(ComponentUtils.enchantmentGlintOverride(stack, stack.isEnchanted()));
    }

    // disable this for now to prioritize Stancements ~isa 28-5-25
    /*@Inject(method = "getRarity", at = @At("HEAD"), cancellable = true)
    public void getRarity(ItemStack stack, CallbackInfoReturnable<Rarity> callback) {
        Rarity rarity = ComponentUtils.rarity(stack, this.rarity);
        if (stack.isEnchanted()) rarity = VSUtils.upRarity(rarity);
        if (callback.getReturnValue() != rarity) callback.setReturnValue(rarity);
    }*/

    @Inject(method = "getUseAnimation", at = @At("HEAD"), cancellable = true)
    public void getUseAnimation(ItemStack stack, CallbackInfoReturnable<UseAction> callback) {
        UseAction animation = stack.isEdible() ? UseAction.EAT : UseAction.NONE;
        callback.setReturnValue(this.getConsumeAnimation(stack, animation));
    }

    @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
    public void getUseDuration(ItemStack stack, CallbackInfoReturnable<Integer> callback) {
        Food properties = stack.getItem().getFoodProperties();
        if (properties != null && properties.isFastFood()) callback.setReturnValue(this.getConsumeTicks(stack, 16));
        callback.setReturnValue(this.getConsumeTicks(stack));
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity livEntity) {
        return this.glider(stack) && stack.getDamageValue() < stack.getMaxDamage() - 1;
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity livEntity, int flightTicks) {
        if (!livEntity.level.isClientSide && (flightTicks + 1) % 20 == 0) {
            stack.hurtAndBreak(1, livEntity, livEntity1 -> livEntity1.broadcastBreakEvent(Equippable.getSlot(stack, EquipmentSlotType.CHEST)));
        }
        return true;
    }

    @Override
    public void renderHelmetOverlay(ItemStack stack, PlayerEntity player, int width, int height, float partialTicks) {
        ResourceLocation cameraOverlay = this.getCameraOverlay(stack, null);
        if (cameraOverlay != null) {
            cameraOverlay = new ResourceLocation(cameraOverlay.getNamespace(), "textures/" + cameraOverlay.getPath() + ".png");
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.defaultBlendFunc();
            Minecraft.getInstance().getTextureManager().bind(cameraOverlay);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuilder();
            buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
            buffer.vertex(0, height, -90).uv(0, 1).endVertex();
            buffer.vertex(width, height, -90).uv(1, 1).endVertex();
            buffer.vertex(width, 0, -90).uv(1, 0).endVertex();
            buffer.vertex(0, 0, -90).uv(0, 0).endVertex();
            tessellator.end();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
        }
    }
}
