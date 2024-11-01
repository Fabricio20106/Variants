package melonystudios.variants.item.custom.bottle;

import melonystudios.variants.entity.custom.StainedExperienceBottleEntity;
import melonystudios.variants.sound.VSSounds;
import melonystudios.variants.util.ComponentUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class StainedExperienceBottleItem extends StainedFullGlassBottleItem {
    public StainedExperienceBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return ComponentUtils.enchantmentGlintOverride(stack, true);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        SoundEvent event = getConsumeSound(handStack, VSSounds.THROWN_BOTTLE_THROW.get());
        world.playSound(null, player.getX(), player.getY(), player.getZ(), event, SoundCategory.NEUTRAL, 1, 1); // Pitch: 0.3 max, 0,5 min

        int glassColor = 8453920;
        if (getUseRemainder(handStack).getItem() instanceof StainedEmptyGlassBottleItem) glassColor = ((StainedEmptyGlassBottleItem) getUseRemainder(handStack).getItem()).getGlassColor(handStack);

        if (!world.isClientSide) {
            StainedExperienceBottleEntity xpBottleEntity = new StainedExperienceBottleEntity(world, player, handStack, glassColor);
            xpBottleEntity.setItem(handStack);
            xpBottleEntity.shootFromRotation(player, player.xRot, player.yRot, -20, 0.7F, 1);
            world.addFreshEntity(xpBottleEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) handStack.shrink(1);
        applyCooldown(handStack, player, 0);

        return ActionResult.sidedSuccess(handStack, world.isClientSide());
    }
}
