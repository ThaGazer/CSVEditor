package Serialization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {
    private static final String DEFAULT_DELIMINATOR = ",";

    private String deliminator;
    private BufferedReader streamBuffer;

    public CSVReader() {
        setDeliminator(DEFAULT_DELIMINATOR);
    }

    public CSVReader(InputStream inputStream) {
        this(inputStream, DEFAULT_DELIMINATOR);
    }

    public CSVReader(InputStream inputStream, String deliminator) {
        this();

        streamBuffer = new BufferedReader(new InputStreamReader(inputStream));
        setDeliminator(deliminator);
    }

    private void setDeliminator(String newDeliminator) {
        if(deliminator == null || !deliminator.equals(newDeliminator)) {
            deliminator = newDeliminator;
        }
    }

    public List<String> readLine() throws IOException {
        return new LinkedList<>(Arrays.asList(streamBuffer.readLine().split(deliminator)));
    }

    public void close() throws IOException {
        streamBuffer.close();
    }
}
