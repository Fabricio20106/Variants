package melonystudios.variants.mixin.item;

import melonystudios.variants.entity.misc.Leashable;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.LeashKnotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.LeadItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeadItem.class)
public class VSLeadItemMixin extends Item {
    public VSLeadItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "bindPlayerMobs", at = @At("TAIL"), cancellable = true)
    private static void bindPlayerMobs(PlayerEntity player, World world, BlockPos pos, CallbackInfoReturnable<ActionResultType> cir) {
        LeashKnotEntity leash = null;
        boolean success = false;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for (BoatEntity boat : world.getEntitiesOfClass(BoatEntity.class, new AxisAlignedBB((double) x - 7, (double) y - 7, (double) z - 7, (double) x + 7, (double) y + 7, (double) z + 7))) {
            Leashable leashable = (Leashable) boat;
            if (leashable.getLeashHolder() == player) {
                if (leash == null) leash = LeashKnotEntity.getOrCreateKnot(world, pos);

                leashable.setLeashedTo(leash, true);
                success = true;
            }
        }
        if (success) cir.setReturnValue(ActionResultType.SUCCESS);
    }
}
