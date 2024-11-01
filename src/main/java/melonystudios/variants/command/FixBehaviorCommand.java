package melonystudios.variants.command;

import com.google.common.collect.ImmutableMap;
import com.mojang.brigadier.builder.ArgumentBuilder;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Map;

import static melonystudios.variants.Variants.variants;

public class FixBehaviorCommand {
    private static final Map<ResourceLocation, ConsumeBehavior> OLD_NAMES_FIX = new ImmutableMap.Builder<ResourceLocation, ConsumeBehavior>().put(variants("effect"), VSConsumeBehaviors.APPLY_MOB_EFFECTS.get())
            .put(variants("lava"), VSConsumeBehaviors.IGNITE.get()).put(variants("milk"), VSConsumeBehaviors.CLEAR_MOB_EFFECTS.get()).build();

    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("fix").then(Commands.argument("target", EntityArgument.player())
                .then(Commands.literal("exponential_stew").executes(dispatcher -> fixExponentialStew(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target"))))
                .then(Commands.literal("exponential_stew_effects").executes(dispatcher -> fixExponentialStewEffects(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target"))))
                .then(Commands.literal("suspicious_stew_effects").executes(dispatcher -> fixSuspiciousStewEffects(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target"))))
                .then(Commands.literal("correct_ender_bowl").executes(dispatcher -> fixEnderBowlItem(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target"))))
                .then(Commands.literal("old_stew_behavior_names").executes(dispatcher -> fixOldStewBehaviorNames(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target"))))
                .then(Commands.literal("bowl_type_tag").executes(dispatcher -> fixBowlTypeTag(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target"))))
                .then(Commands.literal("update_to_1803").executes(dispatcher -> updateTagsTo1803(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target")))));
    }

    private static int fixOldStewBehaviorNames(CommandSource source, ServerPlayerEntity player) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (handStack.getItem() instanceof ExponentialStewItem) {
            CompoundNBT tag = handStack.getTag();
            if (tag != null && tag.contains("behavior", Constants.TagTypes.COMPOUND)) {
                CompoundNBT behaviorTag = tag.getCompound("behavior");
                if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                    switch (behaviorTag.getString("id")) {
                        case "variants:effect": {
                            behaviorTag.remove("id");
                            behaviorTag.putString("id", "variants:apply_mob_effects");
                            source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix_old_behavior_names.success", behaviorTag.getString("id"), "variants:apply_mob_effects"), true);
                            return 1;
                        }
                        case "variants:milk": {
                            behaviorTag.remove("id");
                            behaviorTag.putString("id", "variants:clear_mob_effects");
                            source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix_old_behavior_names.success", behaviorTag.getString("id"), "variants:clear_mob_effects"), true);
                            return 1;
                        }
                        case "variants:lava": {
                            behaviorTag.remove("id");
                            behaviorTag.putString("id", "variants:ignite");
                            source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix_old_behavior_names.success", behaviorTag.getString("id"), "variants:ignite"), true);
                            return 1;
                        }
                    }
                }
            }
        }
        source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix_old_behavior_names.no_fixes_success"), true);
        return 0;
    }

    private static int fixEnderBowlItem(CommandSource source, ServerPlayerEntity player) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (handStack.getItem() instanceof ExponentialStewItem) {
            CompoundNBT tag = handStack.getTag();
            if (tag != null && tag.contains("bowl", Constants.TagTypes.COMPOUND)) {
                CompoundNBT bowlTag = tag.getCompound("bowl");
                if (bowlTag.contains("name", Constants.TagTypes.STRING) && bowlTag.getString("name").equals("variants:ender_bowl")) {
                    bowlTag.remove("name");
                    bowlTag.putString("name", VSItems.ENDERWOOD_BOWL.get().getRegistryName().toString());
                    source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix_ender_bowl.success"), true);
                    return 1;
                }
            }
        } else {
            source.sendFailure(new TranslationTextComponent("commands.stewbehavior.fix_ender_bowl.not_an_expo_stew", SetBehaviorCommand.getItemDisplayName(handStack)));
        }
        return 0;
    }

    private static int fixExponentialStew(CommandSource source, ServerPlayerEntity player) {
        fixExponentialStewEffects(source, player);
        fixBowlTypeTag(source, player);
        return 1;
    }

    private static int fixSuspiciousStewEffects(CommandSource source, ServerPlayerEntity player) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (handStack.getItem() == Items.SUSPICIOUS_STEW) {
            CompoundNBT tag = handStack.getOrCreateTag();

            if (tag.contains("Effects", Constants.TagTypes.LIST)) {
                ListNBT effectList = tag.getList("Effects", Constants.TagTypes.COMPOUND);
                ListNBT newEffectList = new ListNBT();

                for (int i = 0; i < effectList.size(); ++i) {
                    CompoundNBT effectTag = effectList.getCompound(i);
                    CompoundNBT newEffectTag = new CompoundNBT();

                    newEffectTag.putString("id", Effect.byId(effectTag.getByte("EffectId")).getRegistryName().toString());
                    newEffectTag.putInt("duration", NBTUtils.integerOrDefault("EffectDuration", effectTag, 1));
                    newEffectList.add(newEffectTag);
                }
                tag.put("effects", newEffectList);
                source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix_suspicious_stew_effects.success", player.getDisplayName()), true);
                tag.remove("Effects");
                return 1;
            }
        } else {
            source.sendFailure(new TranslationTextComponent("commands.stewbehavior.fix_suspicious_stew_effects.not_a_suspicious_stew", SetBehaviorCommand.getItemDisplayName(handStack)));
        }
        return 0;
    }

    private static int fixExponentialStewEffects(CommandSource source, ServerPlayerEntity player) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (handStack.getItem() instanceof ExponentialStewItem) {
            CompoundNBT tag = handStack.getOrCreateTag();
            CompoundNBT behaviorTag = handStack.getOrCreateTagElement("behavior");
            CompoundNBT propertiesTag = behaviorTag.getCompound("properties");

            if (tag.contains("effects", Constants.TagTypes.LIST)) {
                ListNBT effectList = tag.getList("effects", Constants.TagTypes.COMPOUND);
                ListNBT newEffectList = new ListNBT();

                for (int i = 0; i < effectList.size(); ++i) {
                    CompoundNBT effectTag = effectList.getCompound(i);
                    CompoundNBT newEffectTag = new CompoundNBT();

                    assert effectTag.contains("id", Constants.TagTypes.SHORT);
                    newEffectTag.putString("id", Effect.byId(effectTag.getShort("id")).getRegistryName().toString());
                    newEffectTag.putInt("duration", NBTUtils.integerOrDefault("duration", effectTag, 1));
                    newEffectList.add(newEffectTag);
                }
                propertiesTag.put("effects", newEffectList);
                behaviorTag.put("properties", propertiesTag);
                source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix_exponential_stew_effects.success", player.getDisplayName()), true);
                tag.remove("effects");
                return 1;
            } else if (tag.contains("effects", Constants.TagTypes.COMPOUND)) {
                CompoundNBT effectTag = tag.getCompound("effects");
                CompoundNBT newEffectTag = new CompoundNBT();
                ListNBT newEffectList = new ListNBT();

                assert effectTag.contains("id", Constants.TagTypes.SHORT);
                newEffectTag.putString("id", Effect.byId(effectTag.getByte("id")).getRegistryName().toString());
                newEffectTag.putInt("duration", effectTag.getShort("duration"));
                newEffectList.add(newEffectTag);
                propertiesTag.put("effects", newEffectList);
                behaviorTag.put("properties", propertiesTag);
                source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix_exponential_stew_effects.success", player.getDisplayName()), true);
                tag.remove("effects");
                return 1;
            }
        } else {
            source.sendFailure(new TranslationTextComponent("commands.stewbehavior.fix_exponential_stew_effects.not_an_expo_stew", SetBehaviorCommand.getItemDisplayName(handStack)));
        }
        return 0;
    }

    private static int fixBowlTypeTag(CommandSource source, ServerPlayerEntity player) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (handStack.getItem() instanceof ExponentialStewItem) {
            CompoundNBT tag = handStack.getOrCreateTag();
            CompoundNBT bowlType = handStack.getTagElement("bowl_type");
            if (bowlType == null) {
                source.sendFailure(new TranslationTextComponent("commands.stewbehavior.fix_bowl.no_tag", player.getDisplayName()));
                return 0;
            } else {
                CompoundNBT bowl = handStack.getOrCreateTagElement("bowl");
                bowl.putString("name", NBTUtils.stringOrDefault("bowl_name", bowlType, VSItems.OAK_BOWL.get().getRegistryName().toString()));
                bowl.putInt("texture_id", NBTUtils.integerOrDefault("bowl_id", bowlType, 0));
                source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix_bowl.success", player.getDisplayName()), true);
                tag.remove("bowl_type");
                return 1;
            }
        } else {
            source.sendFailure(new TranslationTextComponent("commands.stewbehavior.fix_bowl.not_an_expo_stew", SetBehaviorCommand.getItemDisplayName(handStack)));
            return 0;
        }
    }

    private static int updateTagsTo1803(CommandSource source, ServerPlayerEntity player) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (handStack.getItem() instanceof ExponentialStewItem) {
            CompoundNBT tag = handStack.getTag();
            CompoundNBT consumableTag = handStack.getOrCreateTagElement("consumable");
            int returnValue = 0;

            // Switching the bowl tag to the correct place.
            if (tag != null && tag.contains("bowl", Constants.TagTypes.COMPOUND)) {
                CompoundNBT bowlTag = tag.getCompound("bowl");

                CompoundNBT remainderTag = new CompoundNBT();
                remainderTag.putString("id", NBTUtils.stringOrDefault("name", bowlTag, VSItems.OAK_BOWL.get().getRegistryName().toString()));
                tag.putInt("texture_id", bowlTag.getInt("texture_id"));
                consumableTag.put("use_remainder", remainderTag);
                tag.remove("bowl");
                returnValue += 1;
            }

            // "behavior" tag.
            if (tag != null && tag.contains("behavior", Constants.TagTypes.COMPOUND)) {
                CompoundNBT oldBehaviorTag = tag.getCompound("behavior");

                String oldID = NBTUtils.stringOrDefault("id", oldBehaviorTag, VSConsumeBehaviors.DEFAULT.get().getRegistryName().toString());
                CompoundNBT behaviorTag = oldBehaviorTag.getCompound("properties");
                behaviorTag.putString("id", replaceBehaviorID(oldID));
                consumableTag.put("behavior", behaviorTag);
                tag.remove("behavior");
                returnValue += 1;
            }

            if (returnValue > 1) source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix.upgrade_to_1803.success", returnValue), true);
            else source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.fix.upgrade_to_1803.no_fixes"), true);
            return returnValue;
        } else {
            source.sendFailure(new TranslationTextComponent("commands.stewbehavior.set.fail.not_a_tcf"));
            return 0;
        }
    }

    private static String replaceBehaviorID(String oldID) {
        if (OLD_NAMES_FIX.containsKey(ResourceLocation.tryParse(oldID))) {
            return OLD_NAMES_FIX.get(new ResourceLocation(oldID)).getRegistryName().toString();
        }
        return oldID;
    }
}
