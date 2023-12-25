package netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import netty.TcpClient;

import java.nio.charset.StandardCharsets;

/**
 * @author bk
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    private TcpClient tcpClient;
    private NettyReconnectChannelHandler nettyReconnectChannelHandler;
    private NettyClientChannelHandler clientChannelHandler;

    public ClientChannelInitializer(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
        nettyReconnectChannelHandler = new NettyReconnectChannelHandler(this.tcpClient);
        clientChannelHandler = new NettyClientChannelHandler();
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new StringDecoder(StandardCharsets.UTF_8));
        pipeline.addLast("encoder", new StringEncoder(StandardCharsets.UTF_8));
        // 断线重连
        pipeline.addLast(nettyReconnectChannelHandler);
        // 业务处理
        pipeline.addLast(clientChannelHandler);
    }
}
