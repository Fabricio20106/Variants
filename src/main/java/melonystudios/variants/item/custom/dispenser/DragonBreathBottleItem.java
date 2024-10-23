package melonystudios.variants.item.custom.dispenser;

import melonystudios.variants.dispenser.DragonBreathBottleDispenseBehavior;
import melonystudios.variants.entity.custom.DragonBreathBottleEntity;
import melonystudios.variants.item.custom.VSItem;
import melonystudios.variants.sound.VSSounds;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class DragonBreathBottleItem extends VSItem {
    public DragonBreathBottleItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new DragonBreathBottleDispenseBehavior());
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), VSSounds.DRAGON_BREATH_BOTTLE_THROW.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!world.isClientSide) {
            DragonBreathBottleEntity bottleEntity = new DragonBreathBottleEntity(world, player);
            bottleEntity.setItem(handStack);
            bottleEntity.shootFromRotation(player, player.xRot, player.yRot, -20, 0.7F, 1);
            world.addFreshEntity(bottleEntity);
            player.getCooldowns().addCooldown(this, 10);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) handStack.shrink(1);

        return ActionResult.sidedSuccess(handStack, world.isClientSide());
    }
}
