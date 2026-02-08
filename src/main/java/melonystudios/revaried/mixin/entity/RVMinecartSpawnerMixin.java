package melonystudios.revaried.mixin.entity;

import melonystudios.revaried.component.RVDataComponents;
import melonystudios.revaried.item.RVItems;
import melonystudios.revaried.option.RVCommonOptions;
import melonystudios.revaried.tag.RVBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecartSpawner.class)
public abstract class RVMinecartSpawnerMixin extends AbstractMinecart {
    @Shadow
    public abstract BaseSpawner getSpawner();

    public RVMinecartSpawnerMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void destroy(DamageSource source) {
        this.kill();
        BlockState state = this.getBlockState();

        if (RVCommonOptions.PLACE_SPAWNER_WHEN_BREAKING_MINECART.get() &&
                !state.is(RVBlockTags.SPAWNER_MINECARTS_CANNOT_REPLACE) &&
                this.level().getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
            BlockPos pos = this.blockPosition();
            BlockEntity blockEntity = this.getBlockEntity();

            // break the block where the minecart is
            Block.dropResources(state, this.level(), pos, blockEntity);
            SoundType soundType = state.getSoundType(this.level(), pos, source.getEntity());
            this.level().playSound(null, pos, soundType.getBreakSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1) / 2, soundType.getPitch() * 0.8F);
            this.level().levelEvent(2001, pos, Block.getId(state));
            this.level().removeBlock(pos, false);
            this.level().gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(source.getEntity(), state));

            // placing the spawner, with data, on that block
            this.level().setBlock(this.blockPosition(), Blocks.SPAWNER.defaultBlockState(), 3);
            if (this.level().getBlockEntity(this.blockPosition()) instanceof SpawnerBlockEntity spawner) spawner.getSpawner().load(this.level(), pos, this.getSpawner().save(new CompoundTag()));

            if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                ItemStack minecart = new ItemStack(Items.MINECART);
                minecart.set(DataComponents.CUSTOM_NAME, this.getCustomName());
                this.spawnAtLocation(minecart);
            }
        } else {
            // drop the item if:
            // - the block being broken is in "#revaried:spawner_minecart_cannot_replace";
            // - the "place spawner when breaking minecart" option is false.
            if (!source.is(DamageTypeTags.IS_EXPLOSION) && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                ItemStack spawnerMinecart = new ItemStack(RVItems.SPAWNER_MINECART.get());
                spawnerMinecart.set(DataComponents.CUSTOM_NAME, this.getCustomName());
                spawnerMinecart.set(RVDataComponents.SPAWNER_DATA.get(), CustomData.of(this.getSpawner().save(new CompoundTag())));
                this.spawnAtLocation(spawnerMinecart);
            }
        }
    }

    @Unique
    @Override
    @Nullable
    public ItemStack getPickedResult(HitResult target) {
        ItemStack spawnerMinecart = new ItemStack(RVItems.SPAWNER_MINECART.get());
        spawnerMinecart.set(DataComponents.CUSTOM_NAME, this.getCustomName());
        spawnerMinecart.set(RVDataComponents.SPAWNER_DATA.get(), CustomData.of(this.getSpawner().save(new CompoundTag())));
        return spawnerMinecart;
    }

    @Unique
    private BlockState getBlockState() {
        return this.level().getBlockState(this.blockPosition());
    }

    @Unique
    private BlockEntity getBlockEntity() {
        return this.level().getBlockEntity(this.blockPosition());
    }
}
