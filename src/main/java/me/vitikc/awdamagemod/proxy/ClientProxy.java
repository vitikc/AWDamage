package me.vitikc.awdamagemod.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.vitikc.awdamagemod.listeners.AWRenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    // Client stuff
    @Override
    public void preInit(FMLPreInitializationEvent e) {

    }

    @Override
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new AWRenderGuiEvent());
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {

    }
}
