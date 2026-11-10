package tbases.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tbases.common.tiles.TileBraizer;
import tbases.init.TBSounds;
import tbases.util.MathUtils;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockBraizer extends BlockContainer implements ITileEntityProvider {
    public static PropertyInteger STATE = PropertyInteger.create("state", 0, 1);

    public BlockBraizer() {
        super(Material.ROCK);
        this.setHardness(1.0F);
        this.setResistance(1.0F);
    }

    public void randomDisplayTick(IBlockState stateIn, World w, BlockPos pos, Random r) {
        if ((Integer)stateIn.getValue(STATE) == 1) {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            w.spawnParticle(EnumParticleTypes.FLAME, (double)x + (double)0.5F + MathUtils.randomDouble(r) / (double)4.0F, (double)y + 0.6, (double)z + (double)0.5F + MathUtils.randomDouble(r) / (double)4.0F, (double)0.0F, 0.04, (double)0.0F, new int[0]);

            for(int i = 0; i < 2; ++i) {
                w.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)x + (double)0.5F + MathUtils.randomDouble(r) / (double)4.0F, (double)y + 0.7, (double)z + (double)0.5F + MathUtils.randomDouble(r) / (double)4.0F, (double)0.0F, r.nextDouble() / (double)20.0F, (double)0.0F, new int[0]);
            }

            w.playSound((double)x + (double)0.5F, (double)y + (double)0.5F, (double)z + (double)0.5F, TBSounds.fire_loop, SoundCategory.BLOCKS, 0.1F, 0.1F, false);
            if ((Integer)stateIn.getValue(STATE) == 1) {
                this.setLightLevel(10.0F);
            }
        }

    }

    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileBraizer();
    }

    protected BlockStateContainer createBlockState() {
        if (STATE == null) {
            STATE = PropertyInteger.create("state", 0, 1);
        }

        return new BlockStateContainer(this, new IProperty[]{STATE});
    }

    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue(STATE);
    }
}