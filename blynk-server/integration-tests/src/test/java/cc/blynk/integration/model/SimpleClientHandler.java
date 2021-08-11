package cc.blynk.integration.model;

import cc.blynk.server.core.protocol.model.messages.MessageBase;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 1/31/2015.
 */
public class SimpleClientHandler extends SimpleChannelInboundHandler<MessageBase> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, MessageBase msg) throws Exception {
        //System.out.println(msg);
    }

}