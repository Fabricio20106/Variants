package melonystudios.variants.effect;

import melonystudios.variants.Variants;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.VSModdedItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Variants.MOD_ID);

    public static final RegistryObject<Potion> LEVITATION = POTIONS.register("levitation", () -> new Potion(new VSEffectInstance(() -> Effects.LEVITATION, 600)));
    public static final RegistryObject<Potion> LONG_LEVITATION = POTIONS.register("long_levitation", () -> new Potion("levitation", new VSEffectInstance(() -> Effects.LEVITATION, 1800)));
    public static final RegistryObject<Potion> STRONG_LEVITATION = POTIONS.register("strong_levitation", () -> new Potion("levitation", new VSEffectInstance(() -> Effects.LEVITATION, 300, 1)));

    public static void addBrewingRecipes() {
        // Potion
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.of(Items.SHULKER_SHELL), PotionUtils.setPotion(new ItemStack(Items.POTION), LEVITATION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), LEVITATION.get())), Ingredient.of(Items.REDSTONE), PotionUtils.setPotion(new ItemStack(Items.POTION), LONG_LEVITATION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), LEVITATION.get())), Ingredient.of(Items.GLOWSTONE_DUST), PotionUtils.setPotion(new ItemStack(Items.POTION), STRONG_LEVITATION.get())));

        // Splash Potion
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), LEVITATION.get())), Ingredient.of(Items.GUNPOWDER), PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), LEVITATION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), LONG_LEVITATION.get())), Ingredient.of(Items.GUNPOWDER), PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), LONG_LEVITATION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), STRONG_LEVITATION.get())), Ingredient.of(Items.GUNPOWDER), PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), STRONG_LEVITATION.get())));

        // Lingering Potion
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), LEVITATION.get())), Ingredient.of(Items.DRAGON_BREATH), PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), LEVITATION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), LONG_LEVITATION.get())), Ingredient.of(Items.DRAGON_BREATH), PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), LONG_LEVITATION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), STRONG_LEVITATION.get())), Ingredient.of(Items.DRAGON_BREATH), PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), STRONG_LEVITATION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), LEVITATION.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), LEVITATION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), LONG_LEVITATION.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), LONG_LEVITATION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), STRONG_LEVITATION.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), STRONG_LEVITATION.get())));

        // Other Bottles
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(Items.EXPERIENCE_BOTTLE)), Ingredient.of(Items.GUNPOWDER), new ItemStack(VSItems.SPLASH_EXPERIENCE_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_EXPERIENCE_BOTTLE.get())), Ingredient.of(Items.DRAGON_BREATH), new ItemStack(VSItems.LINGERING_EXPERIENCE_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_EXPERIENCE_BOTTLE.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), new ItemStack(VSItems.LINGERING_EXPERIENCE_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(Items.DRAGON_BREATH)), Ingredient.of(Items.GUNPOWDER), new ItemStack(VSItems.SPLASH_DRAGON_BREATH.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_DRAGON_BREATH.get())), Ingredient.of(Items.DRAGON_BREATH), new ItemStack(VSItems.LINGERING_DRAGON_BREATH.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_DRAGON_BREATH.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), new ItemStack(VSItems.LINGERING_DRAGON_BREATH.get())));
        if (VSModdedItems.SOPHIE_POTION != null) BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSModdedItems.SOPHIE_POTION)), Ingredient.of(Items.GUNPOWDER), new ItemStack(VSItems.SPLASH_SOPHIE_POTION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_SOPHIE_POTION.get())), Ingredient.of(Items.DRAGON_BREATH), new ItemStack(VSItems.LINGERING_SOPHIE_POTION.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_SOPHIE_POTION.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), new ItemStack(VSItems.LINGERING_SOPHIE_POTION.get())));

        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(Items.HONEY_BOTTLE)), Ingredient.of(Items.GUNPOWDER), new ItemStack(VSItems.SPLASH_HONEY_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_HONEY_BOTTLE.get())), Ingredient.of(Items.DRAGON_BREATH), new ItemStack(VSItems.LINGERING_HONEY_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_HONEY_BOTTLE.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), new ItemStack(VSItems.LINGERING_HONEY_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.LAVA_BOTTLE.get())), Ingredient.of(Items.GUNPOWDER), new ItemStack(VSItems.SPLASH_LAVA_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_LAVA_BOTTLE.get())), Ingredient.of(Items.DRAGON_BREATH), new ItemStack(VSItems.LINGERING_LAVA_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_LAVA_BOTTLE.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), new ItemStack(VSItems.LINGERING_LAVA_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SOUL_LAVA_BOTTLE.get())), Ingredient.of(Items.GUNPOWDER), new ItemStack(VSItems.SPLASH_SOUL_LAVA_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_SOUL_LAVA_BOTTLE.get())), Ingredient.of(Items.DRAGON_BREATH), new ItemStack(VSItems.LINGERING_SOUL_LAVA_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_SOUL_LAVA_BOTTLE.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), new ItemStack(VSItems.LINGERING_SOUL_LAVA_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.MILK_BOTTLE.get())), Ingredient.of(Items.GUNPOWDER), new ItemStack(VSItems.SPLASH_MILK_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_MILK_BOTTLE.get())), Ingredient.of(Items.DRAGON_BREATH), new ItemStack(VSItems.LINGERING_MILK_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_MILK_BOTTLE.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), new ItemStack(VSItems.LINGERING_MILK_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.POWDER_SNOW_BOTTLE.get())), Ingredient.of(Items.GUNPOWDER), new ItemStack(VSItems.SPLASH_POWDER_SNOW_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_POWDER_SNOW_BOTTLE.get())), Ingredient.of(Items.DRAGON_BREATH), new ItemStack(VSItems.LINGERING_POWDER_SNOW_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(new ItemStack(VSItems.SPLASH_POWDER_SNOW_BOTTLE.get())), Ingredient.of(VSItems.STAINED_DRAGON_BREATH.get()), new ItemStack(VSItems.LINGERING_POWDER_SNOW_BOTTLE.get())));
    }
}
