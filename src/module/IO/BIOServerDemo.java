package module.IO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bk
 */
public class BIOServerDemo {
    static AtomicInteger incr = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        ThreadFactory threadFactory = newThreadFactory();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(3, 3, 0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        try(ServerSocket serverSocket = new ServerSocket(6666)) {
           for (;;) {
                Socket socket = serverSocket.accept();
                executorService.execute(()->{
                    handler(socket);
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handler(Socket socket) {
        try(InputStream inputStream = socket.getInputStream()) {
            byte[] bytes = new byte[1024];
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ":" + new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ThreadFactory newThreadFactory() {
        return (Runnable runnable) ->{
            Thread thread = new Thread(runnable);
            thread.setName("thread-" + incr.getAndIncrement());
            return thread;
        };
    }
}
