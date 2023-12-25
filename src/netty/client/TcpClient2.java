package netty.client;

import netty.TcpClient;

/**
 * @author bk
 */
public class TcpClient2 {

    public static void main(String[] args) {
        TcpClient tcpClient = new TcpClient("192.168.1.60",6667);
        tcpClient.connect();
    }
}
