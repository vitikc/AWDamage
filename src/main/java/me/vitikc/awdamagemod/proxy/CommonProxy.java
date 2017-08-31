package me.vitikc.awdamagemod.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import me.vitikc.awdamagemod.config.AWValuesManager;
import me.vitikc.awdamagemod.listeners.AWPlayerDamageEvent;
import me.vitikc.awdamagemod.packet.AWMessage;
import me.vitikc.awdamagemod.packet.AWMessageHandler;
import me.vitikc.awdamagemod.packet.AWPacketHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    // Server side

    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {
        AWPacketHandler.INSTANCE.registerMessage(AWMessageHandler.class, AWMessage.class, AWValuesManager.CLIENT_MSG_DISCRIMINATOR, Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(new AWPlayerDamageEvent());
    }


    public void postInit(FMLPostInitializationEvent e) {

    }
}
