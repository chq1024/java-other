package module.IO;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author bk
 */
public class FileNIO {

    public static void main(String[] args) throws IOException {
//        ByteBuffer buffer = ByteBuffer.allocate(512);
//        File file1 = new File("D:\\bk_workspace\\leetcode\\read.txt");
//        File file2 = new File("D:\\bk_workspace\\leetcode\\write.txt");
//        try(FileInputStream fileInputStream = new FileInputStream(file1);FileOutputStream fileOutputStream = new FileOutputStream(file2)){
//            FileChannel channel01 = fileInputStream.getChannel();
//            FileChannel channel02 = fileOutputStream.getChannel();
//            while (true) {
//                buffer.clear();
//                int read = channel01.read(buffer);
//                if (read == -1) {
//                    break;
//                }
//                buffer.flip();
//                channel02.write(buffer);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        try (RandomAccessFile raf = new RandomAccessFile("D:\\bk_workspace\\leetcode\\read.txt", "rw")) {
            FileChannel channel = raf.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
            map.put("hello".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
