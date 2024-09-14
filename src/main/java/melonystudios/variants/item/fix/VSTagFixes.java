package melonystudios.variants.item.fix;

import melonystudios.variants.Variants;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.food.ExponentialStewItem;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VSRegistries;
import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class VSTagFixes {
    public static final DeferredRegister<TagFix> FIXES = DeferredRegister.create(VSRegistries.TAG_FIX, Variants.MOD_ID);

    public static final RegistryObject<TagFix> CORRECT_ENDER_BOWL = FIXES.register("correct_ender_bowl", () -> new TagFix(() -> Ingredient.of(VSItemTags.EXPONENTIAL_STEWS)) {
        @Override
        public boolean applyFix(ItemStack stack, World world, LivingEntity livEntity) {
            if (stack.getItem() instanceof ExponentialStewItem) {
                CompoundNBT tag = stack.getTag();
                if (tag != null && tag.contains("bowl", Constants.TagTypes.COMPOUND)) {
                    CompoundNBT bowlTag = tag.getCompound("bowl");
                    if (bowlTag.contains("name", Constants.TagTypes.STRING) && bowlTag.getString("name").equals("variants:ender_bowl")) {
                        bowlTag.remove("name");
                        bowlTag.putString("name", VSItems.ENDERWOOD_BOWL.get().getRegistryName().toString());
                        return true;
                    }
                }
            }
            return false;
        }
    });
}
