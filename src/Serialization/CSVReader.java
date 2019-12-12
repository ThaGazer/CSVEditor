package Serialization;

import java.io.*;
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

    public CSVReader(File file) throws FileNotFoundException {
        this(file, DEFAULT_DELIMINATOR);
    }

    public CSVReader(File file, String deliminator) throws FileNotFoundException {
        this();

        streamBuffer = new BufferedReader(new FileReader(file));
        setDeliminator(deliminator);
    }

    private void setDeliminator(String newDeliminator) {
        if(deliminator == null || !deliminator.equals(newDeliminator)) {
            deliminator = newDeliminator;
        }
    }

    public List<String> readLine() throws IOException {
        String line;
        return (line = streamBuffer.readLine()) == null ? null :
                new LinkedList<>(Arrays.asList(line.split(deliminator)));
    }

    public void close() throws IOException {
        streamBuffer.close();
    }
}
