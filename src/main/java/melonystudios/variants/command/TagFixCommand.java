package melonystudios.variants.command;

import com.mojang.brigadier.CommandDispatcher;
import melonystudios.variants.item.fix.TagFix;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class TagFixCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("tagfix")
                .then(Commands.literal("single_item")
                        .then(Commands.argument("player", EntityArgument.player())
                                .executes(source -> applyFixesToSlot(source.getSource(), EntityArgument.getPlayer(source, "player"))))

                )
                .then(Commands.literal("whole_inventory")
                        .then(Commands.argument("player", EntityArgument.player())
                                .executes(source -> applyFixesToInventory(source.getSource(), EntityArgument.getPlayer(source, "player"))))
                )
        );
    }

    private static int applyFixesToSlot(CommandSource source, ServerPlayerEntity player) {
        ItemStack stack = player.getItemInHand(Hand.MAIN_HAND);
        if (stack.isEmpty() || stack.getTag() == null) {
            source.sendFailure(new TranslationTextComponent("commands.tagfix.fail.empty_tag", SetBehaviorCommand.getItemDisplayName(stack)));
            return 0;
        }

        List<TagFix> applicableFixes = TagFix.getApplicableFixes(stack.getItem());
        if (applicableFixes.isEmpty()) {
            source.sendFailure(new TranslationTextComponent("commands.tagfix.fail.no_fixes", SetBehaviorCommand.getItemDisplayName(stack)));
            return 0;
        }

        applicableFixes.forEach(fix -> fix.applyFix(stack.getTag()));
        player.inventoryMenu.broadcastChanges();
        if (applicableFixes.size() == 1) {
            source.sendSuccess(new TranslationTextComponent("commands.tagfix.success.single", applicableFixes.size(), stack.getDisplayName()), true);
        } else {
            source.sendSuccess(new TranslationTextComponent("commands.tagfix.success.multiple", applicableFixes.size(), stack.getDisplayName()), true);
        }
        return 1;
    }

    private static int applyFixesToInventory(CommandSource source, ServerPlayerEntity player) {
        return 1;
    }
}
