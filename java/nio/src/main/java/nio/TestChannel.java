package nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class TestChannel {

    //使用非直接缓冲区与通道复制文件
    @Test
    public void test1()throws Exception{
        FileInputStream fileInputStream = new FileInputStream("1.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("2.jpg");
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(1024);
        while (inputStreamChannel.read(bb) != -1){
            bb.flip();
            outputStreamChannel.write(bb);
            bb.clear();
        }
        outputStreamChannel.close();
        inputStreamChannel.close();
        fileOutputStream.close();
        fileInputStream.close();
    }

    @Test
    public void test2() throws Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);
        inChannel.close();
        outChannel.close();
    }

    @Test
    public void test3()throws Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);
        inChannel.transferTo(0,inChannel.size(),outChannel);
        inChannel.close();
        outChannel.close();
    }


    @Test
    public void test4() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt","r");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(100);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
        channel.read(new ByteBuffer[]{byteBuffer1,byteBuffer2});
        System.out.println(new String(byteBuffer1.array(),0,byteBuffer1.limit()));
        System.out.println(new String(byteBuffer2.array(),0,byteBuffer1.limit()));
    }

    @Test
    public void test5() throws Exception{
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = stringCharsetSortedMap.entrySet();
        for (Map.Entry<String,Charset> entry:entries
             ) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    @Test
    public void test6() throws Exception{
//        Charset cs = Charset.forName("UTF-8");
//        CharsetEncoder charsetEncoder = cs.newEncoder();
//        CharsetDecoder charsetDecoder = cs.newDecoder();
//        CharBuffer charBuffer = CharBuffer.allocate(1024);
//        charBuffer.put("哈哈哈哈哈哈哈哈哈哈哈哈哈哈！");
//        charBuffer.flip();
//        System.out.println(charBuffer.limit());
//        ByteBuffer byteBuffer = ByteBuffer.allocate((charBuffer.limit() + 1)*3);
//        System.out.println(byteBuffer.capacity());
//        byteBuffer = charsetEncoder.encode(charBuffer);
//        System.out.println(byteBuffer.capacity());
//        for (int i = 0;i<byteBuffer.limit();i++){
//            System.out.println(byteBuffer.get());
//        }
        final byte[] b1 = new byte[10];
        byte[] b2 = b1;
        b2[0] = 1;
        b1[0] = 2;
        String s1 = new String("1111");
        s1.replace("1","2");
    }
}
