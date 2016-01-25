package mous.mods.redwire;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedWireConnector extends Block {
	
	// True if we /lit/ like a redstone torch
	private boolean stateOn;
	public static Map field = new HashMap();

	public BlockRedWireConnector (boolean state){
		super(Material.rock);
		setBlockName(RedWire.MODID + '_' + "redWireConnectorBlock");
		setBlockTextureName(RedWire.MODID + ':' + "redWireConnectorBlock");
		setCreativeTab(CreativeTabs.tabRedstone);
		this.setTickRandomly(true);
		this.stateOn = state;
	}
	
	public int tickRate(World world){
		return 2;
	}
	
	public void onBlockAdded(World world, int x, int y, int z){
		if (world.getBlockMetadata(x,y,z) == 0){
			super.onBlockAdded(world, x, y, z);
			//System.out.println("Placed at " + x + ' ' + y + ' ' + z);
		}
		
		if (this.stateOn){
			world.notifyBlocksOfNeighborChange(x, y-1, z, this);
			world.notifyBlocksOfNeighborChange(x, y+1, z, this);
			world.notifyBlocksOfNeighborChange(x-1, y, z, this);
			world.notifyBlocksOfNeighborChange(x+1, y, z, this);
			world.notifyBlocksOfNeighborChange(x, y, z-1, this);
			world.notifyBlocksOfNeighborChange(x, y, z+1, this);
		}
	}
	
	public void breakBlock(World world, int x, int y, int z){
		if (this.stateOn){
			world.notifyBlocksOfNeighborChange(x, y-1, z, this);
			world.notifyBlocksOfNeighborChange(x, y+1, z, this);
			world.notifyBlocksOfNeighborChange(x-1, y, z, this);
			world.notifyBlocksOfNeighborChange(x+1, y, z, this);
			world.notifyBlocksOfNeighborChange(x, y, z-1, this);
			world.notifyBlocksOfNeighborChange(x, y, z+1, this);
		}
	}
	
	// Place dust on block, delete
	//public boolean isSideSolid(IBlockAccess iba, int x, int y, int z, ForgeDirection d){
	//	return true;
	//}
	
	public boolean canProvidePower(){
		// Visit for wire implementation
		return true;
	}
	
	// p?
	public int isProvidingWeakPower(IBlockAccess iba, int x, int y, int z, int p){
		if (!this.stateOn){
			return 0;
		}
		else{
			int i1 = iba.getBlockMetadata(x, y, z);
			// holy hell
			return 
				 i1 == 5 && p == 1 ? 0 : 
				(i1 == 2 && p == 3 ? 0 : 
				(i1 == 4 && p == 2 ? 0 : 
				(i1 == 1 && p == 5 ? 0 : 
				(i1 == 2 && p == 4 ? 0 : 15))));
		}
	}
	
	public int isProvidingStrongPower(IBlockAccess iba, int x, int y, int z, int p){
		return p == 0 ? this.isProvidingWeakPower(iba, x, y, z, p) : 0;
	}

	// hell if i know, copied from BlockRedstoneTorch
    private boolean func_150110_m(World p_150110_1_, int p_150110_2_, int p_150110_3_, int p_150110_4_)
    {
        int l = p_150110_1_.getBlockMetadata(p_150110_2_, p_150110_3_, p_150110_4_);
        return l == 5 && p_150110_1_.getIndirectPowerOutput(p_150110_2_, p_150110_3_ - 1, p_150110_4_, 0) ? true : (l == 3 && p_150110_1_.getIndirectPowerOutput(p_150110_2_, p_150110_3_, p_150110_4_ - 1, 2) ? true : (l == 4 && p_150110_1_.getIndirectPowerOutput(p_150110_2_, p_150110_3_, p_150110_4_ + 1, 3) ? true : (l == 1 && p_150110_1_.getIndirectPowerOutput(p_150110_2_ - 1, p_150110_3_, p_150110_4_, 4) ? true : l == 2 && p_150110_1_.getIndirectPowerOutput(p_150110_2_ + 1, p_150110_3_, p_150110_4_, 5))));
    }
    
    // Schedule block update
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block){
    	if(this.stateOn){
    		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
    		//System.out.println("Tick!");
    	}
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int a, float b, float c, float d){
    	if (world.isRemote){
    		return true;
    	}
    	else{
    		// Query/set connected state?
    		//System.out.println("Activate");
    		return true;
    	}
    }
}
