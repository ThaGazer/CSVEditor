package Serialization;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CSVStream {

    private InputStream in;
    private String delim = " ";
    private boolean newLine = false;
    private boolean eof = false;

    public CSVStream(InputStream inputStream) {
        in = Objects.requireNonNull(inputStream);
    }

    public CSVStream(InputStream inputStream, String token) {
        in = Objects.requireNonNull(inputStream);
        setDeliminator(token);
    }

    private void setDeliminator(String token) {
        delim = token;
    }

    public String next() throws IOException {
        String token = "";
        while (deliminatorCheck(token)) {
            int a;
            if((a = read()) == -1) {
                return token;
            }
            token += (char)a;
        }
        return token.substring(0, token.length() - 1);
    }

    public List<String> readLine() throws IOException {
        LinkedList<String> rowData = new LinkedList<>();
        clearNewLine();

        while(!hitNewLine() || !hitEOF()) {
            String tmp;
            if((tmp = next()) == null) {
                setNewLine();
            } else {
                rowData.add(tmp);
            }
        }

        return List.copyOf(rowData);
    }

    public int read() throws IOException {
        int a;
        if ((a = in.read()) == -1) {
            eof = true;
        }
        return a;
    }

    public boolean deliminatorCheck(String token) {
        if (!token.endsWith("\n")) {
            clearNewLine();
            if (!token.endsWith(delim)) {
                return true;
            }
        } else {
            setNewLine();
        }
        return false;
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

    private void clearNewLine() {
        newLine = false;
    }
}
