package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import netty.client.ClientChannelInitializer;
import netty.client.NettyClientChannelHandler;
import netty.client.NettyReconnectChannelHandler;

import java.nio.charset.StandardCharsets;

/**
 * @author bk
 */
public class TcpClient {

    private Bootstrap bootstrap;
    private String host;
    private Integer port;
    private ExponentialBackOffRetry retryPolicy;

    public TcpClient(String host, int port) {
        this(host, port, new ExponentialBackOffRetry(1000, Integer.MAX_VALUE, 60 * 1000));
    }

    public TcpClient(String host, Integer port, ExponentialBackOffRetry exponentialBackOffRetry) {
        this.host = host;
        this.port = port;
        this.retryPolicy = exponentialBackOffRetry;
        init();
    }

    public ExponentialBackOffRetry getExponentialBackOffRetry() {
        return this.retryPolicy;
    }

    public void init() {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer(TcpClient.this));
    }

    public void connect() {
        synchronized (bootstrap) {
            ChannelFuture channelFuture = bootstrap.connect(host, port);
            channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
                if (channelFuture1.isSuccess()) {
                    System.out.println("连接服务器成功");
                } else {
                    channelFuture1.channel().pipeline().fireChannelInactive();
                }
            });
            Channel channel = channelFuture.channel();
            System.out.println("-------------address:" + channel.localAddress() + "-----------------");
            //客户端需要输入信息，创建一个扫描器
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String msg = scanner.nextLine();
//            //通过channel 发送到服务器端
//            channel.writeAndFlush(msg + "\r\n");
//        }
        }
    }
}
