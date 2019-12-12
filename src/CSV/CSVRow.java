package CSV;

import Serialization.CSVReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class CSVRow {
    private LinkedList<String> data;

    public CSVRow() {
        data = new LinkedList<>();
    }

    public CSVRow(List<String> rowData) {
        this();
        setData(rowData);
    }

    public CSVRow(CSVReader stream) throws IOException {
        this(stream.readLine());
    }

    protected boolean isEmpty() {
        return data.isEmpty();
    }

    protected void setData(List<String> rowData) {
        data.addAll(Objects.requireNonNull(rowData));
    }

    protected List<String> getData() {
        return data;
    }

    protected String getCell(int cell) {
        return data.get(cell);
    }

    protected void moveColumn(int col, int newLoc) {
        data.add(newLoc, data.remove(col));
    }

    @Override
    public String toString() {
        if(isEmpty()) {
            return null;
        }

        return getData().toString();
    }
}
