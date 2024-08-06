package melonystudios.variants.item.custom;

import melonystudios.variants.dispenser.DispenseVSBoatBehavior;
import melonystudios.variants.entity.custom.VSBoatEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;

public class VSBoatItem extends BoatItem {
    private static final Predicate<Entity> SPECTATORS_PREDICATE = EntityPredicates.NO_SPECTATORS.and(Entity::canBeCollidedWith);
    private final String woodType;

    public VSBoatItem(Item.Properties properties, String woodType) {
        super(null, properties);
        this.woodType = woodType;
        DispenserBlock.registerBehavior(this, new DispenseVSBoatBehavior(woodType));
    }

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        RayTraceResult fluidRayTrace = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.ANY);
        if (fluidRayTrace.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(handStack);
        } else {
            Vector3d viewVector = player.getViewVector(1);
            List<Entity> entitiesAroundPossiblePlacement = world.getEntities(player, player.getBoundingBox().expandTowards(viewVector.scale(5)).inflate(1), SPECTATORS_PREDICATE);
            if (!entitiesAroundPossiblePlacement.isEmpty()) {
                Vector3d eyePosition = player.getEyePosition(1);

                for(Entity entity : entitiesAroundPossiblePlacement) {
                    AxisAlignedBB axisAlignedBB = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (axisAlignedBB.contains(eyePosition)) return ActionResult.pass(handStack);
                }
            }

            if (fluidRayTrace.getType() == RayTraceResult.Type.BLOCK) {
                VSBoatEntity variantsBoat = new VSBoatEntity(world, fluidRayTrace.getLocation().x, fluidRayTrace.getLocation().y, fluidRayTrace.getLocation().z);
                variantsBoat.setWoodType(woodType);
                variantsBoat.yRot = player.yRot;
                if (!world.noCollision(variantsBoat, variantsBoat.getBoundingBox().inflate(-0.1D))) {
                    return ActionResult.fail(handStack);
                } else {
                    if (!world.isClientSide) {
                        world.addFreshEntity(variantsBoat);
                        if (!player.abilities.instabuild) handStack.shrink(1);
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.sidedSuccess(handStack, world.isClientSide());
                }
            } else {
                return ActionResult.pass(handStack);
            }
        }
    }
}
