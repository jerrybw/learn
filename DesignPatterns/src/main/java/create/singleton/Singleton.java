package create.singleton;

/**
 * Created by jerry on 2018/1/6.
 */
public class Singleton {

    public static void main(String[] args) {
        HungrySingleton hungrySingleton1 = HungrySingleton.getInstance();
        HungrySingleton hungrySingleton2 = HungrySingleton.getInstance();

        System.out.println(hungrySingleton1 == hungrySingleton2);

        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();

        System.out.println(lazySingleton1 == lazySingleton2);

        IODHSingleton iodhSingleton1 = IODHSingleton.getInstance();
        IODHSingleton iodhSingleton2 = IODHSingleton.getInstance();

        System.out.println(iodhSingleton1 == iodhSingleton2);
    }
}

class HungrySingleton{

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return instance;
    }
}

class LazySingleton{

    private static LazySingleton instance;

    private LazySingleton(){

    }

    synchronized public static LazySingleton getInstance(){
        if(instance == null){
            synchronized (LazySingleton.class){
                if (instance == null){
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}

class IODHSingleton{
    private IODHSingleton(){}

    private static class IODHInner{
        private static IODHSingleton instance = new IODHSingleton();
    }

    public static IODHSingleton getInstance(){
        return IODHInner.instance;
    }
}
