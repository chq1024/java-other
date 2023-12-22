package module.IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author bk
 */
public class NIOClientDemo {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        InetSocketAddress address = new InetSocketAddress(6666);
        try (SocketChannel socketChannel = SocketChannel.open(address)) {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            String adr = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(adr + " is ok...");


            new Thread(()->{
               while (true) {
                   try {
                       int readChannel = selector.select();
                       if (readChannel > 0) {
                           Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                           while (iterator.hasNext()) {
                               SelectionKey next = iterator.next();
                               if (next.isReadable()) {
                                   SocketChannel channel = (SocketChannel)next.channel();
                                   ByteBuffer buffer = ByteBuffer.allocate(1024);
                                   channel.read(buffer);
                                   String msg = new String(buffer.array());
                                   System.out.println(msg.trim());
                               }
                           }
                           iterator.remove();
                       }
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
            }).start();

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                socketChannel.write(ByteBuffer.wrap(line.getBytes()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
