package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.custom.DefaultStewBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSRegistries;
import melonystudios.variants.util.VSUtils;
import melonystudios.variants.util.tag.ConsumeBehaviorTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public interface TagConfigurableFood {
    // Consumable tag (consumable)
    // animation (UseAnimation)
    // consume_seconds (use duration)
    // sound (eating & drinking sound)

    default UseAction getConsumeAnimation(ItemStack stack) {
        return getConsumeAnimation(stack, UseAction.EAT);
    }

    default UseAction getConsumeAnimation(ItemStack stack, UseAction animation) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("animation", Constants.TagTypes.STRING)) {
            return NBTUtils.parseAnimationFromNBT(consumableTag.getString("animation"));
        }
        return animation;
    }

    default SoundEvent getDefaultConsumeSound() {
        return SoundEvents.GENERIC_EAT;
    }

    default int getConsumeTicks(ItemStack stack) {
        return getConsumeTicks(stack, 32);
    }

    default int getConsumeTicks(ItemStack stack, int ticks) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("consume_ticks", Constants.TagTypes.ANY_NUMERIC)) {
            return consumableTag.getInt("consume_ticks");
        }
        return ticks;
    }

    default SoundEvent getConsumeSound(ItemStack stack) {
        return getConsumeSound(stack, SoundEvents.GENERIC_EAT);
    }

    default SoundEvent getConsumeSound(ItemStack stack, SoundEvent sound) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("sound", Constants.TagTypes.STRING)) {
            ResourceLocation soundLocation = new ResourceLocation(consumableTag.getString("sound"));
            if (ForgeRegistries.SOUND_EVENTS.containsKey(soundLocation)) return ForgeRegistries.SOUND_EVENTS.getValue(soundLocation);
        }
        return sound;
    }

    default int getCooldown(ItemStack stack, int cooldown) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("cooldown", Constants.TagTypes.ANY_NUMERIC)) {
            return consumableTag.getInt("cooldown");
        }
        return cooldown;
    }

    default void applyCooldown(ItemStack stack, LivingEntity livEntity, int cooldownTicks) {
        if (livEntity instanceof PlayerEntity) {
            ((PlayerEntity) livEntity).getCooldowns().addCooldown(stack.getItem(), getCooldown(stack, cooldownTicks));
        }
    }

    default boolean hasUseRemainder() {
        return false;
    }

    default ItemStack getDefaultUseRemainder() {
        return new ItemStack(Items.GLASS_BOTTLE);
    }

    default ItemStack getUseRemainder(ItemStack stack) {
        return getUseRemainder(stack, getDefaultUseRemainder());
    }

    default ItemStack getUseRemainder(ItemStack stack, ItemStack remainderStack) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("use_remainder", Constants.TagTypes.COMPOUND)) {
            return VSUtils.loadStack(consumableTag.getCompound("use_remainder"));
        }
        return remainderStack;
    }

    default float getTextureIdentifier(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("texture_id", Constants.TagTypes.ANY_NUMERIC)) {
            return tag.getFloat("texture_id");
        }
        return 0;
    }

    default void executeConsumeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        executeConsumeBehavior(stack, world, livEntity, new DefaultStewBehavior());
    }

    default void executeConsumeBehavior(ItemStack stack, World world, LivingEntity livEntity, StewBehavior behavior) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
            CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
            if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                StewBehavior tagBehavior = VSRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                if (tagBehavior != null && canRunBehavior(tagBehavior)) tagBehavior.executeFromStewNBT(stack, world, livEntity, getBehaviorProperties(stack));
            }
        } else {
            if (canRunBehavior(behavior)) behavior.executeFromStewNBT(stack, world, livEntity, getBehaviorProperties(stack));
        }
    }

    static boolean canRunBehavior(StewBehavior behavior) {
        return !behavior.is(ConsumeBehaviorTags.CANNOT_RUN_WITHOUT_NBT);
    }

    @Nullable
    default CompoundNBT getBehaviorProperties(ItemStack stack) {
        CompoundNBT consumableTag = stack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
            return consumableTag.getCompound("behavior");
        }
        return null;
    }
}
