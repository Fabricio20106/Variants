package melonystudios.variants.mixin.item;

import melonystudios.variants.entity.misc.DyeableShulker;
import melonystudios.variants.sound.VSSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DyeItem.class)
public class RVDyeItemMixin extends Item {
    @Shadow
    @Final
    private DyeColor dyeColor;

    public RVDyeItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "interactLivingEntity", at = @At("HEAD"), cancellable = true)
    public void interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity livEntity, Hand hand, CallbackInfoReturnable<ActionResultType> callback) {
        if (livEntity instanceof ShulkerEntity) {
            ShulkerEntity shulker = (ShulkerEntity) livEntity;
            if (shulker.isAlive() && shulker.getColor() != this.dyeColor) {
                if (!player.level.isClientSide) {
                    ((DyeableShulker) shulker).setColor(this.dyeColor);
                    player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                    stack.shrink(1);
                }
                livEntity.level.playSound(null, livEntity.blockPosition(), VSSounds.DYE_STAIN.get(), SoundCategory.HOSTILE, 1, 1);
                callback.setReturnValue(ActionResultType.sidedSuccess(player.level.isClientSide));
            }
        }

        if (livEntity instanceof SheepEntity) {
            SheepEntity sheep = (SheepEntity) livEntity;
            if (sheep.isAlive() && !sheep.isSheared() && sheep.getColor() != this.dyeColor) {
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                livEntity.level.playSound(null, livEntity.blockPosition(), VSSounds.DYE_STAIN.get(), SoundCategory.NEUTRAL, 1, 1);
            }
        }
    }
}
