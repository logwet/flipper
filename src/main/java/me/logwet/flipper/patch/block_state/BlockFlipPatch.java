package me.logwet.flipper.patch.block_state;

import net.minecraft.world.level.block.state.BlockState;

public interface BlockFlipPatch {
    default BlockState flipper$flipState(BlockState blockState) {
        return blockState;
    }
}
