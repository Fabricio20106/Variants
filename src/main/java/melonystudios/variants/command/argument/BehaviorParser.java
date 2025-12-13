package melonystudios.variants.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.util.RVRegistries;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.tags.ITagCollection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class BehaviorParser {
    public static final DynamicCommandExceptionType UNKNOWN_BEHAVIOR_ERROR = new DynamicCommandExceptionType(behavior -> new TranslationTextComponent("argument.stew_behavior.id.invalid", behavior));
    private static final BiFunction<SuggestionsBuilder, ITagCollection<ConsumeBehavior>, CompletableFuture<Suggestions>> SUGGEST_NOTHING = (builder, tags) -> builder.buildFuture();
    private BiFunction<SuggestionsBuilder, ITagCollection<ConsumeBehavior>, CompletableFuture<Suggestions>> suggestions = SUGGEST_NOTHING;
    private ConsumeBehavior behavior;
    private CompoundNBT properties;
    private final StringReader reader;
    private int tagCursor;

    public BehaviorParser(StringReader reader) {
        this.reader = reader;
    }

    public ConsumeBehavior behavior() {
        return this.behavior;
    }

    public CompoundNBT properties() {
        return this.properties;
    }

    public void readBehavior() throws CommandSyntaxException {
        int cursor = this.reader.getCursor();
        ResourceLocation location = ResourceLocation.read(this.reader);
        if (RVRegistries.CONSUME_BEHAVIOR.containsKey(location)) {
            this.behavior = RVRegistries.CONSUME_BEHAVIOR.getValue(location);
        } else {
            this.reader.setCursor(cursor);
            throw UNKNOWN_BEHAVIOR_ERROR.createWithContext(this.reader, location.toString());
        }
    }

    public void readBehaviorTag() throws CommandSyntaxException {
        this.suggestions = this::suggestTags;
        this.reader.expect('#');
        this.tagCursor = this.reader.getCursor();
    }

    public void readProperties() throws CommandSyntaxException {
        this.properties = new JsonToNBT(this.reader).readStruct();
    }

    public BehaviorParser parse() throws CommandSyntaxException {
        this.suggestions = this::suggestBehavior;
        if (this.reader.canRead() && this.reader.peek() == '#') {
            this.readBehaviorTag();
        } else {
            this.readBehavior();
            this.suggestions = this::suggestOpenProperties;
        }

        if (this.reader.canRead() && this.reader.peek() == '{') {
            this.suggestions = SUGGEST_NOTHING;
            this.readProperties();
        }
        return this;
    }

    private CompletableFuture<Suggestions> suggestOpenProperties(SuggestionsBuilder builder, ITagCollection<ConsumeBehavior> tags) {
        if (builder.getRemaining().isEmpty()) builder.suggest(String.valueOf('{'));
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> suggestTags(SuggestionsBuilder builder, ITagCollection<ConsumeBehavior> tags) {
        return ISuggestionProvider.suggestResource(tags.getAvailableTags(), builder.createOffset(this.tagCursor));
    }

    private CompletableFuture<Suggestions> suggestBehavior(SuggestionsBuilder builder, ITagCollection<ConsumeBehavior> tags) {
        return ISuggestionProvider.suggestResource(RVRegistries.CONSUME_BEHAVIOR.getKeys(), builder);
    }

    public CompletableFuture<Suggestions> fillSuggestions(SuggestionsBuilder builder, ITagCollection<ConsumeBehavior> tags) {
        return this.suggestions.apply(builder.createOffset(this.reader.getCursor()), tags);
    }
}
