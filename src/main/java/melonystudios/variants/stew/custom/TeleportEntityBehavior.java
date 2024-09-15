package melonystudios.variants.stew.custom;

import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import java.util.List;

import static melonystudios.variants.util.NBTUtils.anyNumericOrFloatDefault;
import static melonystudios.variants.util.NBTUtils.booleanOrDefault;

public class TeleportEntityBehavior extends StewBehavior {
    private final boolean randomTeleport;
    private final float teleportDiameter;
    private final BlockPos teleportPosition;
    @Nullable
    private CompoundNBT properties;

    public TeleportEntityBehavior(float teleportDiameter) {
        this(true, teleportDiameter, BlockPos.ZERO);
    }

    public TeleportEntityBehavior(BlockPos teleportPosition) {
        this(false, 0, teleportPosition);
    }

    public TeleportEntityBehavior(boolean randomTeleport, float teleportDiameter, BlockPos teleportPosition) {
        this.randomTeleport = randomTeleport;
        this.teleportDiameter = teleportDiameter;
        this.teleportPosition = teleportPosition;
    }

    public TeleportEntityBehavior() {
        this(true, 16, BlockPos.ZERO);
    }

    public boolean randomlyTeleports() {
        return this.randomTeleport;
    }

    public float getTeleportDiameter() {
        return this.teleportDiameter;
    }

    public BlockPos getTeleportPosition() {
        return this.teleportPosition;
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (!world.isClientSide && this.randomTeleport) VSUtils.teleportToRandomPosition(stack, world, livEntity, this.teleportDiameter);
        else {
            livEntity.teleportTo(this.teleportPosition.getX() + 0.5, this.teleportPosition.getY() + 0.5, this.teleportPosition.getZ() + 0.5);
            livEntity.fallDistance = 0;
            for (int i = 0; i < 128; ++i) {
                double d0 = (double) i / 127;
                float xSpeed = (livEntity.getRandom().nextFloat() - 0.5F) * 0.2F;
                float ySpeed = (livEntity.getRandom().nextFloat() - 0.5F) * 0.2F;
                float zSpeed = (livEntity.getRandom().nextFloat() - 0.5F) * 0.2F;
                double x = MathHelper.lerp(d0, livEntity.xo, livEntity.getX()) + (livEntity.getRandom().nextDouble() - 0.5D) * livEntity.getBbWidth() * 2;
                double y = MathHelper.lerp(d0, livEntity.yo, livEntity.getY()) + livEntity.getRandom().nextDouble() * (double) livEntity.getBbHeight();
                double z = MathHelper.lerp(d0, livEntity.zo, livEntity.getZ()) + (livEntity.getRandom().nextDouble() - 0.5D) * livEntity.getBbWidth() * 2;
                world.addParticle(ParticleTypes.PORTAL, x, y, z, xSpeed, ySpeed, zSpeed);
            }
        }
    }

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        this.properties = propertiesTag;
        BlockPos pos = (propertiesTag != null && propertiesTag.contains("teleport_position", Constants.TagTypes.INTEGER_ARRAY)) ? NBTUtils.readBlockPos(propertiesTag, "teleport_position") : livEntity.blockPosition();
        TeleportEntityBehavior behavior = new TeleportEntityBehavior(booleanOrDefault("random_teleport", propertiesTag, true), anyNumericOrFloatDefault("teleport_diameter", propertiesTag, 16), pos);
        behavior.executeBehavior(stewStack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT propertiesTag = new CompoundNBT();
        if (this.randomTeleport) {
            propertiesTag.putBoolean("random_teleport", true);
            propertiesTag.putFloat("teleport_diameter", this.teleportDiameter);
        }
        else propertiesTag.putIntArray("teleport_pos", new int[] {this.teleportPosition.getX(), this.teleportPosition.getY(), this.teleportPosition.getZ()});
        return propertiesTag;
    }

    @Override
    public List<ITextComponent> addToStewTooltip(ItemStack stack, @Nullable World world, ITooltipFlag flag) {
        List<ITextComponent> tooltip = super.addToStewTooltip(stack, world, flag);
        CompoundNBT propertiesTag = this.properties;
        if (flag.isAdvanced()) {
            if (propertiesTag != null) {
                if (teleportRandomly(propertiesTag)) {
                    tooltip.add(new TranslationTextComponent("tooltip.variants.behavior.teleport_entity.diameter", (int) propertiesTag.getFloat("teleport_diameter")).withStyle(TextFormatting.GRAY));
                } else {
                    if (propertiesTag.contains("teleport_position", Constants.TagTypes.INTEGER_ARRAY)) {
                        int[] position = propertiesTag.getIntArray("teleport_position");
                        tooltip.add(new TranslationTextComponent("tooltip.variants.behavior.teleport_entity.position", position[0], position[1], position[2]).withStyle(TextFormatting.GRAY));
                    }
                }
            }
        }
        return tooltip;
    }

    private boolean teleportRandomly(CompoundNBT propertiesTag) {
        if (propertiesTag.contains("random_teleport", Constants.TagTypes.ANY_NUMERIC)) {
            return propertiesTag.getBoolean("random_teleport");
        } else {
            return true;
        }
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.TELEPORT_ENTITY.get();
    }
}
