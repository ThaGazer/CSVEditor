package Serialization;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class CSVStream {

    private InputStream in;
    private String delim = " ";
    private boolean newLine = false;

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
            token += (char)read();
        }
        return token.substring(0, token.length() - 1);
    }

    public int read() throws IOException {
        int a;
        if ((a = in.read()) == -1) {
            throw new EOFException();
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

    private void setNewLine() {
        newLine = true;
    }

    private void clearNewLine() {
        newLine = false;
    }
}
