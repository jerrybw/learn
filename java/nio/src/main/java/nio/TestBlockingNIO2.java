package nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TestBlockingNIO2 {

    public static void main(String[] args) throws Exception{
        //1.打开通道
        DatagramChannel datagramChannel = DatagramChannel.open();

        //2.设置通道为非阻塞
        datagramChannel.configureBlocking(false);

        //3.获取缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.next();
            //5.向缓冲区写入数据
            byteBuffer.put((LocalDateTime.now().toString()+":\n"+str).getBytes());
            //6.切换模式
            byteBuffer.flip();
            //7.发送数据
            datagramChannel.send(byteBuffer,new InetSocketAddress("127.0.0.1",8989));
            //8.重置缓冲区
            byteBuffer.clear();
        }
        //9.关闭通道
        datagramChannel.close();
    }

    @Test
    public void receive() throws Exception{

        //1.打开通道
        DatagramChannel datagramChannel = DatagramChannel.open();

        //2.设置通道为非阻塞
        datagramChannel.configureBlocking(false);

        //3.绑定端口号
        datagramChannel.bind(new InetSocketAddress(8989));

        //4.打开选择器
        Selector selector = Selector.open();

        //5.将通道已“读取事件”注册到选择器中
        datagramChannel.register(selector,SelectionKey.OP_READ);

        //6.判断当有就绪的通道时进行下一步操作
        while (selector.select() > 0){
            //7.获取就绪的通道所在的SelectionKey的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //8.使用迭代器遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //9.判断SelectionKey的类型，如果是可读类型，打印输出通道内的内容
                if(selectionKey.isReadable()){
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    datagramChannel.receive(byteBuffer);
                    System.out.println(new String(byteBuffer.array(),0,byteBuffer.remaining()));
                }
                //10.调用结束，将已操作完的SelectionKey从set中移除
                iterator.remove();
            }
        }
    }
}
