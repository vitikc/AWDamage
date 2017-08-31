package me.vitikc.awdamagemod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.vitikc.awdamagemod.proxy.CommonProxy;

@Mod(modid = AWDamage.MODID, version = AWDamage.VERSION)
public class AWDamage
{
    public static final String MODID = "AWDamage";
    public static final String VERSION = "1.1_Release";

    @SidedProxy(clientSide = "me.vitikc.awdamagemod.proxy.ClientProxy", serverSide = "me.vitikc.awdamagemod.proxy.CommonProxy")
    public static CommonProxy proxy;


    @Mod.Instance
    public static AWDamage instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}
