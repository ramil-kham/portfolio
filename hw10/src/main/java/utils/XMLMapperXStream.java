package utils;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.List;

public class XMLMapperXStream {
    XStream xs = new XStream();

    public void serializeXML(Object o) throws IOException {
        Writer writer = new FileWriter("target\\testXmlWithXStream.xml");
        xs.toXML(o, writer);
        writer.flush();
        writer.close();
    }
    public void serializeXMLForList(Object o) throws IOException {
        Writer writer = new FileWriter("target\\testXmlWithXStreamForList.xml");
        xs.toXML(o, writer);
        writer.flush();
        writer.close();
    }
    public <T> T deserializeXML(String fileName) {
        return (T) xs.fromXML(new File(fileName));
    }
}