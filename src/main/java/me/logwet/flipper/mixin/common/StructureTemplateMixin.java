package me.logwet.flipper.mixin.common;

import java.util.Random;
import me.logwet.flipper.FlipHelper;
import me.logwet.flipper.FlipHelper.Flip;
import me.logwet.flipper.patch.StructureContainerPatch;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StructureTemplate.class)
public abstract class StructureTemplateMixin {
    @Redirect(
            method = "calculateRelativePosition",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate;transform(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Mirror;Lnet/minecraft/world/level/block/Rotation;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/BlockPos;"))
    private static BlockPos replaceTransform(
            BlockPos targetPos,
            Mirror mirror,
            Rotation rotation,
            BlockPos offset,
            StructurePlaceSettings decorator,
            BlockPos blockPos) {
        return FlipHelper.transform(
                targetPos,
                mirror,
                rotation,
                ((StructureContainerPatch) decorator).flipper$getFlip(),
                ((StructureContainerPatch) decorator).flipper$getGlobalBoundingBox(),
                targetPos);
    }

    @Redirect(
            method =
                    "placeInWorld(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;Ljava/util/Random;I)Z",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/level/block/state/BlockState;rotate(Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;"))
    private BlockState injectFlipBlockState(
            BlockState blockState,
            Rotation rotation,
            LevelAccessor levelAccessor,
            BlockPos blockPos,
            BlockPos blockPos2,
            StructurePlaceSettings structurePlaceSettings,
            Random random,
            int i) {
        blockState = blockState.rotate(rotation);

        if (((StructureContainerPatch) structurePlaceSettings).flipper$getFlip() == Flip.FLIP) {
            blockState = FlipHelper.flipState(blockState);
        }

        return blockState;
    }
}
