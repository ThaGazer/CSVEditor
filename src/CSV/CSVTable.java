package CSV;

import Serialization.CSVStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CSVTable {
    private LinkedList<CSVRow> data;

    private boolean hasHeaders = false;

    public static CSVTable parse(CSVStream stream, boolean headers) throws IOException {
        CSVTable tmp = new CSVTable();

        tmp.setHeaderFlag(headers);

        if(tmp.hasHeaders()) {
            tmp.setHeaders(stream.readLine());
        }


        return tmp;
    }

    public CSVTable() {
        data = new LinkedList<>();
    }

    private void setHeaderFlag(boolean header) {
        hasHeaders = header;
    }

    private void setHeaders(List<String> headers) {
        for(int i = 0; i < headers.size(); i++) {

        }
    }

    public List<String> getHeaders() {
        ArrayList<String> headers = new ArrayList<>();
        for(CSVRow row : data) {
            headers.add(row.getHeader());
        }
        return headers;
    }

    public List<CSVRow> getTable() {
        return List.copyOf(data);
    }


    public boolean hasHeaders() {
        return hasHeaders;
    }

    @Override
    public String toString() {
        StringBuilder printedTable = new StringBuilder();

        for(String header : getHeaders()) {
            printedTable.append(header).append("  ");
        }
        printedTable.append("\n");

        for(CSVRow row : data) {
            printedTable.append(row.toString());
        }
        printedTable.append("\n");

        return printedTable.toString();
    }
}
