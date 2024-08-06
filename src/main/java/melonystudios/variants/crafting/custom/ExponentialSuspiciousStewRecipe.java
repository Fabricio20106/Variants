package melonystudios.variants.crafting.custom;

import melonystudios.variants.crafting.VSRecipeTypes;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ExponentialSuspiciousStewRecipe extends SpecialRecipe {
    private ItemStack bowl = new ItemStack(VSItems.OAK_BOWL.get());

    public ExponentialSuspiciousStewRecipe(ResourceLocation name) {
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
                } else if (stack.getItem().is(VSItemTags.WOODEN_BOWLS) || !flag3) {
                    if (stack.getItem().is(VSItemTags.WOODEN_BOWLS)) {
                        this.bowl = stack;
                    }
                    flag3 = true;
                }
            }
        }

        return flag && flag1 && flag2 && flag3;
    }

    @Override
    @Nonnull
    public ItemStack assemble(CraftingInventory inventory) {
        ItemStack emptyStack = ItemStack.EMPTY;

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && stack.getItem().is(ItemTags.SMALL_FLOWERS)) {
                emptyStack = stack;
                break;
            }
        }

        ItemStack expoSusStew = new ItemStack(VSItems.EXPONENTIAL_SUSPICIOUS_STEW.get(), 1);
        if (emptyStack.getItem() instanceof BlockItem && ((BlockItem) emptyStack.getItem()).getBlock() instanceof FlowerBlock) {
            FlowerBlock smallFlower = (FlowerBlock) ((BlockItem) emptyStack.getItem()).getBlock();
            ExponentialStewItem.writeEffectToStew(expoSusStew, smallFlower.getSuspiciousStewEffect(), smallFlower.getEffectDuration());
            ExponentialStewItem.writeBowl(expoSusStew, this.bowl.getItem());
        }

        return expoSusStew;
    }

    @Override
    public boolean canCraftInDimensions(int sizeX, int sizeY) {
        return sizeX >= 2 && sizeY >= 2;
    }

    @Override
    @Nonnull
    public IRecipeSerializer<?> getSerializer() {
        return VSRecipeTypes.EXPONENTIAL_SUSPICIOUS_STEW.get();
    }
}
