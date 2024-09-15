package melonystudios.variants.item.custom.bottle;

import melonystudios.variants.entity.custom.StainedExperienceBottleEntity;
import melonystudios.variants.util.Constants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class StainedExperienceBottleItem extends StainedFullGlassBottleItem {
    public StainedExperienceBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        if (stack.getTag() != null && stack.getTag().contains("enchantment_glint_override", Constants.TagTypes.ANY_NUMERIC)) return stack.getTag().getBoolean("enchantment_glint_override");
        return true;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_BOTTLE_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

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

        return ActionResult.sidedSuccess(handStack, world.isClientSide());
    }
}
