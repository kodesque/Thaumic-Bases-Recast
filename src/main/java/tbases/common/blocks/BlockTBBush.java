package tbases.common.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tbases.init.TBItems;
import tbases.root.TBCore;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockTBBush extends BlockBush implements IGrowable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
    public int growthStages = 8;
    public int growthDelay = 4;

    public BlockTBBush() {
        super(Material.PLANTS);
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
    }

    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return super.canPlaceBlockAt(world, pos) && world.isAirBlock(pos.up());
    }

    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos.up(), this.getStateFromMeta(8));
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
        int meta = this.getMetaFromState(state);
        if (this.isTopBlock(meta)) {
            if (world.getBlockState(pos.down()).getBlock() != this) {
                world.setBlockToAir(pos);
            }
        } else {
            if (world.getBlockState(pos.up()).getBlock() != this) {
                world.setBlockToAir(pos);
            }

            if (!this.canBlockStay(world, pos, state)) {
                this.dropBlockAsItem(world, pos, state, 0);
                world.setBlockToAir(pos);
            }
        }
    }

    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        BlockPos below = pos.down();
        IBlockState belowState = world.getBlockState(below);
        Block block = belowState.getBlock();

        return !world.isAirBlock(pos) &&
                (block.isReplaceableOreGen(belowState, world, below, BlockStateMatcher.forBlock(Blocks.GRASS)) ||
                        block.isReplaceableOreGen(belowState, world, below, BlockStateMatcher.forBlock(Blocks.DIRT)) ||
                        block.canSustainPlant(belowState, world, below, EnumFacing.UP, this));
    }

    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
        return world.getLightFromNeighbors(pos.up()) >= 9;
    }

    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return this.getMetaFromState(state) % 8 < this.growthStages - 1;
    }

    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        if (world.getBlockState(pos).getBlock() == this) {
            int meta = this.getMetaFromState(state);
            if (this.isTopBlock(meta)) {
                if (meta - 8 < this.growthStages - 1 && world.rand.nextInt(this.growthDelay) == 0) {
                    world.setBlockState(pos, this.getStateFromMeta(meta + 1));
                    world.setBlockState(pos.down(), this.getStateFromMeta(meta - 7));
                }
            } else if (meta < this.growthStages - 1 && world.rand.nextInt(this.growthDelay) == 0) {
                world.setBlockState(pos, this.getStateFromMeta(meta + 1));
                world.setBlockState(pos.up(), this.getStateFromMeta(meta + 9));
            }
        }
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AGE, meta);
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(AGE);
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE);
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return TBCore.proxy.fancyGraphicsEnable() || blockAccess.getBlockState(pos.offset(side)).getBlock() != this
                ? super.shouldSideBeRendered(blockState, blockAccess, pos, side)
                : false;
    }

    public boolean isTopBlock(int meta) {
        return meta > 7;
    }

    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);
        if (world.getBlockState(pos).getBlock() == this) {
            int meta = this.getMetaFromState(state);
            if (this.isTopBlock(meta)) {
                if (meta - 8 < this.growthStages - 1 && world.rand.nextInt(this.growthDelay) == 0) {
                    world.setBlockState(pos, this.getStateFromMeta(meta + 1));
                    world.setBlockState(pos.down(), this.getStateFromMeta(meta - 7));
                }
            } else if (meta < this.growthStages - 1 && world.rand.nextInt(this.growthDelay) == 0) {
                world.setBlockState(pos, this.getStateFromMeta(meta + 1));
                world.setBlockState(pos.up(), this.getStateFromMeta(meta + 9));
            }
        }
    }

    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        if (world instanceof World) {
            World actualWorld = (World) world;
            int metadata = this.getMetaFromState(state);
            ArrayList<ItemStack> ret = new ArrayList<>();

            if (!this.isTopBlock(metadata)) {
                ret.add(new ItemStack(this, 1, 0));
                return ret;
            } else {
                metadata -= 8;
                if (metadata >= this.growthStages - 1) {
                    ret.add(new ItemStack(TBItems.briar_seedbag, 1 + actualWorld.rand.nextInt(4), 0));
                    return ret;
                } else {
                    return ret;
                }
            }
        } else {
            return super.getDrops(world, pos, state, fortune);
        }
    }

    public static enum EnumBlockHalf implements IStringSerializable {
        UPPER,
        LOWER;

        public String toString() {
            return this.getName();
        }

        public String getName() {
            return this == UPPER ? "upper" : "lower";
        }
    }
}

