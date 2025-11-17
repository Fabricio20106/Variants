package melonystudios.variants.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.util.Pair;
import melonystudios.variants.Variants;
import melonystudios.variants.consumable.custom.ApplyMobEffectsBehavior;
import melonystudios.variants.crafting.custom.WoolArmorDyeingRecipe;
import melonystudios.variants.effect.VSEffectInstance;
import melonystudios.variants.event.custom.BehaviorTeleportEvent;
import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static net.minecraft.item.ItemModelsProperties.register;

public class VSUtils {
    private static final List<String> VALID_WOOD_TYPES = Lists.newArrayList("warped", "crimson", "painting", "enderwood");
    public static final RenderSkybox PANORAMA = new RenderSkybox(MainMenuScreen.CUBE_MAP);
    public static final IFormattableTextComponent RESTART_REQUIRED = new TranslationTextComponent("menu.variants.restart_required").withStyle(VSStyles.REVARIED_ACCENT_COLOR_STYLE);
    public static final int DEFAULT_TITLE_HEIGHT = 12;

    /// Puts an item in the player's hands without playing the "Gear equips" sound.
    public static void setItemInHand(PlayerEntity player, Hand hand, ItemStack stack) {
        if (hand == Hand.MAIN_HAND) {
            setItemSlot(player, EquipmentSlotType.MAINHAND, stack);
        } else {
            if (hand != Hand.OFF_HAND) throw new IllegalArgumentException(I18n.get("exception.variants.invalid_hand", hand.toString().toLowerCase(Locale.ROOT)));
            setItemSlot(player, EquipmentSlotType.OFFHAND, stack);
        }
    }

    /// Puts an item any of the player's slots without playing the "Gear equips" sound.
    public static void setItemSlot(PlayerEntity player, EquipmentSlotType slot, ItemStack stack) {
        if (slot == EquipmentSlotType.MAINHAND) {
            player.inventory.items.set(player.inventory.selected, stack);
        } else if (slot == EquipmentSlotType.OFFHAND) {
            player.inventory.offhand.set(0, stack);
        } else if (slot.getType() == EquipmentSlotType.Group.ARMOR) {
            player.inventory.armor.set(slot.getIndex(), stack);
        }
    }

    /** Can be used to add items as a valid dye for dyeing wool armor (currently only sweater).
    * @param dyeItem The item to make usable as a dye for wool armor (can be an item with an object holder);
    * @param color The color this item will apply to the armor, or merge with other colors;
    * @param loadedMod The mod that needs to be loaded for this item to be added to the dyes list.
     */
    public static void woolArmorDyeingColor(Item dyeItem, int color, String loadedMod) {
        WoolArmorDyeingRecipe.DYE_COLORS_MAP = Maps.newHashMap(WoolArmorDyeingRecipe.DYE_COLORS_MAP);
        if (ModList.get().isLoaded(loadedMod)) WoolArmorDyeingRecipe.DYE_COLORS_MAP.put(dyeItem, color);
    }

    /// Adds an item as a villager food (needs to be in {@code #melony:villager_wanted_items} item tag).
    /// @param item The item to make edible for villagers;
    /// @param foodPoints How many food points to decrease the villager's hunger.
    public static void addVillagerFoodItem(Item item, int foodPoints) {
        if (!item.is(VSItemTags.VILLAGER_WANTED_ITEMS)) Variants.LOGGER.info(I18n.get("console.variants.villager_food.item_not_in_tag", I18n.get(item.getDescriptionId()), VSItemTags.VILLAGER_WANTED_ITEMS.getName()));
        VillagerEntity.FOOD_POINTS.put(item, foodPoints);
    }

    public static ResourceLocation minecraft(String name) {
        return new ResourceLocation(name);
    }

    /// Makes a resource location with a custom default namespace (instead of always using "{@code minecraft}").
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

    /// Adds properties for a bow.
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

    /// Adds properties for a shield.
    public static void makeShield(Item shield) {
        register(shield, new ResourceLocation("blocking"), (stack, world, livEntity) -> livEntity != null && livEntity.isUsingItem() && livEntity.getUseItem() == stack ? 1 : 0);
    }

    /// Adds properties for armor designs.
    public static void addArmorDesigns(Item sweater) {
        register(sweater, Variants.variants("design"), (stack, world, livEntity) -> {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("armor_design", Constants.TagTypes.ANY_NUMERIC)) return tag.getInt("armor_design");
            return 0;
        });
    }

    /// Add properties for mob ids for spawner minecarts.
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
                        case "minecraft:pig": return 8;
                        default: return 0;
                    }
                }
            }
            return 0;
        });
    }

    /// Adds properties for exponential stews and stained-glass bottles.
    public static void addTextureIdentifier(Item... items) {
        for (Item item : items) register(item, Variants.variants("texture_id"), (stack, world, livEntity) -> {
            if (stack.getTag() != null && stack.getTag().contains("texture_id", Constants.TagTypes.ANY_NUMERIC)) return stack.getTag().getInt("texture_id");
            return 0;
        });
    }

    public static BehaviorTeleportEvent exactTeleportThroughBehavior(ItemStack stack, World world, LivingEntity livEntity, Vector3d teleportPos) {
        BehaviorTeleportEvent event = new BehaviorTeleportEvent(stack, world, livEntity, teleportPos.x, teleportPos.y, teleportPos.z);
        MinecraftForge.EVENT_BUS.post(event);
        return event;
    }

    public static BehaviorTeleportEvent randomTeleportThroughBehavior(ItemStack stack, World world, LivingEntity livEntity, double teleportX, double teleportY, double teleportZ, float teleportDiameter) {
        BehaviorTeleportEvent.RandomTeleport event = new BehaviorTeleportEvent.RandomTeleport(stack, world, livEntity, teleportX, teleportY, teleportZ, teleportDiameter);
        MinecraftForge.EVENT_BUS.post(event);
        return event;
    }

    /// Adds all the effects a food item gives to its tooltip (using the same style as the {@link ApplyMobEffectsBehavior apply effects} behavior).
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
                tooltip.add(ApplyMobEffectsBehavior.getCategoryTranslation(instance, component.withStyle(VSStyles.getFromRGB(effect.getColor()))));
            }
        }

        if (!attributesList.isEmpty()) {
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
                    tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".food_effects.beneficial_effect", new TranslationTextComponent("attribute.modifier.plus." + modifier.getOperation().toValue(),
                            ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(amount), new TranslationTextComponent(attributePair.getFirst().getDescriptionId())).withStyle(VSStyles.getFromRGB(0x6FC56F))).withStyle(VSStyles.getFromRGB(0x4F7A4F)));
                } else if (baseAmount < 0) {
                    amount = amount * -1;
                    tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".food_effects.harmful_effect", new TranslationTextComponent("attribute.modifier.take." + modifier.getOperation().toValue(),
                            ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(amount), new TranslationTextComponent(attributePair.getFirst().getDescriptionId())).withStyle(VSStyles.getFromRGB(0xD26D6D))).withStyle(VSStyles.getFromRGB(0x7F4B4B)));
                }
            }
        }
    }

    public static List<VSEffectInstance> convertEffectList(List<? extends EffectInstance> effects) {
        List<VSEffectInstance> newEffects = Lists.newArrayList();
        for (EffectInstance instance : effects) newEffects.add(new VSEffectInstance(instance));
        return newEffects;
    }

    public static CompoundNBT saveStack(ItemStack stack, CompoundNBT tag) {
        if (stack == ItemStack.EMPTY) {
            tag.putString("id", "minecraft:air");
            tag.putInt("count", 0);
        } else {
            tag.putString("id", stack.getItem().getRegistryName().toString());
            if (stack.getCount() != 1) tag.putInt("count", stack.getCount());
            if (stack.getTag() != null) tag.put("tags", stack.getTag().copy());
        }
        return tag;
    }

    public static ItemStack loadStack(Item item, int count, CompoundNBT tags) {
        ItemStack stack = new ItemStack(item, count);
        stack.setTag(tags);
        return stack;
    }

    /// Custom stack loading method that supports integer stack counts and string tag parsing.
    public static ItemStack loadStack(CompoundNBT tag) {
        Item item = Items.AIR;
        int count = 1;

        if (tag.contains("id", Constants.TagTypes.STRING)) {
            Item item1 = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(tag.getString("id")));
            if (item1 != null) item = item1;
        }

        if (tag.contains("count", Constants.TagTypes.ANY_NUMERIC)) {
            count = tag.getInt("count");
        }

        ItemStack stack = new ItemStack(item, count);

        if (tag.contains("tags", Constants.TagTypes.STRING)) {
            try {
                CompoundNBT tagsTag = JsonToNBT.parseTag(tag.getString("tags"));
                tag.put("tags", tagsTag);
            } catch (CommandSyntaxException exception) {
                Variants.LOGGER.error(new TranslationTextComponent("error.variants.stack_loading.tag", tag.getString("tags")).getString(), exception.getMessage());
            }
        }

        if (tag.contains("tags", Constants.TagTypes.COMPOUND)) {
            stack.setTag(tag.getCompound("tags"));
        } else if (tag.contains("tag", Constants.TagTypes.COMPOUND)) {
            stack.setTag(tag.getCompound("tag"));
        }
        stack.getItem().verifyTagAfterLoad(tag);

        if (stack.getItem().isDamageable(stack)) stack.setDamageValue(stack.getDamageValue());
        return stack;
    }

    public static String getBoatType(ItemStack stack, String woodType) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("wood_type", Constants.TagTypes.STRING)) {
            if (isValidWoodType(tag.getString("wood_type"))) return tag.getString("wood_type");
        }
        return woodType;
    }

    private static boolean isValidWoodType(String woodType) {
        return VALID_WOOD_TYPES.contains(woodType);
    }

    public static Rarity upRarity(Rarity rarity) {
        switch (rarity) {
            case COMMON:
            case UNCOMMON: return Rarity.RARE;
            case RARE: return Rarity.EPIC;
            case EPIC:
            default: return rarity;
        }
    }
}
