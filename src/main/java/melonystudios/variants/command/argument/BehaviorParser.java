package melonystudios.variants.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.util.VSRegistries;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.tags.ITagCollection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class BehaviorParser {
    public static final DynamicCommandExceptionType UNKNOWN_BEHAVIOR_ERROR = new DynamicCommandExceptionType(object -> new TranslationTextComponent("argument.stew_behavior.id.invalid", object));
    private static final BiFunction<SuggestionsBuilder, ITagCollection<StewBehavior>, CompletableFuture<Suggestions>> SUGGEST_NOTHING = (suggestionsBuilder, tagCollection) -> suggestionsBuilder.buildFuture();
    private final StringReader reader;
    private StewBehavior behavior;
    private int tagCursor;
    private CompoundNBT properties;
    private ResourceLocation locationProperties = new ResourceLocation("");
    private BiFunction<SuggestionsBuilder, ITagCollection<StewBehavior>, CompletableFuture<Suggestions>> suggestions = SUGGEST_NOTHING;

    public BehaviorParser(StringReader reader) {
        this.reader = reader;
    }

    public StewBehavior getBehavior() {
        return this.behavior;
    }

    public CompoundNBT getProperties() {
        return this.properties;
    }

    public void readBehavior() throws CommandSyntaxException {
        int cursor = this.reader.getCursor();
        ResourceLocation location = ResourceLocation.read(this.reader);
        if (VSRegistries.CONSUME_BEHAVIOR.containsKey(location)) {
            this.behavior = VSRegistries.CONSUME_BEHAVIOR.getValue(location);
        } else {
            this.reader.setCursor(cursor);
            throw UNKNOWN_BEHAVIOR_ERROR.createWithContext(this.reader, location.toString());
        }
    }

    public void readLocationProperties() throws CommandSyntaxException {
        this.suggestions = this::suggestProperties;
        this.reader.expect('#');
        this.tagCursor = this.reader.getCursor();
        this.locationProperties = ResourceLocation.read(this.reader);
    }

    public void readProperties() throws CommandSyntaxException {
        this.properties = new JsonToNBT(this.reader).readStruct();
    }

    public BehaviorParser parse() throws CommandSyntaxException {
        this.suggestions = this::suggestBehaviorOrProperties;
        if (this.reader.canRead() && this.reader.peek() == '#') {
            this.readLocationProperties();
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

    private CompletableFuture<Suggestions> suggestOpenProperties(SuggestionsBuilder builder, ITagCollection<StewBehavior> tagCollection) {
        if (builder.getRemaining().isEmpty()) builder.suggest(String.valueOf('{'));
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> suggestProperties(SuggestionsBuilder builder, ITagCollection<StewBehavior> tagCollection) {
        return ISuggestionProvider.suggestResource(tagCollection.getAvailableTags(), builder.createOffset(this.tagCursor));
    }

    private CompletableFuture<Suggestions> suggestBehaviorOrProperties(SuggestionsBuilder builder, ITagCollection<StewBehavior> tagCollection) {
        return ISuggestionProvider.suggestResource(VSRegistries.CONSUME_BEHAVIOR.getKeys(), builder);
    }

    public CompletableFuture<Suggestions> fillSuggestions(SuggestionsBuilder builder, ITagCollection<StewBehavior> tagCollection) {
        return this.suggestions.apply(builder.createOffset(this.reader.getCursor()), tagCollection);
    }
}
