package module.IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author bk
 */
public class NIOServerDemo {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        try(ServerSocketChannel channel = ServerSocketChannel.open()){
            SocketAddress socketAddress = new InetSocketAddress(6666);
            channel.bind(socketAddress);
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int select = selector.select();
                if (select > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey next = iterator.next();
                        if (next.isAcceptable()) {
                            SocketChannel accept = channel.accept();
                            accept.configureBlocking(false);
                            accept.register(selector,SelectionKey.OP_READ);

                            System.out.println(accept.getRemoteAddress() + " 上线");
                        }
                        if (next.isReadable()) {
                            SocketChannel readChannel = (SocketChannel) next.channel();
                            ByteBuffer allocate = ByteBuffer.allocate(1024);
                            int count = readChannel.read(allocate);
                            if (count > 0) {
                                String readContent = new String(allocate.array());
                                System.out.println("from client:" + readContent.strip());

                                // 向其他客户端转发
                                Set<SelectionKey> keys = selector.keys();
                                for (SelectionKey key : keys) {
                                    Channel nextChannel = key.channel();
                                    if (nextChannel instanceof SocketChannel && key != next) {
                                        SocketChannel socketChannel = (SocketChannel) nextChannel;
                                        ByteBuffer wrap = ByteBuffer.wrap(readContent.getBytes());
                                        socketChannel.write(wrap);
                                    }
                                }
                            }
                        }
                        iterator.remove();
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
