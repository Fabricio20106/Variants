package com.junethewoods.variants.item.custom;

import com.junethewoods.variants.block.dispenser.DispenseVSBoatBehavior;
import com.junethewoods.variants.entity.custom.VSBoatEntity;
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

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        RayTraceResult fluidRayTrace = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.ANY);
        if (fluidRayTrace.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(heldItem);
        } else {
            Vector3d vector3D = player.getViewVector(1);
            List<Entity> list = world.getEntities(player, player.getBoundingBox().expandTowards(vector3D.scale(5)).inflate(1), SPECTATORS_PREDICATE);
            if (!list.isEmpty()) {
                Vector3d vector3D1 = player.getEyePosition(1);

                for(Entity entity : list) {
                    AxisAlignedBB axisAlignedBB = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (axisAlignedBB.contains(vector3D1)) {
                        return ActionResult.pass(heldItem);
                    }
                }
            }

            if (fluidRayTrace.getType() == RayTraceResult.Type.BLOCK) {
                VSBoatEntity variantsBoat = new VSBoatEntity(world, fluidRayTrace.getLocation().x, fluidRayTrace.getLocation().y, fluidRayTrace.getLocation().z);
                variantsBoat.setWoodType(woodType);
                variantsBoat.yRot = player.yRot;
                if (!world.noCollision(variantsBoat, variantsBoat.getBoundingBox().inflate(-0.1D))) {
                    return ActionResult.fail(heldItem);
                } else {
                    if (!world.isClientSide) {
                        world.addFreshEntity(variantsBoat);
                        if (!player.abilities.instabuild) {
                            heldItem.shrink(1);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.sidedSuccess(heldItem, world.isClientSide());
                }
            } else {
                return ActionResult.pass(heldItem);
            }
        }
    }
}
