package netty.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import netty.RetryPolicy;
import netty.TcpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author bk
 */
@ChannelHandler.Sharable
public class NettyReconnectChannelHandler extends ChannelInboundHandlerAdapter {

    private int retries = 0;
    private RetryPolicy retryPolicy;
    private TcpClient tcpClient;

    public NettyReconnectChannelHandler(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress() + "已连接~");
        retries = 0;
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(this);
        boolean allowRetry = getRetryPolicy().allowRetry(retries);
        if (allowRetry) {
            long sleepTimeMs = getRetryPolicy().getSleepTimeMs(retries);
            System.out.printf("Try to reconnect to the server after %dms. Retry count: %d.%n", sleepTimeMs, ++retries);

            final EventLoop eventLoop = ctx.channel().eventLoop();
            eventLoop.schedule(() -> {
                System.out.println("Reconnecting ...");
                tcpClient.connect();
            }, sleepTimeMs, TimeUnit.MILLISECONDS);
        }
    }

    private RetryPolicy getRetryPolicy() {
        if (this.retryPolicy == null) {
            this.retryPolicy = tcpClient.getExponentialBackOffRetry();
        }
        return retryPolicy;
    }

}
