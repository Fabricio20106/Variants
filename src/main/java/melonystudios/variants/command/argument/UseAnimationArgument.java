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
import net.minecraft.item.UseAction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UseAnimationArgument implements ArgumentType<UseAction> {
    private static final DynamicCommandExceptionType UNKNOWN_ANIMATION_ERROR = new DynamicCommandExceptionType(location -> new TranslationTextComponent("argument.use_action.unknown", location));
    private static final DynamicCommandExceptionType INVALID_ANIMATION_ERROR = new DynamicCommandExceptionType(location -> new TranslationTextComponent("argument.use_action.invalid", location));

    @Override
    public UseAction parse(StringReader reader) throws CommandSyntaxException {
        int cursorLocation = reader.getCursor();

        while (reader.canRead() && ResourceLocation.isAllowedInResourceLocation(reader.peek())) reader.skip();
        String substring = reader.getString().substring(cursorLocation, reader.getCursor());

        try {
            UseAction animation = parseAnimationFromNBT(substring);
            if (animation == null) throw UNKNOWN_ANIMATION_ERROR.create(substring);
            else return animation;
        } catch (ResourceLocationException resourcelocationexception) {
            reader.setCursor(cursorLocation);
            throw INVALID_ANIMATION_ERROR.createWithContext(reader, substring);
        }
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        List<String> useAnimations = Lists.newArrayList("none", "eat", "drink", "block", "bow", "crossbow", "trident");
        return ISuggestionProvider.suggest(useAnimations.stream(), builder);
    }

    public static UseAnimationArgument animation() {
        return new UseAnimationArgument();
    }

    public static UseAction getAnimation(CommandContext<CommandSource> context, String argumentName) throws CommandSyntaxException {
        UseAction animation = context.getArgument(argumentName, UseAction.class);
        if (animation == null) {
            throw UNKNOWN_ANIMATION_ERROR.create(argumentName);
        } else {
            return animation;
        }
    }

    public static UseAction parseAnimationFromNBT(String animation) {
        switch (animation) {
            case "none": return UseAction.NONE;
            case "drink": return UseAction.DRINK;
            case "block": return UseAction.BLOCK;
            case "bow": return UseAction.BOW;
            case "trident": return UseAction.SPEAR;
            case "crossbow": return UseAction.CROSSBOW;
            case "eat": return UseAction.EAT;
            default: return null;
        }
    }
}
