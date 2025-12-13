package melonystudios.variants.mixin.item;

import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VSStyles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.FireworkStarItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(FireworkRocketItem.class)
public class RVFireworkRocketItemMixin extends Item {
    public RVFireworkRocketItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText", at = @At("HEAD"), cancellable = true)
    public void addUpdatedFireworkTooltip(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo callback) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (!Variants.revaried().settings().updatedFireworkTooltips) return;
        callback.cancel();
        CompoundNBT fireworks = stack.getTagElement("Fireworks");

        if (fireworks == null) {
            tooltip.add(new TranslationTextComponent("tooltip.variants.firework_rocket.flight_duration", new TranslationTextComponent("tooltip.variants.firework_rocket.flight_duration.unknown").withStyle(TextFormatting.DARK_GRAY)).withStyle(TextFormatting.GRAY));
        } else {
            if (fireworks.contains("Flight", Constants.TagTypes.ANY_NUMERIC)) {
                byte flightDuration = fireworks.getByte("Flight");
                TextFormatting flightDurationColor;

                switch (flightDuration) {
                    case 1:
                        flightDurationColor = TextFormatting.RED;
                        break;
                    case 2:
                        flightDurationColor = TextFormatting.GOLD;
                        break;
                    case 3:
                        flightDurationColor = TextFormatting.GREEN;
                        break;
                    default: flightDurationColor = TextFormatting.WHITE;
                }

                tooltip.add(new TranslationTextComponent("tooltip.variants.firework_rocket.flight_duration", new StringTextComponent("" + flightDuration).withStyle(flightDurationColor)).withStyle(TextFormatting.GRAY));
            }

            ListNBT explosions = fireworks.getList("Explosions", Constants.TagTypes.COMPOUND);
            if (!explosions.isEmpty()) {
                tooltip.add(new StringTextComponent(" "));
                tooltip.add(new TranslationTextComponent("tooltip.variants.firework_rocket.explosions").withStyle(VSStyles.FIREWORK_TITLES));

                for (int i = 0; i < explosions.size(); ++i) {
                    CompoundNBT explosion = explosions.getCompound(i);
                    List<ITextComponent> componentList = Lists.newArrayList();
                    FireworkStarItem.appendHoverText(explosion, componentList);
                    if (!componentList.isEmpty()) {
                        for (int j = 1; j < componentList.size(); ++j) {
                            componentList.set(j, new StringTextComponent("").append(componentList.get(j)).withStyle(TextFormatting.GRAY));
                        }

                        tooltip.addAll(componentList);
                    }
                }
            }
        }
    }
}
