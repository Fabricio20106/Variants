package com.junethewoods.variants.common.item;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
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
            BlockPos blockpos = context.getClickedPos();
            if (!this.handleInteraction(player, level.getBlockState(blockpos), level, blockpos, true, context.getItemInHand())) {
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private boolean handleInteraction(Player player, BlockState state, LevelAccessor accessor, BlockPos pos, boolean rightClick, ItemStack stack) {
        if (!player.canUseGameMasterBlocks()) {
            return false;
        } else {
            Block block = state.getBlock();
            StateDefinition<Block, BlockState> statedefinition = block.getStateDefinition();
            Collection<Property<?>> collection = statedefinition.getProperties();
            String s = Registry.BLOCK.getKey(block).toString();
            if (collection.isEmpty()) {
                message(player, new TranslatableComponent(this.getDescriptionId() + ".empty", s));
                return false;
            } else {
                CompoundTag compoundtag = stack.getOrCreateTagElement("debug_property");
                String s1 = compoundtag.getString(s);
                Property<?> property = statedefinition.getProperty(s1);
                if (rightClick) {
                    if (property == null) {
                        property = collection.iterator().next();
                    }

                    BlockState blockstate = cycleState(state, property, player.isSecondaryUseActive());
                    accessor.setBlock(pos, blockstate, 18);
                    message(player, new TranslatableComponent(this.getDescriptionId() + ".update", property.getName(), getNameHelper(blockstate, property)));
                } else {
                    property = getRelative(collection, property, player.isSecondaryUseActive());
                    String s2 = property.getName();
                    compoundtag.putString(s, s2);
                    message(player, new TranslatableComponent(this.getDescriptionId() + ".select", s2, getNameHelper(state, property)));
                }
                return true;
            }
        }
    }

    private static <T extends Comparable<T>> BlockState cycleState(BlockState state, Property<T> comparable, boolean backwards) {
        return state.setValue(comparable, getRelative(comparable.getPossibleValues(), state.getValue(comparable), backwards));
    }

    private static <T> T getRelative(Iterable<T> iterable, @Nullable T currentValue, boolean backwards) {
        return backwards ? Util.findPreviousInIterable(iterable, currentValue) : Util.findNextInIterable(iterable, currentValue);
    }

    private static void message(Player player, Component component) {
        ((ServerPlayer)player).sendMessage(component, ChatType.GAME_INFO, Util.NIL_UUID);
    }

    private static <T extends Comparable<T>> String getNameHelper(BlockState state, Property<T> comparable) {
        return comparable.getName(state.getValue(comparable));
    }
}