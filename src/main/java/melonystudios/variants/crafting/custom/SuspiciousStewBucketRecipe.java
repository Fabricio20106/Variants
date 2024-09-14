package melonystudios.variants.crafting.custom;

import melonystudios.variants.crafting.VSRecipeTypes;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.food.BucketFoodItem;
import melonystudios.variants.util.NBTUtils;
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

import javax.annotation.Nonnull;

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

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
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
    @Nonnull
    public ItemStack assemble(CraftingInventory inventory) {
        ItemStack flowerStack = ItemStack.EMPTY;

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && stack.getItem().is(ItemTags.SMALL_FLOWERS)) {
                flowerStack = stack;
                break;
            }
        }

        ItemStack susStewBucket = new ItemStack(VSItems.SUSPICIOUS_STEW_BUCKET.get());
        if (flowerStack.getItem() instanceof BlockItem && ((BlockItem) flowerStack.getItem()).getBlock() instanceof FlowerBlock) {
            FlowerBlock smallFlower = (FlowerBlock) ((BlockItem) flowerStack.getItem()).getBlock();
            Effect stewEffect = smallFlower.getSuspiciousStewEffect();
            BucketFoodItem.writeEffectToBucket(susStewBucket, stewEffect, smallFlower.getEffectDuration());
            NBTUtils.addHidingTag("hide_behavior_tooltips", susStewBucket);
        }

        return susStewBucket;
    }

    @Override
    public boolean canCraftInDimensions(int sizeX, int sizeY) {
        return sizeX >= 2 && sizeY >= 2;
    }

    @Override
    @Nonnull
    public IRecipeSerializer<?> getSerializer() {
        return VSRecipeTypes.SUSPICIOUS_STEW_BUCKET.get();
    }
}
