package create.abstractFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jerry on 2018/1/5.
 */
public class AbstractFactoryPattern {
    public static void main(String[] args) throws Exception {
        Factory factory = getFactory();
        OperationController operationController = factory.getOperationController();
        operationController.operationControl();
        InterfaceController interfaceController = factory.getInterfaceController();
        interfaceController.interfaceControl();
    }

    public static Factory getFactory() throws Exception{
        InputStream inputStream = AbstractFactoryPattern.class.getResourceAsStream("/abstractFactory.propertie");
        Properties properties = new Properties();
        properties.load(inputStream);
        String factoryName = properties.getProperty("factoryName");
        Class clazz = Class.forName("create.abstractFactory."+factoryName);
        return (Factory) clazz.newInstance();
    }

}
abstract class OperationController{
    public abstract void operationControl();
}

class SymbianOperationController extends OperationController{

    public void operationControl() {
        System.out.println("塞班手机操作控制");
    }
}

class AndroidOperationController extends OperationController{

    public void operationControl() {
        System.out.println("安卓手机操作控制");
    }
}

class WinOperationController extends OperationController{

    public void operationControl() {
        System.out.println("windows手机操作控制");
    }
}

abstract class InterfaceController{
    public abstract void interfaceControl();
}

class SymbianInterfaceController extends InterfaceController{

    public void interfaceControl() {
        System.out.println("塞班手机界面控制");
    }
}
class AndroidInterfaceController extends InterfaceController{

    public void interfaceControl() {
        System.out.println("安卓手机界面控制");
    }
}
class WinInterfaceController extends InterfaceController{

    public void interfaceControl() {
        System.out.println("windows手机界面控制");
    }
}

abstract class Factory{
    public abstract OperationController getOperationController();

    public abstract InterfaceController getInterfaceController();
}

class SymbianFactory extends Factory{
    public OperationController getOperationController() {
        return new SymbianOperationController();
    }

    public InterfaceController getInterfaceController() {
        return new SymbianInterfaceController();
    }
}
class AndroidFactory extends Factory{
    public OperationController getOperationController() {
        return new AndroidOperationController();
    }

    public InterfaceController getInterfaceController() {
        return new AndroidInterfaceController();
    }
}

class WinFactory extends Factory{
    public OperationController getOperationController() {
        return new WinOperationController();
    }

    public InterfaceController getInterfaceController() {
        return new WinInterfaceController();
    }
}