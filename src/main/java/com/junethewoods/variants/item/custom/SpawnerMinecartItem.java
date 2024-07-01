package com.junethewoods.variants.item.custom;

import com.junethewoods.variants.config.VSConfigs;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.item.minecart.SpawnerMinecartEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.RailShape;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SpawnerMinecartItem extends MinecartItem {
    public SpawnerMinecartItem(Properties properties) {
        super(AbstractMinecartEntity.Type.SPAWNER, properties);
    }

    @Override
    @Nonnull
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockState clickedState = world.getBlockState(clickedPos);

        // Spawns the Spawner Minecart with its NBT data.
        if (clickedState.is(BlockTags.RAILS) && !world.isClientSide) {
            RailShape railShape = clickedState.getBlock() instanceof AbstractRailBlock ? ((AbstractRailBlock) clickedState.getBlock()).getRailDirection(clickedState, world, clickedPos, null) : RailShape.NORTH_SOUTH;
            ItemStack contextStack = context.getItemInHand();
            double addedHeight = 0;
            if (railShape.isAscending()) addedHeight = 0.5;

            SpawnerMinecartEntity spawnerMinecart = (SpawnerMinecartEntity) SpawnerMinecartEntity.createMinecart(world, clickedPos.getX() + 0.5, clickedPos.getY() + 0.0625 + addedHeight, clickedPos.getZ() + 0.5, AbstractMinecartEntity.Type.SPAWNER);
            if (contextStack.hasCustomHoverName()) spawnerMinecart.setCustomName(contextStack.getHoverName());
            spawnerMinecart.spawner.load(contextStack.getOrCreateTagElement("spawn_data"));
            world.addFreshEntity(spawnerMinecart);
            contextStack.shrink(1);
            return ActionResultType.SUCCESS;
        }
        return super.useOn(context);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab) && VSConfigs.COMMON_CONFIGS.populateSpawnerMinecartsInTabs.get()) {
            for (EntityType<?> entityType : ForgeRegistries.ENTITIES) {
                if (entityType.canSummon()) {
                    ItemStack minecartStack = new ItemStack(this);
                    CompoundNBT spawnData = minecartStack.getOrCreateTagElement("spawn_data");
                    CompoundNBT subSpawnData = spawnData.getCompound("SpawnData");
                    subSpawnData.putString("id", ForgeRegistries.ENTITIES.getKey(entityType).toString());
                    spawnData.put("SpawnData", subSpawnData);
                    list.add(minecartStack);
                }
            }
        } else {
            super.fillItemCategory(tab, list);
        }
    }

    @Override
    @Nonnull
    public ITextComponent getName(ItemStack stack) {
        ITextComponent entityName = getSpawnEntityDisplayName(stack);
        if (entityName != null) return new TranslationTextComponent(getDescriptionId() + ".with_mob", entityName);
        return super.getName(stack);
    }

    // Methods below copied from Melony Lib.
    @Nullable
    private static ITextComponent getSpawnEntityDisplayName(ItemStack stack) {
        CompoundNBT tag = stack.getTagElement("spawn_data");
        ResourceLocation entityKey = getEntityKey(tag);
        return entityKey != null ? new TranslationTextComponent(ForgeRegistries.ENTITIES.getValue(entityKey).getDescriptionId()) : null;
    }

    @Nullable
    private static ResourceLocation getEntityKey(CompoundNBT tag) {
        if (tag != null && tag.contains("SpawnData")) return ResourceLocation.tryParse(tag.getCompound("SpawnData").getString("id"));
        return null;
    }
}
