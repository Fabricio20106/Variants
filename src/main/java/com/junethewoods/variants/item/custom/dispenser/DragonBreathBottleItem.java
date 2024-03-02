package com.junethewoods.variants.item.custom.dispenser;

import com.junethewoods.variants.block.dispenser.DragonBreathBottleDispenseBehavior;
import com.junethewoods.variants.entity.custom.DragonBreathBottleEntity;
import com.junethewoods.variants.sound.VSSounds;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class DragonBreathBottleItem extends Item {
    public DragonBreathBottleItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new DragonBreathBottleDispenseBehavior());
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), VSSounds.DRAGON_BREATH_BOTTLE_THROW.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            DragonBreathBottleEntity breathBottle = new DragonBreathBottleEntity(world, player);
            breathBottle.setItem(handStack);
            breathBottle.shootFromRotation(player, player.xRot, player.yRot, -20, 0.7F, 1);
            world.addFreshEntity(breathBottle);
            player.getCooldowns().addCooldown(this, 10);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) {
            handStack.shrink(1);
        }

        return ActionResult.sidedSuccess(handStack, world.isClientSide());
    }
}
