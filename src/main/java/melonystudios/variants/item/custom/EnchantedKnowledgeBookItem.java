package melonystudios.variants.item.custom;

import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VSStats;
import melonystudios.variants.util.tab.VSTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class EnchantedKnowledgeBookItem extends EnchantedBookItem {
    public EnchantedKnowledgeBookItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        CompoundNBT tag = handStack.getTag();
        if (!player.abilities.instabuild) player.setItemInHand(hand, ItemStack.EMPTY);

        if (tag != null && tag.contains("recipes", Constants.TagTypes.LIST)) {
            if (!world.isClientSide) {
                ListNBT recipeList = tag.getList("recipes", Constants.TagTypes.STRING);
                List<IRecipe<?>> iRecipesList = Lists.newArrayList();
                assert world.getServer() != null;
                RecipeManager recipeManager = world.getServer().getRecipeManager();

                for (int listString = 0; listString < recipeList.size(); ++listString) {
                    String recipe = recipeList.getString(listString);
                    Optional<? extends IRecipe<?>> optionalRecipe = recipeManager.byKey(new ResourceLocation(recipe));
                    if (!optionalRecipe.isPresent()) {
                        Variants.LOGGER.error(new TranslationTextComponent("error.variants.enchanted_knowledge_book.invalid_recipe", recipe).getString(), recipe);
                        return ActionResult.fail(handStack);
                    }

                    iRecipesList.add(optionalRecipe.get());
                }

                player.awardRecipes(iRecipesList);
                player.awardStat(Stats.ITEM_USED.get(this));
                player.awardStat(VSStats.KNOWLEDGE_BOOKS_USED);
            }

            return ActionResult.sidedSuccess(handStack, world.isClientSide());
        } else {
            Variants.LOGGER.error(new TranslationTextComponent("error.variants.enchanted_knowledge_book.invalid_tag", tag).getString());
            return ActionResult.fail(handStack);
        }
    }

    public static ItemStack createForEnchantment(EnchantmentData enchantmentData) {
        ItemStack bookStack = new ItemStack(VSItems.ENCHANTED_KNOWLEDGE_BOOK.get());
        addEnchantment(bookStack, enchantmentData);
        return bookStack;
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (tab == ItemGroup.TAB_SEARCH) {
            for (Enchantment enchantments : ForgeRegistries.ENCHANTMENTS) {
                if (enchantments.category != null) {
                    for (int i = enchantments.getMinLevel(); i <= enchantments.getMaxLevel(); ++i) {
                        list.add(createForEnchantment(new EnchantmentData(enchantments, i)));
                    }
                }
            }
        } else if (tab == VSTab.TAB) {
            for (Enchantment enchantments : ForgeRegistries.ENCHANTMENTS) {
                list.add(createForEnchantment(new EnchantmentData(enchantments, enchantments.getMaxLevel())));
            }
        }
    }
}
