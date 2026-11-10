package tbases.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tbases.common.templates.TBBlock;
import thaumcraft.api.crafting.IInfusionStabiliserExt;

public class TBSidedBlock extends TBBlock implements IInfusionStabiliserExt {
    boolean stabilise;

    public TBSidedBlock(Material m, boolean b) {
        super(m, b);
    }

    public TBSidedBlock stabilise() {
        this.stabilise = true;
        return this;
    }

    public boolean canStabaliseInfusion(World world, BlockPos paramBlockPos) {
        return this.stabilise;
    }

    public boolean hasSymmetryPenalty(World world, BlockPos pos1, BlockPos pos2) {
        return false;
    }

    public float getSymmetryPenalty(World world, BlockPos pos) {
        return 0.0F;
    }
}