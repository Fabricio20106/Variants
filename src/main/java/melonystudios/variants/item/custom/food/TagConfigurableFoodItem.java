package melonystudios.variants.item.custom.food;

import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSRegistries;
import melonystudios.variants.util.VSUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

public class TagConfigurableFoodItem extends Item implements TagConfigurableFood {
    public boolean useDefaultBehaviorTooltips = true;
    public boolean populateTagsByDefault = false;
    public boolean populateBehavior = false;
    private final boolean useDefaultBehavior;
    protected final StewBehavior behavior;

    public TagConfigurableFoodItem(boolean useDefaultBehavior, StewBehavior behavior, Properties properties) {
        super(properties);
        this.useDefaultBehavior = useDefaultBehavior;
        this.behavior = behavior;
    }

    @Override
    @Nonnull
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        if (livEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) livEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (this.useDefaultBehavior && !world.isClientSide) executeConsumeBehavior(stack, world, livEntity, this.behavior);
        if (getCooldown(stack, 0) != 0) applyCooldown(stack, livEntity, 0);
        return super.finishUsingItem(stack, world, livEntity);
    }

    @Override
    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return getConsumeAnimation(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        Food foodProperties = stack.getItem().getFoodProperties();
        if (foodProperties != null && foodProperties.isFastFood()) {
            return getConsumeTicks(stack, 16);
        }
        return getConsumeTicks(stack);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) list.add(populateDefaultConsumeTags(new ItemStack(this)));
    }

    public ItemStack populateDefaultConsumeTags(ItemStack stack) {
        boolean populateConsumeTags = VSConfigs.COMMON_CONFIGS.populateTagConfigurableFoodTags.get() && this.populateTagsByDefault;
        CompoundNBT tag = new CompoundNBT();
        CompoundNBT consumableTag = new CompoundNBT();
        if (populateConsumeTags || this.populateBehavior) tag = stack.getOrCreateTag();

        if (populateConsumeTags) {
            consumableTag.putString("animation", getConsumeAnimation(stack).toString().toLowerCase(Locale.ROOT));
            consumableTag.putInt("consume_ticks", getConsumeTicks(stack));
            consumableTag.putString("sound", getDefaultConsumeSound().getRegistryName().toString());
            consumableTag.put("use_remainder", VSUtils.saveStack(getUseRemainder(stack), new CompoundNBT()));
        }

        if (this.populateBehavior) {
            CompoundNBT behaviorTag = this.behavior.writePropertiesToNBT();
            behaviorTag.putString("id", this.behavior.getBehaviorRegistry().getRegistryName().toString());
            consumableTag.put("behavior", behaviorTag);
        }

        if (populateConsumeTags || this.populateBehavior) tag.put("consumable", consumableTag);
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (NBTUtils.shouldNotHideTooltip("hide_behavior_tooltips", stack) && this.useDefaultBehaviorTooltips) {
            CompoundNBT consumableTag = stack.getTagElement("consumable");
            if (consumableTag != null && consumableTag.contains("behavior", Constants.TagTypes.COMPOUND)) {
                CompoundNBT behaviorTag = consumableTag.getCompound("behavior");
                if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                    StewBehavior behavior = VSRegistries.CONSUME_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                    if (behavior != null) tooltip.addAll(behavior.addToStewTooltip(stack, world, flag));
                }
            } else {
                tooltip.addAll(this.behavior.addToStewTooltip(stack, world, flag));
            }
        }
    }
}
