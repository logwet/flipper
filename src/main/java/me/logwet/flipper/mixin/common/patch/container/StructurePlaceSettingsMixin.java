package me.logwet.flipper.mixin.common.patch.container;

import me.logwet.flipper.FlipHelper.Flip;
import me.logwet.flipper.patch.StructureContainerPatch;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructurePlaceSettings.class)
public abstract class StructurePlaceSettingsMixin implements StructureContainerPatch {
    @Unique protected BoundingBox globalBoundingBox;
    @Unique protected Flip flip = Flip.FLIP;

    @Override
    public Flip flipper$getFlip() {
        return flip;
    }

    @Override
    public void flipper$setFlip(Flip flip) {
        this.flip = flip;
    }

    @Override
    public BoundingBox flipper$getGlobalBoundingBox() {
        return globalBoundingBox;
    }

    @Override
    public void flipper$setGlobalBoundingBox(BoundingBox boundingBox) {
        globalBoundingBox = boundingBox;
    }

    @Inject(method = "copy", at = @At("TAIL"))
    private void injectCopyFlip(CallbackInfoReturnable<StructurePlaceSettings> cir) {
        ((StructureContainerPatch) cir.getReturnValue()).flipper$setFlip(this.flipper$getFlip());
    }
}
