package utils;

import com.google.gson.Gson;

import java.io.File;
import java.io.Reader;
import java.io.Writer;

public class JSONMapperGSON {
    Gson gson = new Gson();

    public void serializeJSON(Object o, Writer writer) {
        gson.toJson(o, writer);
    }
    public <T> T deserializeJSON(Reader reader, Class clazz) {
        return (T) gson.fromJson(reader, clazz);
    }
}
