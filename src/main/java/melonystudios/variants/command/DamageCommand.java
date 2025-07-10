package melonystudios.variants.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import melonystudios.variants.command.argument.DamageSourceArgument;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;

public class DamageCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("damage").requires(source -> source.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("amount", FloatArgumentType.floatArg(0, Float.MAX_VALUE))
                                .then(Commands.argument("source", DamageSourceArgument.source())
                                        .executes(dispatcher1 -> damageEntity(dispatcher1.getSource(), DamageSourceArgument.getSource(dispatcher1, "source", (LivingEntity) EntityArgument.getEntity(dispatcher1, "target")),
                                                FloatArgumentType.getFloat(dispatcher1, "amount"), EntityArgument.getEntity(dispatcher1, "target")))
                                        .then(Commands.literal("by")
                                                .then(Commands.argument("attacker", EntityArgument.entity())
                                                        .executes(dispatcher1 -> damageEntity(dispatcher1.getSource(), DamageSourceArgument.getSourceWithKiller(dispatcher1, "source", EntityArgument.getEntity(dispatcher1, "attacker")),
                                                                FloatArgumentType.getFloat(dispatcher1, "amount"), EntityArgument.getEntity(dispatcher1, "target")))))))));
    }

    private static int damageEntity(CommandSource source, DamageSource damageSource, float amount, Entity entity) throws CommandSyntaxException {
        if (entity.hurt(damageSource, amount)) {
            source.sendSuccess(new TranslationTextComponent("commands.damage.success", amount, entity.getDisplayName()), true);
            return 1;
        } else {
            throw new SimpleCommandExceptionType(new TranslationTextComponent("commands.damage.invulnerable")).create();
        }
    }
}
