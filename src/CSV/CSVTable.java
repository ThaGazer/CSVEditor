package CSV;

import Serialization.CSVReader;
import Serialization.CSVWriter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CSVTable {
    private LinkedList<CSVRow> data;
    private boolean hasHeaders;
    private CSVRow headers;

    public static CSVTable parse(CSVReader stream, boolean headers) throws IOException {
        CSVTable tmpTable = new CSVTable();
        tmpTable.setHeaderFlag(headers);

        List<String> lineIn;
        if(tmpTable.hasHeaders()) {
            tmpTable.setHeaders(stream.readLine());
        }

        while((lineIn = stream.readLine()) != null) {
            tmpTable.addRow(lineIn);
        }

        return tmpTable;
    }

    public CSVTable() {
        data = new LinkedList<>();
        headers = new CSVRow();
        hasHeaders = false;
    }

    public void write(CSVWriter out) throws IOException {
        if(hasHeaders()) {
            out.write(String.join(",", getHeaders()));
            out.newLine();
        }

        for(CSVRow row : data) {
            out.write(String.join(",", row.getData()));
            out.newLine();
        }
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public boolean hasHeaders() {
        return hasHeaders;
    }

    private void setHeaderFlag(boolean isHeader) {
        hasHeaders = isHeader;
    }

    private void setHeaders(List<String> newHeaders) {
        headers.setData(newHeaders);
    }

    public List<String> getHeaders() {
        return hasHeaders() ? headers.getData() : null;
    }

    public List<String> getRow(int i) {
        return List.copyOf(data.get(i).getData());
    }

    public List<String> getCol(int i) {
        List<String> column = new LinkedList<>();

        for(CSVRow row : data) {
            column.add(row.getCell(i));
        }
        return List.copyOf(column);
    }

    public List<List<String>> getTable() {
        return data.stream().map(CSVRow::getData).collect(Collectors.toList());
    }

    public void addRow(List<String> newRow) {
        if(hasHeaders() && getHeaders().size() != newRow.size()) {
            throw new IllegalArgumentException("Bad row sizing at " + data.size() +
                    ": " + newRow.size() + " expected " + getHeaders().size());
        }
        data.add(new CSVRow(newRow));
    }

    public void addColumn(int index) {
        addColumn(index, "");
    }

    public void addColumn(int index, String str) {
        addColumn(index, str, "");
    }

    public void addColumn(int index, String str, String header) {
        if(hasHeaders()) {
            headers.addColumn(index, header);
        }

        for(CSVRow row : data) {
            row.addColumn(index, str);
        }
    }

    public void moveColumn(int col, int newLoc) {
        if(hasHeaders()) {
            headers.moveColumn(col, newLoc);
        }

        for(CSVRow row : data) {
            row.moveColumn(col, newLoc);
        }
    }

    public void setColumn(int c, String newStr) {
        for(CSVRow row : data) {
            row.setCell(c, newStr);
        }
    }

    public void setCell(int r, int c, String newStr) {
        data.get(r).setCell(c, newStr);
    }

    public List<Integer> searchCol(int columnNumber, String reference) {
        List<Integer> matchingColumns = new ArrayList<>();

        for(int i = 0; i < data.size(); i++) {
            if(contains(reference, data.get(i).getCell(columnNumber))) {
                matchingColumns.add(i);
            }
        }
        return matchingColumns;
    }

    private boolean contains(String reference, String check) {
        Map<String, Boolean> refCheck = new HashMap<>();
        for(String str : reference.toLowerCase().split(" ")) {
            refCheck.put(str, false);
        }

        List<String> splitCheck = Arrays.asList(check.toLowerCase().split(" "));
        for(String str : refCheck.keySet()) {
            if(containsCloseEnough(splitCheck, str)) {
                refCheck.replace(str, true);
            }
        }

        return !refCheck.containsValue(false);
    }

    private boolean containsCloseEnough(List<String> list, String ref) {
        for(String str : list) {
            if(str.contains(ref.subSequence(0,ref.length()))) {
                return true;
            }
        }
        return false;
    }

    public void printTable() {
        StringBuilder printedTable = new StringBuilder();
        if(hasHeaders()) {
            printedTable.append(getHeaders()).append("\n");
        }

        for(CSVRow row : data) {
            printedTable.append(row).append("\n");
        }
        System.out.println(printedTable);
    }

    @Override
    public String toString() {
        return isEmpty() ? "0, 0" : getTable().size() + ", " + getTable().get(0).size();
    }
}
