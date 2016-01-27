package mous.mods.redwire;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRedWireWire extends Item{
	public ItemRedWireWire(){
		setUnlocalizedName(RedWire.MODID + '_' + "redWireWire");
		setCreativeTab(CreativeTabs.tabRedstone);
		setTextureName(RedWire.MODID + ':' + "redWireWire");
	}
	
	// Create UseEvent
	// Check if block is Connector, else ignore
	// Hold those coords, if has coords make connection
	// FUTURE: check line of sight, distance?
	// Look through IE wire code for inspiration
	
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int a, float b, float c, float d){
		UseWireEvent event = new UseWireEvent(player, item, world, x, y, z);
		if (event.getResult() == Result.ALLOW){
			System.out.println("Allow.");
			return true;
		}
		
		Block block = world.getBlock(x, y, z);
		
		if (a != 0 && block == RedWire.redWireConnectorBlock){
			System.out.println("Use success.");
			return true;
		}
		else {
			return false;
		}
	}
}
