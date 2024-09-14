package melonystudios.variants.item.custom.armor;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.item.custom.armor.color.WoolArmorColor;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.tab.VSSweaterTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

// TODO: Make this class more compatibility-friendly.
public class WoolArmorItem extends ArmorItem implements DyeableArmorItem {
    private final String armorName;
    // If you add an item to this list through the method used in Back Math, it will brick the order of the colors in the creative menu! Which is the whole point I wrote this thing.

    // Upsides to this method:
    // - You may register new color easily, and add them straight to the creative tab.
    // - It's the first time I use "for" loops ever for something that I came up with.
    // Downsides to this method:
    // - When you add a new color, it breaks the order of the colors, and it becomes a mess in the menu.
    public static Map<String, Integer> COLOR_NAME_TO_CODE = new ImmutableMap.Builder<String, Integer>()
            .put("color.minecraft.white", 16383998).put("color.f10elements.inno", 15457757).put("color.minecraft.orange", 16351261).put("color.minecraft.magenta", 13061821)
            .put("color.backmath.aljan_light_blue", 13429739).put("color.minecraft.light_blue", 3847130).put("color.variants.glow_black", 8454080).put("color.minecraft.yellow", 16701501)
            .put("color.minecraft.lime", 8439583).put("color.minecraft.pink", 15961002).put("color.minecraft.gray", 4673362).put("color.minecraft.light_gray", 10329495)
            .put("color.minecraft.cyan", 1481884).put("color.minecraft.purple", 8991416).put("color.backmath.insomnian", 4418465).put("color.minecraft.blue", 3949738)
            .put("color.minecraft.brown", 8606770).put("color.backmath.poison_brown", 8921856).put("color.minecraft.green", 6192150).put("color.minecraft.red", 11546150)
            .put("color.backmath.red_yellow", 15731456).put("color.minecraft.black", 1908001).build();

    public WoolArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
        this.armorName = material.getName();
    }

    @Override
    public int getDefaultColor() {
        return 16777215;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (stack.getTag() != null && stack.getTag().getInt("armor_design") > 0) {
            return Variants.variants("textures/models/armor/" + getArmorLocation() + "_layer_" + (slot == EquipmentSlotType.LEGS ? 2 : 1) + "_" + stack.getTag().getInt("armor_design") + ".png").toString();
        }
        return super.getArmorTexture(stack, entity, slot, type);
    }

    // Essentially copied from BipedArmorLayer#getArmorResource() (Forge version).
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
        if (this.allowdedIn(tab)) {
            list.add(new ItemStack(this));

            if (VSConfigs.COMMON_CONFIGS.populateWoolArmorColorInTabs.get()) {
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
        if (this.allowdedIn(tab) && VSConfigs.COMMON_CONFIGS.populateWoolArmorDesignsInTabs.get()) {
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
        if (tab == VSSweaterTab.TAB && VSConfigs.COMMON_CONFIGS.enableInfinitySweatersTab.get()) {
            for (int i = 0; i < VSConfigs.COMMON_CONFIGS.infinitySweatersTabLength.get();  i = i + VSConfigs.COMMON_CONFIGS.infinitySweatersTabSpacing.get())  {
                ItemStack stack = new ItemStack(this);
                CompoundNBT displayTag = stack.getOrCreateTagElement("display");
                CompoundNBT tag = stack.getOrCreateTag();

                displayTag.putInt("color", i);
                tag.putString("color_name", "#" + i);
                list.add(stack);
            }
        }
    }

    public static ItemStack pickRandomColor(ItemStack stack) {
        CompoundNBT displayTag = stack.getOrCreateTagElement("display");
        CompoundNBT tag = stack.getOrCreateTag();

        WoolArmorColor armorColor = rollArmorColor();
        while (armorColor.getArmorDesign().isPresent()) armorColor = rollArmorColor();
        displayTag.putInt("color", armorColor.getColor());
        tag.putString("color_name", armorColor.getColorName());

        return stack;
    }

    private static WoolArmorColor rollArmorColor() {
        Object[] armorColors = WoolArmorColor.DATA_DRIVEN_COLORS.values().toArray();
        int randomValue = random.nextInt(armorColors.length);
        return (WoolArmorColor) armorColors[randomValue];
    }

    public static ItemStack setColorAndName(ItemStack stack, int color, String colorName) {
        CompoundNBT tag = stack.getOrCreateTag();
        CompoundNBT displayTag = stack.getOrCreateTagElement("display");
        displayTag.putInt("color", color);
        tag.putString("color_name", colorName);
        return stack;
    }

    public static ItemStack setArmorDesign(ItemStack stack, int armorDesign) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putInt("armor_design", armorDesign);
        tag.putString("color_name", "armor_design." + Variants.MOD_ID + "." + armorDesign);
        return stack;
    }
}
