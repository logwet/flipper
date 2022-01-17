package me.logwet.flipper.mixin.common.patch.block;

import me.logwet.flipper.FlipHelper;
import me.logwet.flipper.patch.block_state.BlockFlipPatch;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SlabBlock.class)
public abstract class SlabBlockMixin implements BlockFlipPatch {
    @Shadow @Final public static EnumProperty<SlabType> TYPE;

    @Override
    public BlockState flipper$flipState(BlockState blockState) {
        return FlipHelper.swapEnumProperty(TYPE, SlabType.BOTTOM, SlabType.TOP, blockState);
    }
}
