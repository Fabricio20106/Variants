package melonystudios.variants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import melonystudios.variants.block.VSBlocks;
import melonystudios.variants.blockentity.VSBlockEntities;
import melonystudios.variants.blockentity.renderer.VSBedBlockEntityRenderer;
import melonystudios.variants.blockentity.renderer.VSBellBlockEntityRenderer;
import melonystudios.variants.command.argument.RVArgumentTypes;
import melonystudios.variants.config.RVJSONConfig;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.crafting.VSRecipeTypes;
import melonystudios.variants.criterion.VSCriteriaTriggers;
import melonystudios.variants.dispenser.vanilla.BucketDispenseBehavior;
import melonystudios.variants.dispenser.vanilla.EmptyBucketDispenseBehavior;
import melonystudios.variants.effect.VSEffects;
import melonystudios.variants.effect.VSPotions;
import melonystudios.variants.enchantment.VSEnchantments;
import melonystudios.variants.entity.VSEntities;
import melonystudios.variants.entity.renderer.*;
import melonystudios.variants.fluid.VSFluids;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.VSModdedItems;
import melonystudios.variants.item.VSWeaponry;
import melonystudios.variants.item.fix.VSTagFixes;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.loot.VSLootFunctions;
import melonystudios.variants.screen.options.RVConfigCategoriesScreen;
import melonystudios.variants.sound.VSSounds;
import melonystudios.variants.util.*;
import melonystudios.variants.world.biome.VSBiomes;
import melonystudios.variants.world.biome.provider.EnderwoodEndBiomeProvider;
import melonystudios.variants.world.carver.VSConfiguredCarvers;
import melonystudios.variants.world.carver.VSWorldCarvers;
import melonystudios.variants.world.feature.VSConfiguredFeatures;
import melonystudios.variants.world.feature.VSFeatures;
import melonystudios.variants.world.surface.VSSurfaceBuilders;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.Items;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

@Mod(Variants.MOD_ID)
public class Variants {
    public static final Logger LOGGER = LogManager.getLogger("revaried");
    public static final String MOD_ID = "variants";
    public static Variants INSTANCE;
    private RVJSONConfig config = null;
    private final File settingsFile = new File("config/melonystudios", "revaried.json");
    // TO-DO LIST:
    // [19/9/24 - 1.8.0.3] ~isa:
    //   - Make stained dragon's breath usable to make potions;
    //   - Make powder snow and sophie potion recipes in the data generators.

    public Variants() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        INSTANCE = this;
        this.loadConfig();

        VSItems.ITEMS.register(eventBus);
        VSWeaponry.ITEMS.register(eventBus);
        VSBlocks.BLOCKS.register(eventBus);
        VSFluids.FLUIDS.register(eventBus);
        VSEntities.ENTITIES.register(eventBus);
        VSBlockEntities.BLOCK_ENTITIES.register(eventBus);
        VSEffects.EFFECTS.register(eventBus);
        VSPotions.POTIONS.register(eventBus);
        VSEnchantments.ENCHANTMENTS.register(eventBus);
        VSSounds.SOUNDS.register(eventBus);
        VSWorldCarvers.CARVERS.register(eventBus);
        VSFeatures.FEATURES.register(eventBus);
        VSBiomes.BIOMES.register(eventBus);
        VSRecipeTypes.RECIPE_TYPES.register(eventBus);
        VSConsumeBehaviors.BEHAVIORS.register(eventBus);
        VSTagFixes.FIXES.register(eventBus);
        VSLootFunctions.init();
        VSCriteriaTriggers.init();
        VSRegistries.init();
        VSStats.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VSConfigs.COMMON_SPEC, "melonystudios/revaried-common.toml");
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (minecraft, screen) -> new RVConfigCategoriesScreen(screen, Minecraft.getInstance().options));
    }

    public static ResourceLocation variants(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        EntitySpawnPlacementRegistry.register(VSEntities.FISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::checkFishSpawnRules);

        RegistryKey<Biome> paintingwoodForestKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES, VSBiomes.PAINTINGWOOD_FOREST.getId());
        RegistryKey<Biome> azureFieldsKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES, VSBiomes.AZURE_FIELDS.getId());
        if (INSTANCE.getConfig().paintingwoodForest) {
            BiomeDictionary.addTypes(paintingwoodForestKey, OVERWORLD, FOREST, LUSH);
            BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(paintingwoodForestKey, 7));
        }
        if (INSTANCE.getConfig().azureFields) {
            BiomeDictionary.addTypes(azureFieldsKey, OVERWORLD, PLAINS, LUSH);
            BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(azureFieldsKey, 5));
        }

        VSConfiguredFeatures.init();
        VSConfiguredCarvers.init();
        VSSurfaceBuilders.init();
        RVArgumentTypes.init();
        Registry.register(Registry.BIOME_SOURCE, variants("enderwood_end"), EnderwoodEndBiomeProvider.CODEC);

        VSPotions.addBrewingRecipes();
        VSVanillaCompatibility.compostables();
        VSVanillaCompatibility.flammables();

        DispenserBlock.registerBehavior(Items.BUCKET, new EmptyBucketDispenseBehavior());
        DispenserBlock.registerBehavior(Items.WATER_BUCKET, new BucketDispenseBehavior());

        WoodType.register(VSWoodTypes.PAINTING);
        WoodType.register(VSWoodTypes.ENDERWOOD);
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        VSUtils.addTextureIdentifier(VSItems.EXPONENTIAL_MUSHROOM_STEW.get(), VSItems.EXPONENTIAL_BEETROOT_SOUP.get(), VSItems.EXPONENTIAL_RABBIT_STEW.get(), VSItems.EXPONENTIAL_SUSPICIOUS_STEW.get(),
                VSItems.EXPONENTIAL_MELTING_BEET_SOUP.get(), VSItems.EXPONENTIAL_FUNGI_STEW.get(), VSItems.EXPONENTIAL_END_FUNGI_STEW.get(), VSItems.EXPONENTIAL_ALJAN_FUNGI_STEW.get(), VSItems.EXPONENTIAL_WATER_BOWL.get(),
                VSItems.EXPONENTIAL_MILK_BOWL.get(), VSItems.EXPONENTIAL_LAVA_BOWL.get(), VSItems.EXPONENTIAL_SOUL_LAVA_BOWL.get(), VSItems.EXPONENTIAL_POWDER_SNOW_BOWL.get());
        VSUtils.addTextureIdentifier(VSItems.STAINED_EXPERIENCE_BOTTLE.get(), VSItems.STAINED_HONEY_BOTTLE.get(), VSItems.STAINED_DRAGON_BREATH.get(), VSItems.STAINED_POTION.get(),
                VSItems.STAINED_LAVA_BOTTLE.get(), VSItems.STAINED_SOUL_LAVA_BOTTLE.get(), VSItems.STAINED_MILK_BOTTLE.get(), VSItems.STAINED_POWDER_SNOW_BOTTLE.get(), VSItems.STAINED_SOPHIE_POTION.get());
        VSUtils.makeShield(VSWeaponry.EMPTY_ARMOR_SLOT_SHIELD.get());
        VSUtils.makeBow(VSWeaponry.DEBUG_BOW.get());
        VSUtils.addArmorDesigns(VSWeaponry.WOOL_SWEATER.get());
        VSUtils.addSpawnerMinecartMobs(VSItems.SPAWNER_MINECART.get());
        VSUtils.woolArmorDyeingColor(VSModdedItems.RED_YELLOW_DYE, 15731456, "backmath");
        VSUtils.woolArmorDyeingColor(VSModdedItems.ALJAN_LIGHT_BLUE_DYE, 13429739, "backmath");
        VSUtils.woolArmorDyeingColor(VSModdedItems.POISON_BROWN_DYE, 8921856, "backmath");
        VSUtils.woolArmorDyeingColor(VSModdedItems.INSOMNIAN_DYE, 4418465, "backmath");
        VSUtils.woolArmorDyeingColor(VSModdedItems.INNO_DYE, 15457757, "f10elements");
        setRenderTypesForBlocks();

        Atlases.addWoodType(VSWoodTypes.PAINTING);
        Atlases.addWoodType(VSWoodTypes.ENDERWOOD);

        RenderingRegistry.registerEntityRenderingHandler(VSEntities.FISH.get(), FishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(VSEntities.VS_BOAT.get(), VSBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(VSEntities.DRAGON_BREATH_BOTTLE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(VSEntities.SMALL_SOUL_FIREBALL.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(VSEntities.STAINED_EXPERIENCE_BOTTLE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(VSEntities.BEHAVIOR_BOTTLE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(VSEntities.DEBUG_ARROW.get(), DebugArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityType.SPAWNER_MINECART, SpawnerMinecartRenderer::new);

        ClientRegistry.registerKeyBinding(VSKeys.SHOW_TAGS_KEY);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BELL.get(), VSBellBlockEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BEACON.get(), BeaconTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BED.get(), VSBedBlockEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_SIGN.get(), SignTileEntityRenderer::new);
    }

    public void loadConfig() {
        if (this.settingsFile.exists()) {
            try {
                Gson gson = createConfigFileSerializer().create();
                this.config = gson.fromJson(new String(Files.readAllBytes(this.settingsFile.toPath()), StandardCharsets.UTF_8), RVJSONConfig.class);
            } catch (Exception exception) {
                LOGGER.warn(I18n.get("exception.variants.config_error.loading", exception));
            }
        }
        if (this.config == null) this.config = new RVJSONConfig();
        this.upgradeConfig(this.config);
        this.saveConfig();
    }

    public void saveConfig() {
        String config = createConfigFileSerializer().setPrettyPrinting().create().toJson(this.config);
        try {
            Files.write(this.settingsFile.toPath(), config.getBytes(StandardCharsets.UTF_8));
        } catch (IOException exception) {
            LOGGER.warn(I18n.get("exception.variants.config_error.saving", exception));
        }
    }

    private void upgradeConfig(RVJSONConfig config) {
        switch (config.version) {
            case 1805: {
                config.version = 1807;
                config.crimsonWheatPatches = true;
                config.soulCarrotPatches = true;
                config.warpedPotatoPatches = true;
                config.meltingBeetPatches = true;
                break;
            }
            case 1807: {
                config.version = 1808;
                break;
            }
        }
    }

    public RVJSONConfig getConfig() {
        return this.config;
    }

    public static GsonBuilder createConfigFileSerializer() {
        return new GsonBuilder().registerTypeAdapter(RVJSONConfig.class, new RVJSONConfig.Serializer());
    }

    public static void setRenderTypesForBlocks() {
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_SUNNY_FLOWER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_SUGAR_CANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_PAINTING_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CARROTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.CRIMSON_WHEAT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WILD_CRIMSON_WHEAT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.SOUL_CARROTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WILD_SOUL_CARROTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPED_POTATOES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WILD_WARPED_POTATOES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.MELTING_BEETS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WILD_MELTING_BEETS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.SUNNY_FLOWER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BERRY_BUSH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPED_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_BEACON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CAULDRON.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_BEACON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_CAULDRON.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR_WANDERER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR_GRAHAM.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR_FIRST.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_GLASS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_GLASS_PANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_CHAIN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CHAIN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.DIAMOND_CHAIN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.EMERALD_CHAIN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_BARS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_LADDER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.SOUL_BREWING_STAND.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDERWOOD_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDERWOOD_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.OAK_TRAPDOOR_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.BIRCH_TRAPDOOR_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ACACIA_TRAPDOOR_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.DARK_OAK_TRAPDOOR_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.CRIMSON_TRAPDOOR_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDERWOOD_TRAPDOOR_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDER_ROOTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_ENDER_ROOTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.END_SPROUTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDER_FUNGUS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_ENDER_FUNGUS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPING_VINES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPING_VINES_PLANT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDER_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_GRASS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_GOLDEN_CARROTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_NETHER_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_WARPED_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_ENDER_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_TORCH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_SOUL_TORCH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_REDSTONE_TORCH.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_STAINED_GLASS.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_STAINED_GLASS_PANE.get(), RenderType.translucent());

        RenderTypeLookup.setRenderLayer(VSFluids.MUSHROOM_STEW.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSFluids.FLOWING_MUSHROOM_STEW.get(), RenderType.translucent());
    }
}
