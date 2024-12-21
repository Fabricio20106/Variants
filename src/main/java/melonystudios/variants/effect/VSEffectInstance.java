package melonystudios.variants.effect;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

// Variant of EffectInstance that uses suppliers for the effect because effects aren't registered during item registry.
public class VSEffectInstance extends EffectInstance {
    private final Supplier<Effect> effectSupplier;
    private final int duration;
    private int amplifier = 0;
    private boolean ambient = false;
    private boolean showParticles = true;
    private boolean showIcon = true;
    @OnlyIn(Dist.CLIENT)
    private boolean noCounter = false;
    private List<ItemStack> curativeStacks = Lists.newArrayList(new ItemStack(Items.MILK_BUCKET));
    private float chance = 1;

    public VSEffectInstance(EffectInstance instance) {
        super(instance);
        this.effectSupplier = instance::getEffect;
        this.duration = instance.getDuration();
        this.amplifier = instance.getAmplifier();
        this.ambient = instance.isAmbient();
        this.showParticles = instance.isVisible();
        this.showIcon = instance.showIcon();
        this.noCounter = instance.isNoCounter();
        this.curativeStacks = instance.getCurativeItems();
    }

    /**
     * A full custom effect instance made by Variants. Used because effects aren't registered on item registering.
     *
     * @param effect The effect to apply.
     * @param duration How long this effect will last in ticks.
     */
    public VSEffectInstance(Supplier<Effect> effect, int duration) {
        super(null, duration);
        this.effectSupplier = effect;
        this.duration = duration;
    }

    /**
     * A full custom effect instance made by Variants. Used because effects aren't registered on item registering.
     *
     * @param effect The effect to apply.
     * @param duration How long this effect will last in ticks.
     * @param amplifier The amplifier of the effect.
     */
    public VSEffectInstance(Supplier<Effect> effect, int duration, int amplifier) {
        super(null, duration, amplifier);
        this.effectSupplier = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    /**
     * A full custom effect instance made by Variants. Used because effects aren't registered on item registering.
     *
     * @param effect The effect to apply.
     * @param duration How long this effect will last in ticks.
     * @param amplifier The amplifier of the effect.
     * @param ambient Whether this effect is applied by an ambient source, like beacons or conduits.
     */
    public VSEffectInstance(Supplier<Effect> effect, int duration, int amplifier, boolean ambient) {
        super(null, duration, amplifier, ambient, true);
        this.effectSupplier = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
    }

    /**
     * A full custom effect instance made by Variants. Used because effects aren't registered on item registering.
     *
     * @param effect The effect to apply.
     * @param duration How long this effect will last in ticks.
     * @param amplifier The amplifier of the effect.
     * @param ambient Whether this effect is applied by an ambient source, like beacons or conduits.
     * @param showParticles Whether to show particles for this potion.
     */
    public VSEffectInstance(Supplier<Effect> effect, int duration, int amplifier, boolean ambient, boolean showParticles) {
        super(null, duration, amplifier, ambient, showParticles);
        this.effectSupplier = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.showParticles = showParticles;
    }

    /**
     * A full custom effect instance made by Variants. Used because effects aren't registered on item registering.
     *
     * @param effect The effect to apply.
     * @param duration How long this effect will last in ticks.
     * @param amplifier The amplifier of the effect.
     * @param ambient Whether this effect is applied by an ambient source, like beacons or conduits.
     * @param showParticles Whether to show particles for this potion.
     * @param showIcon Whether to show the effect icon on the inventory and on the HUD.
     */
    public VSEffectInstance(Supplier<Effect> effect, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon) {
        super(null, duration, amplifier, ambient, showParticles, showIcon);
        this.effectSupplier = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.showParticles = showParticles;
        this.showIcon = showIcon;
    }

    /**
     * A full custom effect instance made by Variants. Used because effects aren't registered on item registering.
     *
     * @param effect The effect to apply.
     * @param duration How long this effect will last in ticks.
     * @param amplifier The amplifier of the effect.
     * @param ambient Whether this effect is applied by an ambient source, like beacons or conduits.
     * @param showParticles Whether to show particles for this potion.
     * @param showIcon Whether to show the effect icon on the inventory and on the HUD.
     * @param noCounter Whether to hide the duration of this effect in item tooltips.
     */
    public VSEffectInstance(Supplier<Effect> effect, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon, boolean noCounter) {
        super(null, duration, amplifier, ambient, showParticles, showIcon);
        this.effectSupplier = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.showParticles = showParticles;
        this.showIcon = showIcon;
        this.noCounter = noCounter;
    }

    /**
     * A full custom effect instance made by Variants. Used because effects aren't registered on item registering.
     *
     * @param effect The effect to apply.
     * @param duration How long this effect will last in ticks.
     * @param amplifier The amplifier of the effect.
     * @param ambient Whether this effect is applied by an ambient source, like beacons or conduits.
     * @param showParticles Whether to show particles for this potion.
     * @param showIcon Whether to show the effect icon on the inventory and on the HUD.
     * @param noCounter Whether to hide the duration of this effect in item tooltips.
     * @param curativeItems A list of item stacks that can be used to cure this effect. Defaults to <code>minecraft:milk_bucket</code>
     */
    public VSEffectInstance(Supplier<Effect> effect, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon, boolean noCounter, List<ItemStack> curativeItems) {
        super(null, duration, amplifier, ambient, showParticles, showIcon);
        this.effectSupplier = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.showParticles = showParticles;
        this.showIcon = showIcon;
        this.noCounter = noCounter;
        this.curativeStacks = curativeItems;
    }

    public VSEffectInstance withChance(float chance) {
        this.chance = chance;
        return this;
    }

    public float getChance() {
        return this.chance;
    }

    @Override
    @Nonnull
    public Effect getEffect() {
        return this.effectSupplier.get();
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public int getAmplifier() {
        return this.amplifier;
    }

    @Override
    public boolean isAmbient() {
        return this.ambient;
    }

    @Override
    public boolean isVisible() {
        return this.showParticles;
    }

    @Override
    public boolean showIcon() {
        return this.showIcon;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isNoCounter() {
        return this.noCounter;
    }

    @Override
    @Nonnull
    public List<ItemStack> getCurativeItems() {
        return this.curativeStacks;
    }
}
