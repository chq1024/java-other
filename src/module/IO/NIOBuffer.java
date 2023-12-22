package module.IO;

import java.nio.IntBuffer;

/**
 * @author bk
 */
public class NIOBuffer {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        buffer.put(4);

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
