package melonystudios.variants.item.custom.bottle;

import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.food.ConsumableItem;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.custom.DefaultConsumeBehavior;
import melonystudios.variants.util.VSUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.IRandomRange;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

import static melonystudios.variants.item.custom.bottle.GlassType.*;

public class StainedFullGlassBottleItem extends ConsumableItem {
    public static final List<GlassType> BOTTLES = Lists.newArrayList(WHITE, LIGHT_GRAY, GRAY, BLACK, BROWN, RED, ORANGE, YELLOW, LIME, GREEN, CYAN, LIGHT_BLUE, BLUE, PURPLE, MAGENTA, PINK, GLOW_BLACK, QUARTZ);

    public StainedFullGlassBottleItem(ConsumeBehavior behavior, Properties properties) {
        super(true, behavior, properties);
    }

    public StainedFullGlassBottleItem(Properties properties) {
        this(new DefaultConsumeBehavior(), properties);
    }

    @Override
    public boolean hasUseRemainder() {
        return true;
    }

    @Override
    public ItemStack getDefaultUseRemainder() {
        return new ItemStack(VSItems.WHITE_STAINED_GLASS_BOTTLE.get());
    }

    public static ItemStack setTextureIdentifier(ItemStack stack, IRandomRange range) {
        try {
            Object[] bottles = BOTTLES.toArray();
            GlassType type = (GlassType) bottles[range.getInt(random)];

            stack.getOrCreateTagElement("consumable").put("use_remainder", VSUtils.saveStack(type.bottle(), new CompoundNBT()));
            stack.getOrCreateTag().putInt("texture_id", type.textureID());
            return stack;
        } catch (ArrayIndexOutOfBoundsException exception) {
            return stack;
        }
    }

    @Override
    @Nonnull
    public ITextComponent getName(ItemStack stack) {
        if (this.getUseRemainder(stack).getItem() instanceof StainedEmptyGlassBottleItem) {
            StainedEmptyGlassBottleItem bottleItem = (StainedEmptyGlassBottleItem) this.getUseRemainder(stack).getItem();
            ResourceLocation colorName = bottleItem.getColorName(stack);
            return new TranslationTextComponent(this.getDescriptionId(stack), new TranslationTextComponent(getColorTranslation(colorName)));
        }
        return super.getName(stack);
    }

    protected static String getColorTranslation(ResourceLocation colorName) {
        return Util.makeDescriptionId("color", colorName);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab) && Variants.revaried().settings().populateStainedGlassBottlesInTabs) {
            for (GlassType collection : BOTTLES) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");
                consumableTag.put("use_remainder", VSUtils.saveStack(collection.bottle(), new CompoundNBT()));
                tag.putInt("texture_id", collection.textureID());
                list.add(stack);
            }
        } else {
            if (this.allowdedIn(tab)) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");
                consumableTag.put("use_remainder", VSUtils.saveStack(WHITE.bottle(), new CompoundNBT()));
                tag.putInt("texture_id", WHITE.textureID());
                list.add(stack);
            }
        }
    }
}
