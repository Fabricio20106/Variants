package melonystudios.variants.mixin.entity;

import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.command.impl.data.EntityDataAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.SpawnerMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SpawnerMinecartEntity.class)
public abstract class VSSpawnerMinecartEntityMixin extends Entity {
    @Shadow
    public AbstractSpawner spawner;

    public VSSpawnerMinecartEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    public void destroy(DamageSource source) {
        this.remove();
        if (VSConfigs.COMMON_CONFIGS.placeSpawnerWhenBreakingMinecart.get() && !this.level.getBlockState(this.blockPosition()).is(VSBlockTags.SPAWNER_MINECART_CANNOT_REPLACE) && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            // Breaking the block where the minecart is.
            Block.dropResources(getBlockState(), this.level, blockPosition(), getBlockEntity());
            SoundType soundType = getBlockState().getSoundType(this.level, blockPosition(), null);
            this.level.playSound(null, blockPosition(), soundType.getBreakSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1) / 2, soundType.getPitch() * 0.8F);
            Minecraft.getInstance().particleEngine.destroy(blockPosition(), getBlockState());
            this.level.setBlockAndUpdate(blockPosition(), Blocks.AIR.defaultBlockState());

            // Placing the spawner (with data) on that block.
            this.level.setBlockAndUpdate(blockPosition(), Blocks.SPAWNER.defaultBlockState());
            TileEntity blockEntity = getBlockEntity();
            if (blockEntity instanceof MobSpawnerTileEntity) {
                ((MobSpawnerTileEntity) blockEntity).getSpawner().load(new EntityDataAccessor(this).getData());
            }
            this.spawnAtLocation(new ItemStack(Items.MINECART));
        } else {
            if (!source.isExplosion() && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                // Drop item if the block being broken is in "#variants:spawner_minecart_cannot_replace" or if the "placeSpawnerWhenBreakingMinecart" config is off.
                ItemStack minecartStack = new ItemStack(VSItems.SPAWNER_MINECART.get());
                CompoundNBT tag = minecartStack.getOrCreateTag();
                tag.put("spawn_data", this.spawner.save(tag.getCompound("spawn_data")));
                this.spawnAtLocation(minecartStack);
            }
        }
    }

    @Unique
    private BlockState getBlockState() {
        return this.level.getBlockState(blockPosition());
    }

    @Unique
    private TileEntity getBlockEntity() {
        return this.level.getBlockEntity(blockPosition());
    }
}
