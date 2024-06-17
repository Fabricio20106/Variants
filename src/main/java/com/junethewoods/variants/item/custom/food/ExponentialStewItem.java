package com.junethewoods.variants.item.custom.food;

import com.google.common.collect.ImmutableMap;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.custom.*;
import com.junethewoods.variants.util.NBTUtils;
import com.junethewoods.variants.util.VSRegistries;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ExponentialStewItem extends Item {
    public static Map<String, Integer> BOWL_NAME_TO_ID = new ImmutableMap.Builder<String, Integer>().put("oak", 0).put("spruce", 1).put("birch", 2).put("jungle", 3).put("acacia", 4).put("dark_oak", 5).put("painting", 6).put("crimson", 7).put("warped", 8).put("ender", 9).build();
    private final StewBehavior stewBehavior;

    public ExponentialStewItem(StewBehavior behavior, Properties properties) {
        super(properties);
        this.stewBehavior = behavior;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stewStack = new ItemStack(this);
        stewStack.getOrCreateTag().put("behavior", this.stewBehavior.writeBehaviorToNBT(stewStack));
        return stewStack;
    }

    public boolean hasBehaviorInNBT(ItemStack stewStack) {
        return stewStack.getTag() != null && stewStack.getTag().contains("behavior");
    }

    public ITextComponent getBehaviorTranslation(ItemStack stewStack) {
        if (hasBehaviorInNBT(stewStack)) {
            CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
            ResourceLocation behaviorID = ResourceLocation.tryParse(behaviorTag.getString("id"));
            assert behaviorID != null;
            return new TranslationTextComponent("stew_behavior." + behaviorID.getNamespace() + "." + behaviorID.getPath());
        }
        return this.stewBehavior.getDisplayName();
    }

    public static void writeEffectToStew(ItemStack stack, Effect effect, int duration) {
        CompoundNBT behaviorTag = stack.getOrCreateTagElement("behavior");
        CompoundNBT propertiesTag = behaviorTag.getCompound("properties");
        ListNBT effectList = propertiesTag.getList("effects", 9);
        CompoundNBT tag = new CompoundNBT();

        tag.putString("id", effect.getRegistryName().toString());
        tag.putInt("duration", duration);
        effectList.add(tag);
        propertiesTag.put("effects", effectList);
    }

    public ItemStack finishUsingItem(ItemStack stewStack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stewStack, world, livEntity);
        boolean flag = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;

        // Custom Stew Behavior
        executeBehavior(stewStack, world, livEntity);

        // For Suspicious Stew & "Apply Mob Effects" behavior
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        CompoundNBT propertiesTag = behaviorTag.getCompound("properties");
        if (propertiesTag.contains("effects", 9)) {
            ListNBT effectList = propertiesTag.getList("effects", 10);

            for(int i = 0; i < effectList.size(); ++i) {
                int duration = 160; // Default of 8 seconds.
                int amplifier = 0;
                boolean ambient = false;
                boolean showParticles = true;
                boolean showIcon = true;
                CompoundNBT tag1 = effectList.getCompound(i);
                if (tag1.contains("duration", 3)) duration = tag1.getInt("duration");
                if (tag1.contains("amplifier", 3)) amplifier = tag1.getInt("amplifier");
                if (tag1.contains("ambient")) ambient = tag1.getBoolean("ambient");
                if (tag1.contains("show_particles")) showParticles = tag1.getBoolean("show_particles");
                if (tag1.contains("show_icon")) showIcon = tag1.getBoolean("show_icon");

                Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(tag1.getString("id")));
                if (effect != null) livEntity.addEffect(new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon));
            }
        }
        return flag ? superStack : getBowlType(stewStack);
    }

    public static ItemStack getBowlType(ItemStack stewStack) {
        CompoundNBT bowlTypeTag = stewStack.getOrCreateTagElement("bowl_type");
        ResourceLocation containerItem = new ResourceLocation(bowlTypeTag.getString("bowl_name"));

        if (bowlTypeTag.contains("bowl_name") && ForgeRegistries.ITEMS.containsKey(containerItem)) {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(containerItem));
        }

        return new ItemStack(Items.BOWL);
    }

    public void executeBehavior(ItemStack stewStack, World world, LivingEntity livEntity) {
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        if (behaviorTag.contains("id")) {
            StewBehavior behavior = VSRegistries.STEW_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
            if (behavior != null) {
                CompoundNBT propertiesTag = behavior.getBehaviorProperties(stewStack);
                if (behavior instanceof DamageEntityBehavior) {
                    DamageEntityBehavior damageBehavior = new DamageEntityBehavior(NBTUtils.fromMessageID(propertiesTag.getString("source")), propertiesTag.getFloat("amount"));
                    damageBehavior.executeBehavior(stewStack, world, livEntity);
                } else if (behavior instanceof ClearMobEffectsBehavior) {
                    ClearMobEffectsBehavior clearEffectsBehavior = new ClearMobEffectsBehavior(ItemStack.of(propertiesTag.getCompound("curative_item")));
                    clearEffectsBehavior.executeBehavior(stewStack, world, livEntity);
                } else if (behavior instanceof IgniteBehavior) {
                    IgniteBehavior igniteBehavior = new IgniteBehavior(propertiesTag.getInt("ticks_on_fire"));
                    igniteBehavior.executeBehavior(stewStack, world, livEntity);
                } else if (behavior instanceof ExplodeBehavior) {
                    BlockPos pos = propertiesTag.contains("pos") ? NBTUtils.readBlockPos(propertiesTag) : livEntity.blockPosition();
                    ExplodeBehavior explodeBehavior = new ExplodeBehavior(propertiesTag.getInt("radius"), propertiesTag.getBoolean("create_fire"), pos, NBTUtils.fromMessageID(propertiesTag.getString("source")),
                            Explosion.Mode.valueOf(propertiesTag.getString("mode").toUpperCase(Locale.ROOT)));
                    explodeBehavior.executeBehavior(stewStack, world, livEntity);
                } else if (behavior instanceof PlaySoundBehavior) {
                    SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.tryParse(propertiesTag.getString("id")));
                    SoundCategory category1 = SoundCategory.valueOf(propertiesTag.getString("category").toUpperCase(Locale.ROOT));
                    BlockPos pos = propertiesTag.contains("pos") ? NBTUtils.readBlockPos(propertiesTag) : livEntity.blockPosition();
                    PlaySoundBehavior playSoundBehavior = new PlaySoundBehavior(sound, category1, pos, propertiesTag.getBoolean("play_at_player"), propertiesTag.getFloat("volume"), propertiesTag.getFloat("pitch"));
                    playSoundBehavior.executeBehavior(stewStack, world, livEntity);
                }
            } else {
                this.stewBehavior.executeBehavior(stewStack, world, livEntity);
            }
        } else {
            this.stewBehavior.executeBehavior(stewStack, world, livEntity);
        }
    }

    @Override
    public void fillItemCategory(ItemGroup itemTab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(itemTab) && VSConfigs.COMMON_CONFIGS.populateExponentialBowlsInTabs.get()) {
            for (String bowls : BOWL_NAME_TO_ID.keySet()) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT bowlTypeTag = stack.getOrCreateTagElement("bowl_type");

                bowlTypeTag.putString("bowl_name", "variants:" + bowls + "_bowl");
                bowlTypeTag.putInt("bowl_id", BOWL_NAME_TO_ID.get(bowls));
                if (stack.getTag() != null) tag.put("behavior", this.stewBehavior.writeBehaviorToNBT(stack));
                list.add(stack);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        CompoundNBT bowlTypeTag = stack.getOrCreateTagElement("bowl_type");
        ResourceLocation containerItem = new ResourceLocation(bowlTypeTag.getString("bowl_name"));

        if (NBTUtils.shouldHideTooltip("hide_bowl_name", stack)) {
            if (bowlTypeTag.contains("bowl_name") && ForgeRegistries.ITEMS.containsKey(containerItem)) {
                ITextComponent bowlName = ForgeRegistries.ITEMS.getValue(containerItem).getName(ForgeRegistries.ITEMS.getValue(containerItem).getDefaultInstance());
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.bowl", bowlName).withStyle(TextFormatting.GRAY));
            } else {
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.bowl", ForgeRegistries.ITEMS.getValue(Items.BOWL.getRegistryName()).getName(ForgeRegistries.ITEMS.getValue(Items.BOWL.getRegistryName())
                        .getDefaultInstance())).withStyle(TextFormatting.GRAY));
            }
        }
        if (NBTUtils.shouldHideTooltip("hide_stew_behavior", stack)) {
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.behavior",
                    getBehaviorTranslation(stack)).withStyle(TextFormatting.GRAY));
        }
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
