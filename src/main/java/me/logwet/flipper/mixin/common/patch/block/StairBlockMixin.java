package me.logwet.flipper.mixin.common.patch.block;

import me.logwet.flipper.FlipHelper;
import me.logwet.flipper.patch.block_state.BlockFlipPatch;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StairBlock.class)
public abstract class StairBlockMixin implements BlockFlipPatch {
    @Shadow @Final public static EnumProperty<Half> HALF;

    @Override
    public BlockState flipper$flipState(BlockState blockState) {
        return FlipHelper.swapEnumProperty(HALF, Half.BOTTOM, Half.TOP, blockState);
    }
}
