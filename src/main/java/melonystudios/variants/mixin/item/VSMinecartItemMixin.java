package melonystudios.variants.mixin.item;

import melonystudios.variants.item.VSItems;
import melonystudios.variants.util.VSKeys;
import melonystudios.variants.util.VSStats;
import melonystudios.variants.util.VSUtils;
import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.MinecartItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecartItem.class)
public class VSMinecartItemMixin extends Item {
    @Shadow
    @Final
    private AbstractMinecartEntity.Type type;

    public VSMinecartItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void useOn(ItemUseContext context, CallbackInfoReturnable<ActionResultType> callback) {
        World world = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockState clickedState = world.getBlockState(clickedPos);

        // Picks up the spawner and puts in spawn data into the spawner minecart item.
        if (clickedState.is(VSBlockTags.SPAWNERS) && context.getPlayer() != null && VSKeys.isShiftDown() && this.type == AbstractMinecartEntity.Type.RIDEABLE) {
            PlayerEntity player = context.getPlayer();

            ItemStack spawnerMinecartStack = new ItemStack(VSItems.SPAWNER_MINECART.get());
            if (context.getItemInHand().getTag() != null) spawnerMinecartStack.setTag(context.getItemInHand().getTag());
            TileEntity blockEntity = world.getBlockEntity(clickedPos);
            if (blockEntity instanceof MobSpawnerTileEntity) {
                MobSpawnerTileEntity spawnerBlockEntity = (MobSpawnerTileEntity) blockEntity;
                CompoundNBT tag = spawnerMinecartStack.getOrCreateTag();
                tag.put("spawn_data", spawnerBlockEntity.getSpawner().save(new CompoundNBT()));

                SoundType soundType = clickedState.getSoundType(world, clickedPos, null);
                world.playSound(null, clickedPos, soundType.getBreakSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1) / 2, soundType.getPitch() * 0.8F);
                Minecraft.getInstance().particleEngine.destroy(clickedPos, clickedState);

                if (player.isCreative()) {
                    player.inventory.add(spawnerMinecartStack);
                } else {
                    VSUtils.setItemInHand(player, context.getHand(), spawnerMinecartStack);
                }
                world.setBlockAndUpdate(clickedPos, Blocks.AIR.defaultBlockState());
                player.awardStat(VSStats.SPAWNERS_PICKED_UP);
                callback.setReturnValue(ActionResultType.SUCCESS);
            }
        }
    }
}
