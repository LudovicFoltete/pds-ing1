package org.ing1.pds;

import java.io.IOException;
import java.io.Writer;

public interface Serialization {

    void write(Writer out, Object javaBeans) throws IOException;

    Request read(String jsonData) throws IOException;
}
