package me.logwet.flipper.mixin.common.structure;

import me.logwet.flipper.FlipHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.OceanMonumentFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OceanMonumentFeature.OceanMonumentStart.class)
public abstract class OceanMonumentFeatureStartMixin
        extends StructureStart<NoneFeatureConfiguration> {
    public OceanMonumentFeatureStartMixin(
            StructureFeature<NoneFeatureConfiguration> structureFeature,
            int i,
            int j,
            BoundingBox boundingBox,
            int k,
            long l) {
        super(structureFeature, i, j, boundingBox, k, l);
    }

    @Inject(
            method =
                    "generatePieces(Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureManager;IILnet/minecraft/world/level/biome/Biome;Lnet/minecraft/world/level/levelgen/feature/configurations/NoneFeatureConfiguration;)V",
            at = @At("TAIL"))
    private void injectBoundingBoxToPieces(
            ChunkGenerator chunkGenerator,
            StructureManager structureManager,
            int i,
            int j,
            Biome biome,
            NoneFeatureConfiguration noneFeatureConfiguration,
            CallbackInfo ci) {
        FlipHelper.injectBoundingBoxToPieces(this.getPieces(), this.getBoundingBox());
    }
}
