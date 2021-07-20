import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUser {
        User user = new User();
        @Test
        void name1 () throws IOException {
            XMLMapperJavaxXml mapperJavaxXml = new XMLMapperJavaxXml(User.class);
       Writer writer = new FileWriter("target\\testXml.xml");
       mapperJavaxXml.marshall(user, writer);
        writer.flush();
        writer.close();

            User user2 = mapperJavaxXml.unmarshall(new FileReader("target\\testXml.xml"));
            System.out.println(user2);
        }

        @Test
        void name2 () {
            XMLMapperJackson xmlMapperJackson = new XMLMapperJackson();

            xmlMapperJackson.serializer(new File("target\\testXml1.xml"), user);

        User user2 = (User) xmlMapperJackson.deserializer(new File("target\\testXml.xml"), User.class);
        System.out.println(user2);
        }

        @Test
        void name34 () {
            JSONMapperJackson mapperJackson = new JSONMapperJackson();
            mapperJackson.serializeJSON(new File("target\\testJson.json"), user);
        User user3 = (User) mapperJackson.deserialization(new File("target\\testJson.json"), User.class);
        System.out.println(user3);
        }

        @Test
        void TestWithXStream () throws IOException {
            XMLMapperXStream xmlMapperXStream = new XMLMapperXStream();
            xmlMapperXStream.serializeXML(user);
            User userFromXML = xmlMapperXStream.deserializeXML("target\\testXmlWithXStream.xml");
            System.out.println(userFromXML);
            System.out.println(user);
        Assertions.assertEquals(user, userFromXML);
        }
        @Test
        void TestWithGson () throws IOException {
            JSONMapperGSON jsonMapperGSON = new JSONMapperGSON();
            jsonMapperGSON.serializeJSON(user);

            Reader reader = new FileReader("target\\testXmlWithGSON.json");
            int read;
            while ((read = reader.read()) != -1) {
                System.out.print((char) read);
            }
            User userFromJson = jsonMapperGSON.deserializeJSON(reader, User.class);
            reader.close();
            System.out.println(userFromJson);
            System.out.println(user);
        Assertions.assertEquals(user, userFromJson);
        }
        @Test
        void TestWithXStreamForList () throws IOException {
            List<User> listOfUsers = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                User userList = new User();
                listOfUsers.add(userList);
            }
            XMLMapperXStream xmlMapperXStream = new XMLMapperXStream();
            xmlMapperXStream.serializeXMLForList(listOfUsers);
            List<User> userListFromXML = xmlMapperXStream.deserializeXML("target\\testXmlWithXStreamForList.xml");
            System.out.println(userListFromXML);
            System.out.println(listOfUsers);
        Assertions.assertEquals(listOfUsers, userListFromXML);
        }
    @Test
    void TestWithGsonForList () throws IOException {
        List<User> listOfUsers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User userList = new User();
            listOfUsers.add(userList);
        }
        JSONMapperGSON jsonMapperGSON = new JSONMapperGSON();
        jsonMapperGSON.serializeJSONForList(listOfUsers);

        Reader reader = new FileReader("target\\testXmlWithGSONForList.json");
        int read;
        while ((read = reader.read()) != -1) {
            System.out.print((char) read);
        }
        List<User> userFromJsonForList = jsonMapperGSON.deserializeJSON(reader, ListOfUsers.class);
        reader.close();
        System.out.println(userFromJsonForList);
        System.out.println(listOfUsers);
        Assertions.assertEquals(user, userFromJsonForList);
    }
}
