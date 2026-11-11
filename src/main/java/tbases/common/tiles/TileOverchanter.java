package tbases.common.tiles;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import tbases.common.inventory.ContainerOverchanter;
import tbases.util.NBTUtils;
import tbases.util.MathUtils;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.IInteractWithCaster;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.events.EssentiaHandler;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TileOverchanter extends TileEntityLockable implements IInventory, IInteractWithCaster, ITickable {
    public ItemStack inventory;
    private int enchantingTime;
    private boolean xpAbsorbed;
    private boolean isEnchantingStarted;
    private int syncTimer;
    private int ticksExisted;

    public TileOverchanter() {
        this.inventory = ItemStack.EMPTY;
    }

    public int getSizeInventory() {
        return 1;
    }

    public void update() {
        ++this.ticksExisted;
        if (this.syncTimer <= 0) {
            this.syncTimer = 100;
            NBTTagCompound tg = new NBTTagCompound();
            tg.setInteger("0", this.enchantingTime);
            tg.setBoolean("1", this.xpAbsorbed);
            tg.setBoolean("2", this.isEnchantingStarted);
            tg.setInteger("x", this.pos.getX());
            tg.setInteger("y", this.pos.getY());
            tg.setInteger("z", this.pos.getZ());
        } else {
            --this.syncTimer;
        }

        if (this.inventory.isEmpty()) {
            this.isEnchantingStarted = false;
            this.xpAbsorbed = false;
            this.enchantingTime = 0;
        } else if (this.isEnchantingStarted && this.ticksExisted % 20 == 0) {
            this.world.playSound((double)this.pos.getX(), (double)this.pos.getY(), (double)this.pos.getZ(), SoundsTC.infuserstart, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            if (EssentiaHandler.drainEssentia(this, Aspect.MAGIC, (EnumFacing)null, 8, false, 8)) {
                ++this.enchantingTime;
                if (this.enchantingTime >= 16 && !this.xpAbsorbed) {
                    List<EntityPlayer> players = this.world.getEntitiesWithinAABB(EntityPlayer.class, (new AxisAlignedBB((double)this.pos.getX(), (double)this.pos.getY(), (double)this.pos.getZ(), (double)(this.pos.getX() + 1), (double)(this.pos.getY() + 1), (double)(this.pos.getZ() + 1))).grow((double)6.0F, (double)3.0F, (double)6.0F));
                    if (!players.isEmpty()) {
                        for(int i = 0; i < players.size(); ++i) {
                            EntityPlayer p = (EntityPlayer)players.get(i);
                            if (p.experienceLevel >= 30) {
                                p.attackEntityFrom(DamageSource.MAGIC, 8.0F);
                                this.world.playSound((double)this.pos.getX(), (double)this.pos.getY(), (double)this.pos.getZ(), SoundsTC.zap, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                                p.experienceLevel -= 30;
                                this.xpAbsorbed = true;
                                break;
                            }
                        }
                    }
                }

                if (this.xpAbsorbed && this.enchantingTime >= 32) {
                    NBTTagList nbttaglist = this.inventory.getEnchantmentTagList();

                    for(int i = 0; i < nbttaglist.tagCount(); ++i) {
                        int enchId = this.findEnchantment(this.inventory);
                        Enchantment ench = Enchantment.getEnchantmentByID(enchId);
                        NBTTagCompound tag = nbttaglist.getCompoundTagAt(i);
                        if (tag != null && Enchantment.getEnchantmentByID(tag.getShort("id")) == ench) {
                            tag.setShort("lvl", (short)(tag.getShort("lvl") + 1));
                            NBTTagCompound stackTag = NBTUtils.getStackTag(this.inventory);
                            if (!stackTag.hasKey("overchants")) {
                                stackTag.setIntArray("overchants", new int[]{enchId});
                            } else {
                                int[] arrayInt = stackTag.getIntArray("overchants");
                                int[] newArrayInt = new int[arrayInt.length + 1];
                                System.arraycopy(arrayInt, 0, newArrayInt, 0, arrayInt.length);
                                newArrayInt[newArrayInt.length - 1] = enchId;
                                stackTag.setIntArray("overchants", newArrayInt);
                            }
                            break;
                        }
                    }

                    this.isEnchantingStarted = false;
                    this.xpAbsorbed = false;
                    this.enchantingTime = 0;
                    this.world.playSound((double)this.pos.getX(), (double)this.pos.getY(), (double)this.pos.getZ(), SoundsTC.wand, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                }
            } else {
                --this.enchantingTime;
            }
        }

    }

    private boolean canStartEnchanting() {
        if (!this.isEnchantingStarted && !this.inventory.isEmpty() && this.inventory.getEnchantmentTagList().tagCount() > 0) {
            return this.findEnchantment(this.inventory) != -1;
        } else {
            return false;
        }
    }

    private int findEnchantment(ItemStack enchated) {
        NBTTagCompound stackTag = NBTUtils.getStackTag(this.inventory);
        Map<Enchantment, Integer> ench = EnchantmentHelper.getEnchantments(enchated);
        Set<Enchantment> keys = ench.keySet();
        Iterator<Enchantment> $i = keys.iterator();

        while($i.hasNext()) {
            int i = Enchantment.getEnchantmentID((Enchantment)$i.next());
            if (!stackTag.hasKey("overchants")) {
                return i;
            }

            int[] overchants = stackTag.getIntArray("overchants");
            if (!MathUtils.arrayContains(overchants, i)) {
                return i;
            }
        }

        return -1;
    }

    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.enchantingTime = pkt.getNbtCompound().getInteger("0");
        this.xpAbsorbed = pkt.getNbtCompound().getBoolean("1");
        this.isEnchantingStarted = pkt.getNbtCompound().getBoolean("2");
    }

    public boolean isEmpty() {
        return false;
    }

    public ItemStack getStackInSlot(int index) {
        return this.inventory;
    }

    public ItemStack decrStackSize(int index, int count) {
        if (!this.inventory.isEmpty()) {
            if (this.inventory.getMaxStackSize() <= count) {
                ItemStack itemstack = this.inventory;
                this.inventory = ItemStack.EMPTY;
                this.markDirty();
                return itemstack;
            } else {
                ItemStack itemstack = this.inventory.splitStack(count);
                if (this.inventory.getMaxStackSize() == 0) {
                    this.inventory = ItemStack.EMPTY;
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    public ItemStack removeStackFromSlot(int index) {
        ItemStack old = this.inventory.copy();
        this.inventory = ItemStack.EMPTY;
        return old;
    }

    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory = stack;
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return player.dimension == this.world.provider.getDimension() && !this.world.isAirBlock(this.pos);
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return stack.hasTagCompound() && stack.getEnchantmentTagList() != null;
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.enchantingTime = compound.getInteger("enchTime");
        this.xpAbsorbed = compound.getBoolean("xpAbsorbed");
        this.isEnchantingStarted = compound.getBoolean("enchStarted");
        this.inventory = new ItemStack(compound.getCompoundTag("itm"));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("enchTime", this.enchantingTime);
        compound.setBoolean("xpAbsorbed", this.xpAbsorbed);
        compound.setBoolean("enchStarted", this.isEnchantingStarted);
        compound.setTag("itm", this.inventory.serializeNBT());
        return compound;
    }

    public boolean onCasterRightClick(World world, ItemStack itemStack, EntityPlayer entityPlayer, BlockPos blockPos, EnumFacing enumFacing, EnumHand enumHand) {
        if (this.canStartEnchanting() && entityPlayer.isSneaking()) {
            this.isEnchantingStarted = true;
            entityPlayer.swingArm(EnumHand.MAIN_HAND);
            this.syncTimer = 0;
            this.world.playSound((double)this.pos.getX(), (double)this.pos.getY(), (double)this.pos.getZ(), SoundsTC.craftstart, SoundCategory.BLOCKS, 0.5F, 1.0F, false);
            return true;
        } else {
            return false;
        }
    }

    public int getField(int id) {
        return 0;
    }

    public void setField(int id, int value) {
    }

    public int getFieldCount() {
        return 0;
    }

    public void clear() {
        this.inventory = ItemStack.EMPTY;
    }

    public String getName() {
        return "thaumicbases.overchanter";
    }

    @Nullable
    public ITextComponent getDisplayName() {
        return null;
    }

    public boolean hasCustomName() {
        return false;
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerOverchanter(playerInventory, this);
    }

    public String getGuiID() {
        return "thaumicbases:overchanter";
    }
}