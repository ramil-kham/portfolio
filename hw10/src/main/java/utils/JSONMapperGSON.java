package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JSONMapperGSON {
    Gson gson = new Gson();
    public void serializeJSON(Object o) throws IOException {
        Writer writer = new FileWriter("target\\testXmlWithGSON.json");
        gson.toJson(o, writer);
        writer.flush();
        writer.close();
    }
    public  <T> T deserializeJSON(Reader reader, Class clazz) throws IOException {
        return (T) gson.fromJson(reader, clazz);
    }
    public void serializeJSONForList(Object o) throws IOException {
        Writer writer = new FileWriter("target\\testXmlWithGSONForList.json");
        gson.toJson(o, writer);
        writer.flush();
        writer.close();
    }
}
