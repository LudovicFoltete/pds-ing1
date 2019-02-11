package org.ing1.pds;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public interface Serialization {

    void write(Writer out, Object javaBeans) throws IOException;

    Object read(String jsonData) throws IOException;
}
