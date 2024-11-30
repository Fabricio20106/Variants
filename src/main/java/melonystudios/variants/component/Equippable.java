package melonystudios.variants.component;

import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.registries.ForgeRegistries;

public interface Equippable {
    // allowed_entities = would return a list of entity types (or list of resource locations) because it can be an entity type, list of entity types or an entity type tag.

    static EquipmentSlotType getSlot(ItemStack stack) {
        return getSlot(stack, EquipmentSlotType.MAINHAND);
    }

    static EquipmentSlotType getSlot(ItemStack stack, EquipmentSlotType slotType) {
        CompoundNBT equippableTag = stack.getTagElement("equippable");
        if (equippableTag != null && equippableTag.contains("slot", Constants.TagTypes.STRING)) {
            return NBTUtils.parseSlotFromNBT(equippableTag.getString("slot"));
        }
        return slotType;
    }

    static SoundEvent getEquipSound(ItemStack stack) {
        return getEquipSound(stack, SoundEvents.ARMOR_EQUIP_GENERIC);
    }

    static SoundEvent getEquipSound(ItemStack stack, SoundEvent sound) {
        CompoundNBT equippableTag = stack.getTagElement("equippable");
        if (equippableTag != null && equippableTag.contains("equip_sound", Constants.TagTypes.STRING)) {
            ResourceLocation soundLocation = new ResourceLocation(equippableTag.getString("equip_sound"));
            if (ForgeRegistries.SOUND_EVENTS.containsKey(soundLocation)) return ForgeRegistries.SOUND_EVENTS.getValue(soundLocation);
        }
        return sound;
    }

    default ResourceLocation getAssetID(ItemStack stack, ResourceLocation materialName) {
        CompoundNBT equippableTag = stack.getTagElement("equippable");
        if (equippableTag != null && equippableTag.contains("asset_id", Constants.TagTypes.STRING)) {
            return new ResourceLocation(equippableTag.getString("asset_id"));
        }
        return materialName;
    }

    default boolean isDispensable(ItemStack stack) {
        return isDispensable(stack, true);
    }

    default boolean isDispensable(ItemStack stack, boolean dispensable) {
        CompoundNBT equippableTag = stack.getTagElement("equippable");
        if (equippableTag != null && equippableTag.contains("dispensable", Constants.TagTypes.ANY_NUMERIC)) {
            return equippableTag.getBoolean("dispensable");
        }
        return dispensable;
    }

    default boolean isSwappable(ItemStack stack) {
        return isSwappable(stack, true);
    }

    default boolean isSwappable(ItemStack stack, boolean swappable) {
        CompoundNBT equippableTag = stack.getTagElement("equippable");
        if (equippableTag != null && equippableTag.contains("swappable", Constants.TagTypes.ANY_NUMERIC)) {
            return equippableTag.getBoolean("swappable");
        }
        return swappable;
    }

    default boolean damagesOnHurt(ItemStack stack) {
        return damagesOnHurt(stack, true);
    }

    default boolean damagesOnHurt(ItemStack stack, boolean damageOnHurt) {
        CompoundNBT equippableTag = stack.getTagElement("equippable");
        if (equippableTag != null && equippableTag.contains("damage_on_hurt", Constants.TagTypes.ANY_NUMERIC)) {
            return equippableTag.getBoolean("damage_on_hurt");
        }
        return damageOnHurt;
    }

    default ResourceLocation getCameraOverlay(ItemStack stack, ResourceLocation overlayLocation) {
        CompoundNBT equippableTag = stack.getTagElement("equippable");
        if (equippableTag != null && equippableTag.contains("camera_overlay", Constants.TagTypes.STRING)) {
            return new ResourceLocation(equippableTag.getString("camera_overlay"));
        }
        return overlayLocation;
    }

    default boolean glider(ItemStack stack) {
        return stack.getTag() != null && stack.getTag().contains("glider", Constants.TagTypes.COMPOUND);
    }
}
