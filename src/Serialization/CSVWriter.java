package Serialization;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CSVWriter {
    BufferedWriter streamBuffer;

    public CSVWriter() {

    }

    public CSVWriter(OutputStream outputStream) {
        this();
        streamBuffer = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public void write(String str) throws IOException {
        streamBuffer.write(str);
    }

    public void newLine() throws IOException {
        streamBuffer.newLine();
    }

    public void close() throws IOException {
        streamBuffer.close();
    }
}
