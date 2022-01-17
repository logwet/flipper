package me.logwet.flipper.mixin.common.patch.container;

import me.logwet.flipper.FlipHelper.Flip;
import me.logwet.flipper.patch.StructureContainerPatch;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TemplateStructurePiece.class)
public abstract class TemplateStructurePieceMixin extends StructurePieceMixin {
    @Shadow protected StructurePlaceSettings placeSettings;

    @Override
    public void flipper$setFlip(Flip flip) {
        super.flipper$setFlip(flip);

        ((StructureContainerPatch) this.placeSettings).flipper$setFlip(flip);
    }

    @Override
    public void flipper$setGlobalBoundingBox(BoundingBox boundingBox) {
        super.flipper$setGlobalBoundingBox(boundingBox);

        ((StructureContainerPatch) this.placeSettings).flipper$setGlobalBoundingBox(boundingBox);
    }
}
