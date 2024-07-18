package com.junethewoods.variants.command;

import com.junethewoods.variants.command.argument.BehaviorArgument;
import com.junethewoods.variants.command.argument.BehaviorInput;
import com.junethewoods.variants.item.custom.food.ExponentialStewItem;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.HoverEvent;

import java.util.Collection;

public class SetBehaviorCommand {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("set")
                .then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("stew_behavior", BehaviorArgument.behavior())
                        .executes(dispatcher1 -> setBehaviorToItem(dispatcher1.getSource(), BehaviorArgument.getBehavior(dispatcher1, "stew_behavior"),
                                EntityArgument.getPlayers(dispatcher1, "targets")))));
    }

    private static int setBehaviorToItem(CommandSource source, BehaviorInput behavior, Collection<ServerPlayerEntity> players) {
        if (source.hasPermission(2)) {
            for (ServerPlayerEntity serverPlayer : players) {
                if (!serverPlayer.isCreative()) break;
                ItemStack handStack = serverPlayer.getItemInHand(Hand.MAIN_HAND);
                if (handStack.getItem() instanceof ExponentialStewItem) {
                    handStack.getOrCreateTag().remove("behavior");
                    CompoundNBT behaviorTag = handStack.getOrCreateTagElement("behavior");
                    behaviorTag.putString("id", behavior.behavior.getRegistryName().toString());
                    behaviorTag.put("properties", behavior.properties);
                    if (players.size() == 1) {
                        source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.set.success.single", players.iterator().next().getDisplayName(), behavior.behavior.getCommandDisplayName()), true);
                    } else {
                        source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.set.success.multiple", players.size(), behavior.behavior.getCommandDisplayName()), true);
                    }
                } else {
                    source.sendFailure(new TranslationTextComponent("commands.stewbehavior.set.fail.not_an_expo_stew", getItemDisplayName(handStack)));
                }
            }
            return players.size();
        }
        return 0;
    }

    public static ITextComponent getItemDisplayName(ItemStack stack) {
        IFormattableTextComponent component = stack.getHoverName().copy().withStyle(TextFormatting.RED);
        if (stack.hasCustomHoverName()) component.withStyle(TextFormatting.ITALIC);
        IFormattableTextComponent wrappedComponent = TextComponentUtils.wrapInSquareBrackets(component);
        wrappedComponent.withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new HoverEvent.ItemHover(stack))));
        return wrappedComponent;
    }
}
