package org.ing1.pds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class SerializationImpl implements Serialization
{
    private static SerializationImpl ourInstance = new SerializationImpl();
    private ObjectMapper objectMapper;

    public static SerializationImpl getInstance() {
        return ourInstance;
    }

    private SerializationImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public void write(Writer out, Object javaBeans) throws IOException {
        objectMapper.writeValue(out, javaBeans);
    }

    public Request read(String jsonData) throws IOException {
       return objectMapper.readValue(jsonData, Request.class);
    }
}
