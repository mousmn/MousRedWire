package mous.mods.redwire;

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
		return true;
	}
}
