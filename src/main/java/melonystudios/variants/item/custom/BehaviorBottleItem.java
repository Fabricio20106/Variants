package melonystudios.variants.item.custom;

import melonystudios.variants.entity.custom.BehaviorBottleEntity;
import melonystudios.variants.item.custom.food.ConsumableItem;
import melonystudios.variants.consumable.ConsumeBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BehaviorBottleItem extends ConsumableItem {
    private final int particleColor;

    public BehaviorBottleItem(ConsumeBehavior behavior, int particleColor, Properties properties) {
        super(true, behavior, properties);
        this.particleColor = particleColor;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        SoundEvent event = getConsumeSound(handStack, SoundEvents.SPLASH_POTION_THROW);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), event, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!world.isClientSide) {
            BehaviorBottleEntity bottleEntity = new BehaviorBottleEntity(world, player, this.particleColor);
            bottleEntity.setItem(handStack);
            bottleEntity.shootFromRotation(player, player.xRot, player.yRot, -20, 0.5F, 1);
            world.addFreshEntity(bottleEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) handStack.shrink(1);
        applyCooldown(handStack, player, 0);

        return ActionResult.sidedSuccess(handStack, world.isClientSide);
    }
}
