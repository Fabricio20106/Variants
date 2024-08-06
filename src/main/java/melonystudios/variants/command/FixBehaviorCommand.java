package melonystudios.variants.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
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
import net.minecraft.util.text.TranslationTextComponent;

public class FixBehaviorCommand {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("fix").then(Commands.argument("target", EntityArgument.player())
                .then(Commands.literal("exponential_stew").executes(dispatcher -> fixExponentialStew(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target"))))
                .then(Commands.literal("exponential_stew_effects").executes(dispatcher -> fixExponentialStewEffects(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target"))))
                .then(Commands.literal("suspicious_stew_effects").executes(dispatcher -> fixSuspiciousStewEffects(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target"))))
                .then(Commands.literal("bowl_name_tag").executes(dispatcher -> fixBowlNameTag(dispatcher.getSource(), EntityArgument.getPlayer(dispatcher, "target")))));
    }

    private static int fixExponentialStew(CommandSource source, ServerPlayerEntity player) {
        fixExponentialStewEffects(source, player);
        fixBowlNameTag(source, player);
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

    private static int fixBowlNameTag(CommandSource source, ServerPlayerEntity player) {
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
}
