package CSV;

import Serialization.CSVReader;
import Serialization.CSVWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CSVTable {
    private LinkedList<CSVRow> data;
    private boolean hasHeaders;
    private int startingIndex;

    public static CSVTable parse(CSVReader stream, boolean headers) throws IOException {
        CSVTable tmpTable = new CSVTable();
        tmpTable.setHeaderFlag(headers);

        List<String> lineIn;
        while((lineIn = stream.readLine()) != null) {
            tmpTable.addRow(lineIn);
        }

        return tmpTable;
    }

    public CSVTable() {
        data = new LinkedList<>();
        hasHeaders = false;
        startingIndex = 0;
    }

    public void write(CSVWriter out) throws IOException {
        for(CSVRow row : data) {
            out.write(String.join(",", row.getData()));
            out.newLine();
        }
    }

    public boolean hasHeaders() {
        return hasHeaders;
    }

    private void setHeaderFlag(boolean header) {
        hasHeaders = header;
        startingIndex = hasHeaders() ? 1 : 0;
    }

    public List<String> getHeaders() {
        return hasHeaders() ? getRow(0) : null;
    }

    public void addRow(List<String> newRow) {
        data.add(new CSVRow(newRow));
    }

    public List<String> getRow(int i) {
        return data.get(i).getData();
    }

    public List<List<String>> getTable() {
        LinkedList<List<String>> ret = new LinkedList<>();
        data.forEach((k) -> {
            ret.add(k.getData());
        });
        return ret;
    }

    public void moveColumn(int col, int newLoc) {
        for(CSVRow row : data) {
            row.moveColumn(col, newLoc);
        }
    }

    public List<Integer> searchCol(int columnNumber, String reference) {
        List<Integer> matchingColumns = new ArrayList<>();

        for(int i = startingIndex; i < data.size(); i++) {
            if(contains(reference, data.get(i).getCell(columnNumber))) {
                matchingColumns.add(i);
            }
        }
        return matchingColumns;
    }

    private boolean contains(String reference, String check) {
        for(String refStr : reference.toLowerCase().split(" ")) {
            for(String checkStr : check.toLowerCase().split(" ")) {
                if(checkStr.contains(refStr)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printTable() {
        StringBuilder printedTable = new StringBuilder();
        for(CSVRow row : data) {
            printedTable.append(row).append("\n");
        }
        System.out.println(printedTable);
    }

    @Override
    public String toString() {
        return getTable().toString();
    }
}
