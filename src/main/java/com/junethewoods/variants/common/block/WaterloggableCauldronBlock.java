package com.junethewoods.variants.common.block;

/*public class WaterloggableCauldronBlock extends Block {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.join(VoxelShapes.block(),
            VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D),
                    box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D),
                    box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D),
                    INSIDE),
            IBooleanFunction.ONLY_FIRST);

    public WaterloggableCauldronBlock(Properties builder) {
        super(builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0).setValue(WATERLOGGED, false));
    }

    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext selectionContext) {
        return SHAPE;
    }

    public VoxelShape getInteractionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return INSIDE;
    }

    public void entityInside(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
        int i = p_196262_1_.getValue(LEVEL);
        float f = (float)p_196262_3_.getY() + (6.0F + (float)(3 * i)) / 16.0F;
        if (!p_196262_2_.isClientSide && p_196262_4_.isOnFire() && i > 0 && p_196262_4_.getY() <= (double) f) {
            p_196262_4_.clearFire();
            this.setWaterLevel(p_196262_2_, p_196262_3_, p_196262_1_, i - 1);
        }

    }

    public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        ItemStack itemstack = p_225533_4_.getItemInHand(p_225533_5_);
        if (itemstack.isEmpty()) {
            return ActionResultType.PASS;
        } else {
            int i = p_225533_1_.getValue(LEVEL);
            Item item = itemstack.getItem();
            if (item == Items.WATER_BUCKET) {
                if (i < 3 && !p_225533_2_.isClientSide) {
                    if (!p_225533_4_.abilities.instabuild) {
                        p_225533_4_.setItemInHand(p_225533_5_, new ItemStack(Items.BUCKET));
                    }

                    p_225533_4_.awardStat(Stats.FILL_CAULDRON);
                    this.setWaterLevel(p_225533_2_, p_225533_3_, p_225533_1_, 3);
                    p_225533_2_.playSound((PlayerEntity)null, p_225533_3_, SoundEvents.BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return ActionResultType.sidedSuccess(p_225533_2_.isClientSide);
            } else if (item == Items.BUCKET) {
                if (i == 3 && !p_225533_2_.isClientSide) {
                    if (!p_225533_4_.abilities.instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            p_225533_4_.setItemInHand(p_225533_5_, new ItemStack(Items.WATER_BUCKET));
                        } else if (!p_225533_4_.inventory.add(new ItemStack(Items.WATER_BUCKET))) {
                            p_225533_4_.drop(new ItemStack(Items.WATER_BUCKET), false);
                        }
                    }

                    p_225533_4_.awardStat(Stats.USE_CAULDRON);
                    this.setWaterLevel(p_225533_2_, p_225533_3_, p_225533_1_, 0);
                    p_225533_2_.playSound(null, p_225533_3_, SoundEvents.BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return ActionResultType.sidedSuccess(p_225533_2_.isClientSide);
            } else if (item == Items.GLASS_BOTTLE) {
                if (i > 0 && !p_225533_2_.isClientSide) {
                    if (!p_225533_4_.abilities.instabuild) {
                        ItemStack itemstack4 = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
                        p_225533_4_.awardStat(Stats.USE_CAULDRON);
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            p_225533_4_.setItemInHand(p_225533_5_, itemstack4);
                        } else if (!p_225533_4_.inventory.add(itemstack4)) {
                            p_225533_4_.drop(itemstack4, false);
                        } else if (p_225533_4_ instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity)p_225533_4_).refreshContainer(p_225533_4_.inventoryMenu);
                        }
                    }

                    p_225533_2_.playSound((PlayerEntity)null, p_225533_3_, SoundEvents.BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    this.setWaterLevel(p_225533_2_, p_225533_3_, p_225533_1_, i - 1);
                }

                return ActionResultType.sidedSuccess(p_225533_2_.isClientSide);
            } else if (item == Items.POTION && PotionUtils.getPotion(itemstack) == Potions.WATER) {
                if (i < 3 && !p_225533_2_.isClientSide) {
                    if (!p_225533_4_.abilities.instabuild) {
                        ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
                        p_225533_4_.awardStat(Stats.USE_CAULDRON);
                        p_225533_4_.setItemInHand(p_225533_5_, itemstack3);
                        if (p_225533_4_ instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity)p_225533_4_).refreshContainer(p_225533_4_.inventoryMenu);
                        }
                    }

                    p_225533_2_.playSound((PlayerEntity)null, p_225533_3_, SoundEvents.BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    this.setWaterLevel(p_225533_2_, p_225533_3_, p_225533_1_, i + 1);
                }

                return ActionResultType.sidedSuccess(p_225533_2_.isClientSide);
            } else {
                if (i > 0 && item instanceof IDyeableArmorItem) {
                    IDyeableArmorItem idyeablearmoritem = (IDyeableArmorItem)item;
                    if (idyeablearmoritem.hasCustomColor(itemstack) && !p_225533_2_.isClientSide) {
                        idyeablearmoritem.clearColor(itemstack);
                        this.setWaterLevel(p_225533_2_, p_225533_3_, p_225533_1_, i - 1);
                        p_225533_4_.awardStat(Stats.CLEAN_ARMOR);
                        return ActionResultType.SUCCESS;
                    }
                }

                if (i > 0 && item instanceof BannerItem) {
                    if (BannerTileEntity.getPatternCount(itemstack) > 0 && !p_225533_2_.isClientSide) {
                        ItemStack itemstack2 = itemstack.copy();
                        itemstack2.setCount(1);
                        BannerTileEntity.removeLastPattern(itemstack2);
                        p_225533_4_.awardStat(Stats.CLEAN_BANNER);
                        if (!p_225533_4_.abilities.instabuild) {
                            itemstack.shrink(1);
                            this.setWaterLevel(p_225533_2_, p_225533_3_, p_225533_1_, i - 1);
                        }

                        if (itemstack.isEmpty()) {
                            p_225533_4_.setItemInHand(p_225533_5_, itemstack2);
                        } else if (!p_225533_4_.inventory.add(itemstack2)) {
                            p_225533_4_.drop(itemstack2, false);
                        } else if (p_225533_4_ instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity)p_225533_4_).refreshContainer(p_225533_4_.inventoryMenu);
                        }
                    }

                    return ActionResultType.sidedSuccess(p_225533_2_.isClientSide);
                } else if (i > 0 && item instanceof BlockItem) {
                    Block block = ((BlockItem)item).getBlock();
                    if (block instanceof ShulkerBoxBlock && !p_225533_2_.isClientSide()) {
                        ItemStack itemstack1 = new ItemStack(Blocks.SHULKER_BOX, 1);
                        if (itemstack.hasTag()) {
                            itemstack1.setTag(itemstack.getTag().copy());
                        }

                        p_225533_4_.setItemInHand(p_225533_5_, itemstack1);
                        this.setWaterLevel(p_225533_2_, p_225533_3_, p_225533_1_, i - 1);
                        p_225533_4_.awardStat(Stats.CLEAN_SHULKER_BOX);
                        return ActionResultType.SUCCESS;
                    } else {
                        return ActionResultType.CONSUME;
                    }
                } else {
                    return ActionResultType.PASS;
                }
            }
        }
    }

    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (p_196271_1_.getValue(WATERLOGGED)) {
            p_196271_4_.getLiquidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickDelay(p_196271_4_));
        }

        return super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        FluidState fluidstate = p_196258_1_.getLevel().getFluidState(p_196258_1_.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8);
    }

    public void setWaterLevel(World p_176590_1_, BlockPos p_176590_2_, BlockState p_176590_3_, int p_176590_4_) {
        p_176590_1_.setBlock(p_176590_2_, p_176590_3_.setValue(LEVEL, MathHelper.clamp(p_176590_4_, 0, 3)), 2);
        p_176590_1_.updateNeighbourForOutputSignal(p_176590_2_, this);
    }

    public void handleRain(World p_176224_1_, BlockPos p_176224_2_) {
        if (p_176224_1_.random.nextInt(20) == 1) {
            float f = p_176224_1_.getBiome(p_176224_2_).getTemperature(p_176224_2_);
            if (!(f < 0.15F)) {
                BlockState blockstate = p_176224_1_.getBlockState(p_176224_2_);
                if (blockstate.getValue(LEVEL) < 3) {
                    p_176224_1_.setBlock(p_176224_2_, blockstate.cycle(LEVEL), 2);
                }

            }
        }
    }

    public boolean hasAnalogOutputSignal(BlockState p_149740_1_) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
        return p_180641_1_.getValue(LEVEL);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(LEVEL, WATERLOGGED);
    }

    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }
}*/
