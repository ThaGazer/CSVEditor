package Serialization;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CSVStream {

    private static final String DEFAULT_DELIMINATOR = ",";

    private InputStream in;
    private String deliminator;
    private boolean newLine = false;
    private boolean eof = false;

    public CSVStream() {
        setDeliminator(DEFAULT_DELIMINATOR);
    }

    public CSVStream(InputStream inputStream) {
        this(inputStream, DEFAULT_DELIMINATOR);
    }

    public CSVStream(InputStream inputStream, String deliminator) {
        this();
        in = Objects.requireNonNull(inputStream);

        setDeliminator(deliminator);
    }

    private void setDeliminator(String newDeliminator) {
        if(!"".equals(newDeliminator)) {
            deliminator = newDeliminator;
        }
    }

    public boolean isNull() {
        return in == null;
    }

    public String next() throws IOException {
        String token = "";
        do {
            token += (char)read();
        } while(!hitEOF() && deliminatorCheck(token));

        return token.substring(0, token.length() - 1);
    }

    public List<String> readLine() throws IOException {
        LinkedList<String> rowData = new LinkedList<>();
        clearNewLine();


        do {
            rowData.add(next());
        } while(!hitNewLine() && !hitEOF());
//
//            String tmp;
//            if("".equals(tmp = next())) {
//                setEOF();
//            } else {
//                rowData.add(tmp);
//            }
//        }

        return List.copyOf(rowData);
    }

    public int read() throws IOException {
        int a;
        if((a = in.read()) == -1) {
            setEOF();
        }
        return a;
    }

    public boolean deliminatorCheck(String token) {
        if(token.endsWith("\r") || token.endsWith("\n")) {
            setNewLine();
            return false;
        } else {
            return !token.endsWith(deliminator);
        }
    }

    public boolean hitNewLine() {
        return newLine;
    }

    public boolean hitEOF() {
        return eof;
    }

    private void setNewLine() {
        newLine = true;
    }

    private void setEOF() {
        eof = true;
    }

    private void clearNewLine() {
        newLine = false;
    }
}
