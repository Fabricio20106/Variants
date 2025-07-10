package melonystudios.variants.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import melonystudios.variants.util.damage.DamageSourceUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DamageSourceArgument implements ArgumentType<ResourceLocation> {
    private static final DynamicCommandExceptionType UNKNOWN_SOURCE_ERROR = new DynamicCommandExceptionType(location -> new TranslationTextComponent("argument.damage_source.invalid", location));

    @Override
    public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
        return ResourceLocation.read(reader);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        Map<ResourceLocation, DamageSource> combinedSourcesMap = new HashMap<>(DamageSourceUtils.DATA_DRIVEN_SOURCES);
        for (ResourceLocation location : DamageSourceUtils.VALID_DAMAGE_SOURCES) combinedSourcesMap.put(location, DamageSource.GENERIC);
        return ISuggestionProvider.suggestResource(combinedSourcesMap.keySet().stream(), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.asList("out_of_world", "minecraft:thrown", "variants:redstone_poisoning");
    }

    public static DamageSourceArgument source() {
        return new DamageSourceArgument();
    }

    public static DamageSource getSource(CommandContext<CommandSource> context, String argumentName, LivingEntity livEntity) throws CommandSyntaxException {
        ResourceLocation sourceLocation = context.getArgument(argumentName, ResourceLocation.class);
        DamageSource source = DamageSourceUtils.fromLocation(livEntity, sourceLocation);
        if (source == null) {
            throw UNKNOWN_SOURCE_ERROR.create(sourceLocation);
        } else {
            return source;
        }
    }

    public static DamageSource getSourceWithKiller(CommandContext<CommandSource> context, String argumentName, Entity killer) throws CommandSyntaxException {
        ResourceLocation sourceLocation = context.getArgument(argumentName, ResourceLocation.class);
        DamageSource source = DamageSourceUtils.fromLocationWithKiller(killer, sourceLocation);
        if (source == null) {
            throw UNKNOWN_SOURCE_ERROR.create(sourceLocation);
        } else {
            return source;
        }
    }
}
