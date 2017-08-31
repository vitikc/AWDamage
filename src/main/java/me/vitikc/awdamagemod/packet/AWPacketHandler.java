package me.vitikc.awdamagemod.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import me.vitikc.awdamagemod.config.AWValuesManager;

public class AWPacketHandler {
    // Handles network wrapper with created channel
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(AWValuesManager.CHANNEL);
}
