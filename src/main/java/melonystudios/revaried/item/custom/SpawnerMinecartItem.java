package melonystudios.revaried.item.custom;

import melonystudios.revaried.component.RVDataComponents;
import melonystudios.revaried.dispenser.SpawnerMinecartDispenseBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SpawnerMinecartItem extends MinecartItem {
    public SpawnerMinecartItem(Properties properties) {
        super(AbstractMinecart.Type.SPAWNER, properties);
        DispenserBlock.registerBehavior(this, new SpawnerMinecartDispenseBehavior());
    }

    @Override
    @NotNull
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        // Spawns the spawner minecart with its NBT data.
        if (!state.is(BlockTags.RAILS)) {
            return InteractionResult.FAIL;
        } else {
            ItemStack handStack = context.getItemInHand();
            if (level instanceof ServerLevel serverWorld) {
                RailShape railShape = state.getBlock() instanceof BaseRailBlock baseRail ? baseRail.getRailDirection(state, level, pos, null) : RailShape.NORTH_SOUTH;
                double addedHeight = 0;
                if (railShape.isAscending()) addedHeight = 0.5;

                MinecartSpawner spawnerMinecart = (MinecartSpawner) MinecartSpawner.createMinecart(serverWorld, pos.getX() + 0.5, pos.getY() + 0.0625 + addedHeight, pos.getZ() + 0.5, AbstractMinecart.Type.SPAWNER, handStack, context.getPlayer());
                if (handStack.has(DataComponents.CUSTOM_NAME)) spawnerMinecart.setCustomName(handStack.getHoverName());
                if (handStack.has(RVDataComponents.SPAWNER_DATA.get())) spawnerMinecart.getSpawner().load(level, pos, handStack.get(RVDataComponents.SPAWNER_DATA.get()).copyTag());
                level.addFreshEntity(spawnerMinecart);
                serverWorld.gameEvent(GameEvent.ENTITY_PLACE, pos, GameEvent.Context.of(context.getPlayer(), serverWorld.getBlockState(pos.below())));
            }

            handStack.shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
    }

    @Override
    @NotNull
    public Component getName(ItemStack stack) {
        Component entityName = getSpawnEntityDisplayName(stack, "SpawnData");
        if (entityName != null) return Component.translatable(this.getDescriptionId(stack) + ".with_mob", entityName);
        return super.getName(stack);
    }

    @Nullable
    @SuppressWarnings("deprecation")
    public static Component getSpawnEntityDisplayName(ItemStack stack, String tagKey) {
        CompoundTag tag = stack.getOrDefault(RVDataComponents.SPAWNER_DATA.get(), CustomData.EMPTY).getUnsafe();
        Optional<ResourceLocation> entityKey = getEntityKey(tag, tagKey);
        return entityKey.flatMap(location -> BuiltInRegistries.ENTITY_TYPE.getOptional(location).map(
                type -> Component.translatable(type.getDescriptionId()))).orElse(null);
    }

    public static Optional<ResourceLocation> getEntityKey(CompoundTag tag, String tagKey) {
        if (tag.contains(tagKey, Tag.TAG_COMPOUND)) {
            String id = tag.getCompound(tagKey).getCompound("entity").getString("id");
            return Optional.ofNullable(ResourceLocation.tryParse(id));
        } else {
            return Optional.empty();
        }
    }
}
