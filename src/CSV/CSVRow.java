package CSV;

import Serialization.CSVStream;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class CSVRow {
    private String header;
    private LinkedList<String> data;

    public CSVRow() {
        data = new LinkedList<>();
    }

    public CSVRow(String rowTitle) {
        this(rowTitle, new LinkedList<>());
    }

    public CSVRow(String rowTitle, List<String> rowData) {
        this();
        setHeader(rowTitle);
        setData(rowData);
    }

    public CSVRow(CSVStream inputStream) throws IOException {
        setData(inputStream.readLine());
    }

    protected void setHeader(String rowHeader) {
        header = rowHeader;
    }

    protected void setData(List<String> rowData) {
        data.addAll(Objects.requireNonNull(rowData));
    }

    public String getHeader() {
        return header;
    }

    @Override
    public String toString() {
        StringBuilder row = new StringBuilder();
        for(String cell : data) {
            row.append(cell).append("  ");
        }
        row.append("\n");
        return row.toString();
    }
}
