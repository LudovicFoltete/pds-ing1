package org.ing1.pds;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.Writer;

public class SerializationImpl implements Serialization
{
    private static SerializationImpl ourInstance = new SerializationImpl();
    private ObjectMapper objectMapper;

    static SerializationImpl getInstance() {
        return ourInstance;
    }

    private SerializationImpl() {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        objectMapper = new ObjectMapper(jsonFactory);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public void write(Writer out, Object javaBeans) throws IOException {
        objectMapper.writeValue(out, javaBeans);
    }

    public <T> T read(String jsonData, Class<T> c) throws IOException {
       return objectMapper.readValue(jsonData, c);
    }
}
