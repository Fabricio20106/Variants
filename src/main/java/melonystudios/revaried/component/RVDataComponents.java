package melonystudios.revaried.component;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.component.custom.UseRemainder;
import melonystudios.revaried.util.RVCodecs;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RVDataComponents {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Revaried.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CustomData>> SPAWNER_DATA = COMPONENTS.registerComponentType("spawner_data",
            builder -> builder.persistent(CustomData.CODEC).networkSynchronized(CustomData.STREAM_CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Holder<SoundEvent>>> SHATTER_SOUND = COMPONENTS.registerComponentType("shatter_sound",
            builder -> builder.persistent(SoundEvent.CODEC).networkSynchronized(SoundEvent.STREAM_CODEC));

    // these will be obsolete in 1.21.2, but I don't care I'm porting them anyway ~isa 29-01-26
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UseAnim>> USE_ANIMATION = COMPONENTS.registerComponentType("use_animation",
            builder -> builder.persistent(RVCodecs.USE_ANIMATION_CODEC).networkSynchronized(RVCodecs.USE_ANIMATION_STREAM_CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> USE_COOLDOWN = COMPONENTS.registerComponentType("use_cooldown",
            builder -> builder.persistent(ExtraCodecs.intRange(0, Integer.MAX_VALUE)).networkSynchronized(ByteBufCodecs.VAR_INT));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UseRemainder>> USE_REMAINDER = COMPONENTS.registerComponentType("use_remainder",
            builder -> builder.persistent(UseRemainder.CODEC).networkSynchronized(UseRemainder.STREAM_CODEC));
}
