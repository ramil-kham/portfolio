import org.junit.jupiter.api.Test;
import utils.*;

import java.io.*;
import java.text.ParseException;

public class TestUser {
   User user = new User();

   @Test
    void name1() throws IOException {
       XMLMapperJavaxXml mapperJavaxXml = new XMLMapperJavaxXml(User.class);
//       Writer writer = new FileWriter("target\\testXml.xml");
//       mapperJavaxXml.marshall(user, writer);
//        writer.flush();
//        writer.close();

        User user2 = mapperJavaxXml.unmarshall(new FileReader("target\\testXml.xml"));
       System.out.println(user2);
   }

    @Test
    void name2() {
       XMLMapperJackson xmlMapperJackson = new XMLMapperJackson();

                xmlMapperJackson.serializer(new File("target\\testXml1.xml"), user);

//        User user2 = (User) xmlMapperJackson.deserializer(new File("target\\testXml.xml"), User.class);
//        System.out.println(user2);
    }

    @Test
    void name34 () {
        JSONMapperJackson mapperJackson = new JSONMapperJackson();
        mapperJackson.serializeJSON(new File("target\\testJson.json"), user);
//        User user3 = (User) mapperJackson.deserialization(new File("target\\testJson.json"), User.class);
//        System.out.println(user3);
   }

    @Test
    void TestWithXStream() throws IOException {
        XMLMapperXStream xmlMapperXStream = new XMLMapperXStream();
//        Writer writer = new FileWriter("target\\testXmlWithXStream.xml");
//        xmlMapperXStream.serializeXML(user,writer);
//        writer.flush();
//        writer.close();

        xmlMapperXStream.deserializeXML(new File("target\\testXmlWithXStream.xml"));
        System.out.println(user);
    }
    @Test
    void TestWithGson() throws IOException {
        JSONMapperGSON jsonMapperGSON = new JSONMapperGSON();
        Writer writer = new FileWriter("target\\testXmlWithGSON.json");
        jsonMapperGSON.serializeJSON(user,writer);
        writer.flush();
        writer.close();
//        Reader reader = new FileReader("target\\testXmlWithGSON.json");
//        int read;
//        while ((read = reader.read()) !=-1) {
//            System.out.print((char) read);
//        }
//        jsonMapperGSON.deserializeJSON(reader, User.class);
//        reader.close();
    }
}
