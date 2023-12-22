package module.IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author bk
 */
public class NIOSocket {

    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
            serverSocketChannel.socket().bind(inetSocketAddress);

            ByteBuffer[] byteBuffers = new ByteBuffer[2];
            byteBuffers[0] = ByteBuffer.allocate(5);
            byteBuffers[1] = ByteBuffer.allocate(3);

            SocketChannel socketChannel = serverSocketChannel.accept();
            int messageLength = 8;
            while (true) {
                int byteRead = 0;

                while (byteRead < messageLength) {
                    long read = socketChannel.read(byteBuffers);
                    byteRead += read;
                    Arrays.stream(byteBuffers).map(byteBuffer -> "position:" + byteBuffer.position() + ",limit:" + byteBuffer.limit()).forEach(System.out::println);
                }

                Arrays.asList(byteBuffers).forEach(ByteBuffer::flip);

                long byteWrite = 0;
                while (byteWrite < messageLength) {
                    long write = socketChannel.write(byteBuffers);
                    byteWrite += write;
                }

                Arrays.asList(byteBuffers).forEach(ByteBuffer::clear);

                System.out.println("byteRead = " + byteRead + ", byteWrite = " + byteWrite + ", messagelength = " + messageLength);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
