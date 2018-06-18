package nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class TestBlockingNIO {

    @Test
    public void client() throws Exception{
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
        FileChannel fileChannel = FileChannel.open(Paths.get("1.jpg"),StandardOpenOption.READ);
        fileChannel.transferTo(0,fileChannel.size(),socketChannel);
        fileChannel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8989));
        SocketChannel accept = serverSocketChannel.accept();
        FileChannel fileChannel = FileChannel.open(Paths.get("4.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        byte[] bytes = "收到客户端传过来的数据".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (accept.read(byteBuffer)!= -1){
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        byteBuffer.put(bytes);
        byteBuffer.flip();
        accept.write(byteBuffer);
        fileChannel.close();
        accept.close();
        serverSocketChannel.close();
    }


    @Test
    public void client2() throws Exception{
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
        FileChannel fileChannel = FileChannel.open(Paths.get("1.jpg"),StandardOpenOption.READ);
        fileChannel.transferTo(0,fileChannel.size(),socketChannel);
        socketChannel.shutdownOutput();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byte[] b1 = new byte[0];
        byte[] tmp = null;
        while (socketChannel.read(byteBuffer) != -1){
            tmp = new byte[b1.length+byteBuffer.position()];
            byte[] array = byteBuffer.array();
            System.arraycopy(b1,0,tmp,0,b1.length);
            System.arraycopy(array,0,tmp,b1.length,byteBuffer.position());
            b1 = tmp;
            byteBuffer.clear();
        }
        System.out.println(new String(b1));
    }
}
