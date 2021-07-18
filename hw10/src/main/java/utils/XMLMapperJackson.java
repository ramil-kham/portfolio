package utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;


public class XMLMapperJackson {
    private XmlMapper mapper = new XmlMapper();

    public <T> T deserializer(File file, Class clazz) {
        try {
            return (T)mapper.readValue(file, clazz);
        } catch (IOException exception) {
            exception.printStackTrace();
      }
        return null;
    }
    public void serializer(File file, Object o) {
        try {
            mapper.writeValue(file, o);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
