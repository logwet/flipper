package me.logwet.flipper.mixin.common.patch.container;

import me.logwet.flipper.FlipHelper.Flip;
import me.logwet.flipper.patch.StructureContainerPatch;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(StructurePiece.class)
public abstract class StructurePieceMixin implements StructureContainerPatch {
    @Unique protected BoundingBox globalBoundingBox;
    @Unique protected Flip flip;

    @Override
    public BoundingBox flipper$getGlobalBoundingBox() {
        return globalBoundingBox;
    }

    @Override
    public void flipper$setGlobalBoundingBox(BoundingBox boundingBox) {
        this.globalBoundingBox = boundingBox;
    }

    @Override
    public Flip flipper$getFlip() {
        return flip;
    }

    @Override
    public void flipper$setFlip(Flip flip) {
        this.flip = flip;
    }
}
