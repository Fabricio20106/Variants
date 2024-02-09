package com.junethewoods.variants.mixin.block;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Random;

@Mixin(ChorusFlowerBlock.class)
public abstract class VSChorusFlowerBlockMixin extends Block {
    @Shadow
    @Final
    private ChorusPlantBlock plant;

    @Shadow
    protected abstract void placeGrownFlower(World world, BlockPos pos, int age);

    @Shadow
    protected abstract void placeDeadFlower(World world, BlockPos pos);

    public VSChorusFlowerBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "randomTick", at = @At("HEAD"))
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand, CallbackInfo ci) {
        BlockPos abovePos = pos.above();
        if (world.isEmptyBlock(abovePos) && abovePos.getY() < 256) {
            int age = state.getValue(ChorusFlowerBlock.AGE);
            if (age < 5 && ForgeHooks.onCropsGrowPre(world, abovePos, state, true)) {
                boolean flag = false;
                boolean flag1 = false;
                BlockState belowState = world.getBlockState(pos.below());
                Block belowBlock = belowState.getBlock();
                if (belowBlock.is(VSTags.Blocks.CHORUS_FLOWER_PLANTABLE_ON)) {
                    flag = true;
                } else if (belowBlock == this.plant) {
                    int j = 1;

                    for(int k = 0; k < 4; ++k) {
                        Block blockBelow = world.getBlockState(pos.below(j + 1)).getBlock();
                        if (blockBelow != this.plant) {
                            if (blockBelow.is(VSTags.Blocks.CHORUS_FLOWER_PLANTABLE_ON)) {
                                flag1 = true;
                            }
                            break;
                        }

                        ++j;
                    }

                    if (j < 2 || j <= rand.nextInt(flag1 ? 5 : 4)) {
                        flag = true;
                    }
                } else if (belowState.isAir(world, pos.below())) {
                    flag = true;
                }

                if (flag && allNeighborsEmpty(world, abovePos, null) && world.isEmptyBlock(pos.above(2))) {
                    world.setBlock(pos, this.plant.getStateForPlacement(world, pos), 2);
                    this.placeGrownFlower(world, abovePos, age);
                } else if (age < 4) {
                    int l = rand.nextInt(4);
                    if (flag1) {
                        ++l;
                    }

                    boolean flag2 = false;

                    for(int i1 = 0; i1 < l; ++i1) {
                        Direction horizontalDir = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
                        BlockPos relativePos = pos.relative(horizontalDir);
                        if (world.isEmptyBlock(relativePos) && world.isEmptyBlock(relativePos.below()) && allNeighborsEmpty(world, relativePos, horizontalDir.getOpposite())) {
                            this.placeGrownFlower(world, relativePos, age + 1);
                            flag2 = true;
                        }
                    }

                    if (flag2) {
                        world.setBlock(pos, this.plant.getStateForPlacement(world, pos), 2);
                    } else {
                        this.placeDeadFlower(world, pos);
                    }
                } else {
                    this.placeDeadFlower(world, pos);
                }
                ForgeHooks.onCropsGrowPost(world, pos, state);
            }
        }
    }

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    public void canSurvive(BlockState state, IWorldReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState belowState = world.getBlockState(pos.below());
        if (belowState.getBlock() != plant && !belowState.is(VSTags.Blocks.CHORUS_FLOWER_PLANTABLE_ON)) {
            if (!belowState.isAir(world, pos.below())) {
                cir.setReturnValue(false);
            } else {
                boolean flag = false;

                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockState state1 = world.getBlockState(pos.relative(direction));
                    if (state1.is(this.plant)) {
                        if (flag) {
                            cir.setReturnValue(false);
                        }

                        flag = true;
                    } else if (!state1.isAir(world, pos.relative(direction))) {
                        cir.setReturnValue(false);
                    }
                }

                cir.setReturnValue(flag);
            }
        } else {
            cir.setReturnValue(true);
        }
    }

    private static boolean allNeighborsEmpty(IWorldReader world, BlockPos pos, @Nullable Direction direction) {
        for(Direction hDirections : Direction.Plane.HORIZONTAL) {
            if (hDirections != direction && !world.isEmptyBlock(pos.relative(hDirections))) {
                return false;
            }
        }
        return true;
    }
}
