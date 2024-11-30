package melonystudios.variants.item.custom.tool;

import melonystudios.variants.Variants;
import melonystudios.variants.util.ComponentUtils;
import melonystudios.variants.util.Constants;
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
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class DebugBowItem extends BowItem {
    public DebugBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return ComponentUtils.enchantmentGlintOverride(stack, true);
    }

    @Override
    public boolean canAttackBlock(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!world.isClientSide) this.handleInteraction(player, state, world, pos, false, player.getItemInHand(Hand.MAIN_HAND));
        return false;
    }

    @Override
    @Nonnull
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getLevel();
        if (!world.isClientSide && player != null) {
            BlockPos pos = context.getClickedPos();
            this.handleInteraction(player, world.getBlockState(pos), world, pos, true, context.getItemInHand());
        }
        return ActionResultType.sidedSuccess(world.isClientSide);
    }

    public void handleInteraction(PlayerEntity player, BlockState state, IWorld world, BlockPos pos, boolean rightClick, ItemStack stack) {
        if (player.canUseGameMasterBlocks()) {
            Block block = state.getBlock();
            StateContainer<Block, BlockState> stateDefinition = block.getStateDefinition();
            Collection<Property<?>> collection = stateDefinition.getProperties();
            String blockRegistryKey = ForgeRegistries.BLOCKS.getKey(block).toString();
            if (collection.isEmpty()) {
                sendBowMessage(player, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.empty", blockRegistryKey));
            } else {
                CompoundNBT debugProperty = stack.getOrCreateTagElement("debug_property");
                String s1 = debugProperty.getString(blockRegistryKey);
                Property<?> property = stateDefinition.getProperty(s1);
                if (rightClick) {
                    if (property == null) {
                        property = collection.iterator().next();
                    }

                    BlockState state1 = cycleState(state, property, player.isSecondaryUseActive());
                    world.setBlock(pos, state1, Constants.BlockFlags.UPDATE_DEBUG_STATE);
                    sendBowMessage(player, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.update", property.getName(), getNameHelper(state1, property)));
                } else {
                    property = getRelative(collection, property, player.isSecondaryUseActive());
                    String s2 = property.getName();
                    debugProperty.putString(blockRegistryKey, s2);
                    sendBowMessage(player, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.select", s2, getNameHelper(state, property)));
                }
            }
        }
        if (!player.abilities.mayfly) sendBowMessage(player, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.survival").withStyle(TextFormatting.RED));
        if (!player.abilities.mayBuild) sendBowMessage(player, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.adventure").withStyle(TextFormatting.RED));
        if (player.isSpectator()) sendBowMessage(player, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.spectator").withStyle(TextFormatting.RED));
    }

    public static <T extends Comparable<T>> BlockState cycleState(BlockState state, Property<T> property, boolean backwards) {
        return state.setValue(property, getRelative(property.getPossibleValues(), state.getValue(property), backwards));
    }

    public static <T> T getRelative(Iterable<T> allowedValues, @Nullable T currentValue, boolean backwards) {
        return backwards ? Util.findPreviousInIterable(allowedValues, currentValue) : Util.findNextInIterable(allowedValues, currentValue);
    }

    public static void sendBowMessage(PlayerEntity player, ITextComponent component) {
        ((ServerPlayerEntity) player).sendMessage(component, ChatType.GAME_INFO, Util.NIL_UUID);
    }

    public static <T extends Comparable<T>> String getNameHelper(BlockState state, Property<T> property) {
        return property.getName(state.getValue(property));
    }
}