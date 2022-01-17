package me.logwet.flipper.mixin.common.patch.block;

import me.logwet.flipper.FlipHelper;
import me.logwet.flipper.patch.block_state.BlockFlipPatch;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FaceAttachedHorizontalDirectionalBlock.class)
public abstract class FaceAttachedHorizontalDirectionBlockMixin implements BlockFlipPatch {
    @Shadow @Final public static EnumProperty<AttachFace> FACE;

    @Override
    public BlockState flipper$flipState(BlockState blockState) {
        return FlipHelper.swapEnumProperty(FACE, AttachFace.FLOOR, AttachFace.CEILING, blockState);
    }
}
