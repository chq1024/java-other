package module.IO;

import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

/**
 * @author bk
 */
public class BIOClientDemo {

    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("192.168.1.60", 6666)){
            socket.setKeepAlive(true);
            socket.setSendBufferSize(2048);
            for (int i = 0; i < 10; i++) {
                String random = UUID.randomUUID().toString().replace("-","");
                String repeat = random.repeat((1024 / 32));
                byte[] bytes = repeat.getBytes();
                System.out.println(bytes.length);
                socket.getOutputStream().write(bytes);
            }
        }
    }
}
