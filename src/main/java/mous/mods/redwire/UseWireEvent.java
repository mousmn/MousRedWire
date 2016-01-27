package mous.mods.redwire;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
@Event.HasResult
public class UseWireEvent extends PlayerEvent{

	public final ItemStack item;
	public final World world;
	public final int x, y, z;
	
	public UseWireEvent(EntityPlayer player, ItemStack item, World world, int x, int y, int z){
		super(player);
		this.item = item;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
