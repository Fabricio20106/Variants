package melonystudios.variants.data.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.util.tag.VSEnchantmentTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VSEnchantmentTagsProvider extends ForgeRegistryTagsProvider<Enchantment> {
    public VSEnchantmentTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, ForgeRegistries.ENCHANTMENTS, Variants.MOD_ID, fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Enchantment Tags";
    }

    @Override
    protected void addTags() {
        this.tag(VSEnchantmentTags.APPLICABLE_TO_SHEARS).add(Enchantments.BLOCK_EFFICIENCY, Enchantments.BLOCK_FORTUNE, Enchantments.UNBREAKING, Enchantments.MENDING);
        this.tag(VSEnchantmentTags.APPLICABLE_TO_FLINT_AND_STEEL).add(Enchantments.UNBREAKING, Enchantments.MENDING);
        this.tag(VSEnchantmentTags.APPLICABLE_TO_SHIELDS).add(Enchantments.UNBREAKING, Enchantments.MENDING);
    }
}
