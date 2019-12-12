package Serialization;

import java.io.*;

public class CSVWriter {
    BufferedWriter streamBuffer;

    public CSVWriter() {

    }

    public CSVWriter(File file) throws IOException {
        this();
        streamBuffer = new BufferedWriter(new FileWriter(file));
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
