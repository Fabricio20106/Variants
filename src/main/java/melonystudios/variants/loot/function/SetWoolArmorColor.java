package melonystudios.variants.loot.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import melonystudios.variants.item.custom.armor.DyeableArmorItem;
import melonystudios.variants.loot.VSLootFunctions;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;

import javax.annotation.Nonnull;

public class SetWoolArmorColor extends LootFunction {
    private final boolean pickRandomColor;
    private final int color;
    private final String colorName;

    public SetWoolArmorColor(ILootCondition[] conditions, boolean pickRandomColor, int color, String colorName) {
        super(conditions);
        this.pickRandomColor = pickRandomColor;
        this.color = color;
        this.colorName = colorName;
    }

    @Override
    @Nonnull
    public LootFunctionType getType() {
        return VSLootFunctions.SET_WOOL_ARMOR_COLOR;
    }

    @Override
    @Nonnull
    protected ItemStack run(ItemStack stack, LootContext context) {
        if (stack.getItem() instanceof DyeableArmorItem) {
            if (this.pickRandomColor) return DyeableArmorItem.pickRandomColor(stack, context.getRandom());
            else return DyeableArmorItem.setColorAndName(stack, this.color, this.colorName);
        }
        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<SetWoolArmorColor> {
        @Override
        public void serialize(JsonObject object, SetWoolArmorColor function, JsonSerializationContext context) {
            super.serialize(object, function, context);
            if (function.pickRandomColor) {
                object.addProperty("pick_random_color", true);
            } else {
                object.addProperty("color", function.color);
                object.addProperty("color_name", function.colorName);
            }
        }

        @Override
        @Nonnull
        public SetWoolArmorColor deserialize(JsonObject object, JsonDeserializationContext context, ILootCondition[] conditions) {
            if (object.has("pick_random_color")) {
                return new SetWoolArmorColor(conditions, true, 0, "color.minecraft.black");
            } else {
                int color = JSONUtils.getAsInt(object, "color");
                String colorName = JSONUtils.getAsString(object, "color_name");
                return new SetWoolArmorColor(conditions, false, color, colorName);
            }
        }
    }
}
