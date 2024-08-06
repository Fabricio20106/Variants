package melonystudios.variants.world.carver.custom;

import com.mojang.serialization.Codec;
import melonystudios.variants.util.tag.VSBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.carver.CanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class EndRavineCarver extends CanyonWorldCarver {
    public EndRavineCarver(Codec<ProbabilityConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean canReplaceBlock(BlockState state) {
        return state.is(VSBlockTags.END_CARVER_REPLACEABLES);
    }
}
