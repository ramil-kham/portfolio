package utils;

import com.thoughtworks.xstream.XStream;

import java.io.*;

public class XMLMapperXStream {
    XStream xs = new XStream();

    public void serializeXML(Object o, Writer Writer) {
        xs.toXML(o, Writer);
    }
    public <T> T deserializeXML(File file) {
        return (T) xs.fromXML(file);
    }
}