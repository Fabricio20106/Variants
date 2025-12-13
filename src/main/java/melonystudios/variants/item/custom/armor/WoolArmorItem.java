package melonystudios.variants.item.custom.armor;

import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.item.custom.armor.color.WoolArmorColor;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.tab.VSSweaterTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class WoolArmorItem extends ArmorItem implements DyeableArmorItem {
    private final String armorName;

    public WoolArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
        this.armorName = material.getName();
    }

    @Override
    public int getDefaultColor() {
        return 16777215;
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (stack.getTag() != null && stack.getTag().getInt("armor_design") > 0) {
            return Variants.variants("textures/models/armor/" + this.getArmorLocation() + "_layer_" + (slot == EquipmentSlotType.LEGS ? 2 : 1) + "_" + stack.getTag().getInt("armor_design") + ".png").toString();
        }
        return super.getArmorTexture(stack, entity, slot, type);
    }

    /// Essentially copied from {@link net.minecraft.client.renderer.entity.layers.BipedArmorLayer#getArmorResource BipedArmorLayer.getArmorResource()} (Forge version).
    public String getArmorLocation() {
        int index = this.armorName.indexOf(':');
        if (index != -1) return this.armorName.substring(index + 1);
        return "wool";
    }

    @Override
    @Nonnull
    public ITextComponent getName(ItemStack stack) {
        if (stack.getTag() != null && stack.getTag().contains("color_name", Constants.TagTypes.STRING)) {
            return new TranslationTextComponent(this.getDescriptionId() + ".colored", new TranslationTextComponent(stack.getTag().getString("color_name")));
        } else {
            return new TranslationTextComponent(this.getDescriptionId());
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (stack.getTag() != null && stack.getTag().getInt("armor_design") > 0 && NBTUtils.shouldNotHideTooltip("hide_armor_design", stack)) {
            String translation = "tooltip." + Variants.MOD_ID + ".wool_armor_design";
            tooltip.add(new TranslationTextComponent(translation, new TranslationTextComponent(translation + "." + stack.getTag().getInt("armor_design"))).withStyle(TextFormatting.GRAY));
        }
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        // Wool armor colors
        if (this.allowdedIn(tab)) {
            list.add(new ItemStack(this));

            if (Variants.revaried().settings().populateWoolArmorColorsInTabs) {
                List<ResourceLocation> sortedColors = Lists.newArrayList();
                sortedColors.addAll(WoolArmorColor.DATA_DRIVEN_COLORS.keySet());
                sortedColors.sort(null);
                for (ResourceLocation location : sortedColors) {
                    WoolArmorColor armorColor = WoolArmorColor.DATA_DRIVEN_COLORS.get(location);
                    if (!armorColor.getArmorDesign().isPresent()) {
                        ItemStack stack = new ItemStack(this);
                        CompoundNBT displayTag = stack.getOrCreateTagElement("display");
                        CompoundNBT tag = stack.getOrCreateTag();

                        displayTag.putInt("color", armorColor.getColor());
                        tag.putString("color_name", armorColor.getColorName());
                        list.add(stack);
                    }
                }
                sortedColors.clear();
            }
        }

        // Wool armor designs
        if (this.allowdedIn(tab) && Variants.revaried().settings().populateWoolArmorDesignsInTabs) {
            List<ResourceLocation> sortedColors = Lists.newArrayList();
            sortedColors.addAll(WoolArmorColor.DATA_DRIVEN_COLORS.keySet());
            sortedColors.sort(null);
            for (ResourceLocation location : sortedColors) {
                WoolArmorColor armorColor = WoolArmorColor.DATA_DRIVEN_COLORS.get(location);
                if (armorColor.getArmorDesign().isPresent()) {
                    ItemStack stack = new ItemStack(this);
                    CompoundNBT tag = stack.getOrCreateTag();

                    tag.putInt("armor_design", armorColor.getArmorDesign().getAsInt());
                    tag.putString("color_name", armorColor.getOrCreateDescriptionID());
                    list.add(stack);
                }
            }
            sortedColors.clear();
        }

        // Infinity wool sweaters (0 -> 16777215)
        if (tab == VSSweaterTab.TAB && Variants.revaried().settings().infinitySweatersEnabled) {
            for (int color = 0; color < Variants.revaried().settings().infinitySweatersTabLength; color += Variants.revaried().settings().infinitySweatersTabSpacing)  {
                ItemStack stack = new ItemStack(this);
                CompoundNBT displayTag = stack.getOrCreateTagElement("display");
                CompoundNBT tag = stack.getOrCreateTag();

                displayTag.putInt("color", color);
                tag.putString("color_name", new TranslationTextComponent(this.getDescriptionId() + ".infinity", color).getString());
                list.add(stack);
            }
        }
    }
}
