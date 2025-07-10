package melonystudios.variants.command.argument;

import net.minecraft.command.arguments.ArgumentSerializer;

import static net.minecraft.command.arguments.ArgumentTypes.register;

public class RVArgumentTypes {
    public static void init() {
        register("variants:potion", ActualPotionArgument.class, new ArgumentSerializer<>(ActualPotionArgument::potion));
        register("variants:consume_behavior", BehaviorArgument.class, new ArgumentSerializer<>(BehaviorArgument::behavior));
        register("variants:damage_source", DamageSourceArgument.class, new ArgumentSerializer<>(DamageSourceArgument::source));
        register("variants:use_animation", UseAnimationArgument.class, new ArgumentSerializer<>(UseAnimationArgument::animation));
    }
}
