package com.junethewoods.variants.item.tool;

import java.util.function.Supplier;
import com.junethewoods.variants.Variants;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

public enum VSArmors implements IArmorMaterial {
    EQUIPMENT_SLOT_TYPE(Variants.MOD_ID + ":empty_armor_slot", 15, new int[] {2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0f, 0f, () -> Ingredient.of(Tags.Items.INGOTS_IRON)),
    PHANTOM(Variants.MOD_ID + ":phantom", 5, new int[] {1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.of(Items.PHANTOM_MEMBRANE)),
    RABBIT(Variants.MOD_ID + ":rabbit", 5, new int[] {1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.of(Items.RABBIT_HIDE)),
    WOOL(Variants.MOD_ID + ":wool", 5, new int[] {1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.of(ItemTags.WOOL)),
    EMERALD(Variants.MOD_ID + ":emerald", 33, new int[] {3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> Ingredient.of(Tags.Items.GEMS_EMERALD));

    private static final int[] MAX_DAMAGE_ARRAY = new int[] {13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackRes;
    private final LazyValue<Ingredient> repairIngredient;

    VSArmors(String name, int durabilityMultiplier, int[] slotProtections, int enchValue, SoundEvent equipSound, float toughness, float knockbackRes, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackRes = knockbackRes;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.slotProtections[slot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    // Gets the percentage of knockback resistance provided by armor of the material.
    public float getKnockbackResistance() {
        return this.knockbackRes;
    }
}
