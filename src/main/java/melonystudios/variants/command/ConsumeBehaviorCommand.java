package melonystudios.variants.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ConsumeBehaviorCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("consumebehavior")
                .then(SetBehaviorCommand.register())
                .then(FixBehaviorCommand.register()));
    }
}
