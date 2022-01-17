package me.logwet.flipper.mixin.common.patch.block;

import me.logwet.flipper.FlipHelper;
import me.logwet.flipper.patch.block_state.BlockFlipPatch;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BellBlock.class)
public abstract class BellBlockMixin implements BlockFlipPatch {
    @Shadow @Final public static EnumProperty<BellAttachType> ATTACHMENT;

    @Override
    public BlockState flipper$flipState(BlockState blockState) {
        return FlipHelper.swapEnumProperty(
                ATTACHMENT, BellAttachType.FLOOR, BellAttachType.CEILING, blockState);
    }
}
