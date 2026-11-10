package tbases.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tbases.common.templates.TBBlock;
import tbases.root.TBCreativeTabs;
import thaumcraft.api.crafting.IInfusionStabiliserExt;

public class TBBlockCrytal extends TBBlock implements IInfusionStabiliserExt {
    public TBBlockCrytal(Material m, boolean b) {
        super(m, b);
        this.setSoundType(SoundType.GLASS);
        this.setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        this.setHardness(0.5F);
        this.setHarvestLevel("pickaxe", 0);
    }

    public boolean canStabaliseInfusion(World world, BlockPos paramBlockPos) {
        return true;
    }

    public boolean hasSymmetryPenalty(World world, BlockPos pos1, BlockPos pos2) {
        return false;
    }

    public float getSymmetryPenalty(World world, BlockPos pos) {
        return 0.0F;
    }
}
