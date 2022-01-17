package me.logwet.flipper.mixin.common;

import java.util.List;
import java.util.Random;
import me.logwet.flipper.FlipHelper.Flip;
import me.logwet.flipper.patch.StructureContainerPatch;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureStart.class)
public abstract class StructureStartMixin {
    @Shadow @Final protected List<StructurePiece> pieces;

    @Inject(
            method = "placeInChunk",
            at = @At(value = "NEW", target = "net/minecraft/core/BlockPos", shift = Shift.AFTER))
    private void calculateGlobalBoundingBox(
            WorldGenLevel level,
            StructureFeatureManager structureManager,
            ChunkGenerator chunkGenerator,
            Random random,
            BoundingBox box,
            ChunkPos chunkPos,
            CallbackInfo ci) {
        BoundingBox globalBoundingBox = BoundingBox.getUnknownBox();

        for (StructurePiece structurePiece : this.pieces) {
            if (structurePiece.getBoundingBox().intersects(box)) {
                globalBoundingBox.expand(structurePiece.getBoundingBox());
            }
        }

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < this.pieces.size(); i++) {
            StructureContainerPatch piecePatch = ((StructureContainerPatch) this.pieces.get(i));

            piecePatch.flipper$setFlip(Flip.FLIP);
            piecePatch.flipper$setGlobalBoundingBox(globalBoundingBox);
        }
    }
}
