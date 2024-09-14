package melonystudios.variants.command.argument;

import com.google.common.collect.Lists;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class ActualPotionArgument implements ArgumentType<Potion> {
    private static final DynamicCommandExceptionType UNKNOWN_POTION = new DynamicCommandExceptionType(object -> new TranslationTextComponent("argument.potion.unknown", object));

    public static ActualPotionArgument potion() {
        return new ActualPotionArgument();
    }

    @Override
    public Potion parse(StringReader reader) throws CommandSyntaxException {
        ResourceLocation location = ResourceLocation.read(reader);
        return Registry.POTION.getOptional(location).orElseThrow(() -> UNKNOWN_POTION.create(location));
    }

    public static Potion getPotion(CommandContext<CommandSource> context, String argumentName) {
        return context.getArgument(argumentName, Potion.class);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ISuggestionProvider.suggestResource(Registry.POTION.keySet(), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return Lists.newArrayList("water", "minecraft:slowness", "minecraft:long_turtle_master");
    }
}
