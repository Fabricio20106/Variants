package melonystudios.variants.mixin.entity;

import melonystudios.variants.entity.misc.DyeableShulker;
import melonystudios.variants.sound.VSSounds;
import melonystudios.variants.util.VSResourceLocations;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mixin(ShulkerEntity.class)
public abstract class RVShulkerEntityMixin extends GolemEntity implements DyeableShulker {
    @Shadow @Final protected static DataParameter<Byte> DATA_COLOR_ID;
    @Shadow @Nullable public abstract DyeColor getColor();

    public RVShulkerEntityMixin(EntityType<? extends GolemEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void setColor(DyeColor color) {
        this.entityData.set(DATA_COLOR_ID, (byte) color.getId());
    }

    @Override
    @Nonnull
    protected ResourceLocation getDefaultLootTable() {
        if (this.entityData.get(DATA_COLOR_ID) >= 16 || this.getColor() == null) {
            return super.getDefaultLootTable();
        } else {
            switch (this.getColor()) {
                case WHITE: return VSResourceLocations.WHITE_SHULKER;
                case LIGHT_GRAY: return VSResourceLocations.LIGHT_GRAY_SHULKER;
                case GRAY: return VSResourceLocations.GRAY_SHULKER;
                case BLACK: return VSResourceLocations.BLACK_SHULKER;
                case BROWN: return VSResourceLocations.BROWN_SHULKER;
                case RED: return VSResourceLocations.RED_SHULKER;
                case ORANGE: return VSResourceLocations.ORANGE_SHULKER;
                case YELLOW: return VSResourceLocations.YELLOW_SHULKER;
                case LIME: return VSResourceLocations.LIME_SHULKER;
                case GREEN: return VSResourceLocations.GREEN_SHULKER;
                case CYAN: return VSResourceLocations.CYAN_SHULKER;
                case LIGHT_BLUE: return VSResourceLocations.LIGHT_BLUE_SHULKER;
                case BLUE: return VSResourceLocations.BLUE_SHULKER;
                case PURPLE: return VSResourceLocations.PURPLE_SHULKER;
                case MAGENTA: return VSResourceLocations.MAGENTA_SHULKER;
                case PINK: return VSResourceLocations.PINK_SHULKER;
            }
        }
        return super.getDefaultLootTable();
    }

    @Override
    @Nonnull
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        if (handStack.getItem() == Items.INK_SAC && !this.level.isClientSide && this.entityData.get(DATA_COLOR_ID) != 16) {
            this.entityData.set(DATA_COLOR_ID, (byte) 16);
            this.level.playSound(null, this.blockPosition(), VSSounds.INK_SAC_SPLOTCH.get(), SoundCategory.HOSTILE, 1, 1);
            player.awardStat(Stats.ITEM_USED.get(handStack.getItem()));
            handStack.shrink(1);
            return ActionResultType.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }
}
