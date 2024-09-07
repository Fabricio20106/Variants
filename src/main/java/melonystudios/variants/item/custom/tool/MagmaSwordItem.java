package melonystudios.variants.item.custom.tool;

import melonystudios.variants.util.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;

public class MagmaSwordItem extends SwordItem {
    public MagmaSwordItem(IItemTier material, int damage, float swingSpeed, Properties properties) {
        super(material, damage, swingSpeed, properties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livEntity = (LivingEntity) entity;
            if (!livEntity.isInvulnerableTo(DamageSource.IN_FIRE) || !livEntity.isInvulnerableTo(DamageSource.ON_FIRE)) {
                if (stack.getTag() != null && stack.getTag().contains("seconds_on_fire", Constants.TagTypes.ANY_NUMERIC)) livEntity.setSecondsOnFire(stack.getTag().getInt("seconds_on_fire"));
                else livEntity.setSecondsOnFire(10);
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
}
