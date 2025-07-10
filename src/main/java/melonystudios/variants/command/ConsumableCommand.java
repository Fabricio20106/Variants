package melonystudios.variants.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import melonystudios.variants.command.argument.UseAnimationArgument;
import melonystudios.variants.item.custom.BehaviorBottleItem;
import melonystudios.variants.item.custom.bottle.StainedExperienceBottleItem;
import melonystudios.variants.component.Consumable;
import melonystudios.variants.util.VSUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Locale;

public class ConsumableCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("consumable").requires(source -> source.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.literal("consume_ticks")
                                .then(Commands.argument("ticks", IntegerArgumentType.integer(0))
                                        .executes(context -> setConsumeTicks(context, EntityArgument.getPlayer(context, "target"), IntegerArgumentType.getInteger(context, "ticks")))))
                        .then(Commands.literal("animation")
                                .then(Commands.argument("animation", UseAnimationArgument.animation())
                                        .executes(context -> setConsumeAnimation(context, EntityArgument.getPlayer(context, "target"), UseAnimationArgument.getAnimation(context, "animation")))))
                        .then(Commands.literal("cooldown")
                                .then(Commands.argument("cooldown_ticks", IntegerArgumentType.integer(0))
                                        .executes(context -> setCooldown(context, EntityArgument.getPlayer(context, "target"), IntegerArgumentType.getInteger(context, "cooldown_ticks")))))
                        .then(Commands.literal("sound")
                                .then(Commands.argument("eating_sound", ResourceLocationArgument.id()).suggests(SuggestionProviders.AVAILABLE_SOUNDS)
                                        .executes(context -> setConsumeSound(context, EntityArgument.getPlayer(context, "target"), ResourceLocationArgument.getId(context, "eating_sound")))))
                        .then(Commands.literal("shatter_sound")
                                .then(Commands.argument("shattering_sound", ResourceLocationArgument.id()).suggests(SuggestionProviders.AVAILABLE_SOUNDS)
                                        .executes(context -> setShatterSound(context, EntityArgument.getPlayer(context, "target"), ResourceLocationArgument.getId(context, "shattering_sound")))))
                        .then(Commands.literal("use_remainder")
                                .then(Commands.argument("item", ItemArgument.item())
                                        .executes(context -> setUseRemainder(context, EntityArgument.getPlayer(context, "target"), ItemArgument.getItem(context, "item"), 1))
                                        .then(Commands.argument("count", IntegerArgumentType.integer(0, 127))
                                                .executes(context -> setUseRemainder(context, EntityArgument.getPlayer(context, "target"), ItemArgument.getItem(context, "item"), IntegerArgumentType.getInteger(context, "count"))))))));
    }

    private static int setUseRemainder(CommandContext<CommandSource> context, ServerPlayerEntity player, ItemInput itemInput, int count) throws CommandSyntaxException {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (Consumable.validConsumableClass(handStack.getItem())) {
            ItemStack inputStack = itemInput.createItemStack(MathHelper.clamp(count, 0, 127), false);
            CompoundNBT consumableTag = handStack.getOrCreateTagElement("consumable");
            CompoundNBT remainderTag = VSUtils.saveStack(inputStack, new CompoundNBT());
            consumableTag.put("use_remainder", remainderTag);
            context.getSource().sendSuccess(new TranslationTextComponent("commands.consumable.use_remainder.success", player.getDisplayName(), inputStack.getDisplayName()), true);
            return 1;
        }
        context.getSource().sendFailure(new TranslationTextComponent("commands.consumable.not_a_tcf", SetBehaviorCommand.getItemDisplayName(handStack)));
        return 0;
    }

    private static int setConsumeTicks(CommandContext<CommandSource> context, ServerPlayerEntity player, int ticks) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (Consumable.validConsumableClass(handStack.getItem())) {
            CompoundNBT consumableTag = handStack.getOrCreateTagElement("consumable");
            consumableTag.putInt("consume_ticks", ticks);
            context.getSource().sendSuccess(new TranslationTextComponent("commands.consumable.consume_ticks.success" + (ticks < 20 ? ".ticks" : ""), player.getDisplayName(), (ticks < 20 ? ticks : ticks / 20)), true);
            return 1;
        }
        context.getSource().sendFailure(new TranslationTextComponent("commands.consumable.not_a_tcf", SetBehaviorCommand.getItemDisplayName(handStack)));
        return 0;
    }

    private static int setConsumeAnimation(CommandContext<CommandSource> context, ServerPlayerEntity player, UseAction animation) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (Consumable.validConsumableClass(handStack.getItem())) {
            CompoundNBT consumableTag = handStack.getOrCreateTagElement("consumable");
            String name = animation.toString().toLowerCase(Locale.ROOT);
            consumableTag.putString("animation", name.equals("spear") ? "trident" : name);
            context.getSource().sendSuccess(new TranslationTextComponent("commands.consumable.consume_animation.success", player.getDisplayName(), makeUseAnimationTranslation(animation)), true);
            return 1;
        }
        context.getSource().sendFailure(new TranslationTextComponent("commands.consumable.not_a_tcf", SetBehaviorCommand.getItemDisplayName(handStack)));
        return 0;
    }

    private static TranslationTextComponent makeUseAnimationTranslation(UseAction animation) {
        String name = animation.toString().toLowerCase(Locale.ROOT);
        return new TranslationTextComponent("use_animation.minecraft." + (name.equals("spear") ? "trident" : name));
    }

    private static int setCooldown(CommandContext<CommandSource> context, ServerPlayerEntity player, int cooldownTicks) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (Consumable.validConsumableClass(handStack.getItem())) {
            CompoundNBT consumableTag = handStack.getOrCreateTagElement("consumable");
            if (cooldownTicks == 0) consumableTag.remove("cooldown");
            else consumableTag.putInt("cooldown", cooldownTicks);
            context.getSource().sendSuccess(new TranslationTextComponent("commands.consumable.cooldown.success" + (cooldownTicks == 0 ? ".disabled" : (cooldownTicks < 20 ? ".ticks" : "")), player.getDisplayName(), (cooldownTicks < 20 ? cooldownTicks : cooldownTicks / 20)), true);
            return 1;
        }
        context.getSource().sendFailure(new TranslationTextComponent("commands.consumable.not_a_tcf", SetBehaviorCommand.getItemDisplayName(handStack)));
        return 0;
    }

    private static int setConsumeSound(CommandContext<CommandSource> context, ServerPlayerEntity player, ResourceLocation soundLocation) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (Consumable.validConsumableClass(handStack.getItem())) {
            CompoundNBT consumableTag = handStack.getOrCreateTagElement("consumable");
            consumableTag.putString("sound", soundLocation.toString());
            context.getSource().sendSuccess(new TranslationTextComponent("commands.consumable.consume_sound.success", player.getDisplayName(), soundLocation), true);
            return 1;
        }
        context.getSource().sendFailure(new TranslationTextComponent("commands.consumable.not_a_tcf", SetBehaviorCommand.getItemDisplayName(handStack)));
        return 0;
    }

    private static int setShatterSound(CommandContext<CommandSource> context, ServerPlayerEntity player, ResourceLocation soundLocation) {
        ItemStack handStack = player.getItemInHand(Hand.MAIN_HAND);
        if (handStack.getItem() instanceof StainedExperienceBottleItem || handStack.getItem() instanceof BehaviorBottleItem) {
            CompoundNBT consumableTag = handStack.getOrCreateTagElement("consumable");
            consumableTag.putString("shatter_sound", soundLocation.toString());
            context.getSource().sendSuccess(new TranslationTextComponent("commands.consumable.shatter_sound.success", player.getDisplayName(), soundLocation), true);
            return 1;
        }
        context.getSource().sendFailure(new TranslationTextComponent("commands.consumable.not_a_seb", SetBehaviorCommand.getItemDisplayName(handStack)));
        return 0;
    }
}
