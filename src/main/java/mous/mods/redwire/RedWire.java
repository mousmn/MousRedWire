package mous.mods.redwire;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

@Mod(modid = RedWire.MODID, version = RedWire.VERSION, name = RedWire.NAME)
public class RedWire {

	public static final String MODID = "redWire";
	public static final String VERSION = "1.0";
	public static final String NAME = "MousRedWire";
	
	public static Block redWireConnectorBlock;
	public static Item redWireWire;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		redWireConnectorBlock = new BlockRedWireConnector(true);
		GameRegistry.registerBlock(redWireConnectorBlock, "redWireConnectorBlock");
		
		redWireWire = new ItemRedWireWire();
		GameRegistry.registerItem(redWireWire, "redWireWire");
	}

	@EventHandler
	public void init(FMLInitializationEvent event){
		
	}
}
