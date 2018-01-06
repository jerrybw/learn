package create.prototype;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.nio.channels.Pipe;
import java.security.PublicKey;

/**
 * Created by jerry on 2018/1/6.
 */
public class Prototype {
    public static void main(String[] args) {
        Customer customer1 = new Customer();
        Address address = new Address();
        address.address = "北京";
        customer1.address = address;
        Customer customer2 = customer1.clone();
        Customer customer3 = customer1.deepClone();
        System.out.println(customer1 == customer2);
        System.out.println(customer1.address == customer2.address);
        System.out.println(customer1 == customer3);
        System.out.println(customer1.address == customer3.address);
    }
}

class Customer implements Cloneable, Serializable {
    public Address address;

    public Customer clone() {
        Customer customer = null;
        try {
            customer = (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制！");
        }
        return customer;
    }

    public Customer deepClone() {

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
            ByteArrayInputStream bai = new ByteArrayInputStream(bao.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bai);
            return (Customer)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                bao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

class Address implements Serializable {
    public String address;
}