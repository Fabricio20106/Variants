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

public class SetWoolArmorDesign extends LootFunction {
    private final int armorDesign;

    public SetWoolArmorDesign(ILootCondition[] conditions, int armorDesign) {
        super(conditions);
        this.armorDesign = armorDesign;
    }

    @Override
    @Nonnull
    public LootFunctionType getType() {
        return VSLootFunctions.SET_WOOL_ARMOR_DESIGN;
    }

    @Override
    @Nonnull
    protected ItemStack run(ItemStack stack, LootContext context) {
        if (stack.getItem() instanceof DyeableArmorItem) return DyeableArmorItem.setArmorDesign(stack, this.armorDesign);
        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<SetWoolArmorDesign> {
        @Override
        public void serialize(JsonObject object, SetWoolArmorDesign function, JsonSerializationContext context) {
            super.serialize(object, function, context);
            object.addProperty("armor_design", function.armorDesign);
        }

        @Override
        @Nonnull
        public SetWoolArmorDesign deserialize(JsonObject object, JsonDeserializationContext context, ILootCondition[] conditions) {
            int armorDesign = JSONUtils.getAsInt(object, "armor_design");
            return new SetWoolArmorDesign(conditions, armorDesign);
        }
    }
}
