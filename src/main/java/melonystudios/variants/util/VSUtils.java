package melonystudios.variants.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import melonystudios.variants.Variants;
import melonystudios.variants.crafting.custom.WoolArmorDyeingRecipe;
import melonystudios.variants.item.custom.armor.WoolArmorItem;
import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Map;

import static net.minecraft.item.ItemModelsProperties.register;

public class VSUtils {
    // Puts an item in the player's hands without playing the "Gear equips" sound.
    public static void setItemInHand(PlayerEntity player, Hand hand, ItemStack stack) {
        if (hand == Hand.MAIN_HAND) {
            setItemSlot(player, EquipmentSlotType.MAINHAND, stack);
        } else {
            if (hand != Hand.OFF_HAND) throw new IllegalArgumentException("Invalid hand: " + hand);
            setItemSlot(player, EquipmentSlotType.OFFHAND, stack);
        }
    }

    // Puts an item any of the player's slots without playing the "Gear equips" sound.
    public static void setItemSlot(PlayerEntity player, EquipmentSlotType slot, ItemStack stack) {
        if (slot == EquipmentSlotType.MAINHAND) {
            player.inventory.items.set(player.inventory.selected, stack);
        } else if (slot == EquipmentSlotType.OFFHAND) {
            player.inventory.offhand.set(0, stack);
        } else if (slot.getType() == EquipmentSlotType.Group.ARMOR) {
            player.inventory.armor.set(slot.getIndex(), stack);
        }
    }

    // Can be used to add new wool armor (currently only sweater) colors.
    public static void woolArmorColor(String colorName, int colorCode) {
        WoolArmorItem.COLOR_NAME_TO_CODE = Maps.newHashMap(WoolArmorItem.COLOR_NAME_TO_CODE);
        WoolArmorItem.COLOR_NAME_TO_CODE.put(colorName, colorCode);
    }

    // Can be used to add items as a valid dye for dyeing wool armor (currently only sweater).
    public static void woolArmorDyeingColor(Item dyeItem, int color, String loadedMod) {
        WoolArmorDyeingRecipe.DYE_COLORS_MAP = Maps.newHashMap(WoolArmorDyeingRecipe.DYE_COLORS_MAP);
        if (ModList.get().isLoaded(loadedMod)) WoolArmorDyeingRecipe.DYE_COLORS_MAP.put(dyeItem, color);
    }

    // Adds an item as a villager food (needs to be in #melony:villager_wanted_items item tag).
    public static void addVillagerFoodItem(Item item, int foodPoints) {
        if (!item.is(VSItemTags.VILLAGER_WANTED_ITEMS)) LogManager.getLogger().info(new TranslationTextComponent("console.variants.villager_food.item_not_in_tag", new TranslationTextComponent(item.getDescriptionId()), VSItemTags.VILLAGER_WANTED_ITEMS.getName()).getString());
        VillagerEntity.FOOD_POINTS.put(item, foodPoints);
    }

    public static ResourceLocation minecraft(String name) {
        return new ResourceLocation(name);
    }

    public static ResourceLocation namespace(String namespace, String name) {
        String[] location = decompose(namespace, name);
        if (StringUtils.isEmpty(location[0])) {
            return new ResourceLocation(namespace, location[1]);
        } else return new ResourceLocation(location[0], location[1]);
    }

    protected static String[] decompose(String namespace, String location) {
        String[] stringArray = new String[] {namespace, location};
        int separatorIndex = location.indexOf(':');
        if (separatorIndex >= 0) {
            stringArray[1] = location.substring(separatorIndex + 1);
            if (separatorIndex >= 1) stringArray[0] = location.substring(0, separatorIndex);
        }
        return stringArray;
    }

    // Adds properties for a bow.
    public static void makeBow(Item bow) {
        register(bow, new ResourceLocation("pull"), (stack, world, livEntity) -> {
            if (livEntity == null) {
                return 0;
            } else {
                return livEntity.getUseItem() != stack ? 0 : (float) (stack.getUseDuration() - livEntity.getUseItemRemainingTicks()) / 20;
            }
        });
        register(bow, new ResourceLocation("pulling"), (stack, world, livEntity) -> livEntity != null && livEntity.isUsingItem() && livEntity.getUseItem() == stack ? 1 : 0);
    }

    // Adds properties for a shield.
    public static void makeShield(Item shield) {
        register(shield, new ResourceLocation("blocking"), (stack, world, livEntity) -> livEntity != null && livEntity.isUsingItem() && livEntity.getUseItem() == stack ? 1 : 0);
    }

    // Adds properties for armor designs.
    public static void addArmorDesigns(Item sweater) {
        register(sweater, Variants.variants("design"), (stack, world, livEntity) -> {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("armor_design", Constants.TagTypes.INTEGER)) return tag.getInt("armor_design");
            return 0;
        });
    }

    // Add properties for mob ids for spawner minecarts.
    public static void addSpawnerMinecartMobs(Item spawnerMinecart) {
        register(spawnerMinecart, Variants.variants("mob_id"), (stack, world, livEntity) -> {
            CompoundNBT spawnData = stack.getTagElement("spawn_data");
            if (spawnData != null && spawnData.contains("SpawnData", Constants.TagTypes.COMPOUND)) {
                CompoundNBT subSpawnData = spawnData.getCompound("SpawnData");
                if (subSpawnData.contains("id", Constants.TagTypes.STRING)) {
                    switch (subSpawnData.getString("id")) {
                        case "minecraft:zombie": return 1;
                        case "minecraft:skeleton": return 2;
                        case "minecraft:spider": return 3;
                        case "minecraft:cave_spider": return 4;
                        case "minecraft:silverfish": return 5;
                        case "minecraft:blaze": return 6;
                        case "minecraft:magma_cube": return 7;
                        default: return 0;
                    }
                }
            }
            return 0;
        });
    }

    // Adds properties for exponential stews.
    public static void makeExpoStew(Item expoStew) {
        register(expoStew, Variants.variants("texture_id"), (stack, world, livEntity) -> {
            CompoundNBT bowlTag = stack.getTagElement("bowl");
            if (bowlTag != null && bowlTag.contains("texture_id", Constants.TagTypes.INTEGER)) return bowlTag.getInt("texture_id");
            return 0;
        });
    }

    @OnlyIn(Dist.CLIENT)
    public static void addEffectsTooltip(ItemStack stack, List<ITextComponent> tooltip, float durationFactor) {
        List<Pair<EffectInstance, Float>> effectsList = stack.getItem().getFoodProperties().getEffects();
        List<Pair<Attribute, AttributeModifier>> attributesList = Lists.newArrayList();
        if (!effectsList.isEmpty()) {
            for (Pair<EffectInstance, Float> instancePair : effectsList) {
                EffectInstance instance = instancePair.getFirst();
                IFormattableTextComponent component = new TranslationTextComponent(instance.getDescriptionId());
                Effect effect = instance.getEffect();
                Map<Attribute, AttributeModifier> attributeMap = effect.getAttributeModifiers();
                if (!attributeMap.isEmpty()) {
                    for (Map.Entry<Attribute, AttributeModifier> entry : attributeMap.entrySet()) {
                        AttributeModifier modifier = entry.getValue();
                        AttributeModifier newModifier = new AttributeModifier(modifier.getName(), effect.getAttributeModifierValue(instance.getAmplifier(), modifier), modifier.getOperation());
                        attributesList.add(new Pair<>(entry.getKey(), newModifier));
                    }
                }

                if (instance.getAmplifier() > 0) component = new TranslationTextComponent("potion.withAmplifier", component, new TranslationTextComponent("potion.potency." + instance.getAmplifier()));
                if (instance.getDuration() > 20) component = new TranslationTextComponent("potion.withDuration", component, EffectUtils.formatDuration(instance, durationFactor));
                tooltip.add(component.withStyle(effect.getCategory().getTooltipFormatting()));
            }
        }

        if (!attributesList.isEmpty()) {
            tooltip.add(StringTextComponent.EMPTY);
            String correctType = stack.getItem().getUseAnimation(stack) == UseAction.DRINK ? "when_drank" : "when_eaten";
            tooltip.add(new TranslationTextComponent("tooltip.variants.food_effects." + correctType).withStyle(TextFormatting.GRAY));

            for (Pair<Attribute, AttributeModifier> attributePair : attributesList) {
                AttributeModifier modifier = attributePair.getSecond();
                double baseAmount = modifier.getAmount();
                double amount;

                if (modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    amount = modifier.getAmount();
                } else {
                    amount = modifier.getAmount() * 100;
                }

                if (baseAmount > 0) {
                    tooltip.add(new TranslationTextComponent("attribute.modifier.plus." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(amount), new TranslationTextComponent(attributePair.getFirst().getDescriptionId())).withStyle(TextFormatting.BLUE));
                } else if (baseAmount < 0) {
                    amount = amount * -1;
                    tooltip.add(new TranslationTextComponent("attribute.modifier.take." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(amount), new TranslationTextComponent(attributePair.getFirst().getDescriptionId())).withStyle(TextFormatting.RED));
                }
            }
        }
    }
}
