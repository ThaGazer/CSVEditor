package CSV;

import Serialization.CSVStream;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSVTable {
    private boolean hasHeaders = false;
    private LinkedList<CSVRow> data;

    public static CSVTable parse(CSVStream stream, boolean headers) throws IOException {
        CSVTable tmpTable = new CSVTable();
        tmpTable.setHeaderFlag(headers);

        try {
            while(true) {
                tmpTable.addRow(stream);
            }
        } catch(NullPointerException ignored){}

        return tmpTable;
    }

    public CSVTable() {
        data = new LinkedList<>();
    }

    private void setHeaderFlag(boolean header) {
        hasHeaders = header;
    }

    public List<String> getHeaders() {
        return hasHeaders() ? getRow(0) : null;
    }

    public void addRow(CSVStream stream) throws IOException {
        data.add(new CSVRow(stream));
    }

    public void addRow(List<String> newRow) {
        data.add(new CSVRow(newRow));
    }

    public List<String> getRow(int i) {
        return data.get(i).getData();
    }

    public List<List<String>> getTable() {
        LinkedList<List<String>> ret = new LinkedList<>();
        for(CSVRow row : data) {
            ret.add(row.getData());
        }
        return ret;
    }


    public boolean hasHeaders() {
        return hasHeaders;
    }

    @Override
    public String toString() {
        return getTable().toString();
    }
}
