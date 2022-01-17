package me.logwet.flipper;

import java.util.Objects;
import me.logwet.flipper.patch.block_state.BlockFlipPatch;
import me.logwet.flipper.patch.block_state.BlockStateFlipPatch;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class FlipHelper {
    private FlipHelper() {}

    public static BlockState flipState(BlockState blockState) {
        if (blockState instanceof BlockStateFlipPatch) {
            blockState = ((BlockStateFlipPatch) blockState).flipper$flipState();
        }

        return blockState;
    }

    public static BlockState flipState(Block block, BlockState blockState) {
        if (block instanceof BlockFlipPatch) {
            return ((BlockFlipPatch) block).flipper$flipState(blockState);
        }

        return blockState;
    }

    public static <T extends Comparable<T>, V extends T> BlockState swapEnumProperty(
            Property<T> property, V value1, V value2, BlockState blockState) {
        if (value1.equals(blockState.getValue(property))) {
            return blockState.setValue(property, value2);
        }
        if (value2.equals(blockState.getValue(property))) {
            return blockState.setValue(property, value1);
        }

        return blockState;
    }

    public static BlockPos transform(
            BlockPos targetPos,
            Mirror mirror,
            Rotation rotation,
            Flip flip,
            BoundingBox boundingBox,
            BlockPos offset) {
        BlockPos blockPos = StructureTemplate.transform(targetPos, mirror, rotation, offset);

        if (flip == Flip.FLIP) {
            if (Objects.nonNull(boundingBox)) {
                blockPos =
                        new BlockPos(
                                blockPos.getX(), boundingBox.y1 - blockPos.getY(), blockPos.getZ());
            }
        }

        return blockPos;
    }

    public enum Flip {
        NONE,
        FLIP
    }
}
