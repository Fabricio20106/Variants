package melonystudios.variants.item.custom.food;

import com.google.common.collect.ImmutableMap;
import melonystudios.variants.Variants;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSRegistries;
import melonystudios.variants.util.tag.StewBehaviorTags;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.IRandomRange;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ExponentialStewItem extends Item {
    public static Map<String, Integer> BOWL_NAME_TO_ID = new ImmutableMap.Builder<String, Integer>().put("oak", 0).put("spruce", 1).put("birch", 2).put("jungle", 3).put("acacia", 4).put("dark_oak", 5).put("painting", 6).put("crimson", 7).put("warped", 8).put("ender", 9).build();
    private final StewBehavior stewBehavior;

    public ExponentialStewItem(StewBehavior behavior, Properties properties) {
        super(properties);
        this.stewBehavior = behavior;
    }

    public StewBehavior getBehavior() {
        return this.stewBehavior;
    }

    public boolean hasBehaviorInNBT(ItemStack stewStack) {
        return stewStack.getTag() != null && stewStack.getTag().contains("behavior", Constants.TagTypes.COMPOUND);
    }

    public TranslationTextComponent getBehaviorTranslation(ItemStack stewStack) {
        TranslationTextComponent fromConstructor = new TranslationTextComponent("stew_behavior." + this.stewBehavior.getBehaviorRegistry().getRegistryName().getNamespace() + "." + this.stewBehavior.getBehaviorRegistry().getRegistryName().getPath());
        if (hasBehaviorInNBT(stewStack)) {
            CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
            ResourceLocation behaviorID = ResourceLocation.tryParse(behaviorTag.getString("id"));
            assert behaviorID != null;
            if (!behaviorTag.contains("id", Constants.TagTypes.STRING)) return fromConstructor;
            return new TranslationTextComponent("stew_behavior." + behaviorID.getNamespace() + "." + behaviorID.getPath());
        } else {
            return fromConstructor;
        }
    }

    public static void writeEffectToStew(ItemStack stewStack, Effect effect, int duration) {
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        CompoundNBT propertiesTag = behaviorTag.getCompound("properties");
        ListNBT effectList = propertiesTag.getList("effects", Constants.TagTypes.LIST);
        CompoundNBT tag = new CompoundNBT();

        behaviorTag.putString("id", VSStewBehaviors.APPLY_MOB_EFFECTS.get().getRegistryName().toString());
        tag.putString("id", effect.getRegistryName().toString());
        tag.putInt("duration", duration);
        effectList.add(tag);
        propertiesTag.put("effects", effectList);
        behaviorTag.put("properties", propertiesTag);
    }

    public static void writeBowl(ItemStack stewStack, Item bowlItem) {
        CompoundNBT bowlTag = stewStack.getOrCreateTagElement("bowl");
        bowlTag.putString("name", bowlItem.getRegistryName().toString());
        for (String bowlWood : BOWL_NAME_TO_ID.keySet()) {
            if (bowlItem.getRegistryName().toString().contains(bowlWood)) bowlTag.putInt("texture_id", BOWL_NAME_TO_ID.get(bowlWood));
        }
    }

    public static void writeBowlWithTextureID(ItemStack stewStack, Item bowlItem, IRandomRange textureID) {
        CompoundNBT bowlTag = stewStack.getOrCreateTagElement("bowl");
        bowlTag.putString("name", bowlItem.getRegistryName().toString());
        bowlTag.putInt("texture_id", textureID.getInt(random));
    }

    public static void writeBehaviorToStew(ItemStack stewStack, StewBehavior behavior, CompoundNBT properties) {
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        behaviorTag.putString("id", behavior.getRegistryName().toString());
        behaviorTag.put("properties", properties);
    }

    public static boolean canRunBehavior(CompoundNBT behaviorTag, StewBehavior behavior) {
        return behaviorTag.contains("properties", Constants.TagTypes.COMPOUND) || !behavior.is(StewBehaviorTags.CANNOT_RUN_WITHOUT_NBT);
    }

    @Override
    @Nonnull
    public ItemStack finishUsingItem(ItemStack stewStack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stewStack, world, livEntity);
        boolean isPlayerInCreative = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;

        // Custom Stew Behavior
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
            StewBehavior behavior = VSRegistries.STEW_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
            if (behavior != null && canRunBehavior(behaviorTag, behavior)) behavior.executeFromStewNBT(stewStack, world, livEntity, behavior.getBehaviorProperties(stewStack));
        } else {
            if (canRunBehavior(behaviorTag, this.stewBehavior)) this.stewBehavior.executeFromStewNBT(stewStack, world, livEntity, this.stewBehavior.getBehaviorProperties(stewStack));
        }

        // For Suspicious Stew & "Apply Mob Effects" behavior
        CompoundNBT propertiesTag = behaviorTag.getCompound("properties");
        if (propertiesTag.contains("effects", Constants.TagTypes.LIST) && !behaviorTag.isEmpty()) {
            ListNBT effectList = propertiesTag.getList("effects", Constants.TagTypes.COMPOUND);

            for (int i = 0; i < effectList.size(); ++i) {
                int duration = 160; // Default of 8 seconds from Suspicious Stew.
                int amplifier = 0;
                boolean ambient = false;
                boolean showParticles = true;
                boolean showIcon = true;
                boolean noCounter = false;
                CompoundNBT effectTag = effectList.getCompound(i);
                if (effectTag.contains("duration", Constants.TagTypes.INTEGER)) duration = effectTag.getInt("duration");
                if (effectTag.contains("amplifier", Constants.TagTypes.INTEGER)) amplifier = effectTag.getInt("amplifier");
                if (effectTag.contains("ambient", Constants.TagTypes.BYTE)) ambient = effectTag.getBoolean("ambient");
                if (effectTag.contains("show_particles", Constants.TagTypes.BYTE)) showParticles = effectTag.getBoolean("show_particles");
                if (effectTag.contains("show_icon", Constants.TagTypes.BYTE)) showIcon = effectTag.getBoolean("show_icon");
                if (effectTag.contains("no_counter", Constants.TagTypes.BYTE)) noCounter = effectTag.getBoolean("no_counter");

                Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
                if (effect != null) {
                    EffectInstance instance = new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon);
                    if (world.isClientSide) instance.setNoCounter(noCounter);
                    livEntity.addEffect(instance);
                }
            }
        } else {
            if (this.stewBehavior.getEffects() != null) {
                for (EffectInstance instance : this.stewBehavior.getEffects()) livEntity.addEffect(instance);
            }
        }
        return isPlayerInCreative ? superStack : getBowlType(stewStack, livEntity);
    }

    public static ItemStack getBowlType(ItemStack stewStack, LivingEntity livEntity) {
        CompoundNBT bowlTypeTag = stewStack.getOrCreateTagElement("bowl");
        ResourceLocation containerItem = ResourceLocation.tryParse(bowlTypeTag.getString("name"));
        livEntity.eat(livEntity.level, stewStack);

        if (bowlTypeTag.contains("name", Constants.TagTypes.STRING) && ForgeRegistries.ITEMS.containsKey(containerItem)) {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(containerItem));
        }

        return new ItemStack(Items.BOWL);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if ((this.allowdedIn(tab) || tab == ItemGroup.TAB_SEARCH) && VSConfigs.COMMON_CONFIGS.populateExponentialBowlsInTabs.get()) {
            for (String bowls : BOWL_NAME_TO_ID.keySet()) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT bowlTypeTag = stack.getOrCreateTagElement("bowl");

                bowlTypeTag.putString("name", "variants:" + bowls + "_bowl");
                bowlTypeTag.putInt("texture_id", BOWL_NAME_TO_ID.get(bowls));
                if (stack.getTag() != null) tag.put("behavior", this.stewBehavior.writeBehaviorToNBT(stack));
                list.add(stack);
            }
        } else {
            super.fillItemCategory(tab, list);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        CompoundNBT bowlTypeTag = stack.getOrCreateTagElement("bowl");
        ResourceLocation containerItem = new ResourceLocation(bowlTypeTag.getString("name"));

        if (NBTUtils.shouldNotHideTooltip("hide_bowl_name", stack)) {
            if (bowlTypeTag.contains("name", Constants.TagTypes.STRING) && ForgeRegistries.ITEMS.containsKey(containerItem)) {
                ITextComponent bowlName = ForgeRegistries.ITEMS.getValue(containerItem).getName(ForgeRegistries.ITEMS.getValue(containerItem).getDefaultInstance());
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.bowl", bowlName).withStyle(TextFormatting.GRAY));
            } else {
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.bowl", ForgeRegistries.ITEMS.getValue(Items.BOWL.getRegistryName()).getName(ForgeRegistries.ITEMS.getValue(Items.BOWL.getRegistryName())
                        .getDefaultInstance())).withStyle(TextFormatting.GRAY));
            }
        }
        if (NBTUtils.shouldNotHideTooltip("hide_stew_behavior", stack)) {
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.behavior",
                    getBehaviorTranslation(stack)).withStyle(TextFormatting.GRAY));
        }
        if (NBTUtils.shouldNotHideTooltip("hide_behavior_tooltips", stack)) {
            CompoundNBT behaviorTag = stack.getOrCreateTagElement("behavior");
            if (behaviorTag.contains("id", Constants.TagTypes.STRING)) {
                StewBehavior behavior = VSRegistries.STEW_BEHAVIOR.getValue(ResourceLocation.tryParse(behaviorTag.getString("id")));
                if (behavior != null) tooltip.addAll(behavior.addToStewTooltip(stack, world, flag));
            } else {
                tooltip.addAll(this.stewBehavior.addToStewTooltip(stack, world, flag));
            }
        }
    }
}
