import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserTests {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            list.add(user);
        }
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Airis\\IdeaProjects\\portfolio\\hw-9\\target\\users.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);

            FileInputStream fis = new FileInputStream("C:\\Users\\Airis\\IdeaProjects\\portfolio\\hw-9\\target\\users.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List list1 = (List) ois.readObject();
            ois.close();
            System.out.println(list1);
/*
            User addressUser = new User();
            List<String> listOfAddress = new ArrayList<>();
            for (String i = list.toString(); i = list.get(9); i++)
            {
                System.out.println(listOfAddress.get(i));
            }
*/

    }
    @Test
    public void method() throws IOException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            list.add(user);
        }
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Airis\\IdeaProjects\\portfolio\\hw-9\\target\\users.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);

        FileInputStream fis = new FileInputStream("C:\\Users\\Airis\\IdeaProjects\\portfolio\\hw-9\\target\\users.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List list1 = (List) ois.readObject();
        ois.close();
        System.out.println(list1);
    }
    @Test
    public void method1() {
        List<User.Address> listAddress = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User.Address userAddress = new User.Address();
            listAddress.add(userAddress);
        }
        System.out.println(listAddress);
    }


    User user = new User();



    @Test
    void serialization() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Airis\\IdeaProjects\\portfolio\\hw-9\\target\\users1.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.close();
    }

    @Test
    void deserialization() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Airis\\IdeaProjects\\portfolio\\hw-9\\target\\users1.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User user2 = (User) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(user2);
    }


}