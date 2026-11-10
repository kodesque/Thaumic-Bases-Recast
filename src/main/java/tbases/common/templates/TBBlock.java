package tbases.common.templates;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliserExt;

public class TBBlock extends Block implements IInfusionStabiliserExt {
    boolean isGlass;
    boolean stabilise;
    protected SoundType blockSoundType;

    public TBBlock(Material m, boolean b) {
        super(m);
        this.isGlass = b;
        this.blockSoundType = SoundType.STONE;
    }

    public TBBlock stabilise() {
        this.stabilise = true;
        return this;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return !this.isGlass;
    }

    public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
        return this.isGlass ? 7 : 15;
    }

    public boolean canStabaliseInfusion(World world, BlockPos paramBlockPos) {
        return this.stabilise;
    }

    public Block setSoundType(SoundType sound) {
        this.blockSoundType = sound;
        return this;
    }

    public SoundType getSoundType() {
        return this.blockSoundType;
    }

    public float getStabilizationAmount(World world, BlockPos blockPos) {
        return 0.1F;
    }
}
