package melonystudios.revaried.mixin.item;

import melonystudios.revaried.component.RVDataComponents;
import melonystudios.revaried.item.RVItems;
import melonystudios.revaried.misc.RVStatistics;
import melonystudios.revaried.tag.RVBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecartItem.class)
public class RVMinecartItemMixin extends Item {
    @Shadow
    @Final
    AbstractMinecart.Type type;

    public RVMinecartItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "useOn", at = @At(value = "HEAD"), cancellable = true)
    public void pickupSpawner(UseOnContext context, CallbackInfoReturnable<InteractionResult> callback) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        // picks up the spawner and puts in spawn data into the spawner minecart item
        if (state.is(RVBlockTags.PICKUPABLE_SPAWNERS) && context.getPlayer() != null && context.getPlayer().isShiftKeyDown() && this.type == AbstractMinecart.Type.RIDEABLE) {
            Player player = context.getPlayer();

            ItemStack spawnerMinecart = new ItemStack(RVItems.SPAWNER_MINECART.get());
            spawnerMinecart.applyComponents(context.getItemInHand().getComponentsPatch());
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (level instanceof ServerLevel && blockEntity instanceof SpawnerBlockEntity spawner) {
                spawnerMinecart.set(RVDataComponents.SPAWNER_DATA.get(), CustomData.of(spawner.getSpawner().save(new CompoundTag())));
                SoundType soundType = state.getSoundType(level, pos, context.getPlayer());
                level.playSound(null, pos, soundType.getBreakSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1) / 2, soundType.getPitch() * 0.8F);
                // use level events instead of directly telling the particle engine to spawn break particles
                level.levelEvent(2001, pos, Block.getId(state));
                level.removeBlock(pos, false);
                level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, state));

                if (!player.hasInfiniteMaterials()) context.getItemInHand().shrink(1);

                // todo: maybe use ItemUtils.createFilledResult
                if (!player.getInventory().add(spawnerMinecart) && !player.hasInfiniteMaterials()) {
                    player.drop(spawnerMinecart, true);
                }

                player.awardStat(RVStatistics.SPAWNERS_PICKED_UP.get());
            }
            callback.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide()));
        }
    }
}
