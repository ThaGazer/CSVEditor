package Serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    private static final char DEFAULT_DELIMINATOR = ',';

    private char deliminator;
    private BufferedReader streamBuffer;

    public CSVReader() {
        setDeliminator(DEFAULT_DELIMINATOR);
    }

    public CSVReader(File file) throws FileNotFoundException {
        this(file, DEFAULT_DELIMINATOR);
    }

    public CSVReader(File file, char deliminator) throws FileNotFoundException {
        this();

        streamBuffer = new BufferedReader(new FileReader(file));
        setDeliminator(deliminator);
    }

    private void setDeliminator(char newDeliminator) {
        if(newDeliminator != deliminator) {
            deliminator = newDeliminator;
        }
    }

    public List<String> readLine() throws IOException {
        String line;
        if((line = streamBuffer.readLine()) == null) {
            return null;
        }

        return Arrays.asList(aStringSplitterThatActuallyWorks(line, deliminator));
    }

    private String[] aStringSplitterThatActuallyWorks(String line, char deliminator) {
        if(!line.isEmpty()) {
            ArrayList<String> splitter = new ArrayList<>();
            String tmp = "";
            for(int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == deliminator) {
                    splitter.add(tmp);
                    tmp = "";
                } else {
                    tmp += line.charAt(i);
                }
            }
            splitter.add(tmp);

            return splitter.toArray(new String[0]);
        } else {
            return new String[]{};
        }
    }

    public void close() throws IOException {
        streamBuffer.close();
    }
}
