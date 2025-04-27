package melonystudios.variants.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.client.ConfigGuiHandler;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.minecraftforge.server.command.ModIdArgument;

import java.util.Optional;

public class ConfigMenuCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("configmenu")
                .then(Commands.argument("mod_id", ModIdArgument.modIdArgument())
                        .executes(context -> openConfigMenu(context, getModID(context, "mod_id")))));
    }

    public static String getModID(CommandContext<CommandSource> dispatcher, String name) {
        return dispatcher.getArgument(name, String.class);
    }

    private static int openConfigMenu(CommandContext<CommandSource> context, String modID) {
        Minecraft minecraft = Minecraft.getInstance();
        Optional<ModInfo> information = ModList.get().getMods().stream().filter(info -> info.getModId().equals(modID)).findAny();
        if (modID.equals("minecraft")) {
            minecraft.setScreen(new OptionsScreen(minecraft.screen, minecraft.options));
            return 1;
        }

        if (information.isPresent()) {
            ConfigGuiHandler.getGuiFactoryFor(information.get()).map(screenFunc -> screenFunc.apply(minecraft, minecraft.screen)).ifPresent(minecraft::setScreen);
            context.getSource().sendSuccess(new StringTextComponent("Opened config menu for mod '" + modID + "'."), false);
            return 1;
        } else {
            context.getSource().sendFailure(new StringTextComponent("Mod does not have a config menu available"));
            return 0;
        }
    }
}
