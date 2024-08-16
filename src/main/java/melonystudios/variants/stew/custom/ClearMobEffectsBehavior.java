package melonystudios.variants.stew.custom;

import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class ClearMobEffectsBehavior extends StewBehavior {
    private final ItemStack curativeStack;

    public ClearMobEffectsBehavior(ItemStack curativeStack) {
        this.curativeStack = curativeStack;
    }

    public ClearMobEffectsBehavior() {
        this(new ItemStack(Items.MILK_BUCKET));
    }

    public ItemStack getCurativeItem() {
        return this.curativeStack;
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        if (!world.isClientSide) {
            CompoundNBT behaviorTag = stack.getOrCreateTagElement("behavior");
            CompoundNBT propertiesTag = behaviorTag.getCompound("properties");
            ItemStack curativeStack = VSUtils.loadStack(propertiesTag.getCompound("curative_item"));
            livEntity.curePotionEffects(curativeStack);
        }
    }

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, CompoundNBT propertiesTag) {
        ClearMobEffectsBehavior clearEffectsBehavior = new ClearMobEffectsBehavior(VSUtils.loadStack(NBTUtils.compoundOrDefault("curative_item", propertiesTag, milkBucket())));
        clearEffectsBehavior.executeBehavior(stewStack, world, livEntity);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT properties = new CompoundNBT();
        properties.put("curative_item", VSUtils.saveStack(this.curativeStack, new CompoundNBT()));
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.CLEAR_MOB_EFFECTS.get();
    }

    private static CompoundNBT milkBucket() {
        ItemStack milkBucket = new ItemStack(Items.MILK_BUCKET);
        return VSUtils.saveStack(milkBucket, new CompoundNBT());
    }
}
