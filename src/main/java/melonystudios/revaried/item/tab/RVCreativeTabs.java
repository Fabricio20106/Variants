package melonystudios.revaried.item.tab;

import melonystudios.revaried.RVConfigs;
import melonystudios.revaried.Revaried;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static melonystudios.revaried.item.RVItems.*;

public class RVCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Revaried.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = TABS.register("main", () -> CreativeModeTab.builder()
            .icon(() -> CYAN_SHULKER_SHELL.get().getDefaultInstance()).title(Component.translatable("tab.revaried.main")).displayItems((parameters, output) -> {
                // Blocks

                // Items
                output.accept(WHITE_SHULKER_SHELL);
                output.accept(INNO_SHULKER_SHELL);
                output.accept(LIGHT_GRAY_SHULKER_SHELL);
                output.accept(GRAY_SHULKER_SHELL);
                output.accept(BLACK_SHULKER_SHELL);
                output.accept(BROWN_SHULKER_SHELL);
                output.accept(RED_SHULKER_SHELL);
                output.accept(ORANGE_SHULKER_SHELL);
                output.accept(YELLOW_SHULKER_SHELL);
                output.accept(LIME_SHULKER_SHELL);
                output.accept(GREEN_SHULKER_SHELL);
                output.accept(CYAN_SHULKER_SHELL);
                output.accept(LIGHT_BLUE_SHULKER_SHELL);
                output.accept(GLOW_BLACK_SHULKER_SHELL);
                output.accept(BLUE_SHULKER_SHELL);
                output.accept(PURPLE_SHULKER_SHELL);
                output.accept(MAGENTA_SHULKER_SHELL);
                output.accept(PINK_SHULKER_SHELL);
                output.accept(MUSIC_DISC_DOG);
                addSpawnerMinecarts(parameters, output, SPAWNER_MINECART);
            }).build());

    private static void addSpawnerMinecarts(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output, ItemLike item) {
        output.accept(item);
        if (RVConfigs.POPULATE_SPAWNER_MINECARTS.get()) {
            parameters.holders().lookup(Registries.ENTITY_TYPE).ifPresent(types -> types.listElements()
                    .filter(entity -> entity.value().isEnabled(parameters.enabledFeatures()))
                    .map(entity -> Util.make(new ItemStack(item), stack -> {
                        CompoundTag tag = new CompoundTag();
                        CompoundTag entityTag = new CompoundTag();
                        CompoundTag spawnData = new CompoundTag();
                        entityTag.putString("id", entity.key().location().toString());
                        tag.putString("id", entity.key().location().toString());
                        spawnData.put("entity", entityTag);
                        tag.put("SpawnData", spawnData);
                        stack.set(DataComponents.ENTITY_DATA, CustomData.of(tag));
                    }))
                    .forEach(output::accept));
        }
    }
}
