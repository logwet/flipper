package me.logwet.flipper.patch;

import me.logwet.flipper.FlipHelper.Flip;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public interface StructureContainerPatch {
    Flip flipper$getFlip();

    void flipper$setFlip(Flip flip);

    BoundingBox flipper$getGlobalBoundingBox();

    void flipper$setGlobalBoundingBox(BoundingBox boundingBox);
}
