package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class JSONMapperJackson {
    private ObjectMapper objectMapper = new ObjectMapper();

    public void serializeJSON(File file, Object o) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(file, o);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public <T> T deserialization(File file, Class clazz) {
        try {
            return (T) objectMapper.readValue(file, clazz);
        } catch (IOException exception) {
            exception.printStackTrace();
        } return null;
    }
}
