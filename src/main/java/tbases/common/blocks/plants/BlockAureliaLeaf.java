package tbases.common.blocks.plants;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tbases.init.TBItems;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockAureliaLeaf extends Block {
    public BlockAureliaLeaf() {
        super(Material.PLANTS);
        this.setLightLevel(0.3F);
        this.setSoundType(SoundType.PLANT);
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(TBItems.aureliapetal);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB((double)0.25F, (double)0.0F, (double)0.25F, (double)0.75F, (double)0.25F, (double)0.75F);
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return TBItems.aureliapetal;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }
}
