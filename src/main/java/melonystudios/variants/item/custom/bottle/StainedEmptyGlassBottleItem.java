package melonystudios.variants.item.custom.bottle;

import melonystudios.variants.item.VSItems;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VanillaUtils;
import net.minecraft.block.BlockState;
import net.minecraft.command.impl.data.EntityDataAccessor;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class StainedEmptyGlassBottleItem extends Item {
    private final int glassColor;
    private final ResourceLocation colorName;

    public StainedEmptyGlassBottleItem(int glassColor, String colorName, Properties properties) {
        super(properties);
        this.glassColor = glassColor;
        this.colorName = new ResourceLocation(colorName);
    }

    public StainedEmptyGlassBottleItem(DyeColor dyeColor, Properties properties) {
        super(properties);
        this.glassColor = dyeColor.getColorValue();
        this.colorName = new ResourceLocation(dyeColor.getName());
    }

    public int getGlassColor(@Nullable ItemStack stack) {
        if (stack != null) {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("glass_color", Constants.TagTypes.ANY_NUMERIC)) return tag.getInt("glass_color");
        }
        return this.glassColor;
    }

    public ResourceLocation getColorName(@Nullable ItemStack stack) {
        if (stack != null) {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("color_name", Constants.TagTypes.STRING)) return new ResourceLocation(tag.getString("color_name"));
        }
        return this.colorName;
    }

    @Override
    @Nonnull
    public ActionResultType useOn(ItemUseContext context) {
        BlockPos pos = context.getClickedPos();
        World world = context.getLevel();
        BlockState state = world.getBlockState(pos);
        if (state.is(BlockTags.BEEHIVES) && state.hasProperty(BlockStateProperties.LEVEL_HONEY) && context.getPlayer() != null) {
            if (state.getValue(BlockStateProperties.LEVEL_HONEY) >= 5) {
                PlayerEntity player = context.getPlayer();
                ItemStack handStack = context.getPlayer().getItemInHand(Hand.MAIN_HAND);
                world.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LEVEL_HONEY, 0));
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundCategory.NEUTRAL, 1, 1);
                return ActionResult.sidedSuccess(this.turnBottleIntoItem(handStack, player, new ItemStack(VSItems.STAINED_HONEY_BOTTLE.get())), world.isClientSide).getResult();
            }
        }
        return super.useOn(context);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        List<AreaEffectCloudEntity> dragonBreathClouds = world.getEntitiesOfClass(AreaEffectCloudEntity.class, player.getBoundingBox().inflate(2), StainedEmptyGlassBottleItem::isValidEffectCloud);
        ItemStack handStack = player.getItemInHand(hand);

        // Dragon's Breath
        if (!dragonBreathClouds.isEmpty()) {
            AreaEffectCloudEntity effectCloud = dragonBreathClouds.get(0);
            effectCloud.setRadius(effectCloud.getRadius() - 0.5F);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundCategory.NEUTRAL, 1,  1);
            return ActionResult.sidedSuccess(this.turnBottleIntoItem(handStack, player, new ItemStack(VSItems.STAINED_DRAGON_BREATH.get())), world.isClientSide);
        } else {
            RayTraceResult hitResult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
            if (hitResult.getType() == RayTraceResult.Type.MISS) return ActionResult.pass(handStack);
            else {
                if (hitResult.getType() == RayTraceResult.Type.BLOCK) {
                    BlockPos hitPos = ((BlockRayTraceResult) hitResult).getBlockPos();
                    if (!world.mayInteract(player, hitPos)) return ActionResult.pass(handStack);

                    if (world.getFluidState(hitPos).is(FluidTags.WATER)) {
                        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundCategory.NEUTRAL, 1, 1);
                        return ActionResult.sidedSuccess(this.turnBottleIntoItem(handStack, player, VanillaUtils.setPotion(new ItemStack(VSItems.STAINED_POTION.get()), Potions.WATER)), world.isClientSide);
                    }
                }
                return ActionResult.pass(handStack);
            }
        }
    }

    public static boolean isValidEffectCloud(AreaEffectCloudEntity entity) {
        CompoundNBT tag = new EntityDataAccessor(entity).getData();
        boolean hasHarmingInEffects = false;
        if (tag.contains("Effects", Constants.TagTypes.LIST)) {
            ListNBT effectTag = tag.getList("Effects", Constants.TagTypes.COMPOUND);
            for (int i  = 0; i < effectTag.size(); i++) {
                EffectInstance instance = EffectInstance.load(effectTag.getCompound(i));
                if (instance.getEffect() == Effects.HARM) {
                    hasHarmingInEffects = true;
                    break;
                }
            }
        }
        return entity.isAlive() && entity.getParticle() == ParticleTypes.DRAGON_BREATH && hasHarmingInEffects;
    }

    public ItemStack turnBottleIntoItem(ItemStack stack, PlayerEntity player, ItemStack filledStack) {
        player.awardStat(Stats.ITEM_USED.get(this));
        GlassType.setBottle(stack, filledStack);
        return DrinkHelper.createFilledResult(stack, player, filledStack);
    }
}
