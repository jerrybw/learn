package create.factoryMethod;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jerry on 2017/12/30.
 */
public class FactoryMethodPattern {

    public static void main(String[] args) throws Exception {
        ImgReaderFactory factory = getFactory();
        factory.readImg();
    }

    public static ImgReaderFactory getFactory() throws Exception{
        InputStream inputStream = FactoryMethodPattern.class.getResourceAsStream("/factoryMethod.propertie");
        Properties properties = new Properties();
        properties.load(inputStream);
        String factoryName = properties.getProperty("factoryName");
        Class clazz = Class.forName("create.factoryMethod."+factoryName);
        return (ImgReaderFactory)clazz.newInstance();
    }
}

abstract class ImgReader{
    public abstract void readImg();
}

class GIFReader extends ImgReader{
    public void readImg() {
        System.out.println("读取gif图片");
    }
}

class JPGReader extends ImgReader{

    public void readImg() {
        System.out.println("读取jpg图片");
    }
}
abstract class ImgReaderFactory{

    public abstract ImgReader getImgReader();

    public void readImg(){
        ImgReader imgReader = this.getImgReader();
        imgReader.readImg();
    }
}

class GIFReaderFactory extends ImgReaderFactory{
    public ImgReader getImgReader() {
        return new GIFReader();
    }
}

class JPGReaderFactory extends ImgReaderFactory{
    public ImgReader getImgReader() {
        return new JPGReader();
    }
}
