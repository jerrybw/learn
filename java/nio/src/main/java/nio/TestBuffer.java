package nio;

import org.junit.Test;

import java.nio.ByteBuffer;

public class TestBuffer {

    @Test
    public void test1() {
        ByteBuffer bb = ByteBuffer.allocate(10);
        String str = "abcde";
        bb.put(str.getBytes());
        System.out.println(bb.capacity());
        System.out.println(bb.limit());
        System.out.println(bb.position());
        byte[] bytes = new byte[bb.limit()];
        bb.flip();
        bb.get(bytes, 0, bb.limit());
        for (byte b : bytes) {
            System.out.println(b);
        }
        System.out.println(new String(bytes));
    }

    @Test
    public void test2(){
        ByteBuffer bb = ByteBuffer.allocate(10);
        bb.put("abcdef".getBytes());
        byte[] dst = new byte[bb.limit()];
        bb.flip();
        bb.get(dst,0,2);
        System.out.println(bb.position());
        bb.mark();
        bb.get(dst,2,2);
        System.out.println(bb.position());
        bb.reset();
        System.out.println(bb.position());
        System.out.println(bb.remaining());
    }

    @Test
    public void test3(){
        ByteBuffer bb = ByteBuffer.allocateDirect(1024);
        System.out.println(bb.isDirect());
    }
}
