package create.simpleFactory;

/**
 * Created by jerry on 2017/12/30.
 */
public class SimpleFactoryPattern {
    public static void main(String[] args) {
        Phone iphoneX = PhoneFactory.producePhone("IphoneX");
        iphoneX.call();
        iphoneX.saySomething();

        Phone honor9 = PhoneFactory.producePhone("Honor9");
        honor9.call();
        honor9.saySomething();
    }
}
abstract class Phone{
    public void call(){
        System.out.println("打电话");
    }

    public abstract void saySomething();
}

class IphoneX extends Phone{

    public IphoneX(){
        System.out.println("生产一个IphoneX");
    }

    public void saySomething() {
        System.out.println("Hello,I`m Siri!");
    }
}

class Honor9 extends Phone{

    public Honor9(){
        System.out.println("生产一个华为荣耀9");
    }

    public void saySomething() {
        System.out.println("你好，我是小E！");
    }
}

class PhoneFactory{

    public static Phone producePhone(String phoneName){
        if(phoneName!=null ){
            if(phoneName.equals("IphoneX")){
                return new IphoneX();
            }else if(phoneName.equals("Honor9")){
                return new Honor9();
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

}
