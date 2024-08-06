package melonystudios.variants.entity.custom;

import melonystudios.variants.Variants;
import melonystudios.variants.entity.VSEntities;
import melonystudios.variants.item.VSWeaponry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;

import static melonystudios.variants.item.custom.tool.DebugBowItem.*;

public class DebugArrowEntity extends AbstractArrowEntity {
    private static final DataParameter<CompoundNBT> DEBUG_PROPERTY = EntityDataManager.defineId(DebugArrowEntity.class, DataSerializers.COMPOUND_TAG);
    private ItemStack arrowItem = new ItemStack(VSWeaponry.DEBUG_ARROW.get());

    public DebugArrowEntity(EntityType<? extends AbstractArrowEntity> arrow, World world) {
        super(arrow, world);
    }

    public DebugArrowEntity(World world, double x, double y, double z) {
        super(VSEntities.DEBUG_ARROW.get(), x, y, z, world);
    }

    public DebugArrowEntity(World world, LivingEntity shooter) {
        super(VSEntities.DEBUG_ARROW.get(), shooter, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DEBUG_PROPERTY, new CompoundNBT());
    }

    @Override
    protected ItemStack getPickupItem() {
        return this.arrowItem.copy();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        CompoundNBT arrowTag = new CompoundNBT();
        CompoundNBT itemTag = new CompoundNBT();
        itemTag.put("debug_arrow_state", this.entityData.get(DEBUG_PROPERTY));
        arrowTag.put("tag", itemTag);
        tag.put("item", this.arrowItem.save(arrowTag));
        tag.put("debug_arrow_state", this.entityData.get(DEBUG_PROPERTY));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DEBUG_PROPERTY, tag.getCompound("debug_arrow_state"));
        if (tag.contains("item", 10)) this.arrowItem = ItemStack.of(tag.getCompound("item"));
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult hitResult) {
        super.onHitBlock(hitResult);
        if (this.getOwner() != null) {
            if (this.getOwner() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) this.getOwner();
                this.handleInteractionsByPlayer(player, this.level.getBlockState(hitResult.getBlockPos()), this.level, hitResult.getBlockPos(), true, this.arrowItem);
            } else if (this.getOwner() instanceof LivingEntity) {
                this.handleInteractionsByLivingNonPlayer(this.level.getBlockState(hitResult.getBlockPos()), this.level, hitResult.getBlockPos(), true, this.arrowItem);
            }
        }
    }

    public void setPropertyTag(CompoundNBT propertyTag) {
        this.entityData.set(DEBUG_PROPERTY, propertyTag);
    }

    private void handleInteractionsByPlayer(PlayerEntity shooter, BlockState state, IWorld world, BlockPos pos, boolean rightClick, ItemStack arrowStack) {
        if (shooter.canUseGameMasterBlocks()) {
            Block block = state.getBlock();
            StateContainer<Block, BlockState> stateDefinition = block.getStateDefinition();
            Collection<Property<?>> properties = stateDefinition.getProperties();
            String blockRegistryKey = ForgeRegistries.BLOCKS.getKey(block).toString();
            if (properties.isEmpty()) {
                sendBowMessage(shooter, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.empty", new TranslationTextComponent(block.getDescriptionId())));
            } else {
                CompoundNBT debugArrowState = arrowStack.getOrCreateTagElement("debug_arrow_state");
                String propertyKey = debugArrowState.getString(blockRegistryKey);
                setPropertyTag(debugArrowState);
                Property<?> property = stateDefinition.getProperty(propertyKey);
                if (rightClick) {
                    if (property == null) property = properties.iterator().next();
                    BlockState cycledState = cycleState(state, property, shooter.isSecondaryUseActive());
                    world.setBlock(pos, cycledState, 18);
                    sendBowMessage(shooter, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.update", property.getName(), getNameHelper(cycledState, property)));
                } else {
                    property = getRelative(properties, property, shooter.isSecondaryUseActive());
                    String propertyName = property.getName();
                    debugArrowState.putString(blockRegistryKey, propertyName);
                    setPropertyTag(debugArrowState);
                    sendBowMessage(shooter, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.select", propertyName, getNameHelper(state, property)));
                }
            }
        }
        if (!shooter.abilities.mayfly) sendBowMessage(shooter, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.survival").withStyle(TextFormatting.RED));
        if (!shooter.abilities.mayBuild) sendBowMessage(shooter, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.adventure").withStyle(TextFormatting.RED));
        if (shooter.isSpectator()) sendBowMessage(shooter, new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".debug.spectator").withStyle(TextFormatting.RED));
    }

    private void handleInteractionsByLivingNonPlayer(BlockState state, IWorld world, BlockPos pos, boolean rightClick, ItemStack arrowStack) {
        Block block = state.getBlock();
        StateContainer<Block, BlockState> stateDefinition = block.getStateDefinition();
        Collection<Property<?>> properties = stateDefinition.getProperties();
        String blockRegistryKey = ForgeRegistries.BLOCKS.getKey(block).toString();
        if (!properties.isEmpty()) {
            CompoundNBT debugArrowState = arrowStack.getOrCreateTagElement("debug_arrow_state");
            String propertyKey = debugArrowState.getString(blockRegistryKey);
            setPropertyTag(debugArrowState);
            Property<?> property = stateDefinition.getProperty(propertyKey);
            if (rightClick) {
                if (property == null) property = properties.iterator().next();
                BlockState cycledState = cycleState(state, property, false);
                world.setBlock(pos, cycledState, 18);
            } else {
                property = getRelative(properties, property, false);
                String propertyName = property.getName();
                debugArrowState.putString(blockRegistryKey, propertyName);
                setPropertyTag(debugArrowState);
            }
        }
    }

    @Override
    public void checkDespawn() {
        if (this.inGroundTime > 1200) this.remove();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
