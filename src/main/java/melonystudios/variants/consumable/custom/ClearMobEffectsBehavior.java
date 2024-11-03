package melonystudios.variants.consumable.custom;

import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ClearMobEffectsBehavior extends ConsumeBehavior {
    private final ItemStack curativeStack;

    public ClearMobEffectsBehavior(ItemStack curativeStack) {
        this.curativeStack = curativeStack;
    }

    public ClearMobEffectsBehavior() {
        this(new ItemStack(Items.MILK_BUCKET));
    }

    public ItemStack curativeItem() {
        return this.curativeStack;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (!world.isClientSide && propertiesTag != null) {
            ItemStack curativeStack = VSUtils.loadStack(propertiesTag.getCompound("curative_item"));
            livEntity.curePotionEffects(curativeStack);
        } else if (!world.isClientSide && this.curativeStack != null) {
            livEntity.curePotionEffects(this.curativeStack);
        }
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        ClearMobEffectsBehavior clearEffectsBehavior = new ClearMobEffectsBehavior(VSUtils.loadStack(NBTUtils.compoundOrDefault("curative_item", propertiesTag, milkBucket())));
        clearEffectsBehavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        properties.put("curative_item", VSUtils.saveStack(this.curativeStack, new CompoundNBT()));
        return properties;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.CLEAR_MOB_EFFECTS.get();
    }

    private static CompoundNBT milkBucket() {
        ItemStack milkBucket = new ItemStack(Items.MILK_BUCKET);
        return VSUtils.saveStack(milkBucket, new CompoundNBT());
    }
}
