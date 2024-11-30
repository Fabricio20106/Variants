package melonystudios.variants.crafting.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import melonystudios.variants.crafting.VSRecipeTypes;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.armor.DyeableArmorItem;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class WoolArmorDyeingRecipe extends SpecialRecipe {
    public static Map<Item, Integer> DYE_COLORS_MAP = new ImmutableMap.Builder<Item, Integer>().put(VSItems.GLOW_BLACK_DYE.get(), 8454080).build();

    public WoolArmorDyeingRecipe(ResourceLocation location) {
        super(location);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        ItemStack emptyStack = ItemStack.EMPTY;
        List<ItemStack> dyeStacks = Lists.newArrayList();

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof DyeableArmorItem) {
                    if (!emptyStack.isEmpty()) return false;

                    emptyStack = stack;
                } else {
                    if (!(stack.getItem().is(Tags.Items.DYES))) return false;

                    dyeStacks.add(stack);
                }
            }
        }

        return !emptyStack.isEmpty() && !dyeStacks.isEmpty();
    }

    @Override
    @Nonnull
    public ItemStack assemble(CraftingInventory inventory) {
        ItemStack sweaterStack = ItemStack.EMPTY;
        List<Integer> dyeStacks = Lists.newArrayList();

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                Item item = stack.getItem();
                if (item instanceof DyeableArmorItem) {
                    if (!sweaterStack.isEmpty()) return ItemStack.EMPTY;

                    sweaterStack = stack.copy();
                } else {
                    if (!(item.is(Tags.Items.DYES))) return ItemStack.EMPTY;

                    if (item instanceof DyeItem) {
                        dyeStacks.add(((DyeItem) item).getDyeColor().getColorValue());
                    }

                    if (DYE_COLORS_MAP.containsKey(item)) {
                        dyeStacks.add(DYE_COLORS_MAP.get(item));
                    }
                }
            }
        }

        return !sweaterStack.isEmpty() && !dyeStacks.isEmpty() ? DyeableArmorItem.dyeArmor(sweaterStack, dyeStacks) : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int sizeX, int sizeY) {
        return sizeX * sizeY >= 2;
    }

    @Override
    @Nonnull
    public IRecipeSerializer<?> getSerializer() {
        return VSRecipeTypes.WOOL_ARMOR_DYEING.get();
    }
}
