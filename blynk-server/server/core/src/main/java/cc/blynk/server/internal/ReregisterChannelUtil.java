package cc.blynk.server.internal;

import cc.blynk.server.core.model.auth.Session;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 17.10.15.
 */
public final class ReregisterChannelUtil {

    private ReregisterChannelUtil() {
    }

    public static void reRegisterChannel(ChannelHandlerContext ctx,
                                         Session session, ChannelFutureListener completeHandler) {
        ChannelFuture cf = ctx.deregister();
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) {
                session.initialEventLoop.register(channelFuture.channel()).addListener(completeHandler);
            }
        });
    }

}
