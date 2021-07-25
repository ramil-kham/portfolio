package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.List;

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
    public  <T> T deserializeJSONForList(Reader reader, Type type) throws IOException {
//        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
//        List<User> list = new Gson().fromJson(reader, listType);
        return (T) gson.fromJson(reader, type);
    }
}
