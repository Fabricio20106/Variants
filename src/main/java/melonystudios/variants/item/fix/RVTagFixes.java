package melonystudios.variants.item.fix;

import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.RVRegistries;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

public class RVTagFixes {
    public static final DeferredRegister<TagFix> FIXES = DeferredRegister.create(RVRegistries.TAG_FIX, Variants.MOD_ID);

    public static final RegistryObject<TagFix> CORRECT_ENDER_BOWL = FIXES.register("correct_ender_bowl", () -> new TagFix() {
        @Override
        public void applyFix(CompoundNBT tag) {
            if (tag.contains("bowl", Constants.TagTypes.COMPOUND)) {
                CompoundNBT bowl = tag.getCompound("bowl");
                if (bowl.contains("name", Constants.TagTypes.STRING) && bowl.getString("name").equals(Variants.variants("ender_bowl").toString())) {
                    bowl.remove("name");
                    bowl.putString("name", VSItems.ENDERWOOD_BOWL.get().getRegistryName().toString());
                }
            }
        }

        @Override
        public List<Item> applicableItems() {
            List<Item> items = Lists.newArrayList();
            items.add(VSItems.EXPONENTIAL_MUSHROOM_STEW.get());
            items.add(VSItems.EXPONENTIAL_MUSHROOM_STEW.get());
            items.add(VSItems.EXPONENTIAL_BEETROOT_SOUP.get());
            items.add(VSItems.EXPONENTIAL_RABBIT_STEW.get());
            items.add(VSItems.EXPONENTIAL_SUSPICIOUS_STEW.get());
            items.add(VSItems.EXPONENTIAL_FUNGI_STEW.get());
            items.add(VSItems.EXPONENTIAL_END_FUNGI_STEW.get());
            items.add(VSItems.EXPONENTIAL_ALJAN_FUNGI_STEW.get());
            items.add(VSItems.EXPONENTIAL_WATER_BOWL.get());
            items.add(VSItems.EXPONENTIAL_MILK_BOWL.get());
            items.add(VSItems.EXPONENTIAL_LAVA_BOWL.get());
            items.add(VSItems.EXPONENTIAL_SOUL_LAVA_BOWL.get());
            items.add(VSItems.EXPONENTIAL_POWDER_SNOW_BOWL.get());
            return items;
        }
    });
}
