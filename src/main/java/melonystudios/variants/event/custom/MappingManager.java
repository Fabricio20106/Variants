package melonystudios.variants.event.custom;

import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;

public class MappingManager {
    private final String modID;
    private final RegistryEvent.MissingMappings<Block> blockMap;
    private final RegistryEvent.MissingMappings<Item> itemMap;
    private final RegistryEvent.MissingMappings<EntityType<?>> entityMap;
    private final RegistryEvent.MissingMappings<Biome> biomeMap;

    public static MappingManager createBlockMaps(String modID, RegistryEvent.MissingMappings<Block> mappings) {
        return new MappingManager(modID, mappings, null, null, null);
    }

    public static MappingManager createItemMaps(String modID, RegistryEvent.MissingMappings<Item> mappings) {
        return new MappingManager(modID, null, mappings, null, null);
    }

    public static MappingManager createEntityTypeMaps(String modID, RegistryEvent.MissingMappings<EntityType<?>> mappings) {
        return new MappingManager(modID, null, null, mappings, null);
    }

    public static MappingManager createBiomeMaps(String modID, RegistryEvent.MissingMappings<Biome> mappings) {
        return new MappingManager(modID, null, null, null, mappings);
    }

    public MappingManager(String modID, RegistryEvent.MissingMappings<Block> blockMappings, RegistryEvent.MissingMappings<Item> itemMappings, RegistryEvent.MissingMappings<EntityType<?>> entityMappings, RegistryEvent.MissingMappings<Biome> biomeMappings) {
        this.modID = modID;
        this.blockMap = blockMappings;
        this.itemMap = itemMappings;
        this.entityMap = entityMappings;
        this.biomeMap = biomeMappings;
    }

    public void remap(String input, Block output) {
        UnmodifiableIterator<?> iterator;
        RegistryEvent.MissingMappings.Mapping mapping;

        if (this.blockMap != null) {
            iterator = this.blockMap.getAllMappings().iterator();

            while (iterator.hasNext()) {
                mapping = (RegistryEvent.MissingMappings.Mapping<?>) iterator.next();
                if (mapping.key.getNamespace().equals(this.modID) && mapping.key.getPath().equals(input)) mapping.remap(output);
            }
        }

        if (this.itemMap != null) {
            iterator = this.itemMap.getAllMappings().iterator();

            while (iterator.hasNext()) {
                mapping = (RegistryEvent.MissingMappings.Mapping<?>) iterator.next();
                if (mapping.key.getNamespace().equals(this.modID) && mapping.key.getPath().equals(input)) mapping.remap(output.asItem());
            }
        }
    }

    public void remap(String input, Item output) {
        UnmodifiableIterator<?> iterator;
        RegistryEvent.MissingMappings.Mapping mapping;

        if (this.itemMap != null) {
            iterator = this.itemMap.getAllMappings().iterator();

            while (iterator.hasNext()) {
                mapping = (RegistryEvent.MissingMappings.Mapping<?>) iterator.next();
                if (mapping.key.getNamespace().equals(this.modID) && mapping.key.getPath().equals(input)) mapping.remap(output);
            }
        }
    }

    public void remap(String input, EntityType<?> output) {
        UnmodifiableIterator<?> iterator;
        RegistryEvent.MissingMappings.Mapping mapping;

        if (this.entityMap != null) {
            iterator = this.entityMap.getAllMappings().iterator();

            while (iterator.hasNext()) {
                mapping = (RegistryEvent.MissingMappings.Mapping<?>) iterator.next();
                if (mapping.key.getNamespace().equals(this.modID) && mapping.key.getPath().equals(input)) mapping.remap(output);
            }
        }
    }

    public void remap(String input, Biome output) {
        UnmodifiableIterator<?> iterator;
        RegistryEvent.MissingMappings.Mapping mapping;

        if (this.biomeMap != null) {
            iterator = this.biomeMap.getAllMappings().iterator();

            while (iterator.hasNext()) {
                mapping = (RegistryEvent.MissingMappings.Mapping<?>) iterator.next();
                if (mapping.key.getNamespace().equals(this.modID) && mapping.key.getPath().equals(input)) mapping.remap(output);
            }
        }
    }
}
