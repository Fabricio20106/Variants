package com.junethewoods.variants.mixin.entity;

import com.google.common.collect.Lists;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.memory.WalkTarget;
import net.minecraft.entity.ai.brain.task.FarmTask;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPosWrapper;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

@Mixin(FarmTask.class)
public abstract class VSFarmTaskMixin {
    @Shadow
    @Nullable
    protected abstract BlockPos getValidFarmland(ServerWorld world);
    @Shadow
    private int timeWorkedSoFar;

    @Unique
    @Nullable
    private BlockPos aboveFarmlandPos;
    @Unique
    private long nextOkStartTime;
    @Unique
    private final List<BlockPos> validFarmlandAroundVillager = Lists.newArrayList();

    @Inject(method = "validPos", at = @At("HEAD"), cancellable = true)
    private void validPos(BlockPos pos, ServerWorld world, CallbackInfoReturnable<Boolean> cir) {
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        Block blockBelow = world.getBlockState(pos.below()).getBlock();
        cir.setReturnValue(block instanceof CropsBlock && ((CropsBlock) block).isMaxAge(state) || state.isAir() && (blockBelow instanceof FarmlandBlock || blockBelow.is(VSTags.Blocks.FARMLAND)));
    }

    @Inject(method = "tick(Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/entity/merchant/villager/VillagerEntity;J)V", at = @At("HEAD"))
    protected void tick(ServerWorld world, VillagerEntity villager, long time, CallbackInfo ci) {
        if (this.aboveFarmlandPos == null || this.aboveFarmlandPos.closerThan(villager.position(), 1)) {
            if (this.aboveFarmlandPos != null && time > this.nextOkStartTime) {
                BlockState aboveState = world.getBlockState(this.aboveFarmlandPos);
                Block aboveBlock = aboveState.getBlock();
                Block farmlandBlock = world.getBlockState(this.aboveFarmlandPos.below()).getBlock();
                if (aboveBlock instanceof CropsBlock && ((CropsBlock) aboveBlock).isMaxAge(aboveState)) {
                    world.destroyBlock(this.aboveFarmlandPos, true, villager);
                }

                if (aboveState.isAir() && farmlandBlock.is(VSTags.Blocks.FARMLAND) && hasFarmSeeds(villager)) {
                    Inventory inventory = villager.getInventory();

                    for (int i = 0; i < inventory.getContainerSize(); ++i) {
                        ItemStack stack = inventory.getItem(i);
                        boolean plantedCrop = false;
                        if (stack.getItem().is(VSTags.Items.VILLAGER_PLANTABLE_SEEDS)) {
                            Item item = stack.getItem();
                            if (item instanceof BlockItem) {
                                BlockItem blockItem = (BlockItem) item;
                                BlockState cropBlock = blockItem.getBlock().defaultBlockState();
                                world.setBlockAndUpdate(this.aboveFarmlandPos, cropBlock);
                                plantedCrop = true;
                            } else if (stack.getItem() instanceof IPlantable) {
                                if (((IPlantable) stack.getItem()).getPlantType(world, this.aboveFarmlandPos) == PlantType.CROP) {
                                    world.setBlock(this.aboveFarmlandPos, ((IPlantable) stack.getItem()).getPlant(world, this.aboveFarmlandPos), 3);
                                    plantedCrop = true;
                                }
                            }
                        }

                        if (plantedCrop) {
                            world.playSound(null, this.aboveFarmlandPos.getX(), this.aboveFarmlandPos.getY(), this.aboveFarmlandPos.getZ(), SoundEvents.CROP_PLANTED, SoundCategory.BLOCKS, 1, 1);
                            stack.shrink(1);
                            if (stack.isEmpty()) inventory.setItem(i, ItemStack.EMPTY);
                            break;
                        }
                    }
                }

                if (aboveBlock instanceof CropsBlock && !((CropsBlock) aboveBlock).isMaxAge(aboveState)) {
                    this.validFarmlandAroundVillager.remove(this.aboveFarmlandPos);
                    this.aboveFarmlandPos = this.getValidFarmland(world);
                    if (this.aboveFarmlandPos != null) {
                        this.nextOkStartTime = time + 20L;
                        villager.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(new BlockPosWrapper(this.aboveFarmlandPos), 0.5F, 1));
                        villager.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new BlockPosWrapper(this.aboveFarmlandPos));
                    }
                }
            }

            ++this.timeWorkedSoFar;
        }
    }

    @Unique
    public boolean hasFarmSeeds(VillagerEntity villager) {
        return hasAnyMatching(villager, (stack -> stack.getItem().is(VSTags.Items.VILLAGER_PLANTABLE_SEEDS)));
    }

    @Unique
    private boolean hasAnyMatching(VillagerEntity villager, Predicate<ItemStack> stack) {
        for(int i = 0; i < villager.getInventory().getContainerSize(); ++i) {
            ItemStack invStack = villager.getInventory().getItem(i);
            if (stack.test(invStack)) {
                return true;
            }
        }
        return false;
    }
}
