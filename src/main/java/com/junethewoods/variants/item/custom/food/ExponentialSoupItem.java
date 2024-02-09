package com.junethewoods.variants.item.custom.food;

import com.google.common.collect.ImmutableMap;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.item.custom.stew.IStewBehavior;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ExponentialSoupItem extends Item {
    public static Map<String, Integer> BOWL_NAME_TO_ID = new ImmutableMap.Builder<String, Integer>().put("oak", 0).put("spruce", 1).put("birch", 2).put("jungle", 3).put("acacia", 4).put("dark_oak", 5).put("painting", 6).put("crimson", 7).put("warped", 8).put("ender", 9).build();
    private final IStewBehavior stewBehavior;
    private final String compatID;

    public ExponentialSoupItem(IStewBehavior behavior, Properties properties) {
        this(null, behavior, properties);
    }

    public ExponentialSoupItem(String compatID, IStewBehavior behavior, Properties properties) {
        super(properties);
        this.compatID = compatID;
        this.stewBehavior = behavior;
    }

    // Will be used later in the custom recipes.
    public static void saveEffectToBowl(ItemStack stack, Effect effect, int duration) {
        CompoundNBT tag = stack.getOrCreateTag();
        ListNBT effectList = tag.getList("effects", 9);
        CompoundNBT tag1 = new CompoundNBT();

        tag1.putByte("id", (byte) Effect.getId(effect));
        tag1.putInt("duration", duration);
        effectList.add(tag1);
        tag.put("effects", effectList);
    }

    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stack, world, livEntity);
        boolean flag = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;

        // For Suspicious Stew
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("effects", 9)) {
            ListNBT effectList = tag.getList("effects", 10);

            for(int i = 0; i < effectList.size(); ++i) {
                int duration = 160; // Default of 8 seconds.
                CompoundNBT tag1 = effectList.getCompound(i);
                if (tag1.contains("duration", 3)) {
                    duration = tag1.getInt("duration");
                }

                Effect effect = Effect.byId(tag1.getByte("id"));
                if (effect != null) {
                    livEntity.addEffect(new EffectInstance(effect, duration));
                }
            }
        }

        // Custom Stew Behavior
        this.stewBehavior.executeBehavior(stack, world, livEntity);
        if (this.stewBehavior.getEffects() != null) livEntity.addEffect(this.stewBehavior.getEffects());

        return flag ? superStack : getBowlType(stack);
    }

    public static ItemStack getBowlType(ItemStack stack) {
        CompoundNBT bowlTypeTag = stack.getOrCreateTagElement("bowl_type");
        ResourceLocation containerItem = new ResourceLocation(bowlTypeTag.getString("bowl_name"));

        if (bowlTypeTag.contains("bowl_name") && ForgeRegistries.ITEMS.containsKey(containerItem)) {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(containerItem));
        }

        return new ItemStack(Items.BOWL);
    }

    @Override
    public void fillItemCategory(ItemGroup itemTab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(itemTab) && VSConfigs.COMMON_CONFIGS.populateExponentialBowlsInTabs.get()) {
            for (String bowls : BOWL_NAME_TO_ID.keySet()) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT bowlTypeTag = stack.getOrCreateTagElement("bowl_type");

                bowlTypeTag.putString("bowl_name", "variants:" + bowls + "_bowl");
                bowlTypeTag.putInt("bowl_id", BOWL_NAME_TO_ID.get(bowls));
                list.add(stack);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        CompoundNBT bowlTypeTag = stack.getOrCreateTagElement("bowl_type");
        ResourceLocation containerItem = new ResourceLocation(bowlTypeTag.getString("bowl_name"));

        if (bowlTypeTag.contains("bowl_name") && ForgeRegistries.ITEMS.containsKey(containerItem)) {
            ITextComponent bowlName = ForgeRegistries.ITEMS.getValue(containerItem).getName(ForgeRegistries.ITEMS.getValue(containerItem).getDefaultInstance());
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.bowl", bowlName).withStyle(TextFormatting.GRAY));
        }
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
