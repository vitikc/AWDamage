package me.vitikc.awdamagemod.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import me.vitikc.awdamagemod.draw.AWEnumMessages;
import me.vitikc.awdamagemod.listeners.AWRenderGuiEvent;


public class AWMessageHandler implements IMessageHandler<AWMessage, IMessage> {

    // Message handler

    @Override
    public IMessage onMessage(AWMessage message, MessageContext ctx) {
        int toShow = message.toSend;
        AWEnumMessages msg = AWEnumMessages.valueOf(toShow);
        AWRenderGuiEvent.renderColor = msg.color;
        AWRenderGuiEvent.setText(msg.toString());
        return null;
    }
}
