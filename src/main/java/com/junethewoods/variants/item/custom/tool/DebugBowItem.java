package com.junethewoods.variants.item.custom.tool;

import com.junethewoods.variants.Variants;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.Nullable;
import java.util.Collection;

public class DebugBowItem extends BowItem {
    public DebugBowItem(Properties properties) {
        super(properties);
    }

    public boolean isFoil(ItemStack stack) {
        return true;
    }

    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        if (!level.isClientSide) {
            this.handleInteraction(player, state, level, pos, false, player.getItemInHand(InteractionHand.MAIN_HAND));
        }
        return false;
    }

    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        if (!level.isClientSide && player != null) {
            BlockPos pos = context.getClickedPos();
            this.handleInteraction(player, level.getBlockState(pos), level, pos, true, context.getItemInHand());
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private void handleInteraction(Player player, BlockState state, LevelAccessor level, BlockPos pos, boolean rightClick, ItemStack stack) {
        if (player.canUseGameMasterBlocks()) {
            Block block = state.getBlock();
            StateDefinition<Block, BlockState> stateDefinition = block.getStateDefinition();
            Collection<Property<?>> collection = stateDefinition.getProperties();
            String blockRegistryKey = BuiltInRegistries.BLOCK.getKey(block).toString();
            if (collection.isEmpty()) {
                sendMessage(player, Component.translatable("tooltip." + Variants.MOD_ID + ".debug.empty", blockRegistryKey));
            } else {
                CompoundTag debugProperty = stack.getOrCreateTagElement("debug_property");
                String s1 = debugProperty.getString(blockRegistryKey);
                Property<?> property = stateDefinition.getProperty(s1);
                if (rightClick) {
                    if (property == null) {
                        property = collection.iterator().next();
                    }

                    BlockState state1 = cycleState(state, property, player.isSecondaryUseActive());
                    level.setBlock(pos, state1, 18);
                    sendMessage(player, Component.translatable("tooltip." + Variants.MOD_ID + ".debug.update", property.getName(), getNameHelper(state1, property)));
                } else {
                    property = getRelative(collection, property, player.isSecondaryUseActive());
                    String s2 = property.getName();
                    debugProperty.putString(blockRegistryKey, s2);
                    sendMessage(player, Component.translatable("tooltip." + Variants.MOD_ID + ".debug.select", s2, getNameHelper(state, property)));
                }
            }
        }

        if (!player.getAbilities().mayfly) sendMessage(player, Component.translatable("tooltip." + Variants.MOD_ID + ".debug.survival").withStyle(ChatFormatting.RED));
        if (!player.getAbilities().mayBuild) sendMessage(player, Component.translatable("tooltip." + Variants.MOD_ID + ".debug.adventure").withStyle(ChatFormatting.RED));
        if (player.isSpectator()) sendMessage(player, Component.translatable("tooltip." + Variants.MOD_ID + ".debug.spectator").withStyle(ChatFormatting.RED));
    }

    private static <T extends Comparable<T>> BlockState cycleState(BlockState state, Property<T> property, boolean backwards) {
        return state.setValue(property, getRelative(property.getPossibleValues(), state.getValue(property), backwards));
    }

    private static <T> T getRelative(Iterable<T> allowedValues, @Nullable T currentValue, boolean backwards) {
        return (T)(backwards ? Util.findPreviousInIterable(allowedValues, currentValue) : Util.findNextInIterable(allowedValues, currentValue));
    }

    private static void sendMessage(Player player, Component text) {
        ((ServerPlayer) player).sendSystemMessage(text, true);
    }

    private static <T extends Comparable<T>> String getNameHelper(BlockState state, Property<T> property) {
        return property.getName(state.getValue(property));
    }
}