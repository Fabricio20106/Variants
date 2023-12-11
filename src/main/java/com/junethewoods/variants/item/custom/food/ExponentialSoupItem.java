package com.junethewoods.variants.item.custom.food;

import com.junethewoods.variants.Variants;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public class ExponentialSoupItem extends Item {
    public ExponentialSoupItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        ItemStack superStack = super.finishUsingItem(stack, world, livEntity);
        boolean flag = livEntity instanceof PlayerEntity && ((PlayerEntity) livEntity).abilities.instabuild;

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
