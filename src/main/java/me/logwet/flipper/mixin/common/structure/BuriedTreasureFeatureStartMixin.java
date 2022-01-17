package me.logwet.flipper.mixin.common.structure;

import me.logwet.flipper.FlipHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.BuriedTreasureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BuriedTreasureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuriedTreasureFeature.BuriedTreasureStart.class)
public abstract class BuriedTreasureFeatureStartMixin
        extends StructureStart<BuriedTreasureConfiguration> {
    public BuriedTreasureFeatureStartMixin(
            StructureFeature<BuriedTreasureConfiguration> structureFeature,
            int i,
            int j,
            BoundingBox boundingBox,
            int k,
            long l) {
        super(structureFeature, i, j, boundingBox, k, l);
    }

    @Inject(method = "generatePieces", at = @At("TAIL"))
    private void injectBoundingBoxToPieces(
            ChunkGenerator chunkGenerator,
            StructureManager structureManager,
            int i,
            int j,
            Biome biome,
            BuriedTreasureConfiguration buriedTreasureConfiguration,
            CallbackInfo ci) {
        FlipHelper.injectBoundingBoxToPieces(this.getPieces(), this.getBoundingBox());
    }
}
