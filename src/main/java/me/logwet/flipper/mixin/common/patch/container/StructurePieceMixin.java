package me.logwet.flipper.mixin.common.patch.container;

import java.util.Objects;
import me.logwet.flipper.FlipHelper;
import me.logwet.flipper.FlipHelper.Flip;
import me.logwet.flipper.patch.StructureContainerPatch;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(StructurePiece.class)
public abstract class StructurePieceMixin implements StructureContainerPatch {
    @Shadow protected BoundingBox boundingBox;
    @Unique protected BoundingBox globalBoundingBox;
    @Unique protected Flip flip = Flip.FLIP;

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

    @Shadow
    @Nullable
    public abstract Direction getOrientation();

    /**
     * @author logwet
     * @reason FlipHelper structure pieces
     */
    @Overwrite
    public int getWorldY(int y) {
        if (this.getOrientation() == null) {
            return y;
        }

        if (this.flip == Flip.FLIP) {
            if (Objects.nonNull(this.globalBoundingBox)) {
                return this.globalBoundingBox.y1 - y;
            }
        }

        return y + this.boundingBox.y0;
    }

    @ModifyVariable(method = "placeBlock", at = @At("HEAD"), ordinal = 0)
    private BlockState flipBlockstate(BlockState original) {
        if (this.flip == Flip.FLIP) {
            return FlipHelper.flipState(original);
        }

        return original;
    }
}
