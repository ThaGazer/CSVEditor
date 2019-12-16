package CSV;

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

    protected boolean isEmpty() {
        return data.isEmpty();
    }

    protected void setData(List<String> rowData) {
        data.addAll(Objects.requireNonNull(rowData));
    }

    protected List<String> getData() {
        return List.copyOf(data);
    }

    protected String getCell(int cell) {
        return data.get(cell);
    }

    protected void setCell(int column, String newStr) {
        data.set(column, newStr);
    }

    protected void addColumn(int index) {
        addColumn(index, "");
    }

    protected void addColumn(int index, String str) {
        data.add(index, str);
    }

    protected void moveColumn(int col, int newLoc) {
        data.add(newLoc, data.remove(col));
    }

    @Override
    public String toString() {
        return isEmpty() ? null : String.valueOf(getData().size());
    }
}
