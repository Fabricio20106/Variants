package melonystudios.variants.item.custom.bottle;

import com.google.common.collect.Lists;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.food.TagConfigurableFoodItem;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.custom.DefaultStewBehavior;
import melonystudios.variants.util.VSUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

import static melonystudios.variants.item.custom.bottle.GlassType.*;

public class StainedFullGlassBottleItem extends TagConfigurableFoodItem {
    public static final List<GlassType> BOTTLES = Lists.newArrayList(WHITE, LIGHT_GRAY, GRAY, BLACK, BROWN, RED, ORANGE, YELLOW, LIME, GREEN, CYAN, LIGHT_BLUE, BLUE, PURPLE, MAGENTA, PINK, GLOW_BLACK, QUARTZ);
    private final StewBehavior behavior;

    public StainedFullGlassBottleItem(StewBehavior behavior, Properties properties) {
        super(true, behavior, properties);
        this.behavior = behavior;
    }

    public StainedFullGlassBottleItem(Properties properties) {
        this(new DefaultStewBehavior(), properties);
    }

    @Override
    public boolean hasUseRemainder() {
        return true;
    }

    @Override
    public ItemStack getDefaultUseRemainder() {
        return new ItemStack(VSItems.WHITE_STAINED_GLASS_BOTTLE.get());
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
        if (this.allowdedIn(tab) && VSConfigs.COMMON_CONFIGS.populateStainedGlassBottlesInTabs.get()) {
            for (GlassType collection : BOTTLES) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT consumableTag = stack.getOrCreateTagElement("consumable");
                consumableTag.put("use_remainder", VSUtils.saveStack(collection.getBottle(), new CompoundNBT()));
                tag.putInt("texture_id", collection.getTextureIdentifier());
                list.add(stack);
            }
        } else {
            super.fillItemCategory(tab, list);
        }
    }
}
