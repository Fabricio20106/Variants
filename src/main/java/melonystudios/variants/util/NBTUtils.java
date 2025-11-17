package melonystudios.variants.util;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import melonystudios.variants.Variants;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.effect.VSEffectInstance;
import melonystudios.variants.util.damage.DamageSourceUtils;
import melonystudios.variants.util.damage.custom.DamageBehaviorSource;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class NBTUtils {
    public static final Codec<ItemStack> ITEM_STACK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registry.ITEM.fieldOf("id").forGetter(ItemStack::getItem),
            Codec.INT.fieldOf("count").orElse(1).forGetter(ItemStack::getCount),
            CompoundNBT.CODEC.fieldOf("tags").forGetter(ItemStack::getTag)
    ).apply(instance, VSUtils::loadStack));

    public static boolean shouldNotHideTooltip(String toHide, ItemStack stack) {
        if (stack.getTag() != null && stack.getTag().contains(toHide)) {
            return !stack.getTag().getBoolean(toHide);
        }
        return true;
    }

    public static void addHidingTag(String toHide, ItemStack stack) {
        stack.getOrCreateTag().putBoolean(toHide, true);
    }

    public static void addItemTagsTooltip(ItemStack stack, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (flag.isAdvanced()) {
            CompoundNBT tagTag = stack.getTag();
            if (tagTag != null) {
                if (VSConfigs.COMMON_CONFIGS.lineBreaksOnAltTags.get()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".tags", tagTag.getPrettyDisplay(" ", 0)).withStyle(TextFormatting.GRAY));
                else tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".tags", tagTag.getPrettyDisplay()).withStyle(TextFormatting.GRAY));
            }
        }
    }

    public static ItemStack copyDataFromEntity(ItemStack stack, Entity entity) {
        if (entity.hasCustomName()) stack.setHoverName(entity.getCustomName());
        return stack;
    }

    public static void addEffectsFromNBT(CompoundNBT effectTag, World world, LivingEntity livEntity) {
        addEffectsFromNBT(effectTag, world, livEntity, null);
    }

    public static void addEffectsFromNBT(CompoundNBT effectTag, World world, LivingEntity livEntity, Effect backupEffect) {
        float chance = anyNumericOrFloatDefault("chance", effectTag, 1);
        if (world.random.nextFloat() < chance) {
            int duration = 160;
            int amplifier = 0;
            boolean ambient = false;
            boolean showParticles = true;
            boolean showIcon = true;
            boolean noCounter = false;

            if (effectTag.contains("duration", Constants.TagTypes.ANY_NUMERIC)) duration = effectTag.getInt("duration");
            if (effectTag.contains("amplifier", Constants.TagTypes.ANY_NUMERIC)) amplifier = effectTag.getInt("amplifier");
            if (effectTag.contains("ambient", Constants.TagTypes.ANY_NUMERIC)) ambient = effectTag.getBoolean("ambient");
            if (effectTag.contains("show_particles", Constants.TagTypes.ANY_NUMERIC)) showParticles = effectTag.getBoolean("show_particles");
            if (effectTag.contains("show_icon", Constants.TagTypes.ANY_NUMERIC)) showIcon = effectTag.getBoolean("show_icon");
            if (effectTag.contains("no_counter", Constants.TagTypes.ANY_NUMERIC)) noCounter = effectTag.getBoolean("no_counter");

            Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
            if (effect == null && backupEffect != null) effect = backupEffect;
            if (effect != null) {
                EffectInstance instance = new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon);
                if (world.isClientSide) instance.setNoCounter(noCounter);
                if (instance.getEffect().isInstantenous()) instance.getEffect().applyInstantenousEffect(livEntity, livEntity, livEntity, instance.getAmplifier(), 1);
                else livEntity.addEffect(instance);
            }
        }
    }

    public static ListNBT writeEffectsOntoNBT(List<VSEffectInstance> instances) {
        ListNBT effectsList = new ListNBT();
        if (instances != null) {
            for (EffectInstance instance : instances) effectsList.add(writeEffectToNBT(instance));
        }
        return effectsList;
    }

    public static CompoundNBT writeEffectToNBT(EffectInstance instance) {
        CompoundNBT effectTag = new CompoundNBT();

        effectTag.putString("id", instance.getEffect().getRegistryName().toString());
        effectTag.putInt("duration", instance.getDuration());
        if (instance.getAmplifier() > 0) effectTag.putInt("amplifier", instance.getAmplifier());
        if (instance.isAmbient()) effectTag.putBoolean("ambient", instance.isAmbient());
        if (!instance.isVisible()) effectTag.putBoolean("show_particles", instance.isVisible());
        if (!instance.showIcon()) effectTag.putBoolean("show_icon", instance.showIcon());
        if (instance.isNoCounter()) effectTag.putBoolean("no_counter", instance.isNoCounter());
        if (instance instanceof VSEffectInstance) {
            float chance = ((VSEffectInstance) instance).getChance();
            if (chance < 1) effectTag.putFloat("chance", chance);
        }
        return effectTag;
    }

    @Nullable
    public static List<EffectInstance> getEffectsFromNBT(@Nullable World world, ItemStack stewStack) {
        CompoundNBT consumableTag = stewStack.getTagElement("consumable");
        if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
            CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
            List<EffectInstance> effects = Lists.newArrayList();
            if (behaviorTag.contains("effects", Constants.TagTypes.LIST)) {
                ListNBT effectList = behaviorTag.getList("effects", Constants.TagTypes.COMPOUND);

                for (int i = 0; i < effectList.size(); ++i) {
                    List<ItemStack> curativeItemsTemplate = Lists.newArrayList(new ItemStack(Items.MILK_BUCKET));

                    int duration = 160; // Default of 8 seconds from Suspicious Stew.
                    int amplifier = 0;
                    boolean ambient = false;
                    boolean showParticles = true;
                    boolean showIcon = true;
                    boolean noCounter = false; // Finally found out what no_counter does, it just hides the effect duration (shows up as **:**).
                    float chance = 1;
                    List<ItemStack> curativeItems = Lists.newArrayList();
                    CompoundNBT effectTag = effectList.getCompound(i);
                    if (effectTag.contains("duration", Constants.TagTypes.ANY_NUMERIC)) duration = effectTag.getInt("duration");
                    if (effectTag.contains("amplifier", Constants.TagTypes.ANY_NUMERIC)) amplifier = effectTag.getInt("amplifier");
                    if (effectTag.contains("ambient", Constants.TagTypes.ANY_NUMERIC)) ambient = effectTag.getBoolean("ambient");
                    if (effectTag.contains("show_particles", Constants.TagTypes.ANY_NUMERIC)) showParticles = effectTag.getBoolean("show_particles");
                    if (effectTag.contains("show_icon", Constants.TagTypes.ANY_NUMERIC)) showIcon = effectTag.getBoolean("show_icon");
                    if (effectTag.contains("no_counter", Constants.TagTypes.ANY_NUMERIC)) noCounter = effectTag.getBoolean("no_counter");
                    if (effectTag.contains("chance", Constants.TagTypes.ANY_NUMERIC)) chance = effectTag.getFloat("chance");
                    if (effectTag.contains("curative_items", Constants.TagTypes.LIST)) {
                        ListNBT curativeList = effectTag.getList("curative_items", Constants.TagTypes.COMPOUND);
                        for (int c = 0; c < curativeList.size(); c++) curativeItems.add(VSUtils.loadStack(curativeList.getCompound(c)));
                    }

                    Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
                    if (effect != null) {
                        VSEffectInstance instance = new VSEffectInstance(() -> effect, duration, amplifier, ambient, showParticles, showIcon).withChance(chance);
                        if (world != null && world.isClientSide) instance.setNoCounter(noCounter);
                        if (!curativeItems.isEmpty() && !curativeItems.equals(curativeItemsTemplate)) instance.setCurativeItems(curativeItems);
                        effects.add(instance);
                    }
                }
                return effects;
            }
        }
        return null;
    }

    public static void writeDamageSourceOntoNBT(CompoundNBT propertiesTag, DamageSource source) {
        if (source instanceof DamageBehaviorSource) {
            CompoundNBT sourceTag = new CompoundNBT();
            sourceTag.putString("message_id", source.msgId);
            sourceTag.putFloat("food_exhaustion", source.getFoodExhaustion());
            sourceTag.putString("scaling", ((DamageBehaviorSource) source).scaling.getSerializedName());
            sourceTag.putString("death_message_type", ((DamageBehaviorSource) source).deathMessageType.getSerializedName());
            if (source.isExplosion()) sourceTag.putBoolean("is_explosion", source.isExplosion());
            if (source.isProjectile()) sourceTag.putBoolean("is_projectile", source.isProjectile());
            if (source.isMagic()) sourceTag.putBoolean("is_magic", source.isMagic());
            if (source.isFire()) sourceTag.putBoolean("is_fire", source.isFire());
            if (source.isBypassArmor()) sourceTag.putBoolean("bypasses_armor", source.isBypassArmor());
            if (source.isBypassInvul()) sourceTag.putBoolean("bypasses_invulnerability", source.isBypassInvul());
            if (source.isBypassMagic()) sourceTag.putBoolean("bypasses_magic", source.isBypassMagic());
            propertiesTag.put("source", sourceTag);
        } else {
            Map<ResourceLocation, DamageSource> combinedSourcesMap = new HashMap<>(DamageSourceUtils.DATA_DRIVEN_SOURCES);
            for (ResourceLocation location : DamageSourceUtils.VALID_DAMAGE_SOURCES) combinedSourcesMap.put(location, DamageSource.GENERIC);
            List<ResourceLocation> locations = combinedSourcesMap.keySet().stream().filter(location -> location.equals(new ResourceLocation(source.msgId))).collect(Collectors.toList());
            if (!locations.isEmpty()) propertiesTag.putString("source", locations.get(0).toString());
        }
    }

    public static EquipmentSlotType parseSlotFromNBT(String slotType) {
        switch (slotType) {
            case "head": return EquipmentSlotType.HEAD;
            case "chest": return EquipmentSlotType.CHEST;
            case "legs": return EquipmentSlotType.LEGS;
            case "feet": return EquipmentSlotType.FEET;
            case "offhand": return EquipmentSlotType.OFFHAND;
            case "mainhand": default: return EquipmentSlotType.MAINHAND;
        }
    }

    public static UseAction parseAnimationFromNBT(String animation) {
        switch (animation) {
            case "none": return UseAction.NONE;
            case "drink": return UseAction.DRINK;
            case "block": return UseAction.BLOCK;
            case "bow": return UseAction.BOW;
            case "trident": case "spear": return UseAction.SPEAR;
            case "crossbow": return UseAction.CROSSBOW;
            case "eat": default: return UseAction.EAT;
        }
    }

    public static Explosion.Mode parseBlockInteractionFromString(String interaction) {
        switch (interaction) {
            case "break": return Explosion.Mode.BREAK;
            case "destroy": return Explosion.Mode.DESTROY;
            case "none": default: return Explosion.Mode.NONE;
        }
    }

    public static BlockPos readBlockPos(CompoundNBT tag, String tagName) {
        int[] pos = tag.getIntArray(tagName);
        if (pos.length <= 3) return new BlockPos(pos[0], pos[1], pos[2]);
        return new BlockPos(0, 0, 0);
    }

    public static Vector3d readVec3(CompoundNBT tag, String tagName) {
        ListNBT pos = tag.getList(tagName, Constants.TagTypes.DOUBLE);
        if (pos.size() <= 3) return new Vector3d(pos.getDouble(0), pos.getDouble(1), pos.getDouble(2));
        return new Vector3d(0, 0, 0);
    }

    public static ItemStack setPotion(ItemStack stack, Potion potion) {
        ResourceLocation potionLocation = ForgeRegistries.POTION_TYPES.getKey(potion);
        if (potion == Potions.EMPTY) stack.removeTagKey("potion");
        else stack.getOrCreateTag().putString("potion", potionLocation.toString());

        if (!potion.getEffects().isEmpty()) NBTUtils.writeEffectsOntoNBT(VSUtils.convertEffectList(potion.getEffects()));

        return stack;
    }

    public static String stringOrDefault(String name, CompoundNBT tag, String fallback) {
        if (tag != null && tag.contains(name, Constants.TagTypes.STRING)) return tag.getString(name);
        return fallback;
    }

    public static boolean booleanOrDefault(String name, CompoundNBT tag, boolean fallback) {
        if (tag != null && tag.contains(name, Constants.TagTypes.BYTE)) return tag.getBoolean(name);
        return fallback;
    }

    public static int integerOrDefault(String name, CompoundNBT tag, int fallback) {
        if (tag != null && tag.contains(name, Constants.TagTypes.INTEGER)) return tag.getInt(name);
        return fallback;
    }

    public static int anyNumericOrIntDefault(String name, CompoundNBT tag, int fallback) {
        if (tag != null && tag.contains(name, Constants.TagTypes.ANY_NUMERIC)) return tag.getInt(name);
        return fallback;
    }

    public static float floatOrDefault(String name, CompoundNBT tag, float fallback) {
        if (tag != null && tag.contains(name, Constants.TagTypes.FLOAT)) return tag.getFloat(name);
        return fallback;
    }

    public static float anyNumericOrFloatDefault(String name, CompoundNBT tag, float fallback) {
        if (tag != null && tag.contains(name, Constants.TagTypes.ANY_NUMERIC)) return tag.getFloat(name);
        return fallback;
    }

    public static CompoundNBT compoundOrDefault(String name, CompoundNBT tag, CompoundNBT fallback) {
        if (tag != null && tag.contains(name, Constants.TagTypes.COMPOUND)) return tag.getCompound(name);
        return fallback;
    }
}
