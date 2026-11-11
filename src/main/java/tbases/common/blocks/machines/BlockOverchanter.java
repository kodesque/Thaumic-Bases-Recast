package tbases.common.blocks.machines;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tbases.common.tiles.TileOverchanter;
import tbases.init.TBGuiHandler;
import tbases.root.TBCore;

public class BlockOverchanter extends BlockContainer implements ITileEntityProvider {
    protected static final AxisAlignedBB AABB = new AxisAlignedBB((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.75F, (double)1.0F);
    private static boolean keepInventory;

    public BlockOverchanter() {
        super(Material.ROCK, MapColor.PURPLE);
        this.setHardness(2.0F);
        this.setHarvestLevel("pickaxe", 3);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileOverchanter();
    }

    public boolean onBlockActivated(World w, BlockPos pos, IBlockState state, EntityPlayer p, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (w.isRemote) {
            return true;
        } else {
            TileEntity tileentity = w.getTileEntity(pos);
            if (tileentity instanceof TileOverchanter) {
                p.openGui(TBCore.instance, TBGuiHandler.OVERCHANTER, w, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileOverchanter) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileOverchanter)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }
}
