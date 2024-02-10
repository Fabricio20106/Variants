package com.junethewoods.variants.crafting.custom;

import com.junethewoods.variants.crafting.VSRecipeTypes;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.custom.food.BucketFoodItem;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.potion.Effect;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SuspiciousStewBucketRecipe extends SpecialRecipe {
    public SuspiciousStewBucketRecipe(ResourceLocation name) {
        super(name);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;

        for(int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == Blocks.BROWN_MUSHROOM.asItem() && !flag2) {
                    flag2 = true;
                } else if (stack.getItem() == Blocks.RED_MUSHROOM.asItem() && !flag1) {
                    flag1 = true;
                } else if (stack.getItem().is(ItemTags.SMALL_FLOWERS) && !flag) {
                    flag = true;
                } else {
                    if (stack.getItem() != Items.BUCKET || flag3) {
                        return false;
                    }

                    flag3 = true;
                }
            }
        }

        return flag && flag2 && flag1 && flag3;
    }

    @Override
    public ItemStack assemble(CraftingInventory inventory) {
        ItemStack emptyStack = ItemStack.EMPTY;

        for(int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && stack.getItem().is(ItemTags.SMALL_FLOWERS)) {
                emptyStack = stack;
                break;
            }
        }

        ItemStack susStewBucket = new ItemStack(VSItems.SUSPICIOUS_STEW_BUCKET.get(), 1);
        if (emptyStack.getItem() instanceof BlockItem && ((BlockItem) emptyStack.getItem()).getBlock() instanceof FlowerBlock) {
            FlowerBlock smallFlower = (FlowerBlock) ((BlockItem) emptyStack.getItem()).getBlock();
            Effect stewEffect = smallFlower.getSuspiciousStewEffect();
            BucketFoodItem.writeEffectToBucket(susStewBucket, stewEffect, smallFlower.getEffectDuration());
        }

        return susStewBucket;
    }

    @Override
    public boolean canCraftInDimensions(int sizeX, int sizeY) {
        return sizeX >= 2 && sizeY >= 2;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return VSRecipeTypes.SUSPICIOUS_STEW_BUCKET.get();
    }
}
