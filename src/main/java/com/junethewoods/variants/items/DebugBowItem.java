package com.junethewoods.variants.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;

public class DebugBowItem extends BowItem {
    public DebugBowItem(Properties builder) {
        super(builder);
    }

    public boolean isFoil(ItemStack stack) {
        return true;
    }

    public boolean canAttackBlock(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (!worldIn.isClientSide) {
            this.handleInteraction(player, state, worldIn, pos, false, player.getItemInHand(Hand.MAIN_HAND));
        }
        return false;
    }

    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getLevel();
        if (!world.isClientSide && playerentity != null) {
            BlockPos blockpos = context.getClickedPos();
            this.handleInteraction(playerentity, world.getBlockState(blockpos), world, blockpos, true, context.getItemInHand());
        }

        return ActionResultType.sidedSuccess(world.isClientSide);
    }

    private void handleInteraction(PlayerEntity player, BlockState state, IWorld worldIn, BlockPos pos, boolean rightClick, ItemStack stack) {
        if (player.canUseGameMasterBlocks()) {
            Block block = state.getBlock();
            StateContainer<Block, BlockState> statecontainer = block.getStateDefinition();
            Collection<Property<?>> collection = statecontainer.getProperties();
            String s = Registry.BLOCK.getKey(block).toString();
            if (collection.isEmpty()) {
                sendMessage(player, new TranslationTextComponent(this.getDescriptionId() + ".empty", s));
            } else {
                CompoundNBT compoundnbt = stack.getOrCreateTagElement("DebugProperty");
                String s1 = compoundnbt.getString(s);
                Property<?> property = statecontainer.getProperty(s1);
                if (rightClick) {
                    if (property == null) {
                        property = collection.iterator().next();
                    }

                    BlockState blockstate = cycleState(state, property, player.isSecondaryUseActive());
                    worldIn.setBlock(pos, blockstate, 18);
                    sendMessage(player, new TranslationTextComponent(this.getDescriptionId() + ".update", property.getName(), getNameHelper(blockstate, property)));
                } else {
                    property = getRelative(collection, property, player.isSecondaryUseActive());
                    String s2 = property.getName();
                    compoundnbt.putString(s, s2);
                    sendMessage(player, new TranslationTextComponent(this.getDescriptionId() + ".select", s2, getNameHelper(state, property)));
                }
            }
        }
    }

    private static <T extends Comparable<T>> BlockState cycleState(BlockState state, Property<T> propertyIn, boolean backwards) {
        return state.setValue(propertyIn, getRelative(propertyIn.getPossibleValues(), state.getValue(propertyIn), backwards));
    }

    private static <T> T getRelative(Iterable<T> allowedValues, @Nullable T currentValue, boolean backwards) {
        return (T)(backwards ? Util.findPreviousInIterable(allowedValues, currentValue) : Util.findNextInIterable(allowedValues, currentValue));
    }

    private static void sendMessage(PlayerEntity player, ITextComponent text) {
        ((ServerPlayerEntity)player).sendMessage(text, ChatType.GAME_INFO, Util.NIL_UUID);
    }

    private static <T extends Comparable<T>> String getNameHelper(BlockState state, Property<T> propertyIn) {
        return propertyIn.getName(state.getValue(propertyIn));
    }
}