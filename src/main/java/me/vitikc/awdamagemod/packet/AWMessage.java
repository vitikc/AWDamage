package me.vitikc.awdamagemod.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class AWMessage implements IMessage {
    // Message for sending to pipeline

    public int toSend;

    public AWMessage(){ }

    public AWMessage(int toSend){
        this.toSend = toSend;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        toSend = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(toSend);
    }

}
