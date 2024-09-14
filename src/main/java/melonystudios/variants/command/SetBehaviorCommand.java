package melonystudios.variants.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import melonystudios.variants.command.argument.BehaviorArgument;
import melonystudios.variants.command.argument.BehaviorInput;
import melonystudios.variants.item.custom.food.TagConfigurableFood;
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
                .then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("consume_behavior", BehaviorArgument.behavior())
                        .executes(context -> setBehaviorToItem(context.getSource(), BehaviorArgument.getBehavior(context, "consume_behavior"),
                                EntityArgument.getPlayers(context, "targets")))));
    }

    private static int setBehaviorToItem(CommandSource source, BehaviorInput behavior, Collection<ServerPlayerEntity> players) {
        if (source.hasPermission(2)) {
            for (ServerPlayerEntity serverPlayer : players) {
                if (!serverPlayer.isCreative()) break;
                ItemStack handStack = serverPlayer.getItemInHand(Hand.MAIN_HAND);
                if (handStack.getItem() instanceof TagConfigurableFood) {
                    handStack.getOrCreateTag().getCompound("consumable").remove("behavior");
                    CompoundNBT consumableTag = handStack.getOrCreateTagElement("consumable");
                    CompoundNBT behaviorTag = behavior.properties;
                    if (behavior.behavior.getRegistryName() != null) behaviorTag.putString("id", behavior.behavior.getRegistryName().toString());
                    consumableTag.put("behavior", behaviorTag);
                    handStack.getOrCreateTag().put("consumable", consumableTag);
                    if (players.size() == 1) {
                        source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.set.success.tcf.single", players.iterator().next().getDisplayName(), behavior.behavior.getCommandDisplayName()), true);
                    } else {
                        source.sendSuccess(new TranslationTextComponent("commands.stewbehavior.set.success.tcf.multiple", players.size(), behavior.behavior.getCommandDisplayName()), true);
                    }
                } else {
                    source.sendFailure(new TranslationTextComponent("commands.stewbehavior.set.fail.not_a_tcf", getItemDisplayName(handStack)));
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
