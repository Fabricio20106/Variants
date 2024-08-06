package melonystudios.variants.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import melonystudios.variants.util.tag.StewBehaviorTags;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class BehaviorArgument implements ArgumentType<BehaviorInput> {
    private static final Collection<String> EXAMPLES = Arrays.asList("variants:default", "variants:damage_entity");

    public static BehaviorArgument behavior() {
        return new BehaviorArgument();
    }

    @Override
    public BehaviorInput parse(StringReader reader) throws CommandSyntaxException {
        BehaviorParser parser = new BehaviorParser(reader).parse();
        return new BehaviorInput(parser.getBehavior(), parser.getProperties());
    }

    public static <C> BehaviorInput getBehavior(CommandContext<C> context, String argument) {
        return context.getArgument(argument, BehaviorInput.class);
    }

    @Override
    public <C>CompletableFuture<Suggestions> listSuggestions(CommandContext<C> context, SuggestionsBuilder builder) {
        StringReader reader = new StringReader(context.getInput());
        reader.setCursor(builder.getStart());
        BehaviorParser parser = new BehaviorParser(reader);

        try {
            parser.parse();
        } catch (CommandSyntaxException ignored) {}
        return parser.fillSuggestions(builder, StewBehaviorTags.getAllTags());
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}
